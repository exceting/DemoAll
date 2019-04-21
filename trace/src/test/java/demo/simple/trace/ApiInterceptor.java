package demo.simple.trace;

import com.google.common.base.Strings;
import demo.simple.context.Context;
import demo.simple.context.ContextHolder;
import demo.simple.trace.core.SimpleSpan;
import demo.simple.trace.core.SimpleTracer;
import demo.simple.trace.utils.SpanTags;
import io.opentracing.References;

import java.util.HashMap;

/**
 * Http拦截器，API执行实际逻辑前后的逻辑切入
 */
public class ApiInterceptor {

    //开始Http处理请求之前要做的，一般这里产生上下文，并交给TL传递上下文对象，这里也是链路初始化的地方
    public void beforeHandle(Request request) {
        Context context = new Context(); //上下文对象
        SimpleTracer tracer = null;
        SimpleSpan parent = null;

        //这里是为跨系统调用做的协议头传递，因为我们这个API也可能是公司内别的业务方内部调用，那么这个时候就需要约定协议头，一旦协议头中带有约定好的链路字段，那么就认为我们这个API本次调用相对于别的系统是个子链路
        String traceId = request.headers.get("x1-trace-id"); //拿到协议头的父链路id，子链路继承之
        String parentId = request.headers.get("x1-span-id"); //拿到协议头的父span信息
        String sampled = request.headers.get.get("x1-sampled"); //是否上报

        if (traceId != null && parentId != null && sampled == true) {
            tracer = new SimpleTracer(request.getUri, "所属项目名"); //这里用url当成是初始化span的title
            // 符合这种情况的，我们这里的parent其实只是一个相对于别的系统的child
            SimpleSpan.SimpleSpanContext simpleSpanContext = new SimpleSpan.SimpleSpanContext(traceId, parentId, new HashMap<>());
            parent = tracer.buildSpan(request.getUri)
                    .addReference(References.CHILD_OF, simpleSpanContext) //建立父子关系，如果是别的业务方调用我们这个http服务，那么这里这一步，也就建立了跟调用方的父子关系，traceId等是继承的调用方的，意味着本次调用也属于调用方的一环，这也就实现了跨系统的链路追踪
                    .withTag(SpanTags.COMPONENT, "http")
                    .withTag(SpanTags.SPAN_KIND, "server").start(); //启动span
        } else { //执行else，说明该http调用是一次自己完整的调用，不属于任何父链路，那么就无需建立关系，直接初始化tracer即可
            tracer = new SimpleTracer(request.getUri, "所属项目名");
            parent = tracer.buildSpan(request.getUri)
                    .withTag(SpanTags.COMPONENT, "http")
                    .withTag(SpanTags.SPAN_KIND, "server")
                    .start(); //启动span
        }

        //将封装好的tracer和parentSpan设置到上下文对象里去
        context.setSimpleTracer(tracer);
        context.setParent(parent);

        ContextHolder.setContext(context); //将本次请求生成的上下文对象放进ContextHolder（也就是TL里），方便在任意位置取出使用
    }

    //业务逻辑处理中
    public void hadle() {
        //本次API请求实际走的业务逻辑，也就是A服务调用、B服务调用等这些实际的业务逻辑处理
        doing();
    }

    //Http业务处理完成后的触发
    public void afterHandler() {
        //Http调用结束的时候，取出当前链路信息，完成数据的上报
        SimpleTracer tracer = ContextHolder.getContext().getTracer();
        SimpleSpan parent = ContextHolder.getContext().getParent();
        if (tracer != null && parent != null) {
            parent.finish(); //结束掉parent Span
            tracer.pushSpans(); //上报这次产生的链路数据（spans）
        }
    }

}

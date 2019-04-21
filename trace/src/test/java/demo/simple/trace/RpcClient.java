package demo.simple.trace;

import demo.simple.context.ContextHolder;
import demo.simple.trace.core.SimpleSpan;
import demo.simple.trace.core.SimpleTracer;
import demo.simple.trace.utils.SpanTags;

/**
 * Create by 18073 on 2019/4/21.
 */
public class RpcClient {

    //等到服务端响应方法
    public void requestRpc(RpcRequest request) {
        //调用前执行
        SimpleSpan span = null;
        SimpleSpan parent = ContextHolder.getContext().getParent();
        SimpleTracer tracer = ContextHolder.getContext().getTracer();
        if (tracer != null && parent != null) {//↓这个title就设置成rpc调用的那个方法名即可
            span = tracer.buildSpan(request.getRpcMethod).asChildOf(parent) //建立父子关系，因为rpc client调用属于API调用的子链路
                    .withTag(SpanTags.COMPONENT, "grpc")
                    .withTag(SpanTags.PEER_SERVICE, method)
                    .withTag(SpanTags.SPAN_KIND, "client")
                    .start(); //启动这个span

            //设置协议头，因为被调用的RPC服务相对于我们来说是个子链路
            request.setHeader("x1-rpc-span-id", span.context().spanId());
            request.setHeader("x1-rpc-trace-id", span.context().traceId());
            request.setHeader("x1-rpc-sampled", span.sampled());
        }

        rpcServerRequest(request); //实际调用rpc服务

        //调用后执行
        if(span != null){
            span.finish(); //完成本次追踪
        }
    }
}

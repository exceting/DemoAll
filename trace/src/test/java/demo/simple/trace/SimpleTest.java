package demo.simple.trace;

import demo.simple.trace.core.SimpleSpan;
import demo.simple.trace.core.SimpleTracer;
import demo.simple.trace.utils.SpanTags;
import io.opentracing.References;
import org.junit.Test;

import java.util.HashMap;

/**
 * Create by 18073 on 2019/4/21.
 */
public class SimpleTest {

    private SimpleTracer tracer = null;

    private SimpleSpan parent = null;

    //假设这里是链路开始的地方
    @Test
    public void test1() {

        //创建链路
        tracer = new SimpleTracer("test1", "projectName");
        parent = tracer.buildSpan("test1")
                .withTag(SpanTags.COMPONENT, "http")
                .withTag(SpanTags.SPAN_KIND, "server")
                .start(); //span开始

        //--------------------------------------------------
        String result1 = getResult1(); //假设getResult1需要链路追踪
        System.out.println("r1 = " + result1);

        String result2 = getResult2(); //假设getResult2需要链路追踪
        System.out.println("r2 = " + result2);
        //--------------------------------------------------

        //下面标记着一次链路追踪的结束
        parent.finish(); //主span结束
        tracer.pushSpans(); //触发span数据推送
    }

    public String getResult1() {

        //前戏，建立getResult1自己的追踪span
        SimpleSpan currentSpan = null;
        if (tracer != null && parent != null) {
            //当前链路视为test1方法的子链路，建立父子关系
            SimpleSpan.SimpleSpanContext context = new SimpleSpan.SimpleSpanContext(parent.context().traceId(),
                    parent.context().spanId(), new HashMap<>()); //建立父子关系，traceId和父spanId被指定
            currentSpan = tracer.buildSpan("getResult1")
                    .addReference(References.CHILD_OF, context)
                    .withTag(SpanTags.COMPONENT, "redis")
                    .withTag(SpanTags.SPAN_KIND, "client").start(); //启动自己的追踪span
        }

        try {
            Thread.sleep(1000L);
            return "result1";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "";
        } finally {
            if (currentSpan != null) {
                currentSpan.finish(); //最后完成本次链路追踪
            }
        }
    }

    public String getResult2() {
        //前戏，建立getResult1自己的追踪span
        SimpleSpan currentSpan = null;
        if (tracer != null && parent != null) {
            //当前链路视为test1方法的子链路，建立父子关系
            SimpleSpan.SimpleSpanContext context = new SimpleSpan.SimpleSpanContext(parent.context().traceId(),
                    parent.context().spanId(), new HashMap<>()); //建立父子关系，traceId和父spanId被指定
            currentSpan = tracer.buildSpan("getResult2")
                    .addReference(References.CHILD_OF, context)
                    .withTag(SpanTags.COMPONENT, "redis")
                    .withTag(SpanTags.SPAN_KIND, "client").start(); //启动自己的追踪span
        }

        try {
            Thread.sleep(2000L);
            return "result2";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return "";
        } finally {
            if (currentSpan != null) {
                currentSpan.finish(); //最后完成本次链路追踪
            }
        }
    }

}

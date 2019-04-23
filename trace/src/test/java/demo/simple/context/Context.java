package demo.simple.context;

import demo.simple.trace.core.SimpleSpan;
import demo.simple.trace.core.SimpleTracer;

/**
 * Create by 18073 on 2019/4/18.
 * 一次请求的上下文对象，主要用来传递Trace对象，实际项目中可能带有更多的信息
 */
public class Context {

    private SimpleTracer simpleTracer; //当前链路对象

    private SimpleSpan parent; //当前链路全局父span

    //也可以放很多别的上下文内容，这里省略...

    public SimpleTracer getSimpleTracer() {
        return simpleTracer;
    }

    public void setSimpleTracer(SimpleTracer simpleTracer) {
        this.simpleTracer = simpleTracer;
    }

    public SimpleSpan getParent() {
        return parent;
    }

    public void setParent(SimpleSpan parent) {
        this.parent = parent;
    }
}

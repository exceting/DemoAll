/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */

package demo.simple.trace.core;

import demo.simple.trace.data.PushHandler;
import demo.simple.trace.utils.PushUtils;
import io.opentracing.*;
import io.opentracing.propagation.Format;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 追踪器，实现Tracer接口
public class SimpleTracer implements Tracer {

    private final List<SimpleSpan> finishedSpans = new ArrayList<>(); //存放链路中已执行完成的span（finished span）
    private String project; //项目名称
    private Boolean sampled; //是否上报（由采样率算法生成该值）


    public SimpleTracer(boolean sampled, String project) {
        this.project = project;
        this.sampled = sampled;
    }

    public SimpleTracer(String uri, String project) {
        this.project = project;
        this.sampled = PushUtils.sampled(uri); //本次追踪是否上报
    }

    public boolean isSampled() {
        return sampled;
    }

    public synchronized void reset() {
        this.finishedSpans.clear();
    }

    public synchronized List<SimpleSpan> finishedSpans() {
        return new ArrayList<>(this.finishedSpans);
    }

    public String getProject() {
        return project;
    }

    @Override
    public ScopeManager scopeManager() {
        return null;
    }

    @Override
    public SpanBuilder buildSpan(String operationName) {
        return new SpanBuilder(operationName);
    }

    @Override
    public <C> void inject(SpanContext spanContext, Format<C> format, C carrier) {

    }

    @Override
    public <C> SpanContext extract(Format<C> format, C carrier) {
        return null;
    }

    @Override
    public Span activeSpan() {
        return null;
    }

    /**
     * 目前tracer我们建议非单例的，所以这个同步锁没什么用
     */
    synchronized void appendFinishedSpan(SimpleSpan biliSpan) {
        this.finishedSpans.add(biliSpan);
    }

    //上报span，这个方法一般在一次链路完成时调用，负责将finishedSpans里的数据上报给追踪系统
    public synchronized void pushSpans() {
        if (sampled != null && sampled) {
            List<SimpleSpan> finished = this.finishedSpans;
            if (finished.size() > 0) {
                finished.stream().filter(SimpleSpan::sampled).forEach(span -> PushHandler.getHandler().pushSpan(span));
                this.reset(); //每发生一次推送，则清理一次已完成span集合
            }
        }
    }

    // Tracer对象内部类SpanBuilder，实现了标准里的Tracer.SpanBuilder接口，用来负责创建span
    public final class SpanBuilder implements Tracer.SpanBuilder {
        private final String title; //操作名，也就是span的title
        private long startMicros; //初始化开始时间
        private List<SimpleSpan.Reference> references = new ArrayList<>(); //父子关系
        private Map<String, Object> initialTags = new HashMap<>(); //tag描述信息初始化

        //创建span用的title传入
        SpanBuilder(String title) {
            this.title = title;
        }

        @Override
        public SpanBuilder asChildOf(SpanContext parent) {
            return addReference(References.CHILD_OF, parent);
        }

        @Override
        public SpanBuilder asChildOf(Span parent) {
            if (parent == null) {
                return this;
            }
            return addReference(References.CHILD_OF, parent.context());
        }

        @Override
        public SpanBuilder ignoreActiveSpan() {
            return this;
        }

        @Override
        public SpanBuilder addReference(String referenceType, SpanContext referencedContext) {
            if (referencedContext != null) {
                //添加父子关系，其实这里就是初始化了Span里的Reference对象，这个对象会在创建Span对象时作为参数传进去，然后具体关系的确立，是在Span对象内（具体Span类的代码段会展示）
                this.references.add(new SimpleSpan.Reference((SimpleSpan.SimpleSpanContext) referencedContext, referenceType));
            }
            return this;
        }

        @Override
        public SpanBuilder withTag(String key, String value) {
            this.initialTags.put(key, value);
            return this;
        }

        @Override
        public SpanBuilder withTag(String key, boolean value) {
            this.initialTags.put(key, value);
            return this;
        }

        @Override
        public SpanBuilder withTag(String key, Number value) {
            this.initialTags.put(key, value);
            return this;
        }

        @Override
        public SpanBuilder withStartTimestamp(long microseconds) {
            this.startMicros = microseconds;
            return this;
        }

        @Override
        public Scope startActive(boolean finishOnClose) {
            return null;
        }

        @Override
        public SimpleSpan start() {
            return startManual();
        }

        @Override
        public SimpleSpan startManual() { //创建并开始一个span
            if (this.startMicros == 0) {
                this.startMicros = SimpleSpan.nowMicros(); //就是在这里初始化startTime的
            }
            //这里触发SimpleSpan的构造方法
            return new SimpleSpan(SimpleTracer.this, title, startMicros, initialTags, references);
        }
    }

}

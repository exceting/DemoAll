/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */

package demo.simple.trace.core;

import demo.simple.trace.data.PushUtils;
import io.opentracing.*;
import io.opentracing.propagation.Format;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunqinwen
 * @version \: BiliTracer.java,v 0.1 2018-09-12 14:08
 * 追踪器
 */
public class BiliTracer implements Tracer {

    private final List<SimpleSpan> finishedSpans = new ArrayList<>();
    private String project;
    private boolean isDebug = false;
    private Boolean sampled = null;


    public BiliTracer(boolean sampled, String project, boolean isDebug) {
        this.project = project;
        this.sampled = sampled;
        this.isDebug = isDebug;
    }

    public BiliTracer(String uri, String project, boolean isDebug) {
        this();
        this.project = project;
        if (isDebug) {
            this.sampled = true;
        } else {
            this.sampled = PushUtils.sampled(uri);
        }
        this.isDebug = isDebug;
    }

    @Nullable
    public Boolean isSampled() {
        return sampled;
    }

    public synchronized void reset() {
        this.finishedSpans.clear();
    }

    public synchronized List<SimpleSpan> finishedSpans() {
        return new ArrayList<>(this.finishedSpans);
    }

    protected void onSpanFinished(SimpleSpan biliSpan) {
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
        return this.propagator.extract(format, carrier);
    }

    @Override
    public Span activeSpan() {
        return null;
    }

    /**
     * 目前tracer我们建议非单例的，所以这个同步锁没什么卵用
     */
    synchronized void appendFinishedSpan(SimpleSpan biliSpan) {
        this.finishedSpans.add(biliSpan);
        this.onSpanFinished(biliSpan);
    }

    public synchronized void pushSpans() {
        if (sampled != null && sampled) {
            List<SimpleSpan> finished = this.finishedSpans;
            if (finished.size() > 0) {
                finished.stream().filter(SimpleSpan::sampled).forEach(f -> {

                    if (pushSpan != null) {
                        if (isDebug) {
                            PushUdpHandler.getHandler().pushSpan(pushSpan.toByteArray());
                        } else {
                            PushUnixSockHandler.getHandler().pushSpan(pushSpan.toByteArray());
                        }
                    }
                });
                this.reset();
            }
        }
    }

    private SpanContext activeSpanContext() {
        Span span = activeSpan();
        if (span == null) {
            return null;
        }

        return span.context();
    }

    public final class SpanBuilder implements Tracer.SpanBuilder {
        private final String title;
        private long startMicros;
        private List<SimpleSpan.Reference> references = new ArrayList<>();
        private boolean ignoringActiveSpan;
        private Map<String, Object> initialTags = new HashMap<>();

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
            ignoringActiveSpan = true;
            return this;
        }

        @Override
        public SpanBuilder addReference(String referenceType, SpanContext referencedContext) {
            if (referencedContext != null) {
                this.references.add(new SimpleSpan.Reference((SimpleSpan.BiliSpanContext) referencedContext, referenceType));
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
            return BiliTracer.this.scopeManager().activate(this.startManual(), finishOnClose);
        }

        @Override
        public SimpleSpan start() {
            return startManual();
        }

        @Override
        public SimpleSpan startManual() {
            if (this.startMicros == 0) {
                this.startMicros = SimpleSpan.nowMicros();
            }
            SpanContext activeSpanContext = activeSpanContext();
            if (references.isEmpty() && !ignoringActiveSpan && activeSpanContext != null) {
                references.add(new SimpleSpan.Reference((SimpleSpan.BiliSpanContext) activeSpanContext, References.CHILD_OF));
            }
            return new SimpleSpan(BiliTracer.this, title, startMicros, initialTags, references);
        }
    }

}

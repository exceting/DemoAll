/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */

package demo.simple.trace.core;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import io.opentracing.References;
import io.opentracing.Span;
import io.opentracing.SpanContext;

import javax.annotation.Nullable;
import java.util.*;

public class SimpleSpan implements Span {

    private final SimpleTracer biliTracer;
    private final long parentId; // 父span改值为0
    private final long startTime; // 计时开始开始时间戳
    private final Map<String, Object> tags;
    private final List<LogEntry> logEntries = new ArrayList<>();
    private final List<Reference> references; // 关系，外部传入
    private final List<RuntimeException> errors = new ArrayList<>();
    private BiliSpanContext context; // spanContext,内部包含追踪id、span自身id
    private boolean finished; // 当前span是否结束标识
    private long endTime; // 计时结束时间戳
    private boolean sampled; // 是否为抽样数据，取决于父节点，依次嫡传下来给其子节点
    private String project; // 追踪目标的项目名
    private String title;

    SimpleSpan(SimpleTracer tracer, String title, long startTime, Map<String, Object> initialTags, List<Reference> refs) {
        this.biliTracer = tracer;// 这里传入的tracer是针对本次跟踪过程唯一对象，负责收集已完成的span
        this.title = title;
        this.startTime = startTime;
        this.project = tracer.getProject();
        this.sampled = tracer.isSampled();
        if (initialTags == null) {
            this.tags = new HashMap<>();
        } else {
            this.tags = new HashMap<>(initialTags);
        }
        if (refs == null) {
            this.references = Collections.emptyList();
        } else {
            this.references = new ArrayList<>(refs);
        }
        BiliSpanContext parent = findPreferredParentRef(this.references);
        if (parent == null) {
            // 父节点
            this.context = new BiliSpanContext(nextId(), nextId(), new HashMap<>());
            this.parentId = 0;
        } else {
            // 子节点
            this.context = new BiliSpanContext(parent.traceId, nextId(), mergeBaggages(this.references));
            this.parentId = parent.spanId;
        }
    }

    @Nullable
    private static BiliSpanContext findPreferredParentRef(List<Reference> references) {
        if (references.isEmpty()) {
            return null;
        }
        for (Reference reference : references) {
            if (References.CHILD_OF.equals(reference.getReferenceType())) {
                return reference.getContext();
            }
        }
        return references.get(0).getContext();
    }

    private static Map<String, String> mergeBaggages(List<Reference> references) {
        Map<String, String> baggage = new HashMap<>();
        for (Reference ref : references) {
            if (ref.getContext().baggage != null) {
                baggage.putAll(ref.getContext().baggage);
            }
        }
        return baggage;
    }

    //生成指定ID
    static long nextId() {
        return Math.abs(Hashing.farmHashFingerprint64()
                .hashString(UUID.randomUUID().toString(), Charsets.UTF_8)
                .asLong());
    }

    //监控系统一般单位为纳秒
    static long nowMicros() {
        return System.currentTimeMillis() * 1000 * 1000;
    }

    public String title() {
        return this.title;
    }

    @Override
    public SimpleSpan setOperationName(String title) {
        finishedCheck("title = [%s] 的span已finish过一次", title);
        this.title = title;
        return this;
    }

    public long parentId() {
        return parentId;
    }

    public long startTime() {
        return startTime;
    }

    public long endTime() {
        assert endTime > 0 : "must call finish() before endTime()";
        return endTime;
    }

    public Map<String, Object> tags() {
        return new HashMap<>(this.tags);
    }

    public List<LogEntry> logEntries() {
        return new ArrayList<>(this.logEntries);
    }

    public List<RuntimeException> generatedErrors() {
        return new ArrayList<>(errors);
    }

    public List<Reference> references() {
        return new ArrayList<>(references);
    }

    @Override
    public synchronized BiliSpanContext context() {
        return this.context;
    }

    @Override
    public void finish() {
        this.finish(nowMicros());
    }

    @Override
    public synchronized void finish(long endTime) {
        finishedCheck("Finishing already finished span");
        this.endTime = endTime;
        this.biliTracer.appendFinishedSpan(this);
        this.finished = true;
    }

    @Override
    public SimpleSpan setTag(String key, String value) {
        return setObjectTag(key, value);
    }

    @Override
    public SimpleSpan setTag(String key, boolean value) {
        return setObjectTag(key, value);
    }

    @Override
    public SimpleSpan setTag(String key, Number value) {
        return setObjectTag(key, value);
    }

    private synchronized SimpleSpan setObjectTag(String key, Object value) {
        finishedCheck("Adding tag {%s:%s} to already finished span", key, value);
        tags.put(key, value);
        return this;
    }

    @Override
    public final Span log(Map<String, ?> fields) {
        return log(nowMicros(), fields);
    }

    @Override
    public final synchronized SimpleSpan log(long timestampMicros, Map<String, ?> fields) {
        finishedCheck("Adding logs %s at %d to already finished span", fields, timestampMicros);
        this.logEntries.add(new LogEntry(timestampMicros, fields));
        return this;
    }

    @Override
    public SimpleSpan log(String event) {
        return this.log(nowMicros(), event);
    }

    @Override
    public SimpleSpan log(long timestampMicroseconds, String event) {
        return this.log(timestampMicroseconds, Collections.singletonMap("event", event));
    }

    @Override
    public synchronized Span setBaggageItem(String key, String value) {
        finishedCheck("Adding baggage {%s:%s} to already finished span", key, value);
        this.context = this.context.withBaggageItem(key, value);
        return this;
    }

    @Override
    @Nullable
    public synchronized String getBaggageItem(String key) {
        return this.context.getBaggageItem(key);
    }

    public boolean sampled() {
        return sampled;
    }

    public String project() {
        return project;
    }

    public SimpleSpan setProject(String project) {
        this.project = project;
        return this;
    }

    private synchronized void finishedCheck(String format, Object... args) {
        if (finished) {
            RuntimeException ex = new IllegalStateException(String.format(format, args));
            errors.add(ex);
            throw ex;
        }
    }

    @Override
    public String toString() {
        return "{" + "traceId:" + context.traceId()
                + ", spanId:" + context.spanId()
                + ", parentId:" + parentId
                + ", title:\"" + title + "\"}";
    }

    public static final class BiliSpanContext implements SpanContext {
        private final long traceId;
        private final Map<String, String> baggage;
        private final long spanId;

        public BiliSpanContext(long traceId, long spanId, Map<String, String> baggage) {
            this.baggage = baggage;
            this.traceId = traceId;
            this.spanId = spanId;
        }

        @Nullable
        public String getBaggageItem(String key) {
            return this.baggage.get(key);
        }

        public long traceId() {
            return traceId;
        }

        public long spanId() {
            return spanId;
        }

        public BiliSpanContext withBaggageItem(String key, String val) {
            Map<String, String> newBaggage = new HashMap<>(this.baggage);
            newBaggage.put(key, val);
            return new BiliSpanContext(this.traceId, this.spanId, newBaggage);
        }

        @Override
        public Iterable<Map.Entry<String, String>> baggageItems() {
            return baggage.entrySet();
        }
    }

    public static final class LogEntry {
        private final long timestampMicros;
        private final Map<String, ?> fields;

        LogEntry(long timestampMicros, Map<String, ?> fields) {
            this.timestampMicros = timestampMicros;
            this.fields = fields;
        }
    }

    public static final class Reference {
        private final BiliSpanContext context;
        private final String referenceType;

        public Reference(BiliSpanContext context, String referenceType) {
            this.context = context;
            this.referenceType = referenceType;
        }

        public BiliSpanContext getContext() {
            return context;
        }

        public String getReferenceType() {
            return referenceType;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Reference reference = (Reference) o;
            return Objects.equals(context, reference.context)
                    && Objects.equals(referenceType, reference.referenceType);
        }

        @Override
        public int hashCode() {
            return Objects.hash(context, referenceType);
        }
    }
}

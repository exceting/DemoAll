/**
 * sharemer.com Inc.
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

// 链路Span，实现标准里的Span接口
public class SimpleSpan implements Span {

    private final SimpleTracer biliTracer; //链路追踪对象（一次追踪建议生成一个链路对象，尽量不要用单例，会有同步锁影响并发效率）
    private final long parentId; // 父span该值为0
    private final long startTime; // 计时开始开始时间戳
    private final Map<String, Object> tags; //一些扩展信息
    private final List<Reference> references; // 关系，外部传入
    private final List<RuntimeException> errors = new ArrayList<>();
    private SimpleSpanContext context; // spanContext,内部包含traceId、span自身id
    private boolean finished; // 当前span是否结束标识
    private long endTime; // 计时结束时间戳
    private boolean sampled; // 是否为抽样数据，取决于父节点，依次嫡传下来给其子节点
    private String project; // 追踪目标的项目名
    private String title; //方法名

    SimpleSpan(SimpleTracer tracer, String title, long startTime, Map<String, Object> initialTags, List<Reference> refs) {
        this.biliTracer = tracer; // 这里传入的tracer是针对本次跟踪过程唯一对象，负责收集已完成的span
        this.title = title;
        this.startTime = startTime;
        this.project = tracer.getProject();
        this.sampled = tracer.isSampled(); //是否上报，该字段根据具体的采样率方法生成
        if (initialTags == null) {
            this.tags = new HashMap<>();
        } else {
            this.tags = new HashMap<>(initialTags);
        }
        if (refs == null) { //span对象由tracer对象创建，创建时会把父子关系传入
            this.references = Collections.emptyList();
        } else {
            this.references = new ArrayList<>(refs);
        }
        SimpleSpanContext parent = findPreferredParentRef(this.references); //查看是否存在父span
        if (parent == null) {  //通常父span为空的情况，都是链路开始的地方，这里会生成traceId
            // 当前链路还不存在父span，则本次span就置为父span，下面会生成traceId和当前父span的spanId
            this.context = new SimpleSpanContext(nextId(), nextId(), new HashMap<>());
            this.parentId = 0; //父span的parentId是0
        } else {
            // 当前链路已经存在父span了，那么子span的parentId置为当前父span的id，表示当前span是属于这个父span的子span，同时traceId也延用父span的（表示属于同一链路）
            this.context = new SimpleSpanContext(parent.traceId, nextId(), mergeBaggages(this.references));
            this.parentId = parent.spanId;
        }
    }

    @Nullable
    private static SimpleSpanContext findPreferredParentRef(List<Reference> references) {
        if (references.isEmpty()) {
            return null;
        }
        for (Reference reference : references) {
            if (References.CHILD_OF.equals(reference.getReferenceType())) { //现有的reference中存在父子关系（简单理解，这个关系就是BuildSpan的时候传入的）
                return reference.getContext(); //返回父span的context信息（包含traceId和它的spanId）
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

    public List<Reference> references() {
        return new ArrayList<>(references);
    }

    @Override
    public synchronized SimpleSpanContext context() {
        return this.context;
    }

    @Override
    public void finish() {
        this.finish(nowMicros());
    }

    @Override
    public synchronized void finish(long endTime) {
        finishedCheck("当前span处于完成态");
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
        finishedCheck("添加新tag {%s:%s} 失败，当前span处于完成态", key, value);
        tags.put(key, value);
        return this;
    }

    @Override
    public final Span log(Map<String, ?> fields) {
        return log(nowMicros(), fields);
    }

    @Override
    public final synchronized SimpleSpan log(long timestampMicros, Map<String, ?> fields) {
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
        finishedCheck("添加新baggage {%s:%s} 失败，当前span处于完成态", key, value);
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

    // SpanContext 
    public static final class SimpleSpanContext implements SpanContext {
        private final long traceId;
        private final Map<String, String> baggage;
        private final long spanId;

        public SimpleSpanContext(long traceId, long spanId, Map<String, String> baggage) {
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

        public SimpleSpanContext withBaggageItem(String key, String val) {
            Map<String, String> newBaggage = new HashMap<>(this.baggage);
            newBaggage.put(key, val);
            return new SimpleSpanContext(this.traceId, this.spanId, newBaggage);
        }

        @Override
        public Iterable<Map.Entry<String, String>> baggageItems() {
            return baggage.entrySet();
        }
    }

    public static final class Reference {
        private final SimpleSpanContext context;
        private final String referenceType;

        public Reference(SimpleSpanContext context, String referenceType) {
            this.context = context;
            this.referenceType = referenceType;
        }

        public SimpleSpanContext getContext() {
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

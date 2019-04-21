/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2018 All Rights Reserved.
 */

package demo.simple.trace.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import demo.simple.trace.core.SimpleSpan;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class PushHandler {

    private static final PushHandler handler = new PushHandler();

    private BlockingQueue<SimpleSpan> queue;

    private PushHandler() {
        this.queue = new LinkedBlockingQueue<>(); //数据管道
        new Thread(this::pushTask).start();
    }

    public static PushHandler getHandler() {
        return handler;
    }

    public void pushSpan(SimpleSpan span) {
        queue.offer(span);
    }

    private void pushTask() {
        if (queue != null) {
            SimpleSpan span;
            while (true) {
                try {
                    span = queue.take();
                    //为了测试，这里只打印了基本信息，实际环境中这里需要做数据推送（kafka、UnixSocket等）
                    StringBuilder sb = new StringBuilder()
                            .append("tracerId=")
                            .append(span.context().traceId())
                            .append(", parentId=")
                            .append(span.parentId())
                            .append(", spanId=")
                            .append(span.context().spanId())
                            .append(", title=")
                            .append(span.title())
                            .append(", 耗时=")
                            .append((span.endTime() / 1000000) + " - " + (span.startTime() / 1000000) + " = " + ((span.endTime() / 1000000) - (span.startTime() / 1000000)))
                            .append("ms, tags=")
                            .append(span.tags().toString());

                    System.out.println(sb.toString());
                } catch (InterruptedException e) {
                    System.err.println("err!! " + e.getMessage());
                }
            }
        }
    }
}

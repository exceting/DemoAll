/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.reactor.cobar.handlers;

import demo.jdk.reactor.cobar.net.NIOHandler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author sunqinwen
 * @version \: BackendAsyncHandler.java,v 0.1 2019-04-04 11:41
 */
public abstract class BackendAsyncHandler implements NIOHandler {

    protected final BlockingQueue<byte[]> dataQueue = new LinkedBlockingQueue<>();
    protected final AtomicBoolean isHandling = new AtomicBoolean(false);

    protected void offerData(byte[] data, Executor executor) {
        if (dataQueue.offer(data)) {
            handleQueue(executor);
        } else {
            offerDataError();
        }
    }

    protected abstract void offerDataError();

    protected abstract void handleData(byte[] data);

    protected abstract void handleDataError(Throwable t);

    protected void handleQueue(final Executor executor) {
        if (isHandling.compareAndSet(false, true)) {
            // 异步处理后端数据
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        byte[] data = null;
                        while ((data = dataQueue.poll()) != null) {
                            handleData(data);
                        }
                    } catch (Throwable t) {
                        handleDataError(t);
                    } finally {
                        isHandling.set(false);
                        if (dataQueue.size() > 0) {
                            handleQueue(executor);
                        }
                    }
                }
            });
        }
    }
}

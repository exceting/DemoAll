/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.concurrent.lock;

import demo.concurrent.lock.queue.SimpleQueue;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleQueueTest {

    private static ExecutorService executorService = Executors.newFixedThreadPool(6);

    private static SimpleQueue<Integer> simpleQueue = new SimpleQueue<>(5);

    @Test
    public void simpleQueueTest() throws Exception {

        executorService.execute(() -> {
            simpleQueue.put(1);
            simpleQueue.put(2);
            simpleQueue.put(3);
            simpleQueue.put(4);
            simpleQueue.put(5);
            simpleQueue.put(6);

            simpleQueue.put(7);
            simpleQueue.put(8);
            simpleQueue.put(9);
            simpleQueue.put(10);
            simpleQueue.put(11);
            simpleQueue.put(12);
        });

        Thread.sleep(5000L);

        executorService.execute(() -> {

            Integer r;
            while ((r = simpleQueue.take()) != null) {
                System.out.println(r);
            }

            System.out.println("---------------");
        });

        Thread.sleep(5000L);
    }

}

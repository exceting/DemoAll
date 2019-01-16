/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.concurrent.lock;

import demo.concurrent.lock.queue.SimpleQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sunqinwen
 * @version \: ReentrantLockTest.java,v 0.1 2019-01-16 11:46
 * 利用重入锁实现一个具有最大接收能力和最小接收能力的队列
 */
public class ReentrantLockTest {

    static ExecutorService executorService = Executors.newFixedThreadPool(6);

    static SimpleQueue<Integer> simpleQueue = new SimpleQueue<>(5);


    public static void main(String[] args) throws Exception {

        executorService.execute(()->{
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

        executorService.execute(()->{

            Integer r;
            while ((r = simpleQueue.take()) != null){
                System.out.println(r);
            }
        });
    }

}

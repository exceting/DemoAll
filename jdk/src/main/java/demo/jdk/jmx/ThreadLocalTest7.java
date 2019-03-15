/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.jmx;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author sunqinwen
 * @version \: ThreadLocalTest7.java,v 0.1 2019-02-26 9:05 
 *
 */
public class ThreadLocalTest7 {

    private static ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(2));

    private static ThreadLocal<Integer> tl = new TransmittableThreadLocal<>();
    private static ThreadLocal<Integer> tl2 = new TransmittableThreadLocal<>();
    private static ThreadLocal<Integer> tl3 = new TransmittableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        tl.set(1);
        tl2.set(2);
        executorService.execute(() -> {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("async-1: %s: tl1=%s, tl2=%s, tl3=%s", Thread.currentThread().getName(), tl.get(), tl2.get(), tl3.get()));
        });
        tl3.set(7);
        tl.set(2); // 等上面的线程池第一次启用完了，父线程再给自己赋值
        System.out.println(String.format("%s: tl1=%s, tl2=%s, tl3=%s", Thread.currentThread().getName(), tl.get(), tl2.get(), tl3.get()));

        /*executorService.execute(()->{
            System.out.println(String.format("async-2: %s: tl1=%s, tl2=%s, tl3=%s", Thread.currentThread().getName(), tl.get(), tl2.get(), tl3.get()));
        });*/
    }
}

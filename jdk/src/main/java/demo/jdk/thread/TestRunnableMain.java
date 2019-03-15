/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.thread;

import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author sunqinwen
 * @version \: TestRunnableMain.java,v 0.1 2019-02-26 9:18
 */
public class TestRunnableMain {

    private static ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(2));

    public static void main(String[] args) {
        executorService.execute(new TestRunnable(()->{
            System.out.println("main run into, current thread = " + Thread.currentThread().getName());
        }));
    }

}

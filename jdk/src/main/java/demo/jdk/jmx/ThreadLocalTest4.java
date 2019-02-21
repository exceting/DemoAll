/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.jmx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author sunqinwen
 * @version \: ThreadLocalTest4.java,v 0.1 2019-02-21 10:38 
 *
 */
public class ThreadLocalTest4 {

    // 为了方便观察，我们假定线程池里只有一个线程
    private static ExecutorService executorService = Executors.newFixedThreadPool(1);

    private static ThreadLocal<Integer> tl = new InheritableThreadLocal<>();

    public static void main(String[] args) throws Exception{

        int x = 21;
        System.out.println(x % 10);
        System.out.println((x % 100)/10);
        System.out.println((x % 1000)/100);

        System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), tl.get()));

        executorService.execute(()->{
            System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), tl.get()));
        });

        Thread.sleep(1L); //确保上面的会在tl.set执行之前执行
        tl.set(1); // 等上面的线程池第一次启用完了，父线程再给自己赋值

        executorService.execute(()->{
            System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), tl.get()));
        });

        System.out.println(String.format("线程名称-%s, 变量值=%s", Thread.currentThread().getName(), tl.get()));
    }

}

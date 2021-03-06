/**
 * sharemer.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.concurrent.async.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author sunqinwen
 * @version \: WebHome.java,v 0.1 2019-01-16 18:54
 * 假设一个网站的首页，需要各种组装信息，利用future模式进行提高响应速度
 */
public class WebHome {

    private static ExecutorService executorService = Executors.newFixedThreadPool(6);

    // 同步获取
    public WebModule getWebModuleMsgSync() {
        WebModule webModule = new WebModule();
        webModule.setTop(getTop());
        webModule.setLeft(getLeft());
        webModule.setRight(getRight());
        webModule.setUser(getUser());
        return webModule;
    }

    // 异步获取
    public WebModule getWebModuleMsgAsync() throws ExecutionException, InterruptedException {
        Future<String> top = executorService.submit(this::getTop);
        Future<String> left = executorService.submit(this::getLeft);
        Future<String> right = executorService.submit(this::getRight);
        Future<String> user = executorService.submit(this::getUser);
        WebModule webModule = new WebModule();
        webModule.setTop(top.get());
        webModule.setLeft(left.get());
        webModule.setRight(right.get());
        webModule.setUser(user.get());
        return webModule;
    }

    // 简单异步获取
    public WebModule getWebModuleMsgSimpleAsync() throws ExecutionException, InterruptedException {

        long s = System.currentTimeMillis();
        WebModule webModule = new WebModule();

        Thread topTask = new Thread(() -> webModule.setTop(this.getTop()));
        Thread leftTask = new Thread(() -> webModule.setLeft(this.getLeft()));
        Thread rightTask = new Thread(() -> webModule.setRight(this.getRight()));
        Thread userTask = new Thread(() -> webModule.setUser(this.getUser()));

        //触发各个异步任务
        topTask.start();
        leftTask.start();
        rightTask.start();
        userTask.start();

        System.out.println("--------"+(System.currentTimeMillis() - s));

        long s2 = System.currentTimeMillis();
        //等待所有的任务均执行完毕
        topTask.join();
        leftTask.join();
        rightTask.join();
        userTask.join();
        System.out.println("--------"+(System.currentTimeMillis() - s2));

        return webModule;
    }

    private String getTop() { // 这里假设getTop需要执行200ms
        try {
            Thread.sleep(200L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "顶部banner位";
    }

    private String getLeft() { // 这里假设getLeft需要执行50ms
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "左边栏";
    }

    private String getRight() { // 这里假设getRight需要执行80ms
        try {
            Thread.sleep(80L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "右边栏";
    }

    private String getUser() { // 这里假设getUser需要执行100ms
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "用户信息";
    }

}
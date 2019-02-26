/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.thread;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author sunqinwen
 * @version \: TestRunnable.java,v 0.1 2019-02-26 9:13
 */
public class TestRunnable implements Runnable {

    private Map<Integer, Integer> maps;

    private Runnable runnable;

    public TestRunnable(Runnable runnable) {
        this.runnable = runnable;
        initMap();
        System.out.println("TestRunnable into, current thread = " + Thread.currentThread().getName());
    }

    private void initMap() {
        System.out.println("initMap into, current thread = " + Thread.currentThread().getName());
        maps = Maps.newHashMap();
        maps.put(1, 1);
        maps.put(2, 2);
    }

    @Override
    public void run() {
        System.out.println("run into, current thread = " + Thread.currentThread().getName());
        runnable.run();
    }
}

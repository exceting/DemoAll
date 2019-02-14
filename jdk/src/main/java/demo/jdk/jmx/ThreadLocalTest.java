/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.jmx;

/**
 * @author sunqinwen
 * @version \: ThreadLocalTest.java,v 0.1 2019-02-14 19:05
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> tl = new ThreadLocal<>();

    public static void main(String[] args) {
        tl.set(1);
        System.out.println(tl.get());
    }

}

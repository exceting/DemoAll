/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.jmx;

/**
 *
 * @author sunqinwen
 * @version \: ThreadLocalTest2.java,v 0.1 2019-02-15 11:48 
 *
 */
public class ThreadLocalTest2 {

    private static ThreadLocal<Integer> tl1 = new ThreadLocal<>();
    private static ThreadLocal<Integer> tl2 = new ThreadLocal<>();
    private static ThreadLocal<Integer> tl3 = new ThreadLocal<>();
    private static ThreadLocal<Integer> tl4 = new ThreadLocal<>();
    private static ThreadLocal<Integer> tl5 = new ThreadLocal<>();
    private static ThreadLocal<Integer> tl6 = new ThreadLocal<>();
    private static ThreadLocal<Integer> tl7 = new ThreadLocal<>();
    private static ThreadLocal<Integer> tl8 = new ThreadLocal<>();
    private static ThreadLocal<Integer> tl9 = new ThreadLocal<>();
    private static ThreadLocal<Integer> tl10 = new ThreadLocal<>();
    private static ThreadLocal<Integer> tl11 = new ThreadLocal<>();
    private static ThreadLocal<Integer> tl12 = new ThreadLocal<>();
    private static ThreadLocal<Integer> tl13 = new ThreadLocal<>();
    private static ThreadLocal<Integer> tl14 = new ThreadLocal<>();
    private static ThreadLocal<Integer> tl15 = new ThreadLocal<>();
    private static ThreadLocal<Integer> tl16 = new ThreadLocal<>();
    private static ThreadLocal<Integer> tl17 = new ThreadLocal<>();

    public static void main(String[] args) throws Exception {
        tl1.set(1);
        tl2.set(2);
        tl3.set(3);
        tl4.set(4);
        tl5.set(5);
        tl6.set(6);
        tl7.set(7);
        tl8.set(8);
        tl9.set(9);
        tl10.set(10);
        tl11.set(11);
        tl12.set(12);
        tl13.set(13);
        tl14.set(14);
        tl15.set(15);
        tl16.set(16);
        tl17.set(17);


        System.out.println(tl1.get());
        System.out.println(tl2.get());
        System.out.println(tl3.get());
        System.out.println(tl4.get());
        System.out.println(tl5.get());
        System.out.println(tl6.get());
        System.out.println(tl7.get());
        System.out.println(tl8.get());
        System.out.println(tl9.get());
        System.out.println(tl10.get());
        System.out.println(tl11.get());
        System.out.println(tl12.get());
        System.out.println(tl13.get());
        System.out.println(tl14.get());
        System.out.println(tl15.get());
        System.out.println(tl16.get());
        System.out.println(tl17.get());

        System.out.println(tl1.get());
        System.out.println(tl2.get());
        System.out.println(tl3.get());
        System.out.println(tl4.get());
        System.out.println(tl5.get());
        System.out.println(tl6.get());
        System.out.println(tl7.get());
        System.out.println(tl8.get());
        System.out.println(tl9.get());
        System.out.println(tl10.get());
        System.out.println(tl11.get());
        System.out.println(tl12.get());
        System.out.println(tl13.get());
        System.out.println(tl14.get());
        System.out.println(tl15.get());
        System.out.println(tl16.get());
        System.out.println(tl17.get());
    }

}

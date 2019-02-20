/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.jmx;

/**
 * @author sunqinwen
 * @version \: ThreadLocalTest2.java,v 0.1 2019-02-15 11:48
 */
public class ThreadLocalTest2 {

    private static ThreadLocal<Integer> tl = new InheritableThreadLocal<>();
    private static ThreadLocal<Hello> tl2 = new InheritableThreadLocal<>();

    public static void main(String[] args) throws Exception {
        tl.set(1);

        Hello hello = new Hello();
        hello.setName("init");
        tl2.set(hello);
        System.out.println(String.format("当前线程名称: %s, main方法内获取线程内数据为: tl = %s，tl2.name = %s，hello对象地址 = %s",
                Thread.currentThread().getName(), tl.get(), tl2.get().getName(), tl2.get()));
        fc();
        tl.set(2);
        new Thread(() -> {
            new Thread(()->{
               fc();
            }).start();
            fc();
        }).start();
        Thread.sleep(1000L); //保证下面fc执行一定在上面异步代码之后执行
        fc(); //继续在主线程内执行，验证上面那一步是否对主线程上下文内容造成影响
    }

    private static void fc() {
        System.out.println(String.format("当前线程名称: %s, fc方法内获取线程内数据为: tl = %s，tl2.name = %s，hello对象地址 = %s",
                Thread.currentThread().getName(), tl.get(), tl2.get().getName(), tl2.get()));
    }

}

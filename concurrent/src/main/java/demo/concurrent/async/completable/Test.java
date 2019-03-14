/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.concurrent.async.completable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author sunqinwen
 * @version \: Test.java,v 0.1 2019-03-14 17:01
 */
public class Test {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        long s = System.currentTimeMillis();
        Test t = new Test();

        //runAsync用于执行没有返回值的异步任务
        CompletableFuture future0 = CompletableFuture.runAsync(t::zero)
                .exceptionally(e -> {
                    System.out.println("Zero出错！");
                    return null;
                }); //这里是异常处理，指的是该异步任务执行中出错，应该做的处理

        //supplyAsync方法用于执行带有返回值的异步任务
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(t::a)
                .exceptionally(e -> {
                    System.out.println("方法A出错！");
                    return null;
                });

        //thenCompose方法用于连接两个CompletableFuture任务，如下代表futureA结束后将执行结果交由另外一个CompletableFuture处理，然后将执行链路最终赋值给futureB
        CompletableFuture<String> futureB = futureA.thenCompose(a -> CompletableFuture.supplyAsync(() -> t.b(a)))
                .exceptionally(e -> {
                    System.out.println("方法B出错！");
                    return null;
                });

        //thenAccept方法用于将一个任务的结果，传给需要该结果的任务，如下表示futureD的执行需要futureA的结果，与thenApply不同的是，这个方法没有有返回值
        CompletableFuture futureD = futureA.thenAccept(t::d);

        //thenApply方法用于将一个任务的结果，传给需要该结果的任务，如下表示futureE的执行需要futureA的结果，与thenAccept不同的是，这个方法有返回值
        CompletableFuture<String> futureE = futureA.thenApply(t::e)
                .exceptionally(e -> {
                    System.out.println("方法E出错！");
                    return null;
                });

        /**
         * thenApply方法概念容易与thenCompose混淆，毕竟最终目的很相似
         */

        //thenCombine方法用于连接多个异步任务的结果，如下ab方法需要futureA和futureB的执行结果，那么就可以使用thenCombine进行连接
        //注意，执行到ab这里，说明futureA和futureB一定已经执行完了
        CompletableFuture<String> futureAB = futureA.thenCombine(futureB, t::ab)
                .exceptionally(e -> {
                    System.out.println("方法AB出错！");
                    return null;
                });

        CompletableFuture<String> futureAE = futureA.thenCombine(futureE, t::ae)
                .exceptionally(e -> {
                    System.out.println("方法AE出错！");
                    return null;
                });

        CompletableFuture<String> futureC = CompletableFuture.supplyAsync(t::c)
                .exceptionally(e -> {
                    System.out.println("方法C出错！");
                    return null;
                });

        CompletableFuture<Integer> futureF = futureA.thenApply(t::f)
                .exceptionally(e -> {
                    System.out.println("方法F出错！");
                    return null;
                });

        CompletableFuture<String> futureAF = futureA.thenCombine(futureF, t::af)
                .exceptionally(e -> {
                    System.out.println("方法AF出错！");
                    return null;
                });

        //CompletableFuture.allOf(future0, futureA, futureB, futureAB, futureC, futureD, futureE).get();

        System.out.println("方法Zero输出：" + future0.get());
        System.out.println("方法A输出：" + futureA.get());
        System.out.println("方法B输出：" + futureB.get());
        System.out.println("方法AB输出：" + futureAB.get());
        System.out.println("方法C输出：" + futureC.get());
        System.out.println("方法D输出：" + futureD.get());
        System.out.println("方法E输出：" + futureE.get());
        System.out.println("方法AE输出：" + futureAE.get());
        System.out.println("方法F输出：" + futureF.get());
        System.out.println("方法AF输出：" + futureAF.get());
        System.out.println("耗时：" + (System.currentTimeMillis() - s) + "ms");
    }

    private void zero() {
        sleep(100L);
        System.out.println("零度快乐水是真的快乐！\n-----------------------------");
    }

    private String a() {
        sleep(500L);
        return "a";
    }

    private String b(String a) {
        sleep(1000L);
        return a + "b";
    }

    private String c() {
        sleep(500L);
        return "c";
    }

    private String ab(String a, String b) {
        sleep(100L);
        return a + "|" + b;
    }

    private void d(String a) {
        sleep(1000L);
        System.out.println("d方法触发，拿到的a = " + a);
    }

    private String e(String a) {
        sleep(100L);
        return a + "e";
    }

    private String ae(String a, String e) {
        sleep(100L);
        return a + "|" + e;
    }

    private Integer f(String a) {
        sleep(500L);
        return a.hashCode() + 10000;
    }

    private String af(String a, Integer f) {
        sleep(100L);
        return a + "|" + f;
    }


    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

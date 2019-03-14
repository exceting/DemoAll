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

        CompletableFuture future0 = CompletableFuture.runAsync(t::zero);

        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(t::a)
                .exceptionally(e -> {
                    System.out.println("获取A字母出错！");
                    return null;
                });

        CompletableFuture<String> futureB = futureA.thenCompose(a -> CompletableFuture.supplyAsync(() -> t.b(a)))
                .exceptionally(e -> {
                    System.out.println("获取B字母出错！");
                    return null;
                });

        CompletableFuture<String> futureC = CompletableFuture.supplyAsync(t::c)
                .exceptionally(e -> {
                    System.out.println("获取C字母出错！");
                    return null;
                });

        CompletableFuture<String> futureAB = futureA.thenCombine(futureB, t::ab)
                .exceptionally(e -> {
                    System.out.println("获取AB字母出错！");
                    return null;
                });

        CompletableFuture.allOf(future0, futureA, futureB, futureAB, futureC).get();

        System.out.println(future0.get() + "         " + futureA.get() + "        " + futureB.get() + "        " + futureC.get() + "       " + futureAB.get());
        System.out.println("耗时：" + (System.currentTimeMillis() - s) + "ms");
    }

    private String zero() {
        sleep(100L);
        System.out.println("零度快乐水是真的快乐！");
        return null;
    }

    private String a() {
        sleep(500L);
        return "a";
    }

    private String b(String a) {
        sleep(1000L);
        if (a != null) {
            return a + "b";
        }
        return null;
    }

    private String c() {
        sleep(500L);
        return "c";
    }

    private String ab(String a, String b) {
        sleep(100);
        if (a != null && b != null) {
            return a + "|" + b;
        }
        return null;
    }


    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

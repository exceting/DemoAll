package demo.leetcode.easy;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import netscape.javascript.JSObject;

import java.net.InetAddress;
import java.net.URI;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 1115，SR
 * 两个不同的线程将会共用一个 FooBar实例：
 * <p>
 * 线程 A 将会调用foo()方法，而
 * 线程 B 将会调用bar()方法
 * 请设计修改程序，以确保 "foobar" 被输出 n 次。
 */
public class FooBar {
    private int n;

    public static void main(String[] args) throws Exception {

        long a = 3L;
        long b = 9L;
        long c = (long) (((double) a / (double) b) * 10000);

        System.out.println(((double) c / 10000D) * 100);


        List<Integer> data = Lists.newArrayList(4, 5, 2, 1, 8, 7);
        data = data.stream().sorted(Comparator.comparingInt(d -> d)).collect(Collectors.toList());

        System.out.println(data);

        int[] ii = new int[]{1, 2, 4};
        for (int i = 0; i < ii.length / 2; i++) {
            System.out.println(ii[i] + "   " + ii[(ii.length-1) - i]);
        }

        System.out.println("20221015".substring(0, 8));

        String ss = String.format("%sms(%s%%)", 2, 98);
        System.out.println(ss);
    }

    public static void dg(TraceVo traceVo) {
        if (traceVo.getChildren().size() != 0) {
            System.out.println(traceVo.id);
            traceVo.children.forEach(c -> {
                dg(c);
                System.out.print(c.id + ",");
            });
            System.out.println();
        }
    }

    public static class TraceVo {
        private int id;
        private int parentId;
        private List<TraceVo> children;

        public TraceVo(int id, int parentId, List<TraceVo> children) {
            this.id = id;
            this.parentId = parentId;
            this.children = children;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getParentId() {
            return parentId;
        }

        public void setParentId(int parentId) {
            this.parentId = parentId;
        }

        public List<TraceVo> getChildren() {
            return children;
        }

        public void setChildren(List<TraceVo> children) {
            this.children = children;
        }
    }

    public FooBar(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (this) {
                this.notify();
                printFoo.run();
                this.wait();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {
            synchronized (this) {
                this.notify();
                printBar.run();
                this.wait();
            }
        }
    }
}

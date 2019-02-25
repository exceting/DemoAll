package demo.jdk.jmx;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.threadpool.TtlExecutors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create by 18073 on 2019/2/25.
 */
public class ThreadLocalTest6 {

    private static ExecutorService executorService = TtlExecutors.getTtlExecutorService(Executors.newFixedThreadPool(2));

    private static ThreadLocal<Integer> tl = new TransmittableThreadLocal<>();
    private static ThreadLocal<Hello> tl2 = new TransmittableThreadLocal<>();
    private static ThreadLocal<Integer> tl3 = new TransmittableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        tl.set(1);
        tl2.set(new Hello("test_1"));

        executorService.execute(() -> {
            tl2.set(new Hello("test_2"));
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("async-1: %s: tl1=%s, tl2=%s, tl3=%s", Thread.currentThread().getName(), tl.get(), tl2.get().getName(), tl3.get()));
        });

        tl3.set(7);

        Thread.sleep(1000L);

        tl.set(2); // 等上面的线程池第一次启用完了，父线程再给自己赋值

        System.out.println(String.format("%s: tl1=%s, tl2=%s, tl3=%s", Thread.currentThread().getName(), tl.get(), tl2.get().getName(), tl3.get()));

        executorService.execute(()->{
            System.out.println(String.format("async-2: %s: tl1=%s, tl2=%s, tl3=%s", Thread.currentThread().getName(), tl.get(), tl2.get().getName(), tl3.get()));
        });
    }

}


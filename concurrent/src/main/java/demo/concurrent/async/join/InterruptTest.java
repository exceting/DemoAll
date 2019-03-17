package demo.concurrent.async.join;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Create by 18073 on 2019/3/17.
 */
public class InterruptTest {

    private static ExecutorService executorService = Executors.newFixedThreadPool(1);

    public static void main(String[] args) {
        executorService.execute(() -> {
            sleep(1000L);
            Thread.currentThread().interrupt();
            System.out.println(Thread.currentThread().getName() + "2333   "+ Thread.currentThread().isInterrupted());
        });

        sleep(2000L);

        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "sadasfadf  " +Thread.currentThread().isInterrupted());
        });
    }

    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

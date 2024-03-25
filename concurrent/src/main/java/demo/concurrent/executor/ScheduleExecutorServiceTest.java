package demo.concurrent.executor;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduleExecutorServiceTest {

    public static void main(String[] args) {
        ScheduledExecutorService respScheduler = new ScheduledThreadPoolExecutor(2);
        System.out.println("task begin:" + System.currentTimeMillis() / 1000);
        respScheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000); //2000
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "--task1 run:" + System.currentTimeMillis() / 1000);
            }
        }, 2, 3, TimeUnit.SECONDS);
        respScheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "--task2 run:" + System.currentTimeMillis() / 1000);
            }
        }, 2, 1, TimeUnit.SECONDS);

        new Thread();
    }

}

package demo.jdk.thread;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MySemaphore {

    private final AtomicLong counter;

    private final Lock lock = new ReentrantLock();
    private final Condition waitQueue = lock.newCondition();

    public MySemaphore() {
        this(0);
    }

    public MySemaphore(int init) {
        this.counter = new AtomicLong(init);
    }

    public void up() {

        lock.lock();
        try {
            long cur = counter.incrementAndGet();
            if (cur <= 0) {
                waitQueue.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public void down() throws InterruptedException {
        lock.lock();
        try {
            long cur = counter.decrementAndGet();
            if (cur < 0) {
                waitQueue.await();
            }
        } finally {
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        MySemaphore mySemaphore = new MySemaphore();

    }
}

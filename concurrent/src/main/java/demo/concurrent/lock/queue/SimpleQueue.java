/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.concurrent.lock.queue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sunqinwen
 * @version \: SimpleQueue.java,v 0.1 2019-01-16 14:47
 * 利用重入锁和重入锁的线程调度实现的简单阻塞队列
 */
public class SimpleQueue<T> {

    static ReentrantLock lock = new ReentrantLock();

    private T[] nodes;

    private int tail = 0; // 入元素下标

    private int count = 0; // 元素个数

    private int head = 0; // 出元素下标

    public SimpleQueue(int size) {
        nodes = (T[]) new Object[size];
    }

    private static Condition notFull = lock.newCondition();

    private static Condition notEmpty = lock.newCondition();

    public void put(T t) {
        try {
            lock.lock();
            if (count == nodes.length) { // 队列已满，阻塞
                System.out.println("目前队列已满，等待取值中");
                notFull.await();
            }
            if (tail > (nodes.length - 1)) { // 当前游标值已经大于数组游标最大值了，则从0开始计算
                tail = 0;
            }
            nodes[tail] = t; // 给当前游标位赋值
            count++; // 入元素元素个数+1
            tail++; // 游标值+1
            notEmpty.signalAll(); // 走到这里说明队列内至少有一个元素，则唤醒取值
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public T take() {
        T t = null;
        try {
            lock.lock();
            if (count == 0) { // 队列已空，等待加值
                System.out.println("目前队列已空，等待入值中");
                notEmpty.await();
            }
            if (head > (nodes.length - 1)) { // 若取值游标大于游标最大值，则从0开始计算
                head = 0;
            }
            t = nodes[head]; // 拿到元素值
            nodes[head] = null; // 清空原有位置上的值
            head++; // 取值游标+1
            count--; // 元素个数-1
            notFull.signalAll(); // 走到这里说明队列至少有一个空位，则唤醒入值
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return t;
    }

}

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
 */
public class SimpleQueue<T> {

    static ReentrantLock lock = new ReentrantLock();

    private T[] nodes;

    private int cursor = 0; // 元素下标

    private int count = 0; // 元素个数

    private int head = 0; // 获取时元素下标

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

            if (cursor > (nodes.length - 1)) {
                cursor = 0;
            }

            nodes[cursor] = t;

            count++;

            cursor++;

            notEmpty.signalAll();

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

            if (head > (nodes.length - 1)) {
                head = 0;
            }

            t = nodes[head];

            nodes[head] = null;

            head++;

            count--;

            notFull.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return t;
    }

}

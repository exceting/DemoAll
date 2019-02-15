/**
 * Bilibili.com Inc.
 * Copyright (c) 2009-2019 All Rights Reserved.
 */
package demo.jdk.jmx;

/**
 * @author sunqinwen
 * @version \: ThreadLocalTest.java,v 0.1 2019-02-14 19:05
 */
public class ThreadLocalTest {

    private static ThreadLocal<Integer> tl = new ThreadLocal<>();

    public static void main(String[] args) throws Exception {
        tl.set(1);
        System.out.println(String.format("当前线程名称: %s, main方法内获取线程内数据为: %s",
                Thread.currentThread().getName(), tl.get()));
        fc();
        new Thread(()->{
            tl.set(2); //在子线程里设置上下文内容为2
            fc();
        }).start();
        Thread.sleep(1000L); //保证下面fc执行一定在上面异步代码之后执行
        fc(); //继续在主线程内执行，验证上面那一步是否对主线程上下文内容造成影响
    }

    private static void fc() {
        System.out.println(String.format("当前线程名称: %s, fc方法内获取线程内数据为: %s",
                Thread.currentThread().getName(), tl.get()));
    }



    public void set(T value) {
        Thread t = Thread.currentThread(); //获取当前线程
        ThreadLocal.ThreadLocalMap map = getMap(t); //拿到当前线程里保存的TL对象的map
        if (map != null){
            map.set(this, value); //若已经存在对应的map对象，直接把当前的TL对象set进去，赋值value，标记当前线程针对该TL对象所持有的值
        } else {
            createMap(t, value); //若不存在，直接创建
        }
    }

    // getMap方法，我们可以看到，这个map其实是线程对象的一个属性
    ThreadLocal.ThreadLocalMap getMap(Thread t) {
        return t.threadLocals;
    }

    private void set(ThreadLocal<?> key, Object value) {

        ThreadLocal.ThreadLocalMap.Entry[] tab = table;
        int len = tab.length;
        int i = key.threadLocalHashCode & (len-1);

        for (ThreadLocal.ThreadLocalMap.Entry e = tab[i];
             e != null;
             e = tab[i = nextIndex(i, len)]) {
            ThreadLocal<?> k = e.get();

            if (k == key) {
                e.value = value;
                return;
            }

            if (k == null) {
                replaceStaleEntry(key, value, i);
                return;
            }
        }

        tab[i] = new ThreadLocal.ThreadLocalMap.Entry(key, value);
        int sz = ++size;
        if (!cleanSomeSlots(i, sz) && sz >= threshold)
            rehash();
    }

}

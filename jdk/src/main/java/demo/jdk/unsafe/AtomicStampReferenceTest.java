package demo.jdk.unsafe;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 解决CAS ABA问题
 */
public class AtomicStampReferenceTest {

    private AtomicStampedReference<Integer> v = new AtomicStampedReference<>(0, 0);

    public static void main(String[] args) throws Exception {
        AtomicStampReferenceTest atomicStampReferenceTest = new AtomicStampReferenceTest();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                atomicStampReferenceTest.incr();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                atomicStampReferenceTest.incr();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("========>  " + atomicStampReferenceTest.v.getStamp());
    }

    private int incr() {
        return addAndGet(1);
    }

    private int addAndGet(int delta) {
        int expectV;
        int expectStamp;
        do {
            expectV = v.getReference();
            expectStamp = v.getStamp();
        } while (!v.weakCompareAndSet(expectV, (expectV + delta), expectStamp, (expectStamp + 1)));
        return expectV + delta;
    }

}

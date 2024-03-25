package demo.jdk.unsafe;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {

    private final AtomicReference<Nums> atomicReference = new AtomicReference<>();

    public static void main(String[] args) throws Exception {
        AtomicReferenceTest atomicReferenceTest = new AtomicReferenceTest();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                atomicReferenceTest.aBPlus();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                atomicReferenceTest.aBPlus();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("========>  a = " + atomicReferenceTest.atomicReference.get().a
                + "    b = " + atomicReferenceTest.atomicReference.get().b);
    }

    private void aBPlus() {
        Nums newOne;
        Nums expectOne;

        do {
            expectOne = atomicReference.get();
            newOne = new Nums();
            if (expectOne != null) {
                newOne.a = expectOne.a + 1;
                newOne.b = expectOne.b + 1;
            }
        } while (!atomicReference.compareAndSet(expectOne, newOne));
    }

    public static class Nums {
        private int a = 1;
        private int b = 1;
    }

}

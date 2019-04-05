package demo.concurrent.async.join;

/**
 * Create by 18073 on 2019/3/16.
 */
public class JoinTest {

    private boolean isStop = false;

    public static void main(String[] args) throws Exception {
        JoinTest test = new JoinTest();
        Thread loopT = new Thread(test::loopTask);
        loopT.start();

        //sleep(2000L); //2s后终止线程
        //test.setStop(true);

        new Thread(()->{
            sleep(2000L);
            loopT.interrupt();
        }).start();

        long s = System.currentTimeMillis();
        loopT.join();
        System.out.println("线程终止后，join阻塞时间为：" + (System.currentTimeMillis() - s));
        System.out.println("end~");
    }

    public void setStop(boolean stop) {
        isStop = stop;
    }

    public void loopTask() {
        while (!isStop) { //若状态为false，则继续执行下面的逻辑，每隔1s打印一次
            if(!sleep(1000L)){
                break;
            }
            System.out.println("loop trigger ~");
        }
        //事实上，在终止掉线程后，还有接下来的逻辑要执行
        long s = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            int[] a = new int[100]; //模拟耗时操作，这里不能用sleep了，因为当前线程已经被终止了
        }
        System.out.println("线程终止后，逻辑快运行时间：" + (System.currentTimeMillis() - s));
    }

    public static boolean sleep(long time) {
        try {
            Thread.sleep(time);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }

}

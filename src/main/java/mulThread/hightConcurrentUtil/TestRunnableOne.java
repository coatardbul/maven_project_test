package mulThread.hightConcurrentUtil;

import java.util.concurrent.CountDownLatch;

public class TestRunnableOne implements Runnable{

    /** 处理main线程阻塞（等待所有子线程） */
    private CountDownLatch countDown;

    /** 线程名字 */
    private String  threadName;


    public TestRunnableOne(CountDownLatch countDownLatch, String threadName) {
        this.countDown = countDownLatch;
        this.threadName = threadName;
    }

    @Override
    public void run() {
        System.out.println( "[" + threadName + "] Running ! [countDownLatch.getCount() = " + countDown.getCount() + "]." );
        // 每个独立子线程执行完后,countDownLatch值减1
        countDown.countDown();
    }

    public static void main(String [] args) throws InterruptedException {
        int countNum = 5;
        CountDownLatch countDownLatch = new CountDownLatch(countNum);
        for(int i=0; i<countNum; i++){
            new Thread(new TestRunnableOne(countDownLatch,"子线程" + (i+100))).start();
        }
        System.out.println("主线程阻塞,等待所有子线程执行完成");
        //endLatch.await()使得主线程（main）阻塞直到endLatch.countDown()为零才继续执行
        countDownLatch.await();
        System.out.println("所有线程执行完成!");
    }
}
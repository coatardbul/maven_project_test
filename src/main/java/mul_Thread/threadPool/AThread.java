package mul_Thread.threadPool;

import lombok.extern.slf4j.Slf4j;


public class AThread implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(10000);
            System.out.println("线程执行中："+Thread.currentThread().getName());
        } catch (Exception e) {
           e.printStackTrace();
        }
    }
}

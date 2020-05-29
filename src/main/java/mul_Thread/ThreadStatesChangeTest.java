package mul_Thread;

import lombok.SneakyThrows;

public class ThreadStatesChangeTest extends Thread{


    @SneakyThrows
    @Override
    public void run() {
        for(int i=0;i<20;i++){
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(4000);
            Thread.yield();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread r=new ThreadStatesChangeTest();

        Thread r1=new ThreadStatesChangeTest();
        r.start();
        Thread.sleep(3000);
        r1.start();
    }
}

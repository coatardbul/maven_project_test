package test;

import java.util.concurrent.ConcurrentHashMap;

public class regrerregef {

    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        concurrentHashMap.put("sb", "sb");
        concurrentHashMap.put("123", "123");
        concurrentHashMap.put("222", "222");
        concurrentHashMap.put("333", "333");
        //for(String str: concurrentHashMap.keySet()){}
        MyThread myThread = new MyThread();
        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();

        myThread.start();

        myThread.join();
        myThread1.start();
        myThread1.join();

        myThread2.start();
        myThread2.join();



    }


}

class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("sb");
    }
}
class MyThread1 extends Thread {
    @Override
    public void run() {
        System.out.println("sb1");
    }
}
class MyThread2 extends Thread {
    @Override
    public void run() {
        System.out.println("sb2");
    }
}


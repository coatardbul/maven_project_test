package bookSourceCode.javaHightConcurrencyProgram.ch2;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapMultThread {

    public static Map<String,String> map=new ConcurrentHashMap<>();

    public static class AddThread implements Runnable{

        @Override
        public void run() {
            for(int i=0;i<100000;i++){
                map.put(String.valueOf(i),Integer.toBinaryString(i));
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t=new Thread(new AddThread(),"t");
        Thread t1=new Thread(new AddThread(),"t1");
        t.start();
        t1.start();
        t.join();
        t1.join();
        System.out.println(map.size());

    }
}

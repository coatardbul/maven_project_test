package bookSourceCode.javaHightConcurrencyProgram.ch2;

import java.util.ArrayList;
import java.util.List;

public class ArrayListMultThread {

    public static  List  list=new ArrayList();

    public static class AddThread implements Runnable{

        @Override
        public void run() {
            for(int i=0;i<10000000;i++){
                list.add(i);
            }

            System.out.println(Thread.currentThread().getName()+"-----"+list.size());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable target;
        Thread d1=new Thread(new AddThread(),"d1");
        Thread d2=new Thread(new AddThread(),"d2");
        d1.start();
        d2.start();
//        d1.join();
//        d2.join();

        System.out.println("########"+list.size());
    }
}

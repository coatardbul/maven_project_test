package mul_Thread;

public class SynchronizedTest implements Runnable {
    public static int count = 0;

    public  void add() {
        synchronized(this){
            for (int i = 0; i < 100000; i++) {
                count++;
            }
        }


    }

    @Override
    public void run() {
        synchronized(this) {
            for (int i = 0; i < 100000; i++) {
                try {
                    System.out.println(Thread.currentThread().getName() + ":" + (count++));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
//        add();
//        System.out.println(Thread.currentThread().getName() + ":" + (count++));
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        for (int i = 0; i < 10; i++) {
           Thread t1= new Thread(new SynchronizedTest());
           t1.start();
          // t1.join();
        }
        System.out.println(count);
    }
}

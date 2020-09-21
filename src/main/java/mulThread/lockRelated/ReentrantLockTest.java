package mulThread.lockRelated;

public class ReentrantLockTest {

    public static void main(String[] args) {
        Counter counter = new Counter();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (counter.getCount() >= 0) {
                  //  counter.desc();
                }
            }
        };
         new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();

    }


}

//class Counter{
//    private int count = 1010;
//    public void desc(){
//        System.out.println(Thread.currentThread().getName() +"--->"+count);
//        count--;
//    }
//
//    public int getCount() {
//        return count;
//    }
//}


//class Counter {
//    private int count = 10000;
//    private Lock lock = new ReentrantLock();
//
//    public void desc() {
//        lock.lock();//上锁
//
//        if (count >= 0) {
//            System.out.println(Thread.currentThread().getName() + "--->" + count);
//            count--;
//        }
//
//        lock.unlock();//释放锁
//    }
//
//    public int getCount() {
//        return count;
//    }
//}

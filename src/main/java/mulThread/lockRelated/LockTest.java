package mulThread.lockRelated;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {


    public static void main(String[] args) {
        Lock lock=new ReentrantLock();
        lock.lock();
        lock.lock();

        lock.lock();
        lock.lock();
        lock.lock();
        lock.lock();
        lock.lock();
        lock.lock();

        lock.unlock();
    }
}

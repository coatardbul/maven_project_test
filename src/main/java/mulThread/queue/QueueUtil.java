package mulThread.queue;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueUtil {

    public  static  Queue getArrayBlockQueueByLength(int length) {
        Queue queue = new ArrayBlockingQueue(length);
        for (int i = 1; i <= length; i++) {
            queue.offer(2 * i);
        }
        return queue;
    }
}

package timedTask.timer;

import java.util.Timer;
import java.util.TimerTask;

public class timedTest {
    public static void main(String[] args) {
        for (int i = 0; i < 10; ++i) {
            new Timer("timer - " + i).schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " run ");
                }
            }, 1000);
        }
    }

}

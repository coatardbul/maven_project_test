package concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class test1 {

    public static void main(String[] args) throws InterruptedException {
        final Map<String, String> map = new HashMap();
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            map.put(UUID.randomUUID().toString(), "sss");
                        }
                    },"ttt"+i).start();
                }

            }
        },"ffff");
        t.start();
        t.join();
    }


}

package concurrent;

import java.text.SimpleDateFormat;
import java.util.*;

public class test1 {

    public static void main(String[] args) throws InterruptedException {
//        final Map<String, String> map = new HashMap();
//        Thread t = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                for (int i = 0; i < 10000; i++) {
//                    new Thread(new Runnable() {
//                        @Override
//                        public void run() {
//                            map.put(UUID.randomUUID().toString(), "sss");
//                        }
//                    },"ttt"+i).start();
//                }
//
//            }
//        },"ffff");
//        t.start();
//        t.join();



    }
    public static String getBeforeDayDateFormat(int day) {
        day = 0 - day;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE, day);
        return new SimpleDateFormat("yyyyMMdd").format(calendar.getTime());
    }


}

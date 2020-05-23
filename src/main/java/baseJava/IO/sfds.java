package baseJava.IO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class sfds {
    public static void main(String[] args) {
        String ss = "aaa|bb  bb";
        System.out.println(new Date());

    }

    public static String covStringToStringTwo(String sDate) {
        SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat1.parse(sDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String newDate = dateFormat.format(date);
        return newDate;
    }
}

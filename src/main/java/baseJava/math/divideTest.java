package baseJava.math;

import baseJava.util.DateRangeUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class divideTest {
    private static String exception(Throwable t) throws IOException {
        if (t == null) {
            return null;
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            t.printStackTrace(new PrintStream(baos));
        } finally {
            baos.close();
        }
        return baos.toString();
    }



    public static void main(String[] args) {
        String analyseDateRange="202001";
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(analyseDateRange.substring(0, 4)) );
        cal.set(Calendar.MONTH, Integer.parseInt(analyseDateRange.substring(4, 6))-1);
        cal.add(Calendar.MONTH, -1);
        String s= cal.get(Calendar.YEAR) + DateRangeUtil.getDayMonthStr(cal.get(Calendar.MONTH) + 1);

        System.out.println(s);

    }


    public static String getDayMonthStr(int num) {
        if (num < 10) {
            return "0" + num;
        } else {
            return String.valueOf(num);
        }
    }
}

package baseJava.string;

import org.junit.Test;

import java.util.*;

public class StringConvert {


    /**
     * 将SURPLUS_PERIOD_TYPE转换成surplusPeriodType
     */
    @Test
    public void stringUpConvertToDown() {
        int i = "09:30".compareTo("01:11");
        System.out.println(i);
    }

    @Test
    public void test13() {
        Map<String, List<String>> param = new HashMap<>();
        List<String> s=new ArrayList<>();
        s.add("123");
        s.add("456");
        param.put("hello",s);
        List<String> t=new ArrayList<>();
        t.add("111");
        t.add("222");
        s=t;
        param.put("hello",t);
        System.out.println("123");
    }

    /**
     * 将SURPLUS_PERIOD_TYPE来转换成Surplus_Period_Type
     */
    @Test
    public void stringFirstUpConvert() {
        String str = "SURPLUS_PERIOD_TYPE".toLowerCase();
        char[] arr = str.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                arr[i] = String.valueOf(arr[i]).toUpperCase().charAt(0);
            }
            if (i - 1 >= 0 && String.valueOf(arr[i - 1]).equals("_")) {
                arr[i] = String.valueOf(arr[i]).toUpperCase().charAt(0);
            } else {
                continue;
            }
        }
        // System.out.println(Arrays.toString(arr));
        System.out.print("text");
        for (char c : arr) {

            System.out.print(c);
        }
    }
}

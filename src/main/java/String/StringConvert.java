package String;

import org.junit.Test;

public class StringConvert {


    /**
     * 将SURPLUS_PERIOD_TYPE转换成surplusPeriodType
     */
    @Test
    public void stringUpConvertToDown() {
        String str = "CONVEXITY_DOWN".toLowerCase();
        StringBuffer sb = new StringBuffer("");
        if (str.contains("_")) {
            String[] arr = str.split("_");
            for (int i = 0; i < arr.length; i++) {
                if(i==0){
                    sb.append(arr[i]);
                    continue;
                }
                sb.append(String.valueOf(arr[i].charAt(0)).toUpperCase());
                sb.append(arr[i].substring(1,arr[i].length()));
              //  sb.append(arr[i].replace(arr[i].charAt(0), String.valueOf(arr[i].charAt(0)).toUpperCase().charAt(0)));

            }
        }
        System.out.println(sb);
    }
}

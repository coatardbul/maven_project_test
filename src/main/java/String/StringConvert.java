package String;

import org.junit.Test;

import java.util.Arrays;

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

    /**
     * 将SURPLUS_PERIOD_TYPE来转换成Surplus_Period_Type
     */
    @Test
    public  void stringFirstUpConvert(){
        String str="SURPLUS_PERIOD_TYPE".toLowerCase();
        char[] arr=str.toCharArray();
        for(int i=0;i<arr.length;i++){
            if(i==0){
                arr[i]=String.valueOf(arr[i]).toUpperCase().charAt(0);
            }
            if(i-1>=0&&String.valueOf(arr[i-1]).equals("_")){
                arr[i]=String.valueOf(arr[i]).toUpperCase().charAt(0);
            }else {
                continue;
            }
        }
       // System.out.println(Arrays.toString(arr));
        System.out.print("text");
        for(char c:arr){

            System.out.print(c);
        }
    }
}

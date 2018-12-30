package math;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class sad {
        public static void main(String[] args) {
            System.out.println("科学计数法数字");
            double num1 = 50123.12E25;
            System.out.println(num1);
            BigDecimal bd1 = new BigDecimal(num1);
            System.out.println(bd1.toPlainString());
            System.out.println(bd1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
            System.out.println("普通数字");
            double num2 = 50123.12;
            System.out.println(num2);
            BigDecimal bd2 = new BigDecimal(num2);
            System.out.println(bd2.toPlainString());
            //bd1.compareTo()
            System.out.println(bd2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        }

        @Test
    public void test2(){
            Map<String,String> map=new HashMap<>();
            map.put("sb","1321");
            System.out.println(map.get("312123"));
        }


}

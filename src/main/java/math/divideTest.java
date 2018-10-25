package math;

import java.math.BigDecimal;

public class divideTest {
    public static void main(String[] args) {
        int a4 = 3;
        long b4 = 14;
        System.out.println(b4 % a4);
        BigDecimal bd = new BigDecimal(a4);
        BigDecimal b3 = new BigDecimal(b4);

        System.out.println(Integer.MAX_VALUE);

        System.out.println("除法运算结果是：" + b3.divide(bd, BigDecimal.ROUND_UP));
    }
}

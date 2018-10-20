package JDBCTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class test2 {
    public static void main(String[] args) {
        int a4 = 3;
        long b4 = 14;
        System.out.println(b4 % a4);
        BigDecimal bd = new BigDecimal(a4);
        BigDecimal b3 = new BigDecimal(b4);


        System.out.println("除法运算结果是：" + b3.divide(bd, BigDecimal.ROUND_UP));


    }
}

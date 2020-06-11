package baseJava.math;

import org.junit.Test;

import java.math.BigDecimal;

public class MathTest {

    @Test
    public void test1() {
        BigDecimal b = new BigDecimal(String.valueOf(Long.MAX_VALUE)+"00000"+".0123456789");
        System.out.println(b.setScale(2,BigDecimal.ROUND_UNNECESSARY));
        System.out.println(b.setScale(2,2));
        System.out.println(b.setScale(2,3));
        System.out.println(b.setScale(2,4));
        System.out.println(b.setScale(2,5));
        System.out.println(b.setScale(2,6));
        System.out.println(b.setScale(2,7));
        System.out.println(b.setScale(2,8));

        System.out.println(b.toString());
    }
}

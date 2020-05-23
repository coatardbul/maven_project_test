package baseJava.math;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;

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
       String s="12312";
       System.out.println(new BigDecimal(s.toString()));


//        System.out.println(Integer.MAX_VALUE);
//
//        System.out.println("除法运算结果是：" + b3.divide(bd, BigDecimal.ROUND_UP));
    }
}

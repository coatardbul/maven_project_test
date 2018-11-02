package math;

import org.junit.Test;

import java.util.HashMap;

public class MathTest {

    @Test
    public void test1() {

        int s = Math.max((int) (14 / .75f) + 1, 16);
        int t = (int) (15 / 0.75f);
        System.out.println(s);
        System.out.println(t);
    }
}

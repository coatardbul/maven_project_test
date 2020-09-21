package baseJava.exception;

import org.junit.Test;

import java.io.FileNotFoundException;

public class ExceptionTryCatch {


    @Test
    public void test() {
        try {
            throwException();
            System.out.println("这一行不会被打印出来");
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            throwException();
            System.out.println("这一行不会被打印出来");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("这一行会被打印出来");
    }

    public static void throwException() throws Exception {
        int i = 0;
        if (i == 0) {
            throw new FileNotFoundException();
        }
    }

    /**
     * 如果在finally中返回值，那么在程序中抛出的异常信息将会被吞噬掉。
     * 这是一个非常值得注意的问题，因为异常信息是非常重要的，
     * 在出现问题时，我们通常凭它来查找问题。如果编码不小心而导致异常被吞噬，
     * 排查起来是相当困难的，这将是一个大隐患。
     *
     * @return
     */
    public Integer finaillyCatch() {
        try {
            throwException();
        } catch (Exception e) {
            System.out.println("catch中的打印");
            e.printStackTrace();
        } finally {
            return 2;
        }
    }

    @Test
    public void test2() {
        finaillyCatch();
    }

    /**
     * 不可检查的异常的捕捉
     */
    @Test
    public void test3() {
        String[] strArr = {"123sd21", "12312sdds"};
        try {
            for (int i = 0; i < strArr.length + 1; i++) {
                System.out.println(strArr[i]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

}

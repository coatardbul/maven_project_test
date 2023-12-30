package baseJava.string;

import baseJava.string.MyTask;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    主程序类,测试任务类
 */

public class MyTest {
    public static void main(String[] args) {
        try {
            BigDecimal divide = new BigDecimal(122).subtract(new BigDecimal(0)).divide(new BigDecimal(0));
            System.out.println("divide: " + divide);
        }catch (Exception e) {
            System.out.println(12321321);
            return;
        }finally {
            System.out.println("2222");
        }



    }


}
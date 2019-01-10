package baseJava.test.compareDecorator;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;

/**
 * @author zhengbinMac
 * 测试类
 */
public class Test {

    public static void main(String[] args) throws IOException {
        Cafe cafe = new ConcreteCafe();

        Cafe milkCafe = new MilkDecorator(cafe);
        milkCafe.getCafe();
        System.out.println("----------------------------------------");
        Cafe sugarCafe = new SugarDecorator(cafe);
        sugarCafe.getCafe();
        System.out.println("----------------------------------------");
        Cafe sugarMilkCafe = new MilkDecorator(new SugarDecorator(new ConcreteCafe()));
        sugarMilkCafe.getCafe();
        System.out.println("----------------------------------------");
        Cafe milkSugarCafe = new SugarDecorator(new MilkDecorator(cafe));
        milkSugarCafe.getCafe();
        System.out.println("----------------------------------------");

        File file = new File("/Users/zhengbinMac/test.txt");

        FileInputStream in2 = new FileInputStream(file);
        byte[] tempByte = new byte[1024];
        int hasRead = 0;
        System.out.println("开始时间：" + new Date().getTime());
        while((hasRead = in2.read(tempByte)) > 0) {
            System.out.println(new String(tempByte,0,hasRead));
        }
        System.out.println("结束时间：" + new Date().getTime());
        in2.close();

        System.out.println("----------------------------------------");

        FileInputStream in4 = new FileInputStream(file);
        byte[] tempByte2 = new byte[(int)file.length()];
        System.out.println("开始时间：" + new Date().getTime());
        in4.read(tempByte2);
        System.out.println("结束时间：" + new Date().getTime());
        in4.close();
        System.out.println(new String(tempByte2));

        System.out.println("----------------------------------------");

//        DataInputStream in3 = new DataInputStream(new FileInputStream(file));
//        String s;
//        System.out.println("开始时间：" + new Date().getTime());
//        while((s=in3.readLine()) != null) {
//            System.out.println(s);
//        }
//        System.out.println("结束时间：" + new Date().getTime());
//        in3.close();

        System.out.println("----------------------------------------");

//        DataInputStream in5 = new DataInputStream(new BufferedInputStream(new FileInputStream(file)));
//        String s1;
//        System.out.println("开始时间：" + new Date().getTime());
//        while((s1 = in5.readLine()) != null) {
//            System.out.println(s1);
//        }
//        System.out.println("结束时间：" + new Date().getTime());
//        in5.close();

        System.out.println("----------------------------------------");

        BufferedReader in6 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String s2;
        System.out.println("开始时间：" + new Date().getTime());
        while((s2 = in6.readLine()) != null) {
            System.out.println(s2);
        }
        System.out.println("结束时间：" + new Date().getTime());
        in6.close();

        System.out.println("----------------------------------------");


    }

}


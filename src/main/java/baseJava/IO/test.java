package baseJava.IO;

import java.util.Scanner;

import java.lang.Thread;


public class test {

    public static void main(String[] args) {


        String str = "2021年12月21日";

        boolean matches = str.matches("[0-9]{4}[\u4E00-\u9FA5]{1}[0-9]{2}[\u4E00-\u9FA5]{1}[0-9]{2}[\u4E00-\u9FA5]{1}");

        System.out.println(matches);


        String str1 = "年";

        boolean matches1 = str1.matches("[\u4E00-\u9FA5]{1}");

        System.out.println(matches1);

    }

}
package leetCode.num7;

import org.junit.Test;

public class Num7 {


    @Test
    public void test1(){
        int s=12345670;
        String str=String.valueOf(s);
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<str.length();i++){
            sb.append(str.charAt(str.length()-1-i));
        }
        String ss=sb.toString();
        int result=Integer.parseInt(ss);
        System.out.println(result);
    }
}

package leetCode;

import org.junit.Test;

public class Num9 {

    public static Boolean isPalindrome(int x) {
        String str=String.valueOf(x);
        if("-".equals(str.charAt(0))|| str.length()%2==0){
            return false;
        }
        if(str.length()%2==1){
            Boolean check=true;
            for(int i=0;i<str.length()/2;i++){
                if(!String.valueOf(str.charAt(i)).equals(String.valueOf(str.charAt(str.length()-1-i)))){
                    check=false;
                }
            }
            return check;
        }
        return false;
    }

    @Test
    public void test1(){
       Boolean b=isPalindrome(12321);
       System.out.println(b);


    }
}

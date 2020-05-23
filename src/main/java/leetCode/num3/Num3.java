package leetCode.num3;

import org.junit.Test;

import java.util.ArrayList;

public class Num3 {
    /**
     * Given a string, find the length of the longest substring without repeating characters.
     * <p>
     * Example 1:
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        String str = "";
        for (int i = 0; i < s.length() - 2; i++) {
           for(int j=i;j<s.length()-1;i++){
               String strTemp=s.substring(i,j);
               //FIXME 
//               if(strTemp.length()>1 && strTemp.contains( s.charAt(j+1))){
//
//               }
           }
        }
        return 0;
    }

    @Test
    public void test() {
        String str = "update|insert|select";

        String[] dd = str.split("\\|");
        System.out.println(dd.length);
    }
}

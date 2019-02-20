package leetCode;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 */
public class Num5 {
    /**
     * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
     * <p>
     * Example 1:
     * Input: "babad"
     * Output: "bab"
     * Note: "aba" is also a valid answer.
     * <p>
     * Example 2:
     * Input: "cbbd"
     * Output: "bb"
     */
    @Test
    public void test1() {
        String str = "abababaaaaaa";
        System.out.println(getPalindromicString(str));
    }


    public String getPalindromicString(String resource) {
        String resultStr = "";
        //i 为开始的坐标
        for (int i = 0; i < resource.length() - 2; i++) {
            //j为回文的长度
            for (int j = 1; j <= getRemainder(resource.length() - i, j); j++) {
                System.out.println("i=" + i + "      j=" + j);
                if (isPalindromic(resource, i, j) && (2 * j + 1) > resultStr.length()) {
                    resultStr = resource.substring(i, i + 2 * j + 1);
                }
            }
        }
        if (resultStr.length() == 0) {
            return "";
        }
        return resultStr;
    }

    /**
     * for example: abcdcba
     * length=3
     * begin=0
     * check whether the String is a palindromic substring
     *
     * @param source
     * @param begin
     * @param length
     * @return
     */
    public Boolean isPalindromic(String source, int begin, int length) {
        //统计回文字符串相等的次数
        int num = length;
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            //如果当前字符串和中间的字符串相等，则跳出循环
            if (source.charAt(begin + i) == source.charAt(begin +length)) {
                break;
            }
            if (i == 0 && source.charAt(begin + i) == source.charAt(begin + 2 * length - i)) {
                list.add(source.charAt(begin + i));
                num--;
                continue;
            }
            if (source.charAt(begin + i) == source.charAt(begin + 2 * length - i)) {
                if (list.contains(source.charAt(begin + i))) {
                    break;
                } else {
                    list.add(source.charAt(begin + i));
                    num--;
                }
            }
        }
        if (num == 0) {
            return true;
        }
        return false;
    }

    /**
     * 如果为偶数，则取余数-1，如果为奇数，则取余数
     *
     * @param strLength
     * @param begin
     * @return
     */
    public int getRemainder(int strLength, int begin) {
        //结果为偶数
        if ((strLength - begin) % 2 == 0) {
            return (strLength - begin) / 2 - 1;
        }
        return (strLength - begin) / 2;
    }


    @Test
    public void testGetPalindromicString() {
        int length = 10;
        int begin = 4;
        System.out.println(getRemainder(length, begin));
    }

    @Test
    public void testisPalindromic() {
        String str = "abcdcba";
        Boolean b = isPalindromic(str, 0, 3);
        System.out.println(b);
    }
}

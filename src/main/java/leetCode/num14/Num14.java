/**
 * Copyright  2019-2029 联通集团财务有限公司版权所有。
 */
package leetCode.num14;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 * <p>
 * Input: ["flower","flow","flight"]
 * Output: "fl"
 * <p>
 * Input: ["dog","racecar","car"]
 * Output: ""
 *
 * @author: suxiaolei
 * @date: 2019/9/10
 */
public class Num14 {
    public String longestCommonPrefix(String[] strs) {
        int len=Integer.MAX_VALUE;
        //获取字符串数组的长度
        for(int i = 0; i < strs.length; i++){
            if(strs[i].length()<len){
                len=strs[i].length();
            }
        }
        //对于单个数组，特殊处理
        if(strs.length==1){
            return strs[0];
        }


        String str = "";
        int total=0;
        for (int i = 0; i <= len; i++) {
            String strTemp="";
            int maxNum = 0;
            Map<String, Integer> map = new HashMap<>();
            for (int j = 0; j < strs.length; j++) {
                map.put(strs[j].substring(0, i), map.get(strs[j].substring(0, i)) == null ? 0 : map.get(strs[j].substring(0, i)) + 1);
                if (map.get(strs[j].substring(0, i)) > maxNum) {
                    maxNum = map.get(strs[j].substring(0, i));
                    strTemp = strs[j].substring(0, i);
                }
                if (maxNum>0&&maxNum >= total&&str.length()<=strTemp.length() ) {
                    str = strs[j].substring(0, i);
                    total=maxNum;
                }
            }
            if (maxNum == 0) {
                break;
            }

        }
        return str;
    }

    @Test

    public void dd() {
        Map<String, Integer> map = new HashMap<>();
        System.out.println(map.get("ssss"));
        String[] str = {"flows","flsss","fltt"};
        String ss=longestCommonPrefix1(str);
        System.out.println(ss);

    }
    public String longestCommonPrefix1(String[] strs) {
        if (strs.length == 0) return "";
        String pre = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(pre) != 0) {
                pre = pre.substring(0, pre.length()-1);
            }
        }
        return pre;
    }

}

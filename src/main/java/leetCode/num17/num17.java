/**
 * Copyright  2019-2029 联通集团财务有限公司版权所有。
 */
package leetCode.num17;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: suxiaolei
 * @date: 2019/9/10
 */
public class num17 {

    public List<String> letterCombinations(String digits) {
        List list = new ArrayList();
        if ("".equals(digits) || digits == null) {
            return null;
        }
        if (digits.length() == 1) {

            for (int i = 0; i < getStrArr(digits).length; i++) {
                list.add(getStrArr(digits)[i]);
            }
            return list;
        }
        String str1 = digits.substring(0, 1);
        String[] arr1 = getStrArr(str1);
        String str2 = digits.substring(1, 2);
        String[] arr2 = getStrArr(str2);

        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr2.length; j++) {
                list.add(arr1[i] + arr2[j]);
            }
        }
        return list;
    }

    public String[] getStrArr(String str) {
        if ("2".equals(str)) {
            return new String[]{"a", "b", "c"};
        }
        if ("3".equals(str)) {
            return new String[]{"d", "e", "f"};
        }
        if ("4".equals(str)) {
            return new String[]{"g", "h", "i"};
        }
        if ("5".equals(str)) {
            return new String[]{"j", "k", "l"};
        }
        if ("6".equals(str)) {
            return new String[]{"m", "n", "o"};
        }
        if ("7".equals(str)) {
            return new String[]{"p", "q", "r", "s"};
        }
        if ("8".equals(str)) {
            return new String[]{"t", "u", "v"};
        }
        if ("9".equals(str)) {
            return new String[]{"w", "x", "y", "z"};
        }
        return null;

    }
}

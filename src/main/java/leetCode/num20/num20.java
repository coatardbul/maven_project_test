
package leetCode.num20;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * <p>
 * An input string is valid if:
 * <p>
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 *
 * @author: suxiaolei
 * @date: 2019/9/11
 */
public class num20 {
    List<String> right = new ArrayList<>();

    public boolean isValid(String s) {
        Map<String, String> map = new HashMap<>();
        map.put("}", "{");
        map.put("]", "[");
        map.put(")", "(");
        if (s.length() % 2 == 1) {
            return false;
        }
        int countMatch = 0;
        for (int i = 1; i < s.length(); i++) {
            if (map.containsKey(s.substring(i, i + 1)) && s.substring(i - 1 - 2 * countMatch, i - 2 * countMatch).equals(map.get(s.substring(i, i + 1)))) {
                countMatch++;
            } else {
                return false;
            }
        }
        return true;
    }

    @Test
    public void te() {

        System.out.println("系统初始化的关键字不能删除或停用".contains("不能"));
    }

    public Boolean backTrace(String str, Map<String, String> map) {
        if (str.length() == 0 || str == null) {
            return false;
        }
        if (str.length() % 2 == 1) {
            return false;
        } else {
            if (str.length() == 2 && str.substring(0, 1).equals(map.get(str.substring(1, 2)))) {
                return true;
            } else {
                for (int i = 0; i < str.length(); i++) {
                    //后面的符号与前面的符号是否对应
                    if (map.containsKey(str.substring(i, i + 1)) && i > 1) {
                        //对应
                        if (str.substring(i - 1, i).equals(map.get(str.substring(i, i + 1)))) {
                            return backTrace(str.substring(0, i - 1) + str.substring(i + 1 >= str.length() ? str.length() : i + 1, str.length()), map);
                        } else {
                            return false;
                        }
                    }
                }

            }
            return false;
        }
    }
}

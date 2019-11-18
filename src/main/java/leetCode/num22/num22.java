
package leetCode.num22;

import java.util.List;

/**
 * @author: suxiaolei
 * @date: 2019/9/11
 */
public class num22 {

    public List<String> generateParenthesis(int n) {
        return null;
    }

    public Boolean isMatch(String str, int n) {
        int leftCount = 0;
        int rightCount = 0;
        for (int i = 0; i < str.length(); i++) {
            if ("(".equals(str.substring(i, i + 1))) {
                leftCount++;
            }
            if (")".equals(str.substring(i, i + 1))) {
                rightCount++;
            }
        }
        if (leftCount == n && rightCount == n) {
            return true;
        } else {
            return false;
        }
    }

    public List<String> getList(int n) {
        return null;
    }

    public String getss(String str, int n) {
        for (int i = 0; i < n; i++) {

        }
        return null;
    }
}

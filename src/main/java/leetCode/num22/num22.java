
package leetCode.num22;

import java.util.List;

/**
 *Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
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

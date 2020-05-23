
package leetCode.num22;

import java.util.List;

/**
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 * <p>
 * For example, given n = 3, a solution set is:
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 */
public class num122 {
    public final static String LEFT_PARENTHESES_STRING = "(";
    public final static String RIGHT_PARENTHESES_STRING = ")";

    public List<String> generateParenthesis(int n) {
//        NodeString ns = new NodeString();
//        for (int i = 1; i < 2 * n; i++) {
//           if(ns.sb.toString().c){}
//        }

        return null;
    }

    static class NodeString {
        public StringBuffer sb;
        public int leftCount;
        public int rightCount;
        public NodeString leftParentheses;
        public NodeString rightParentheses;

        public NodeString() {
            sb = new StringBuffer(LEFT_PARENTHESES_STRING);
            leftCount=1;
            rightCount=0;
        }
    }

}


package leetCode.num22;

import java.util.ArrayList;
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
public class num22 {

    public List<String> generateParenthesis(int n) {
        List<String>  list=new ArrayList<>();
        setList(list,new StringBuffer("("),1,0,n);
        return list;
    }

    public void setList(List<String>  list,StringBuffer sb, int leftNum, int rightNum,int n) {
        if(leftNum==n&&rightNum==n){
            list.add(sb.toString());
            return;
        }
        if(leftNum<n){
            setList(list,sb.append("("),leftNum+1,rightNum,n);
            sb.deleteCharAt(sb.length() - 1);

        }
        if(rightNum<leftNum){
            setList(list,sb.append(")"),leftNum,rightNum+1,n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {

        num22 d=new num22();
        System.out.println(d.generateParenthesis(4));
    }

}

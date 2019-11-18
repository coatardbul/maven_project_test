
package leetCode.num17;

import java.util.List;

/**
 * @author: suxiaolei
 * @date: 2019/9/10
 */
public class num17 {

    public List<String> letterCombinations(String digits) {
        return null;
    }

    public void backTrack() {

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

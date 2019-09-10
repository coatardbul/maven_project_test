package leetCode.num12;

import org.junit.Test;

/**
 * Integer to Roman
 * Medium
 * <p>
 * 633
 * <p>
 * 1985
 * <p>
 * Favorite
 * <p>
 * Share
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * <p>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, two is written as II in Roman numeral,
 * just two one's added together.
 * Twelve is written as, XII, which is simply X + II.
 * The number twenty seven is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right. However,
 * the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four.
 * The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * Example 1:
 * <p>
 * Input: 3
 * Output: "III"
 * Example 2:
 * <p>
 * Input: 4
 * Output: "IV"
 * Example 3:
 * <p>
 * Input: 9
 * Output: "IX"
 * Example 4:
 * <p>
 * Input: 58
 * Output: "LVIII"
 * Explanation: L = 50, V = 5, III = 3.
 * Example 5:
 * <p>
 * Input: 1994
 * Output: "MCMXCIV"
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 */
public class Num12 {
    @Test
    public void mafsdfdsin() {
        String str = intToRoman(58);
        System.out.println(str);
    }

    /**
     * Input is guaranteed to be within the range from 1 to 3999.
     *
     * @param numStr
     * @return
     */
    public String intToRoman(int num) {
        String numStr = String.valueOf(num);
        String str = "";
        for (int i = 0; i < numStr.length(); i++) {
            str += getsss(Integer.valueOf(numStr.substring(i, i + 1)).intValue(), numStr.length() - 1 - i);
        }
        return str;
    }

    /**
     * 根据num返回对应的Roman定义的值
     *
     * @param num
     * @return
     */
    public String getStringByNumber(int num) {
        Roman[] values = Roman.values();
        for (int i = 0; i < values.length; i++) {
            Roman value = values[i];
            if (num == values[i].getValue()) {
                return values[i].getCode();
            }
        }
        return null;
    }

    /**
     * 对应位置上的数字，count 在那个位置
     *
     * @param num
     * @param count
     * @return
     */
    public String getsss(int num, int count) {
        String str = "";
        if (num < Roman.FIVE.getValue() - Roman.ONE.getValue()) {
            for (int i = 0; i < num; i++) {
                str += getStringByNumber(gettt(count));
            }
        } else if (num == Roman.FIVE.getValue() - Roman.ONE.getValue()) {
            str += getStringByNumber(Roman.ONE.getValue() * gettt(count)) + getStringByNumber(Roman.FIVE.getValue() * gettt(count));
        } else if (num == Roman.TEN.getValue() - Roman.ONE.getValue()) {
            str += getStringByNumber(gettt(count)) + getStringByNumber(gettt(count + 1));
        } else if (num > Roman.FIVE.getValue()) {
            str += getStringByNumber(Roman.FIVE.getValue() * gettt(count));
            for (int i = 0; i < num - Roman.FIVE.getValue(); i++) {
                str += getStringByNumber(gettt(count));
            }
        } else {
            str += getStringByNumber(num * gettt(count));
        }
        return str;
    }

    /**
     * 返回10的倍数
     *
     * @param count
     * @return
     */
    public int gettt(int count) {
        int num = 1;
        for (int i = 0; i < count; i++) {
            num *= 10;
        }
        return num;
    }


}

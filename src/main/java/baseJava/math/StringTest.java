package baseJava.math;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

/**
 * <p>
 * Note:
 * <p>
 * Date: 2021/8/20
 *
 * @author Su Xiaolei
 */
public class StringTest {
    public static void main(String[] args) {

        String str="AB23C123";
        BigDecimal result = convert(str);
        System.out.println(result);
    }


    public static BigDecimal  convert(String str){
        if(StringUtils.isNotBlank(str)){
            StringBuffer stringBuffer=new StringBuffer();
            for(int i=0;i<str.length();i++){
                char c = str.charAt(i);
                if(contail(c)){
                    stringBuffer.append(c);
                }
            }
            return new BigDecimal(stringBuffer.toString());
        }else {
            return null;
        }

    }
    /**
     * 是否包含数字，小数
     * @param str
     * @return
     */
    public static boolean contail(char str){
        char[] s=new char[]{'1','2','3','4','5','6','7','8','9','0','.','-'};
        for(int i=0;i<s.length;i++){
            if(str==s[i]){
                return true;
            }
        }
        return false;
    }
}

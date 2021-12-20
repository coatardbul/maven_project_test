package baseJava.string;


import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Calendar;

public class StringMatch {
    @Test
    public void match() {
        String str = "你好少年.xml";
        System.out.println(str.substring(0,1));

        try {
            str.getBytes("GBK");

            byte[] gbks = Arrays.copyOfRange(str.getBytes("GBK"), str.getBytes("GBK").length-9, str.getBytes("GBK").length);
            System.out.println(new String(gbks,"GBK"));


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        // UTF-8编码的byte流强行用iso-8859-1解码，毫无疑问的乱码了
        String str1 = convertEncodingFormat(str, "UTF-8", "iso-8859-1");
        System.out.println(str1);

        // 将str1再转化为byte流,重新用UTF-8解码，乱码问题解决
        String str2 = convertEncodingFormat(str1, "iso-8859-1", "UTF-8");
        System.out.println(str2);


    }


    public String convertEncodingFormat(String str, String formatFrom, String FormatTo) {
        String result = null;
        if (!(str == null || str.length() == 0)) {
            try {
                result = new String(str.getBytes(formatFrom), FormatTo);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

}
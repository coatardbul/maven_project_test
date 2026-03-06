package robot;

import util.DateTimeUtil;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.util.Date;

/**
 * <p>
 * Note:
 * <p>
 * Date: 2024/3/4
 *
 * @author Su Xiaolei
 */
public class test {

    private static SecureRandom random = new SecureRandom();

    public static void main(String[] args) throws ParseException {
//        String dateStr = DateTimeUtil.getDateFormat(new Date(), DateTimeUtil.YYYY_MM_DD);
        String dateStr= "2024-03-06";
        String beginStr = dateStr + " 00:00:00";
        String endStr = dateStr + " 23:59:59";
        long l = DateTimeUtil.convertDateToTimestamp(beginStr) / 1000;
        long l1 = DateTimeUtil.convertDateToTimestamp(endStr) / 1000;
        System.out.println(l);
        System.out.println(l1);

    }

    private static String generateRandomHexToken(int byteLength) {
        byte[] token = new byte[byteLength];
        random.nextBytes(token);
        return new BigInteger(1, token).toString(16); // 转换为十六进制字符串
    }
}

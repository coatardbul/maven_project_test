package robot;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class HmacSha1Signature {
    public static void main(String[] args) {
        try {
            // 待签名的字符串
            String stringToSign = "StringToSign";

            // 使用的密钥（这里需要替换为你的密钥）
            String secretKey = "your_secret_key";

            // 使用 HMAC-SHA1 算法
            Mac mac = Mac.getInstance("HmacSHA1");

            // 使用指定的密钥初始化 Mac 对象
            SecretKeySpec secret = new SecretKeySpec(secretKey.getBytes(), "HmacSHA1");
            mac.init(secret);

            // 执行签名
            byte[] hmacData = mac.doFinal(stringToSign.getBytes());

            // 将结果转换为 Base64 编码的字符串
            String result = Base64.getEncoder().encodeToString(hmacData);

            // 打印结果
            System.out.println("HMAC-SHA1: " + result);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}

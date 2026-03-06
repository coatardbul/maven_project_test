package pic;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Example {
    public static void main(String[] args) {
        String originalString = "Hello, World!";
        System.out.println("原始字符串: " + originalString);
        System.out.println("MD5加密后: " + getMD5EncryptedString(originalString));
    }

    public static String getMD5EncryptedString(String input) {
        final byte[] defaultBytes = input.getBytes();
        try {
            final MessageDigest md5MsgDigest = MessageDigest.getInstance("MD5");
            md5MsgDigest.reset();
            md5MsgDigest.update(defaultBytes);
            final byte messageDigest[] = md5MsgDigest.digest();

            StringBuilder hexString = new StringBuilder();
            for (final byte element : messageDigest) {
                String hex = Integer.toHexString(0xFF & element);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}

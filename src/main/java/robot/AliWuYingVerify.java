package robot;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SimpleTimeZone;
import java.util.UUID;

/**
 * <p>
 * Note:
 * <p>
 * Date: 2023/12/28
 *
 * @author Su Xiaolei
 */
public class AliWuYingVerify {
    private static final String ENCODING = "UTF-8";

    private static String percentEncode(String value) throws UnsupportedEncodingException {
        return value != null ? URLEncoder.encode(value, ENCODING).replace("+", "%2 0").replace("*", "%2A").replace("%7E", "~") : null;
    }

    private static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

    private static String formatIso8601Date(Date date) {

        SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);

        df.setTimeZone(new SimpleTimeZone(0, "GMT"));

        return df.format(date);
    }


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException {

        String appkey="f2603712bf449b67";
        String appsecret="47c20afe-963b-4103-95c9-db001e5f2ed9";

        final String HTTP_METHOD = "GET";
        Map<String, String> parameters = new HashMap();
        parameters.put("Version", "20200430");
        parameters.put("AccessKeyId",appkey);
        parameters.put("Timestamp", formatIso8601Date(new Date()));
        parameters.put("SignatureMethod", "HMAC-SHA1");
        parameters.put("SignatureVersion", "1.0");
        parameters.put("SignatureNonce", UUID.randomUUID().toString());
        parameters.put("Format", "json");


        String[] sortedKeys = parameters.keySet().toArray(new String[]{});
        Arrays.sort(sortedKeys);
        final String SEPARATOR = "&";
        StringBuilder stringToSign = new StringBuilder();
        stringToSign.append(HTTP_METHOD).append(SEPARATOR);
        stringToSign.append(percentEncode("/")).append(SEPARATOR);
        StringBuilder canonicalizedQueryString = new StringBuilder();

        for (String key : sortedKeys) {
            canonicalizedQueryString.append("&").append(percentEncode(key)).append("=").append(percentEncode(parameters.get(key)));
        }
        stringToSign.append(percentEncode(canonicalizedQueryString.toString().substring(1)));

        System.out.println("stringToSign原始值:"+stringToSign.toString());
        // 以下是一段计算签名的示例代码
        final String ALGORITHM = "HmacSHA1";
        final String ENCODING = "UTF-8";
        String  key = appsecret+"&";
        Mac mac = Mac.getInstance(ALGORITHM);
        mac.init(new SecretKeySpec(key.getBytes(ENCODING), ALGORITHM));
        byte[] signData = mac.doFinal(stringToSign.toString().getBytes(ENCODING));
        String signature = new String(Base64.encodeBase64(signData));
        parameters.put("Signature", signature);
        System.out.println("signature:"+signature);
        parameters.put("Signature", signature);


        StringBuffer url=new StringBuffer("https://console-rpa.aliyun.com/rpa/openapi/raas/resource/ListRobots");

        int paramNum=0;
        if(parameters.size()>0){
            url.append("?");
            for(Map.Entry<String, String> map: parameters.entrySet()){
                if(paramNum==0){
                    url.append(map.getKey()+"="+URLEncoder.encode(map.getValue(), StandardCharsets.UTF_8.toString()));
                }else {
                    url.append("&"+map.getKey()+"="+URLEncoder.encode(map.getValue(), StandardCharsets.UTF_8.toString()));
                }
                paramNum++;
            }
        }

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url.toString()); // 替换为你的目标 URL
        CloseableHttpResponse response = httpClient.execute(request);
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            //返回json格式
            String res = EntityUtils.toString(response.getEntity());

            System.out.println(res);
        }


    }


}

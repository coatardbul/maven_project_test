//package robot;
//
//import org.apache.commons.codec.binary.Base64;
//import org.apache.http.HttpStatus;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.message.BasicNameValuePair;
//import org.apache.http.util.EntityUtils;
//
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.BufferedReader;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.SimpleTimeZone;
//import java.util.UUID;
//
///**
// * <p>
// * Note:
// * <p>
// * Date: 2023/12/28
// *
// * @author Su Xiaolei
// */
//public class AliPostVerify {
//
//    private static final String ENCODING = "UTF-8";
//
//    private static String percentEncode(String value) throws UnsupportedEncodingException {
//        return value != null ? URLEncoder.encode(value, ENCODING).replace("+", "%20").replace("*", "%2A").replace("%7E", "~") : null;
//    }
//
//    private static final String ISO8601_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
//
//    private static String formatIso8601Date(Date date) {
//        SimpleDateFormat df = new SimpleDateFormat(ISO8601_DATE_FORMAT);
//        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
//        return df.format(date);
//    }
//
//    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
//        String appkey = "f2603712bf449b67";
//        String appsecret = "47c20afe-963b-4103-95c9-db001e5f2ed9";
//
//
//        final String HTTP_METHOD = "POST";
//        Map<String, String> parameters = new HashMap();
//// 输入公共请求参数
//        parameters.put("Version", "20200430");
//        parameters.put("AccessKeyId", appkey);
//        parameters.put("Timestamp", formatIso8601Date(new Date()));
//        parameters.put("SignatureMethod", "HMAC-SHA1");
//        parameters.put("SignatureVersion", "1.0");
//        parameters.put("SignatureNonce", UUID.randomUUID().toString());
//        parameters.put("Format", "json");
//
//
//// 排序请求参数
//        String[] sortedKeys = parameters.keySet().toArray(new String[]{});
//        Arrays.sort(sortedKeys);
//        final String SEPARATOR = "&";
//// 构造 stringToSign 字符串
//        StringBuilder stringToSign = new StringBuilder();
//        stringToSign.append(HTTP_METHOD).append(SEPARATOR);
//        stringToSign.append(percentEncode("/")).append(SEPARATOR);
//        StringBuilder canonicalizedQueryString = new StringBuilder();
//        for (String key : sortedKeys) {
//// 这里注意编码 key 和 value
//            canonicalizedQueryString.append("&")
//                    .append(percentEncode(key)).append("=")
//                    .append(percentEncode(parameters.get(key)));
//        }
//// 这里注意编码 canonicalizedQueryString
//        stringToSign.append(percentEncode(
//                canonicalizedQueryString.toString().substring(1)));
//
//
//        // 以下是一段计算签名的示例代码
//        final String ALGORITHM = "HmacSHA1";
//        final String ENCODING = "UTF-8";
//       String  key = appsecret+"&";
//        Mac mac = Mac.getInstance(ALGORITHM);
//        mac.init(new SecretKeySpec(key.getBytes(ENCODING), ALGORITHM));
//        byte[] signData = mac.doFinal(stringToSign.toString().getBytes(ENCODING));
//        String signature = new String(Base64.encodeBase64(signData));
//        parameters.put("Signature", signature);
//
//        StringBuffer url = new StringBuffer("https://console-rpa.aliyun.com/rpa/openapi/app/approve");
//
//        int paramNum = 0;
//        if (parameters.size() > 0) {
//            url.append("?");
//            for (Map.Entry<String, String> map : parameters.entrySet()) {
//                if (paramNum == 0) {
//                    url.append(map.getKey() + "=" + URLEncoder.encode(map.getValue(), StandardCharsets.UTF_8.toString()));
//                } else {
//                    url.append("&" + map.getKey() + "=" + URLEncoder.encode(map.getValue(), StandardCharsets.UTF_8.toString()));
//                }
//                paramNum++;
//            }
//        }
//
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        HttpPost httpPost = new HttpPost(url.toString()); // 替换为目标 URL
//
//
//        StringEntity requestEntity = new StringEntity(
//                "appId=9e7ca1f2-aed0-405c-b5e6-93821d07b89c",
//                "UTF-8");
//        httpPost.setEntity(requestEntity);
//
//        CloseableHttpResponse response = httpClient.execute(httpPost);
//        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//            //返回json格式
//            String res = EntityUtils.toString(response.getEntity(),"UTF-8");
//
//            System.out.println(res);
//        }
//    }
//
//
//    private static String restfulReq(String url, String method) {
//        Map<String, String> params = new HashMap<>();
//        String result = "";
//        try {
//            Map<String, String> header = new HashMap<>();
//            header.put("contentType", "application/json;charset=UTF-8");
//            if ("POST".equals(method)) {
//                result = doPost(url, header, joinParams(params), method);
//            }
//            if ("GET".equals(method)) {
//                result = doGet(url, header, params, method);
//            }
//        } catch (Exception err) {
//            throw new RuntimeException(err.toString());
//        }
//        return result;
//    }
//
//    /**
//     * 向指定URL发送POST请求
//     */
//    private static String doPost(String url, Map<String, String> header, String param, String method) {
//        DataOutputStream out = null;
//        BufferedReader in = null;
//        String result = "";
//        try {
//            URL realUrl = new URL(url);
//            // 打开和URL之间的连接
//            HttpURLConnection conn = (HttpURLConnection) realUrl
//                    .openConnection();
//            // 设置通用的请求属性
//            conn.setRequestProperty("accept", "*/*");
//            conn.setRequestMethod(method);
//
//            Iterator<Map.Entry<String, String>> it = header.entrySet().iterator();
//            while (it.hasNext()) {
//                Map.Entry<String, String> entry = it.next();
//                conn.setRequestProperty(entry.getKey(), entry.getValue());
//            }
//            conn.setUseCaches(false);
//            // 发送POST请求必须设置如下两行
//            conn.setDoOutput(true);
//            conn.setDoInput(true);
//            conn.setReadTimeout(6000);
//            conn.setConnectTimeout(6000);
//            // 定义BufferedReader输入流来读取URL的响应
//            try {
//                if (param != null && !"".equals(param.trim())) {
//                    out = new DataOutputStream(conn.getOutputStream());
//                    out.write(param.getBytes());
//                    // flush输出流的缓冲
//                    out.flush();
//                }
//                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                String line;
//                while ((line = in.readLine()) != null) {
//                    result += line;
//                }
//            } catch (Exception e) {
//                System.out.println("异常" + e);
//            }
//        } catch (Exception e) {
//            System.out.println("异常" + e);
//        }
//        // 使用finally块来关闭输出流和输入流
//        finally {
//            try {
//                if (out != null) {
//                    out.close();
//                }
//                if (in != null) {
//                    in.close();
//                }
//            } catch (IOException ex) {
//                System.out.println("异常--" + ex.getMessage());
//            }
//        }
//        return result;
//    }
//
//
//    private static String joinParams(Map<String, String> params) {
//        if (params != null) {
//            String result = "";
//            StringBuilder sb = new StringBuilder();
//            Iterator<Map.Entry<String, String>> newEntryIt = params.entrySet().iterator();
//            while (newEntryIt.hasNext()) {
//                Map.Entry<String, String> entry = newEntryIt.next();
//                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
//            }
//            if (sb.toString().endsWith("&")) {
//                result = sb.substring(0, sb.length() - 1);
//            }
//            return result;
//        }
//        return null;
//    }
//}

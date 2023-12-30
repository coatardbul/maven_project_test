package com.company;

import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    /**
     * 服务器地址（以RPA公有云集群为例）
     */
    public static final String SERVER = "https://console-rpa.aliyun.com";

    /**
     * 调用接口路径, 以createServiceTask为例
     */
    public static final String PATH = "/rpa/openapi/app/applyApp?";

    /**
     * AKSK
     */
    public static final String ACCESS_KEY_ID = "f2603712bf449b67";
    public static final String ACCESS_KEY_SECRET ="47c20afe-963b-4103-95c9-db001e5f2ed9";


    public static void main(String[] args) {
        // 接口专有参数
        String httpMethod = "POST";
        String appId = "834733e5-ce55-4f7c-b88b-7791e1c9b7e6";
        String clientId = "机器人id";
        String appParams = "[{\"name\":\"示例参数1\",\"value\":\"示例内容1\"},{\"name\":\"示例参数2\",\"value\":\"示例内容2\"}]";
        Map<String, String> specialParams = new HashMap<>();
        specialParams.put("appId", appId);
//        specialParams.put("clientId", clientId);
//        specialParams.put("appParams", appParams);

        // 调用示例
        try {
            Map<String, String> parameters = new HashMap();
            // 公共请求参数
            String signatureNonce = UUID.randomUUID().toString();
            String timeStamp = formatIso8601Date(new Date());
            parameters.put("Version", "20200430");
            parameters.put("AccessKeyId", ACCESS_KEY_ID);
            parameters.put("Timestamp", timeStamp);
            parameters.put("SignatureMethod", "HMAC-SHA1");
            parameters.put("SignatureVersion", "1.0");
            parameters.put("SignatureNonce", signatureNonce);
            parameters.put("Format", "json");
            parameters.putAll(specialParams);

            // 排序请求参数
            String[] sortedKeys = parameters.keySet().toArray(new String[]{});
            Arrays.sort(sortedKeys);

            // 构造 stringToSign 字符串
            StringBuilder stringToSign = new StringBuilder();

            stringToSign.append(httpMethod).append("&");
            stringToSign.append(percentEncode("/")).append("&");

            // 签名参数
            StringBuilder canonicalizedQueryString = new StringBuilder();

            // url公告参数构建
            StringBuilder urlParamsBuild = new StringBuilder();

            for (String key : sortedKeys) {
                // 这里注意编码 key 和 value
                canonicalizedQueryString.append("&")
                        .append(percentEncode(key)).append("=")
                        .append(percentEncode(parameters.get(key)));
                urlParamsBuild.append("&")
                        .append(percentEncode(key)).append("=")
                        .append(percentEncode(parameters.get(key)));
            }
            stringToSign.append(percentEncode(
                    canonicalizedQueryString.substring(1)));
            // 计算签名
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(new SecretKeySpec((ACCESS_KEY_SECRET + "&").getBytes(StandardCharsets.UTF_8), "HmacSHA1"));
            byte[] signData = mac.doFinal(stringToSign.toString().getBytes(StandardCharsets.UTF_8));
            final BASE64Encoder base64Encoder = new BASE64Encoder();
            String signature = base64Encoder.encode(signData);
            String urlReq = urlParamsBuild.substring(1);
            String url = SERVER + PATH + urlReq + "&Signature=" + percentEncode(signature);
            String requestResult = restfulReq(url, httpMethod);
            System.out.println(requestResult);
        } catch (Exception e) {
            System.out.println("encode失败，异常：" + e);
        }
    }

    /**
     * 发送请求
     */
    private static String restfulReq(String url, String method) {
        Map<String, String> params = new HashMap<>();
        String result = "";
        try {
            Map<String, String> header = new HashMap<>();
            header.put("contentType", "application/json;charset=UTF-8");
            if ("POST".equals(method)) {
                result = doPost(url, header, joinParams(params), method);
            }
            if ("GET".equals(method)) {
                result = doGet(url, header, params, method);
            }
        } catch (Exception err) {
            throw new RuntimeException(err.toString());
        }
        return result;
    }


    private static String joinParams(Map<String, String> params) {
        if (params != null) {
            String result = "";
            StringBuilder sb = new StringBuilder();
            Iterator<Map.Entry<String, String>> newEntryIt = params.entrySet().iterator();
            while (newEntryIt.hasNext()) {
                Map.Entry<String, String> entry = newEntryIt.next();
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            if (sb.toString().endsWith("&")) {
                result = sb.substring(0, sb.length() - 1);
            }
            return result;
        }
        return null;
    }


    /**
     * 向指定URL发送POST请求
     */
    private static String doPost(String url, Map<String, String> header, String param, String method) {
        DataOutputStream out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            HttpURLConnection conn = (HttpURLConnection) realUrl
                    .openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestMethod(method);

            Iterator<Map.Entry<String, String>> it = header.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
            conn.setUseCaches(false);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setReadTimeout(6000);
            conn.setConnectTimeout(6000);
            // 定义BufferedReader输入流来读取URL的响应
            try {
                if (param != null && !"".equals(param.trim())) {
                    out = new DataOutputStream(conn.getOutputStream());
                    out.write(param.getBytes());
                    // flush输出流的缓冲
                    out.flush();
                }
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    result += line;
                }
            } catch (Exception e) {
                System.out.println("异常" + e);
            }
        } catch (Exception e) {
            System.out.println("异常" + e);
        }
        // 使用finally块来关闭输出流和输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                System.out.println("异常--" + ex.getMessage());
            }
        }
        return result;
    }

    /**
     * 向指定URL发送GET请求
     */
    private static String doGet(String urlStr, Map<String, String> header, Map<String, String> params, String method) {
        URL url;
        HttpURLConnection conn = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            // URL传入参数
            String queryString = "";
            if (params != null) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    queryString += entry.getKey()
                            + "="
                            + URLEncoder.encode(entry.getValue().toString(),
                            "UTF-8") + "&";
                }
            }
            if (queryString.length() > 0) {
                queryString = queryString
                        .substring(0, queryString.length() - 1);

                urlStr = urlStr + "?" + queryString;
            }
            url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(6000);
            conn.setConnectTimeout(6000);
            conn.setRequestMethod(method);
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("contentType", "application/json;charset=utf-8");

            Iterator<Map.Entry<String, String>> it = header.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                baos = new ByteArrayOutputStream();
                int len = -1;
                byte[] buf = new byte[128];
                while ((len = is.read(buf)) != -1) {
                    baos.write(buf, 0, len);
                }
                baos.flush();
                return baos.toString();
            } else {
                throw new RuntimeException(conn.getResponseCode() + ":" + conn.getResponseMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException(e.toString());
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                System.out.println("异常" + e);
            }
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                System.out.println("异常" + e);
            }
            conn.disconnect();
        }
    }


    private static String percentEncode(String value) throws UnsupportedEncodingException {
        return value != null ? URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~") : null;
    }


    private static String formatIso8601Date(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return df.format(date);
    }

}

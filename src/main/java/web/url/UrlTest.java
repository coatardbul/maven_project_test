package web.url;

import baseJava.IO.BodyUnit;
import baseJava.IO.ResponseDto;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

public class UrlTest {

    public static void main(String[] args) {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        int num = 3;
        // 参数
        StringBuffer params = new StringBuffer();
        // 字符数据最好encoding以下;这样一来，某些特殊字符才能传过去(如:某人的名字就是“&”,不encoding的话,传不过去)
        params.append("id=" + num);


        // 创建Post请求
        HttpGet httpPost = new HttpGet("http://www.lianhanghao.com/index.php/Index/Ajax" + "?" + params);

        // 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");

        // 响应模型
        CloseableHttpResponse response = null;
        try {
            // 由客户端执行(发送)Post请求
            response = httpClient.execute(httpPost);
            response.setHeader("Content-Type", "text/html; charset=UTF-8");
            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();

            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity, "utf-8"));
                // System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));

                System.out.println(URLDecoder.decode(EntityUtils.toString(responseEntity, "utf-8"), "gb2312"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Test
    public void te() {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            // 创建httpget.
            HttpGet httpget = new HttpGet("http://www.lianhanghao.com/index.php/Index/index/bank/2/province/3/city/37/p/1.html");
            System.out.println("executing request " + httpget.getURI());
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                System.out.println("--------------------------------------");
                // 打印响应状态
                System.out.println(response.getStatusLine());
                if (entity != null) {
                    // 打印响应内容长度
                    System.out.println("Response content length: " + entity.getContentLength());
                    // 打印响应内容
                    // System.out.println("Response content: " + EntityUtils.toString(entity));
                    String str = EntityUtils.toString(entity);
                    System.out.println("Response content: " + str);
                    System.out.println("Response content: " + decodeUnicode("\\u77f3\\u5bb6\\u5e84\\u5e02"));
                    String jsonList = "[{'beanId':'1','beanName':'jack'},{'beanId':'2','beanName':'rose'}]";
                    HtmlParse.getID(str);
                    ObjectMapper mapper = new ObjectMapper();
//                    List<Bean> beanList = mapper.readValue(jsonList, new TypeReference<List<Bean>>() {});
//                    List<Bean> beanList = mapper.readValue(jsonList, new TypeReference<Map<String,Object>>() {});
                    List<ProvinceCItyInfo> responseDto1= JSON.parseObject(str,  new com.alibaba.fastjson.TypeReference<List<ProvinceCItyInfo>>(){});
                    System.out.println(responseDto1);


                    List<ProvinceCItyInfo> beanList = mapper.readValue(str, new TypeReference<List<ProvinceCItyInfo>>() {});
                System.out.println(beanList);
                }
                System.out.println("------------------------------------");
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }
}

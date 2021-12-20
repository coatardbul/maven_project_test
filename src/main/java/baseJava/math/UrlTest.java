package baseJava.math;

import baseJava.math.ThsPriceBO;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        HttpGet httpPost = new HttpGet("http://d.10jqka.com.cn/v6/line/hs_002724/01/all.js?hexin-v=A6rEhVkvvr5fKTLSdVxojvC1_RtPGy5zIJ-iGTRjVv2IZ0SNHKt-hfAv8m8H");

        // 设置ContentType(注:如果只是传普通参数的话,ContentType不一定非要用application/json)
        httpPost.setHeader("Referer", "http://www.iwencai.com/");

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
                String s = EntityUtils.toString(responseEntity, "utf-8");
                System.out.println("响应内容为:" + s);
                // System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));

                ObjectMapper objectMapper = new ObjectMapper();

                int i = s.indexOf("{");
                String tt=s.substring(i,s.length()-1);
                ThsPriceBO thsPriceBO = objectMapper.readValue(tt, ThsPriceBO.class);

System.out.println(1);

//                System.out.println(URLDecoder.decode(EntityUtils.toString(responseEntity, "utf-8"), "gb2312"));

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
            HttpGet httpget = new HttpGet("http://d.10jqka.com.cn/v6/line/hs_002724/01/all.js?hexin-v=A6rEhVkvvr5fKTLSdVxojvC1_RtPGy5zIJ-iGTRjVv2IZ0SNHKt-hfAv8m8H");
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
                    System.out.println("Response content: " + EntityUtils.toString(entity));
                    String str = EntityUtils.toString(entity);
                    System.out.println("Response content: " + str);
                    System.out.println("Response content: " + decodeUnicode("\\u77f3\\u5bb6\\u5e84\\u5e02"));


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


    @Test
    public void te1() throws IOException {
        String str="{\"646\":\"1\",\"402\":\"1804581117.00\",\"407\":\"1527033954.00\",\"527198\":\"9282857.000\",\"10\":\"28.60\",\"24\":\"28.59\",\"25\":\"3600.00\",\"30\":\"28.60\",\"31\":\"19860.00\",\"8\":\"28.86\",\"9\":\"28.29\",\"13\":\"18109706.00\",\"19\":\"516069260.00\",\"7\":\"28.61\",\"15\":\"8738711.00\",\"14\":\"8826849.00\",\"69\":\"34.52\",\"70\":\"23.02\",\"223\":\"21616639.00\",\"224\":\"58448995.00\",\"225\":\"37375507.00\",\"226\":\"50994019.00\",\"237\":\"91092384.00\",\"238\":\"91240083.00\",\"259\":\"76266240.00\",\"260\":\"89035391.00\",\"38\":\"-1\",\"37\":\"-1\",\"39\":\"-1\",\"23\":\"\",\"22\":\"\",\"90\":\"\",\"92\":\"\",\"254\":\"\",\"278\":\"\",\"49\":\"238740.00\",\"271\":\"-1\",\"51\":\"\",\"276\":\"\",\"277\":\"\",\"12\":\"1\",\"17\":\"71100.00\",\"95\":\"\",\"96\":\"\",\"273\":\"-1\",\"274\":\"\",\"74\":\"0.00\",\"75\":\"0.00\",\"85\":\"\",\"127\":\"\",\"130\":\"22022.00\",\"264648\":\"-0.170\",\"199112\":\"-0.59\",\"2942\":\"17.769\",\"1968584\":\"1.186\",\"2034120\":\"17.769\",\"1378761\":\"28.497\",\"526792\":\"1.981\",\"395720\":\"-81966.000\",\"461256\":\"-62.970\",\"3475914\":\"43673171000.000\",\"3541450\":\"51611020000.000\",\"1771976\":\"0.880\",\"134152\":\"28.642\",\"592920\":\"4.855\",\"3153\":\"24.070\",\"6\":\"28.77\",\"66\":\"\",\"stop\":0,\"time\":\"2021-07-10 08:50:40 \\u5317\\u4eac\\u65f6\\u95f4\",\"name\":\"\\u4e50\\u666e\\u533b\\u7597\",\"marketType\":\"HS_stock_gem\",\"profit\":\"\\u662f\",\"vote_diff\":\"\\u5426\",\"security_type\":\"--\",\"registration\":\"\\u5426\",\"protocol_control_arch\":\"\\u5426\",\"listing_status\":\"--\",\"equalRights\":\"\\u662f\",\"5\":\"300003\",\"stockStatus\":\"\\u95ed\\u5e02\",\"marketid\":\"33\",\"updateTime\":\"2021-07-09 15:30\"}";

        ObjectMapper objectMapper = new ObjectMapper();


        Map thsPriceBO = objectMapper.readValue(str, HashMap.class);

        System.out.println(1);

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

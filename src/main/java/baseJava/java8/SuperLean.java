package baseJava.java8;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import common.entity.Book;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SuperLean {

    private static final String STRATEGY_URL = "http://www.iwencai.com/customized/chart/get-robot-data";

    public static void main(String[] args) throws ScriptException, FileNotFoundException, NoSuchMethodException {

        strategyCommon();
    }

    public static void strategyCommon() throws BusinessException, NoSuchMethodException, ScriptException, FileNotFoundException {
        //获取策略返回
        String response = getStrategyResponseStr();
        if (StringUtils.isNotBlank(response)) {
            //解析返回体
            JSONObject requestObject = null;
            try {
                requestObject = JSONObject.parseObject(response);
            } catch (JSONException e) {
                throw new BusinessException("解析http请求返回的数据异常,返回字符串为：" + response + " 异常信息：" + e.getMessage());
            }
            //基础信息
            JSONArray componentsArray = requestObject.getJSONObject("data").getJSONArray("answer")
                    .getJSONObject(0).getJSONArray("txt").getJSONObject(0)
                    .getJSONObject("content").getJSONArray("components");

            JSONObject baseObject = null;
            if (componentsArray.size() == 1) {
                baseObject = componentsArray.getJSONObject(0).getJSONObject("data");
            } else {
                baseObject = componentsArray.getJSONObject(componentsArray.size() - 1).getJSONObject("data");
            }
            //解析的数据信息
            JSONArray data = baseObject.getJSONArray("datas");

            //总数
            Integer totalNum = baseObject.getJSONObject("meta").getJSONObject("extra").getObject("row_count", Integer.class);

            int index = 1232;
        }
        return;
    }



    private static StrategyQueryBO getDefaultStrategyQuery() {
        StrategyQueryBO result = new StrategyQueryBO();
        result.setSecondary_intent("stock");
        result.setLog_info("{\\\"input_type\\\":\\\"typewrite\\\"}");
        result.setIwcpro(1);
        result.setSource("Ths_iwencai_Xuangu");
        result.setVersion("2.0");
        result.setPerpage(100);
        result.setPage(1);
//        result.setQuery_area();
//        result.setBlock_list();

        result.setAdd_info("");
        return result;
    }
    private static String getStrategyResponseStr() throws BusinessException, NoSuchMethodException, ScriptException, FileNotFoundException {
        //默认信息
        StrategyQueryBO defaultStrategyQuery = getDefaultStrategyQuery();
        //请求dto信息
//        defaultStrategyQuery.setSort_key(dto.getOrderStr());
//        defaultStrategyQuery.setSort_order(dto.getOrderBy());
        defaultStrategyQuery.setQuestion("非创业板，非st板块，非科创板，2022年01月05日涨停，2022年01月06日涨停");
        //http请求
        String jsonString = JsonUtil.toJson(defaultStrategyQuery);

        String result = null;
        int retryNum = 5;
        while (retryNum > 0) {
            try {
                result = doPost(STRATEGY_URL, jsonString);
            } catch (Exception e) {
                retryNum--;
                continue;
            }
            if (StringUtils.isNotBlank(result)) {
                break;
            }
        }
        return result;
    }

    private static String doPost(String  url, String  jsonString) throws IOException {
        List<Header> headerList = new ArrayList<>();
        String cookieValue="other_uid=Ths_iwencai_Xuangu_d0yv0c2qbuwxxr4076fajv816q6j9quy; ta_random_userid=dbwn8c27fu; cid=a7f6ebeb5111466ff7ff4306cb9a85e41658914348; cid=a7f6ebeb5111466ff7ff4306cb9a85e41658914348; ComputerID=a7f6ebeb5111466ff7ff4306cb9a85e41658914348; WafStatus=0; user=MDptb181NTg0MDIwOTg6Ok5vbmU6NTAwOjU2ODQwMjA5ODo1LDEsNDA7NiwxLDQwOzcsMTExMTExMTExMTEwLDQwOzgsMTExMTAxMTEwMDAwMTExMTEwMDEwMDEwMDEwMDAwMDAsNDA7MzMsMDAwMTAwMDAwMDAwLDc5OzM2LDEwMDExMTExMDAwMDExMDAxMDExMTExMSw3OTs0NiwwMDAwMTExMTEwMDAwMDExMTExMTExMTEsNzk7NTEsMTEwMDAwMDAwMDAwMDAwMCw3OTs1OCwwMDAwMDAwMDAwMDAwMDAwMSw3OTs3OCwxLDc5Ozg3LDAwMDAwMDAwMDAwMDAwMDAwMDAxMDAwMCw3OTs0NCwxMSw0MDsxLDEwMSw0MDsyLDEsNDA7MywxLDQwOzEwMiwxLDQwOjI0Ojo6NTU4NDAyMDk4OjE2NzU4OTM4MDk6OjoxNjA4NjI0MTgwOjQwMjk5MTowOjFlYmM1YzMyOTgxMDk3YWY1NWZjMjlmMmMwNTBkZDFlODpkZWZhdWx0XzQ6MQ%3D%3D; userid=558402098; u_name=mo_558402098; escapename=mo_558402098; ticket=36118fd6c391130cd1fa7690ff8fc2f9; user_status=0; utk=57d89fc8e7dc2893e546aff057a8a937; PHPSESSID=b56b60ae919db6e6b85a5bb34affcd0c; v=";
                String heXinStr = "AwEmsKqpAYHLmm21G67BTbhfEEYYLnUgn6IZNGNW_YhnSiuwq36F8C_yKQbw";
        Header cookie = new BasicHeader("Cookie", cookieValue + heXinStr);
        Header hexin = new BasicHeader("hexin-v", heXinStr);
        Header orign =new BasicHeader("Origin", "http://www.iwencai.com");
        headerList.add(cookie);
        headerList.add(hexin);
        headerList.add(orign);

        HttpPost httpPost = new HttpPost(url);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
        httpPost.setEntity(entity);
        if (headerList != null && headerList.size() > 0) {
            List<Header> collect = headerList.stream().filter(item -> item.getName().equals("Content-Type")).collect(Collectors.toList());
            if (collect.size() == 0) {
                httpPost.addHeader("Content-Type", "application/json;charset=utf8");
            }
        }
        if (headerList != null && headerList.size() > 0) {
            for (Header headerTemp : headerList) {
                httpPost.addHeader(headerTemp);
            }
        }
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36");
        Integer sockTimeout=5000;
        RequestConfig defaultRequestConfig=  RequestConfig.custom().setConnectTimeout(sockTimeout).setConnectionRequestTimeout(sockTimeout).setSocketTimeout(sockTimeout).build();

        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(defaultRequestConfig).build();

        CloseableHttpResponse response = httpClient.execute(httpPost);

        //设置状态码
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            //返回json格式
            String res = EntityUtils.toString(response.getEntity());
            return res;
        }else {
            return null;
        }


    }

}

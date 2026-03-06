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
import util.TongHuaShunUtil;

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

    private static final String STRATEGY_NEXT_URL = "http://www.iwencai.com/gateway/urp/v7/landing/getDataList?iwcpro=1";


    private static final String STATUS_SUCCESS = "0";

    private static final String STATUS_CODE = "status_code";

    private static final String STATUS_MSG = "status_msg";


    public static void main(String[] args) throws ScriptException, FileNotFoundException, NoSuchMethodException {

        String queryStr="非创业板，非st板块，非科创板，2022年01月05日未涨停，";

        StrategyBO strategyBO = wenCaiFirstProcess(queryStr);
        if (strategyBO.getTotalNum() > strategyBO.getData().size()) {
            int totalTime = strategyBO.getTotalNum() / 100;
            for (int i = 2; i <= totalTime + 1; i++) {
//                try {
//                    Thread.sleep(new Random().nextInt(2000) + 3000);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }

                StrategyBO strategyTemp = strategyNextProcess(queryStr,i,100);
                strategyBO.getData().addAll(strategyTemp.getData());
            }
        }
        int i=0;
    }

    public static StrategyBO wenCaiFirstProcess(String queryStr) throws BusinessException, NoSuchMethodException, ScriptException, FileNotFoundException {
        //获取策略返回
        StrategyBO result = new StrategyBO();

        String response = getStrategyResponseStr(queryStr);
        if (StringUtils.isNotBlank(response)) {
            //解析返回体
            JSONObject requestObject = null;
            try {
                requestObject = JSONObject.parseObject(response);
            } catch (JSONException e) {
                throw new BusinessException("解析http请求返回的数据异常,返回字符串为：" + response + " 异常信息：" + e.getMessage());
            }
            if (!STATUS_SUCCESS.equals(requestObject.getString(STATUS_CODE))) {
                throw new BusinessException("请求同花顺策略问句异常，" + requestObject.getString(STATUS_MSG));
            }
            //基础信息
            JSONArray componentsArray = requestObject.getJSONObject("data").getJSONArray("answer")
                    .getJSONObject(0).getJSONArray("txt").getJSONObject(0)
                    .getJSONObject("content").getJSONArray("components");

            JSONObject baseObject = null;
            if (componentsArray.size() == 1) {
                baseObject = componentsArray.getJSONObject(0).getJSONObject("data");
            } else {
                baseObject = componentsArray.getJSONObject(componentsArray.size() - 2).getJSONObject("data");
            }
            //解析的数据信息
            JSONArray data = baseObject.getJSONArray("datas");
            if (data == null) {
                System.out.println("data==空");
            }
            //总数
            Integer totalNum = baseObject.getJSONObject("meta").getJSONObject("extra").getObject("row_count", Integer.class);
            System.out.println("策略查询返回数据总数：" + data.size());
            result.setData(data);
            result.setTotalNum(totalNum);
//            log.info("策略查询返回数据总数：" + data.size() + "数据详情" + data.toString());
//            result.setData(data);
//            result.setTotalNum(totalNum);
//            addUpLimitDescribe(result);
        }
        return result;
    }


    public static StrategyBO strategyNextProcess(String queryStr, int page, int pageSize) throws BusinessException, NoSuchMethodException, ScriptException, FileNotFoundException {
        StrategyBO result = new StrategyBO();
        //获取策略返回
        String response = getStrategyNextResponseStr(queryStr,page,pageSize);
        if (StringUtils.isNotBlank(response)) {
            //解析返回体
            JSONObject requestObject = null;
            try {
                requestObject = JSONObject.parseObject(response);
            } catch (JSONException e) {
                throw new BusinessException("解析http请求返回的数据异常,返回字符串为：" + response + " 异常信息：" + e.getMessage());
            }
            if (!STATUS_SUCCESS.equals(requestObject.getString(STATUS_CODE))) {
                throw new BusinessException("请求同花顺策略问句异常，" + requestObject.getString(STATUS_MSG));
            }
            //基础信息
            JSONArray data = requestObject.getJSONObject("answer").getJSONArray("components")
                    .getJSONObject(0).getJSONObject("data").getJSONArray("datas");

            if (data == null) {
                return null;
            }
            //总数
            Integer totalNum = data.size();
            System.out.println("策略查询返回数据总数：" + data.size());
//            log.info("策略查询返回数据总数：" + data.size() + "数据详情" + data.toString());
            result.setData(data);
            result.setTotalNum(totalNum);
//            addUpLimitDescribe(result);
        }
        return result;
    }



    private static String getNextParam(String queryStr, int page, int pageSize) {

        StringBuffer sb = new StringBuffer();
        sb.append("query=").append(queryStr);
//        sb.append("&urp_sort_way=desc");
//        sb.append("&urp_sort_index=最新涨跌幅");
        sb.append("&page=").append(page);
        sb.append("&perpage=").append(pageSize);
        sb.append("&comp_id=6836372");
        sb.append("&uuid=24087");
        return sb.toString();
    }



    private static StrategyQueryBO getDefaultStrategyQuery() {
        StrategyQueryBO result = new StrategyQueryBO();
        result.setSecondary_intent("stock");
        result.setLog_info("{\\\"input_type\\\":\\\"typewrite\\\"}");
        result.setIwcpro(1);
        result.setSource("Ths_iwencai_Xuangu");
        result.setVersion("2.0");
        result.setPerpage("100");
        result.setPage(1);
        result.setRsh("558402098");
//        result.setQuery_area();
//        result.setBlock_list();

        result.setAdd_info("");
        return result;
    }

    private static String getStrategyNextResponseStr(String queryStr, int page, int pageSize) throws BusinessException, NoSuchMethodException, ScriptException, FileNotFoundException {
        //默认信息
        String jsonString = getNextParam(queryStr,page,pageSize);

        List<Header> headerList = new ArrayList<>();

        Header contentType = new BasicHeader("Content-Type", "application/x-www-form-urlencoded");

        headerList.add(contentType);
        String result = null;
        int retryNum = 5;
        while (retryNum > 0) {
            try {
                result = doPost(STRATEGY_NEXT_URL, jsonString,headerList);
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



    private static String getStrategyResponseStr(String queryStr) throws BusinessException, NoSuchMethodException, ScriptException, FileNotFoundException {
        //默认信息
        StrategyQueryBO defaultStrategyQuery = getDefaultStrategyQuery();
        //请求dto信息
//        defaultStrategyQuery.setSort_key(dto.getOrderStr());
//        defaultStrategyQuery.setSort_order(dto.getOrderBy());
        defaultStrategyQuery.setQuestion(queryStr);
        //http请求
        String jsonString = JsonUtil.toJson(defaultStrategyQuery);

        String result = null;
        int retryNum = 5;
        while (retryNum > 0) {
            try {
                result = doPost(STRATEGY_URL, jsonString,null);
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

    private static String doPost(String  url, String  jsonString,List<Header> headerList) throws IOException, ScriptException, NoSuchMethodException {

        if(headerList==null){
            headerList = new ArrayList<>();
        }
        String cookieValue="ta_random_userid=hzs7q37n9r; other_uid=Ths_iwencai_Xuangu_k9tpptcta2isamn98qxfuekrsovvzqol; cid=a30284cdb8195515dc818ecc85afafa51736044758; u_ukey=A10702B8689642C6BE607730E11E6E4A; u_uver=1.0.0; u_dpass=iojQ93ocNWrMNXFf6CKDKYKgBEt5UYZc7tnTn9Va1hFCHo8z65To06w3auBw1d5MHi80LrSsTFH9a%2B6rtRvqGg%3D%3D; u_did=2C82132BE23F452DB9684ADB93F503FE; u_ttype=WEB; wencai_pc_version=0; cid=a30284cdb8195515dc818ecc85afafa51736044758; ComputerID=a30284cdb8195515dc818ecc85afafa51736044758; WafStatus=0; v=";
        String heXinStr = TongHuaShunUtil.getHeXinStr();

//        String heXinStr = "AwEmsKqpAYHLmm21G67BTbhfEEYYLnUgn6IZNGNW_YhnSiuwq36F8C_yKQbw";
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

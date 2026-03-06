package zmkg;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
public class TestController {


    private CloseableHttpClient client= HttpClients.custom().build();

    public List<TickData> getStockTickInfo(String code, String date) {

        //  String thiscode = removePrefix(code,2);
        // 创建 HttpPost 对象，并设置请求地址
        HttpPost httpPost = new HttpPost("http://124.222.217.230:9727/py/stock/getAuctionTickInfo");

        // 设置请求头部信息
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");

        JSONObject jsonObject=new JSONObject();

        jsonObject.put("code","000062");
        jsonObject.put("dateStr","2024-08-30");

        // 准备请求体数据
        String requestBody = JSONUtil.toJsonStr(jsonObject);
        List<TickData> tickDataList = new ArrayList<>();
        try {
            // 设置请求体数据
            httpPost.setEntity(new StringEntity(requestBody));

            // 发送请求并获取响应
            CloseableHttpResponse response = client.execute(httpPost);

            // 获取响应状态码
            int statusCode = response.getStatusLine().getStatusCode();

            new BigDecimal("").intValue();

            // 获取响应体内容
            HttpEntity entity = response.getEntity();
            String responseContent = EntityUtils.toString(entity);

            JSONArray jsonArray = JSON.parseArray(responseContent);


            for (Object object : jsonArray) {
                JSONObject tick = (JSONObject) object;
                String time = tick.get("time").toString();
                String[] parts = time.split(":");

                int hour = Integer.parseInt(parts[0]);
                int minute = Integer.parseInt(parts[1]);

                if (hour < 9 || (hour == 9 && minute < 30)) {
                    if (minute >= 20) {
                        TickData tickData = new TickData();
                        tickData.setVol(tick.get("vol").toString());
                        tickData.setPrice(tick.get("price").toString());
                        tickData.setTime(time);
                        tickDataList.add(tickData);
                    }
                }



            }
            return tickDataList;

        } catch (IOException e) {
            log.error("发生了异常->{}",e.getStackTrace());
        }
        return tickDataList;
    }
    public static void main(String[] args) {
        TestController testController=new TestController();
         testController.getStockTickInfo("SH600491", "2024-06-20");

    }


    public Collection<TickPriceStaticBo>  test3(String code, String dateStr) {



        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("dateStr", dateStr);

        List<TickInfo> tickInfos = new ArrayList<>();


        String responseStr = this.getRSp(code, dateStr);
        if (StringUtils.isNotBlank(responseStr)) {
            tickInfos = convertTickInfo(responseStr);

        }

        if (tickInfos.size() <= 0) {
            return null;
        }
        double maxPrice = this.getMaxPrice(tickInfos);
        double minPrice = this.getMinPrice(tickInfos);
        BigDecimal open = tickInfos.get(0).getPrice();


        BigDecimal close = tickInfos.get(tickInfos.size()-1).getPrice();

        Integer status;
        if (close.compareTo(open) >= 0) {
            status = 1;
        }else {
            status = 2;
        }
        Map<BigDecimal, TickPriceStaticBo> priceMap = new HashMap<>();




        for(TickInfo tickInfo:tickInfos){
            TickPriceStaticBo bo=null;
            if(!priceMap.containsKey(tickInfo.getPrice())){
                priceMap.put(tickInfo.getPrice(),new TickPriceStaticBo(tickInfo.getPrice()));
            }
            bo= priceMap.get(tickInfo.getPrice());
            if(tickInfo.getBuySellFlag().equals("DOWN")){
                TickPriceStaticBo tickPriceStaticBo = priceMap.get(tickInfo.getPrice());
                tickPriceStaticBo.setSellVol(tickPriceStaticBo.getSellVol().add(tickInfo.getVol()));
                tickPriceStaticBo.setSellAmount(tickPriceStaticBo.getSellAmount().add(getAmount(code,tickInfo)));
                //新增返回结果
                tickPriceStaticBo.setStatus(status);
                //最高价
                tickPriceStaticBo.setHigh(new BigDecimal(maxPrice).setScale(2, RoundingMode.HALF_EVEN));
                //最低价
                tickPriceStaticBo.setLow(new BigDecimal(minPrice).setScale(2, RoundingMode.HALF_EVEN));
                //开盘价
                tickPriceStaticBo.setClose(close);
                tickPriceStaticBo.setOpen(open);
            }else {
                TickPriceStaticBo tickPriceStaticBo = priceMap.get(tickInfo.getPrice());
                tickPriceStaticBo.setBuyVol(tickPriceStaticBo.getBuyVol().add(tickInfo.getVol()));
                tickPriceStaticBo.setBuyAmount(tickPriceStaticBo.getBuyAmount().add(getAmount(code,tickInfo)));
                //新增返回结果
                tickPriceStaticBo.setStatus(status);
                //最高价
                tickPriceStaticBo.setHigh(new BigDecimal(maxPrice).setScale(2, RoundingMode.HALF_EVEN));
                //最低价
                tickPriceStaticBo.setLow(new BigDecimal(minPrice).setScale(2, RoundingMode.HALF_EVEN));
                //开盘价
                tickPriceStaticBo.setClose(close);
                tickPriceStaticBo.setOpen(open);
            }
        }


        //   BigDecimal reduce = tickInfos.stream().map(TickInfo::getVol).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal reduce = tickInfos.stream().map(TickInfo::getVol).reduce(BigDecimal.ZERO, BigDecimal::add);

        // 按照值进行排序
        List<Map.Entry<BigDecimal, TickPriceStaticBo>> list = new ArrayList<>(priceMap.entrySet());
        list.sort((entry1, entry2) -> entry2.getKey().compareTo(entry1.getKey()));
        // 将排序后的结果存入 LinkedHashMap 以保持顺序
        Map<BigDecimal, TickPriceStaticBo> sortedMap = new LinkedHashMap<>();

        List<TickPriceStaticBo> result=new ArrayList<>();
        for (Map.Entry<BigDecimal, TickPriceStaticBo> entry : list) {
            TickPriceStaticBo value = entry.getValue();
            value.setVolRate(value.getBuyVol().add(value.getSellVol()).divide(reduce,4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100)));
            sortedMap.put(entry.getKey(), value);
            result.add(value);
        }

        return sortedMap.values();

    }

    /**
     * 顶部，中不，底部,返回结果
     *
     * @param values
     * @return
     */
    private String getResultRemark(Collection<TickPriceStaticBo> values){
        List<TickPriceStaticBo> result = values.stream().collect(Collectors.toList());

        int splitNum=5;
        int batchNum = new BigDecimal(result.size()).divide(new BigDecimal(splitNum), 0, BigDecimal.ROUND_HALF_UP).intValue();
        for(int i=1;i<splitNum;i++){
            for(int j=0;j<batchNum;j++){
                int index = (i - 1) * batchNum + j;
                if(i==1){
                    result.get(index).setRegion("顶部");
                }
                if(i==2){
                    result.get(index).setRegion("上");
                }

                if(i==3){
                    result.get(index).setRegion("中");
                }

                if(i==4){
                    result.get(index).setRegion("下");
                }
            }
        }
        for(int i=batchNum*(splitNum-1);i<result.size();i++){
            result.get(i).setRegion("底部");
        }

        //占比前三
        //对占比进行排序
        List<TickPriceStaticBo> sortVolRateList = values.stream()
                .sorted(Comparator.comparing(TickPriceStaticBo::getVolRate).reversed())
                .collect(Collectors.toList());
        List<TickPriceStaticBo> sortVolRateSubList = sortVolRateList.subList(0, 3);
        //根据区域分组
        Map<String, List<TickPriceStaticBo>> collect = result.stream().collect(Collectors.groupingBy(TickPriceStaticBo::getRegion));

        //区域结果
        Map<String,Integer> regionMap=new HashMap<>();
        collect.forEach((regionStr, list) -> {
            list.forEach(person ->{
                for(int i=0;i<sortVolRateSubList.size();i++){
                    //判断占比是否在区域内
                   if( sortVolRateSubList.get(i).getPrice().compareTo(person.getPrice())==0){
                       if(regionMap.containsKey(regionStr)){
                           regionMap.put(regionStr,regionMap.get(regionStr)+1);
                       }else {
                           regionMap.put(regionStr,1);
                       }
                   }

                }
            });
        });
        StringBuffer sb=new StringBuffer();
        regionMap.forEach((regionStr,num)->{
            sb.append(regionStr+num);
        });
        return sb.toString();
    }
    /**
     *
     * 占比前三验证
     * @return
     */
    private boolean getVolSortVerify(Collection<TickPriceStaticBo> values){
        List<TickPriceStaticBo> result = values.stream().collect(Collectors.toList());
        //对占比进行排序
        List<TickPriceStaticBo> collect = values.stream()
                .sorted(Comparator.comparing(TickPriceStaticBo::getVolRate).reversed())
                .collect(Collectors.toList());
        List<TickPriceStaticBo> tickPriceStaticBos = collect.subList(0, 3);
//        Map<BigDecimal, TickPriceStaticBo> collect1 = tickPriceStaticBos.stream().collect(Collectors.toMap(TickPriceStaticBo::getVolRate, Function.identity()));

        int allNum=0;
        //前三必须有两个占比搞的
        for(int i=0;i<3;i++){
            TickPriceStaticBo tickPriceStaticBo = result.get(i);
            for(int j=0;j<tickPriceStaticBos.size();j++){
                TickPriceStaticBo tickPriceStaticBo1 = tickPriceStaticBos.get(j);
                if(tickPriceStaticBo1.getVolRate().compareTo(tickPriceStaticBo.getVolRate())==0){
                    allNum++;
                }
            }
        }
        if(allNum>=2){
            return true;
        }else {
            return false;
        }
    }



    // 获取List中的price字段的最大值
    public  double getMaxPrice(List<TickInfo> tickInfos) {
        double maxPrice = Double.MIN_VALUE;

        for (TickInfo item : tickInfos) {
            double price =item.getPrice().doubleValue();
            if (price > maxPrice) {
                maxPrice = price;
            }
        }
        return maxPrice;
    }

    // 获取List中的price字段的最小值
    public  double getMinPrice(List<TickInfo> tickInfos) {
        double minPrice = Double.MAX_VALUE;
        for (TickInfo item : tickInfos) {
            double price =item.getPrice().doubleValue();
            if (price < minPrice) {
                minPrice = price;
            }
        }
        return minPrice;
    }

    // 获取List中time字段小于指定时间的记录
    public  BigDecimal  filterByOpen(List<TickInfo> tickInfos, String timeString) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime specifiedTime = LocalTime.parse(timeString, formatter);
        BigDecimal open = null;
        for (TickInfo item : tickInfos) {
            try {
                LocalTime time = LocalTime.parse((String) item.getTime(), formatter);
                if (time.isBefore(specifiedTime)) {
                    open = item.getPrice();
                }
            } catch (DateTimeException dateTimeException) {

            }

        }

        return open;
    }

    // 获取List中time字段大于指定时间的记录
    public  BigDecimal  filterByClose(List<TickInfo> tickInfos, String timeString) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalTime specifiedTime = LocalTime.parse(timeString, formatter);
        BigDecimal close = null;
        for (TickInfo item : tickInfos) {
            try {
                LocalTime time = LocalTime.parse((String) item.getTime(), formatter);
                if (time.isAfter(specifiedTime)) {
                    close=  item.getPrice();
                }
            } catch (Exception e) {

            }

        }

        return close;
    }

    @SneakyThrows
    public String getRSp(String code, String date) {

        String thiscode = removePrefix(code,2);
        // 创建 HttpPost 对象，并设置请求地址
        HttpPost httpPost = new HttpPost("http://124.222.217.230:9727/py/stock/getTickInfo");

        // 设置请求头部信息
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setHeader("Accept", "application/json");

        JSONObject jsonObject=new JSONObject();
        jsonObject.put("code",thiscode);
        jsonObject.put("dateStr",date);

        // 准备请求体数据
        String requestBody = JSON.toJSONString(jsonObject);
        // 设置请求体数据
        httpPost.setEntity(new StringEntity(requestBody));

        // 发送请求并获取响应
        CloseableHttpResponse response = client.execute(httpPost);

        // 获取响应状态码
        int statusCode = response.getStatusLine().getStatusCode();


        // 获取响应体内容
        HttpEntity entity = response.getEntity();
        String responseContent = EntityUtils.toString(entity);

        return responseContent;
    }

    private BigDecimal getAmount(String code,TickInfo tickInfo){
        if(code.substring(0,1).equals("1")){
            return tickInfo.getPrice().multiply(tickInfo.getVol()).multiply(new BigDecimal(10));
        }else {
            return tickInfo.getPrice().multiply(tickInfo.getVol()).multiply(new BigDecimal(100));
        }
    }


    @SneakyThrows

    public List<TickInfo> getStockTickInfo(String code) {

        String thisCode = this.removePrefix(code, 2);

        String codeUrl = this.getCodeUrl(thisCode);

        long l = System.currentTimeMillis();


        String url=null;
        if (code.substring(0, 2).equals("12") || code.substring(0, 2).equals("11")) {

            url="https://push2.eastmoney.com/api/qt/stock/get?" +
                    "invt=2&fltt=1&cb=jQuery3510778125534289833_" + (l - 1) + "&fields=f58%2Cf734%2Cf107%2Cf57%2Cf43%2Cf59%2Cf169%2Cf170%2Cf152%2Cf46%2Cf60%2Cf44%2Cf45%2Cf171%2Cf47%2Cf86%2Cf292%2Cf19%2Cf39%2Cf20%2Cf40%2Cf17%2Cf531%2Cf18%2Cf15%2Cf16%2Cf13%2Cf14%2Cf11%2Cf12%2Cf37%2Cf38%2Cf35%2Cf36%2Cf33%2Cf34%2Cf31%2Cf32%2Cf48%2Cf50%2Cf161%2Cf49%2Cf191%2Cf192%2Cf71%2Cf264%2Cf263%2Cf262%2Cf267%2Cf265%2Cf268%2Cf706%2Cf700%2Cf701%2Cf703%2Cf154%2Cf704%2Cf702%2Cf705%2Cf721%2Cf51%2Cf52%2Cf301" +
                    "&secid=" + codeUrl + "&ut=fa5fd1943c7b386f172d6893dbfba10b&wbp2u=6751315946175528%7C0%7C1%7C0%7Cweb&_=" + l;

        } else {

            url="https://push2.eastmoney.com/api/qt/stock/details/get?fields1=f1,f2,f3,f4&fields2=f51,f52,f53,f54,f55" +
                    "&fltt=2&cb=jQuery3510020623351060268913_" + (l - 1) + "&pos=-5000&secid=" + codeUrl + "&ut=fa5fd1943c7b386f172d6893dbfba10b&wbp2u=3155316106775332%7C0%7C1%7C0%7Cweb&_=" + l;

        }
        HttpGet httpGet = new HttpGet(url);
        //东方财富 接口 地址
        httpGet.addHeader("Referer", "http://quote.eastmoney.com/");
        httpGet.addHeader("user-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");


        // 响应模型
        CloseableHttpResponse response = null;

        // 由客户端执行(发送)Post请求
        response = client.execute(httpGet);
        // 从响应模型中获取响应实体
        HttpEntity responseEntity = response.getEntity();
        String str = EntityUtils.toString(responseEntity);
        String jsonDataCleaned = str.substring(str.indexOf('(') + 1, str.lastIndexOf(')'));



        JSONObject jsonObject =  JSON.parseObject(jsonDataCleaned);
        List<TickInfo> transactions = new ArrayList<>();

        try {
            JSON.parseObject(String.valueOf(jsonObject.get("data")), Feature.SupportArrayToBean)
                    .getJSONArray("details")
                    .forEach(obj -> {
                        String[] values = ((String) obj).split(",");
                        //TickData transaction = new TickData();
                        TickInfo transaction = new TickInfo();

                        String value = values[0];
                        String[] parts = value.split(":");
                        int hour = Integer.parseInt(parts[0]);
                        int minute = Integer.parseInt(parts[1]);
                        if (compareTimes(value,"09:25:00")){
                            transaction.setTime(values[0]);
                            transaction.setPrice(new BigDecimal(values[1]));
                            transaction.setVol(new BigDecimal(values[2]));

                            if (values[4].equals("1")) {
                                transaction.setBuySellFlag("DOWN");
                            }
                            if (values[4].equals("2")) {
                                transaction.setBuySellFlag("UP");
                            }
                            transactions.add(transaction);
                        }

                    });
        } catch (NullPointerException nullPointerException) {
            log.error("发生了错误 获取竞价数据异常->{}",code);
        }


        //System.out.println("这是大结果:"+JSON.toJSONString(transactions));
        //分时成交 TimeSharingTransaction
        return transactions;

    }

    // 比较两个时间字符串并返回一个布尔值
    public static boolean compareTimes(String time1Str, String time2Str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        try {
            LocalTime time1 = LocalTime.parse(time1Str, formatter);
            LocalTime time2 = LocalTime.parse(time2Str, formatter);

            return !time1.isBefore(time2);
        } catch (Exception e) {
            // 如果解析时间字符串时发生异常，返回false或抛出自定义异常
            // 这里为了简单起见，我们直接返回false
            return false;
        }
    }

    private  String getCodeUrl(String code) {
        String codeUrl;
        if (code.substring(0, 2).equals("00") || code.substring(0, 2).equals("12") || code.substring(0, 3).equals("300") || code.substring(0, 3).equals("301")) {
            codeUrl = "0." + code;
        } else {
            codeUrl = "1." + code;
        }
        return codeUrl;
    }

    private List<TickInfo> convertTickInfo(String respnse) {
        JSONArray objects = JSONArray.parseArray(respnse);
        List<TickInfo> result = new ArrayList<>();
        for (int i = 0; i < objects.size(); i++) {
            try {
                JSONObject jsonObject = objects.getJSONObject(i);
                TickInfo TickInfo = new TickInfo();
                TickInfo.setTime(jsonObject.getString("time"));
                TickInfo.setPrice(new BigDecimal(jsonObject.getString("price")));
                TickInfo.setVol(new BigDecimal(jsonObject.getString("vol")));
                TickInfo.setBuySellFlag(jsonObject.getString("buySellFlag"));

                result.add(TickInfo);
            } catch (Exception e) {
                log.error("转换数据异常" + e.getMessage(), e);
            }

        }
        return result;

    }




    /**
     * 将SH或者SZ前缀进行移除
     * @param code
     * @param prefixLength
     * @return
     */
    private   String removePrefix(String code, int prefixLength) {
        if (code == null || code.length() <= prefixLength) {
            log.error("传入的股票代码并非标准代码->{}",code);
        }
        return code.substring(prefixLength);
    }
}

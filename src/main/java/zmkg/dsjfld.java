//package zmkg;
//
//import com.alibaba.fastjson.JSONObject;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpRequestBase;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.util.EntityUtils;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * <p>
// * Note:
// * <p>
// * Date: 2024/4/20
// *
// * @author Su Xiaolei
// */
//public class dsjfld {
//
//
//    public static void main(String[] args) {
//
//        HttpConfigBo httpConfigBo = new HttpConfigBo();
//        httpConfigBo.setProxy(null);
//        httpConfigBo.setHttpClient(current.getHttpClient());
//        httpConfigBo.setHttpRequestBase(httpPost);
//
//
//        CloseableHttpClient httpClient = current.getHttpClient();
//        HttpRequestBase httpRequestBase = httpPost;
//
//        CloseableHttpResponse response = httpClient.execute(httpRequestBase);
//        log.info("请求url：" + httpRequestBase.toString() + "响应状态为:" + response.getStatusLine());
//        //设置状态码
//        result.setHttpStatus(response.getStatusLine().getStatusCode());
//        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
//            //返回json格式
//            String res = EntityUtils.toString(response.getEntity());
//            result.setResponseStr(res);
//            if(org.apache.commons.lang3.StringUtils.isNotBlank(res)&&res.contains("Nginx forbidden")){
//                log.error("请求结果：Nginx forbidden");
//                if (isProxy) {
//                    //删除当前ip，重试
//                    deleteProxy(httpConfigBo);
//                }
//            }
//        }
//    }
//
//
//    private final static BigDecimal allAuctionNum = new BigDecimal(100);
//
//
//    /**
//     * @param list 结果
//     * @param bd   超过多少比例为非法长度，
//     * @return false 长度未达标
//     */
//    public static JSONObject getResult(List<TickData> list, BigDecimal bd) {
//        JSONObject result = new JSONObject();
//        result.put("auctionLenFlag", true);
//        //数据长度未达标
//        if (new BigDecimal(list.size()).compareTo(allAuctionNum.multiply(bd)) < 0) {
//            result.put("auctionLenFlag", false);
//        }
//
//        list.sort((o1, o2) -> o1.getTime().compareTo(o2.getTime()));
//        int upNum = 0;
//        int downNum = 0;
//        for (int i = 1; i < list.size(); i++) {
//            if (new BigDecimal(list.get(i - 1).getPrice()).compareTo(new BigDecimal(list.get(i).getPrice())) > 0) {
//                downNum++;
//            }
//            if (new BigDecimal(list.get(i - 1).getPrice()).compareTo(new BigDecimal(list.get(i).getPrice())) < 0) {
//                upNum++;
//            }
//        }
//        result.put("upNum", upNum);
//        result.put("downNum", downNum);
//
//
//        return result;
//
//
//    }
//
//
//    public static JSONObject getResult(List<TickData> list) {
//        JSONObject result = new JSONObject();
//        list.sort((o1, o2) -> o1.getTime().compareTo(o2.getTime()));
//        int upNum = 0;
//        int downNum = 0;
//        for (int i = 1; i < list.size(); i++) {
//            if (new BigDecimal(list.get(i - 1).getPrice()).compareTo(new BigDecimal(list.get(i).getPrice())) > 0) {
//                downNum++;
//            }
//            if (new BigDecimal(list.get(i - 1).getPrice()).compareTo(new BigDecimal(list.get(i).getPrice())) < 0) {
//                upNum++;
//            }
//        }
//        result.put("upNum", upNum);
//        result.put("downNum", downNum);
//
//
//        return result;
//
//
//    }
//
//}

package network.urlSpider;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class Andm {

    public static void main(String[] args) throws IOException {

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet("https://wannianrili.51240.com/ajax/?q=2021-02&v=20031909");
        // 响应模型
        CloseableHttpResponse response = null;

        // 由客户端执行(发送)Post请求
        response = httpClient.execute(httpGet);
        //  response.setHeader("Content-Type", "text/html; charset=UTF-8");
        // 从响应模型中获取响应实体
        HttpEntity responseEntity = response.getEntity();
        String str = EntityUtils.toString(responseEntity);
        Document doc = Jsoup.parse(str);
        // 所有#id的标签
        Elements elements = doc.select("body");
        // 返回第一个
        Element body1 = doc.select("body").get(0);
        //doc.select("body").get(0).childNodes.get(23);

        List<Node> nodes1 = body1.children().get(0).childNodes();
        for (int i = 1; i < nodes1.size(); i++) {
            String xingqi=nodes1.get(i).childNodes().get(0).childNodes().get(0).toString();
           String zhengyueday= nodes1.get(i).childNodes().get(1).childNodes().get(0).toString();
            String layueday= nodes1.get(i).childNodes().get(2).childNodes().get(0).toString();

        }

            //日期信息
        List<Node> nodes = body1.children().get(0).childNodes().get(0).childNodes();
        String str1 = nodes.get(0).childNodes().get(0).attr("onclick").toString();
        String date = str1.substring(11, 18);

       int week=1;
        for (int i = 14; i < nodes.size(); i++) {
           int weeks= week%7;
           if(weeks==7){
               //0 123456
           }
            if ("wnrl_kongbai".equals(nodes.get(i).attr("class"))) {
                //空白
            }
            if ("wnrl_riqi".equals(nodes.get(i).attr("class"))) {
                //日期
                if(StringUtils.isBlank(nodes.get(i).childNodes().get(0).attr("class"))){
                    //空白
                }else {
                    String zhengyueDay= nodes.get(i).childNodes().get(0).childNodes().get(0).childNodes().get(0).toString();
                    String layueDay=  nodes.get(i).childNodes().get(0).childNodes().get(1).childNodes().get(0).toString();
                    if ("wnrl_riqi_mo".equals(nodes.get(i).childNodes().get(0).attr("class"))) {
                       //周末
                    }
                    if ("wnrl_riqi_xiu".equals(nodes.get(i).childNodes().get(0).attr("class"))) {
                        //休息
                    }
                }
            }
            week++;
        }
        System.out.println(body1);
    }
}

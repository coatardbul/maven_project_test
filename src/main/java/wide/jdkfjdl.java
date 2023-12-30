package wide;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Note:
 * <p>
 * Date: 2022/11/19
 *
 * @author Su Xiaolei
 */
public class jdkfjdl {

    public static void main(String[] args) throws IOException {

        excuseUrlAndParse("http://ddx.gubit.cn/jiedu/?date=20221012");

    }

    private  static List excuseUrlAndParse(String url) throws IOException {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet(url);
        // 响应模型
        CloseableHttpResponse response = null;

        // 由客户端执行(发送)Post请求
        response = httpClient.execute(httpGet);
        // 从响应模型中获取响应实体
        HttpEntity responseEntity = response.getEntity();
        String str = EntityUtils.toString(responseEntity);
        Document doc = Jsoup.parse(str);


        List list = new ArrayList();
        Elements header = doc.getElementsByClass("header");

        for(int i=0;i<header.size();i++){
            String key = header.get(i).children().get(0).text();

            // 所有#id的标签
            Elements elements = doc.select("tbody").get(i).children();


            List<String>codes = new ArrayList<String>();
            for (Element element : elements) {

                String text = element.children().get(0).children().get(0).children().get(2).text();

                codes.add(text);
            }
            Map<String,List<String>>result= new HashMap<>( );
            result.put(key,codes);
            list.add(result);
        }




        return list;


    }

}

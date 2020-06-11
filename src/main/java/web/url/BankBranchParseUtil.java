package web.url;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankBranchParseUtil {
    public final static int NUM = 34;
    public final static String provinceCityUrl = "http://www.lianhanghao.com/index.php/Index/Ajax?id=";
    public final static String bankHtmlUrl = "http://www.lianhanghao.com/index.php";
    public final static int BANK = 2;
    public final static int PAGE = 5;

    public final static  UrlResponseInfo urlResponseInfo = new UrlResponseInfo();
    /**
     * 将省的枚举转换成map类型
     *
     * @return
     */
    public Map<String, String> getProvinceMap() {
        Map<String, String> map = new HashMap<String, String>();
        for (ProvinceEnum p : ProvinceEnum.values()) {
            map.put(p.getCode(), p.getMessage());
        }
        return map;


    }

    /**
     * 获取省市请求url
     *
     * @param num
     * @return
     */
    public List<String> getUrlListByProvinceNum(int num) {
        List<String> list = new ArrayList<>(num);
        for (int i = 1; i <= num; i++) {
            list.add(provinceCityUrl + (i));
        }
        return list;
    }

    /**
     * 根据url获取html中的对象实体
     *
     * @param url
     * @return
     */
    public HttpEntity getHttpEntityByUrl(String url) throws IOException {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet(url);
        // 响应模型
        CloseableHttpResponse response = null;

        // 由客户端执行(发送)Post请求
        response = httpClient.execute(httpGet);
        //  response.setHeader("Content-Type", "text/html; charset=UTF-8");
        // 从响应模型中获取响应实体
        HttpEntity responseEntity = response.getEntity();

        return responseEntity;
    }

    /**
     * 将返回的省市请求处理成List对象
     *
     * @param httpEntity
     * @return
     * @throws IOException
     */
    public List<ProvinceCItyInfo> parseProviceCItyInfo(HttpEntity httpEntity) throws IOException {
        if (httpEntity == null) {
            return null;
        }
        String str = EntityUtils.toString(httpEntity);
        List<ProvinceCItyInfo> list = JSON.parseObject(str, new TypeReference<List<ProvinceCItyInfo>>() {
        });
        return list;
    }

    public void parseHtmlInfo(HttpEntity httpEntity) throws IOException {

        String str = EntityUtils.toString(httpEntity);
        Document doc = Jsoup.parse(str);
        // 所有#id的标签
        Elements elements = doc.select("body");
        // 返回第一个  width_11 auto list3
        Element e = doc.select("body").get(0);
        List<Node> nodes = e.getElementsByTag("table").get(1).getElementsByTag("tbody").get(0).childNodes();
        if (nodes != null && nodes.size() > 0) {
            for (Node n : nodes) {
                if (n instanceof Element) {
                    Elements n1 = ((Element) n).getElementsByTag("td");
                    String bankNo = n1.get(0).text();
                    String bankAddr = n1.get(1).text();

                    String bankAddr11 = n1.get(3).text();
                }
            }
        }
        System.out.println(nodes);
    }

    /**
     * 根据 银行，省，市，页码获取html的url
     *
     * @param bank
     * @param procince
     * @param city
     * @param page
     * @return
     */
    public String getHtmlUrl(int bank, int procince, int city, int page) {
        StringBuffer sb = new StringBuffer(bankHtmlUrl);
        sb.append("/Index/index");
        sb.append("/bank/" + bank);
        sb.append("/province/" + procince);
        sb.append("/city/" + city);
        sb.append("/p/" + page + ".html");
        return sb.toString();
    }

    public String getHtmlUrl(int bank, String procince, String city, int page) {
        return getHtmlUrl(Integer.valueOf(bank), Integer.valueOf(procince), Integer.valueOf(city), Integer.valueOf(page));
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        BankBranchParseUtil b = new BankBranchParseUtil();
        //获取省市请求url
        List<String> urlList = b.getUrlListByProvinceNum(NUM);
        //根据请求的url集合获取所有的省市信息
        List<ProvinceCItyInfo> allList = b.getProvinceCItyInfos(urlList);
        List<String> list = b.convertProvinceCityInfoToHtmlUrl(allList);

        for (String str : list) {
            HttpEntity httpEntityByUrl = b.getHttpEntityByUrl(str);
            b.parseHtmlInfo(httpEntityByUrl);
        }


    }

    /**
     * 根据请求的url集合获取所有的省市信息
     *
     * @param urlList
     * @return
     * @throws InterruptedException
     * @throws IOException
     */
    private List<ProvinceCItyInfo> getProvinceCItyInfos(List<String> urlList) throws InterruptedException, IOException {
        if (urlList == null || urlList.size() == 0) {
            return null;
        }
        //获取所有的省市信息
        List<ProvinceCItyInfo> allList = new ArrayList<>();
        for (int i = 0; i < urlList.size(); i++) {
            Thread.sleep((long) (Math.random() * (1000)));
            HttpEntity httpEntityByUrl = getHttpEntityByUrl(urlList.get(i));
            List<ProvinceCItyInfo> responseDto1 = parseProviceCItyInfo(httpEntityByUrl);
            for (ProvinceCItyInfo p : responseDto1) {
                p.setIdName(getProvinceMap().get(p.getId()));
            }
            allList.addAll(responseDto1);
            System.out.println(responseDto1);
        }
        return allList;
    }

    /**
     * 将省市信息转换成   html url的集合
     *
     * @param list
     * @return
     */
    private List<String> convertProvinceCityInfoToHtmlUrl(List<ProvinceCItyInfo> list) {
        List<String> result = new ArrayList<>();
        if (list == null || list.size() == 0) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            ProvinceCItyInfo provinceCItyInfo = list.get(i);
            for (int j = 1; j < PAGE; j++) {
                String htmlUrl = getHtmlUrl(BANK, provinceCItyInfo.getPid(), provinceCItyInfo.getId(), j);
                result.add(htmlUrl);
            }
        }
        return result;
    }
}

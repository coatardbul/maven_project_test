package web.url;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.beanutils.BeanUtils;
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
import java.lang.reflect.InvocationTargetException;
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

    public final static UrlResponseInfo urlResponseInfo = new UrlResponseInfo();

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

    public List<BankBranchInfo> parseHtmlInfo(HttpEntity httpEntity, UrlProvinceCItyInfo urlProvinceCItyInfo) throws IOException {
        List<BankBranchInfo> list = new ArrayList<>();

        String str = EntityUtils.toString(httpEntity);
        Document doc = Jsoup.parse(str);
        // 所有body
        Elements elements = doc.select("body");
        // 返回第一个
        Element e = doc.select("body").get(0);
        if(e.getElementsByTag("table")==null||
                e.getElementsByTag("table").size()<1||
                e.getElementsByTag("table").get(1)==null||
                e.getElementsByTag("table").get(1).getElementsByTag("tbody")==null||
                e.getElementsByTag("table").get(1).getElementsByTag("tbody").get(0)==null){
            return list;
        }
        List<Node> nodes = e.getElementsByTag("table").get(1).getElementsByTag("tbody").get(0).childNodes();
        if (nodes != null && nodes.size() > 0) {
            for (Node n : nodes) {
                if (n instanceof Element) {
                    Elements n1 = ((Element) n).getElementsByTag("td");
                  /*  String bankNo = n1.get(0).text();
                    String bankAddr = n1.get(1).text();
                    String bankAddr11 = n1.get(3).text();*/
                    BankBranchInfo b = new BankBranchInfo();
                    b.setCityId(urlProvinceCItyInfo.getSid());
                    b.setBranchNo(n1.get(0).text());
                    b.setBranchAddr(n1.get(1).text());
                    b.setBranchProviceCityAddr(n1.get(3).text());
                    list.add(b);
                }
            }
        }
        return list;
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
    public String getHtmlUrl(int bank, String procince, String city, int page) {
        StringBuffer sb = new StringBuffer(bankHtmlUrl);
        sb.append("/Index/index");
        sb.append("/bank/" + bank);
        sb.append("/province/" + procince);
        sb.append("/city/" + city);
        if (page != 0) {
            sb.append("/p/" + page + ".html");
        }
        return sb.toString();
    }


    public String getHtmlUrl(String url, int page) {
        return url + "/p/" + page + ".html";

    }

    public String getHtmlUrl(int bank, String procince, String city) {
        return getHtmlUrl(Integer.valueOf(bank), procince, city, 0);
    }

    public static void main(String[] args) throws IOException, InterruptedException, InvocationTargetException, IllegalAccessException {
        BankBranchParseUtil b = new BankBranchParseUtil();
        //获取省市请求url
        List<String> urlList = b.getUrlListByProvinceNum(NUM);
        //根据请求的url集合获取所有的省市信息
        List<ProvinceCItyInfo> allList = b.getProvinceCItyInfos(urlList);
        //将省市信息转换成   html url的集合 带页码
        List<UrlProvinceCItyInfo> list = b.convertProvinceCityInfoToHtmlUrl(allList);

        List<BankBranchCollectionInfo> bankBranchCollectionInfos = new ArrayList<>();
        //一个市对应的联行号信息
        List<BankBranchInfo> bankBranchInfos = new ArrayList<>();
        for (UrlProvinceCItyInfo urlProvinceCItyInfo : list) {
            Map<String, BankBranchInfo> map = new HashMap<>();
            BankBranchCollectionInfo bankBranchCollectionInfo = new BankBranchCollectionInfo();
            for (int j = 1; j < PAGE; j++) {
                Thread.sleep((long) (Math.random() * (100)));
                String htmlUrl = b.getHtmlUrl(urlProvinceCItyInfo.getUrl(), j);
                System.out.println(htmlUrl);
                HttpEntity httpEntityByUrl = b.getHttpEntityByUrl(htmlUrl);
                List<BankBranchInfo> list1 = b.parseHtmlInfo(httpEntityByUrl, urlProvinceCItyInfo);

                for (BankBranchInfo br : list1) {
                    map.put(br.getBranchNo().substring(3, 7), br);
                }
                if (list1.size() > 0) {
                    bankBranchInfos.addAll(list1);

                }
            }
            bankBranchCollectionInfo.setId(urlProvinceCItyInfo.getSid());
            bankBranchCollectionInfo.setMap(map);
            System.out.println(map);

            bankBranchCollectionInfos.add(bankBranchCollectionInfo);
        }
        System.out.println();

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
            Thread.sleep((long) (Math.random() * (100)));
            HttpEntity httpEntityByUrl = getHttpEntityByUrl(urlList.get(i));
            //返回单个省对应的多个市的集合
            List<ProvinceCItyInfo> provinceCItyInfos = parseProviceCItyInfo(httpEntityByUrl);
            //返回多个省市集合
            allList.addAll(provinceCItyInfos);
        }
        return allList;
    }

    private List<UrlProvinceCItyInfo> convertProvinceCityInfoToHtmlUrl(List<ProvinceCItyInfo> list) throws InvocationTargetException, IllegalAccessException {
        return convertProvinceCityInfoToHtmlUrl(list, false);
    }

    /**
     * 将省市信息转换成   html url的集合
     *
     * @param list 是否带页码
     * @return
     */
    private List<UrlProvinceCItyInfo> convertProvinceCityInfoToHtmlUrl(List<ProvinceCItyInfo> list, Boolean flag) throws InvocationTargetException, IllegalAccessException {
        List<UrlProvinceCItyInfo> result = new ArrayList<>();
        if (list == null || list.size() == 0) {
            return null;
        }
        for (int i = 0; i < list.size(); i++) {
            UrlProvinceCItyInfo urlProvinceCItyInfo = new UrlProvinceCItyInfo();
            BeanUtils.copyProperties(urlProvinceCItyInfo, list.get(i));
            if (flag) {
                for (int j = 1; j < PAGE; j++) {
                    String htmlUrl = getHtmlUrl(BANK, list.get(i).getPid(), list.get(i).getSid(), j);
                    urlProvinceCItyInfo.setUrl(htmlUrl);
                    result.add(urlProvinceCItyInfo);
                }
            } else {
                System.out.println(list.get(i));
                String htmlUrl = getHtmlUrl(BANK, list.get(i).getPid(), list.get(i).getSid());
                urlProvinceCItyInfo.setUrl(htmlUrl);
                result.add(urlProvinceCItyInfo);
            }

        }
        return result;
    }
}

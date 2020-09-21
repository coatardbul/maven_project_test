package network.urlSpider;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParse {



    public static Element getID(String body) {
        Document doc = Jsoup.parse(body);
        // 所有#id的标签
        Elements elements = doc.select("body");
        // 返回第一个
        Element body1 = doc.select("body").get(0);
        //doc.select("body").get(0).childNodes.get(23);
        return body1;
    }



}

package zmkg;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientExample222 {
    public static void main(String[] args) {
        // 创建HttpClient实例
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            long l = System.currentTimeMillis();


            String plateCode="BK1157";

            String url="https://12.push2.eastmoney.com/api/qt/clist/get?cb=jQuery112408158997362912552_"+(l-1)+"&pn=1&pz=2000&po=1&np=1&ut=bd1d9ddb04089700cf9c27f6f7426281&fltt=2&invt=2&wbp2u=6751315946175528|0|1|0|web&fid=f3&fs=b:"+plateCode+"+f:!50&fields=f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f12,f13,f14,f15,f16,f17,f18,f20,f21,f23,f24,f25,f22,f11,f62,f128,f136,f115,f152,f45&_="+l;

            // 构建HttpGet请求


            url = url.replaceAll("\\|", "%124");

            HttpGet request = new HttpGet(url);

            // 添加请求头"Referer", "http://quote.eastmoney.com/"
            request.addHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36");


            // 执行请求并获取响应
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                String responseBody = EntityUtils.toString(response.getEntity(), "UTF-8");
                System.out.println(responseBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package robot;

import com.sun.deploy.net.URLEncoder;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Note:
 * <p>
 * Date: 2024/1/2
 *
 * @author Su Xiaolei
 */
public class AliModifyVerify {
    /**
     * 服务器地址（以RPA公有云集群为例）
     */
    public static final String SERVER = "https://console-rpa.aliyun.com";

    /**
     * 调用接口路径, 以createServiceTask为例
     */
    public static final String PATH = "/rpa/openapi/app/applyApp?";

    public static void main(String[] args) {
        // ... 你的现有代码中的参数初始化 ...

        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            // 构建请求URL
            String url = SERVER + PATH;

            // 构建请求参数
            Map<String, String> parameters = new HashMap<>();
            // ... 添加你的参数 ...

            // 签名处理
            // ... 你的签名逻辑 ...

            // 构建完整的URL（如果需要）
            url += createQueryString(parameters);

            HttpPost post = new HttpPost(url);

            // 设置请求头
            post.setHeader("Accept-Charset", "UTF-8");
            post.setHeader("Content-Type", "application/json;charset=utf-8");
            // ... 其他头部设置 ...

            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(post);

            // 处理响应
            if (response.getStatusLine().getStatusCode() == 200) {
                String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
                // 处理响应字符串
            } else {
                throw new RuntimeException("请求失败: " + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String createQueryString(Map<String, String> parameters) throws UnsupportedEncodingException {
        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            if (queryString.length() > 0) {
                queryString.append("&");
            }
            queryString.append(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.toString()))
                    .append("=")
                    .append(URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.toString()));
        }
        return queryString.toString();
    }

    // ... 你的其他方法 ...
}

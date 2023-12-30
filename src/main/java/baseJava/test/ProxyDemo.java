import java.io.IOException;
import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class ProxyDemo {
    // 代理隧道验证信息
    private final static String ProxyUser = "GQAR1726740893213088";
    private final static String ProxyPass = "p5gHIhk0hI2ueaxF";

    // 代理服务器
    private final static String ProxyHost = "dyn.horocn.com";
    private final static Integer ProxyPort = 50000;

    private static void getUrlProxyContent(String url) {
        Authenticator.setDefault(new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(ProxyUser, ProxyPass.toCharArray());
            }
        });

        System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ProxyHost, ProxyPort));

        try {
            Document doc = Jsoup.connect(url).timeout(3000).proxy(proxy).get();

            if (doc != null) {
                System.out.println(doc.body().html());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        // 要访问的目标页面
        String targetUrl = "https://www.baidu.com";

        getUrlProxyContent(targetUrl);
    }
}

package robot;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.PlaywrightException;
import com.microsoft.playwright.Request;

import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * Note:
 * <p>
 * Date: 2023/12/9
 *
 * @author Su Xiaolei
 */
public class FanWeiPort {

    public static void sdfsdfs(String[] args) throws InterruptedException, ScriptException, FileNotFoundException, NoSuchMethodException {

        try (Playwright playwright = Playwright.create()) {

//            Browser browser = playwright.chromium().connectOverCDP("http://localhost:9222/");
            Browser browser = playwright.chromium().connectOverCDP("http://localhost:9222");

            BrowserContext browserContext = browser.contexts().get(0);
            Page page = browserContext.newPage();

            page.navigate("http://124.222.217.230:9627");
            // 在已连接的 Chromium 实例中执行其他操作...
            Thread.sleep(20 * 1000);
            browser.close();
        } catch (PlaywrightException e) {
            e.printStackTrace();
        }

    }
    // 将网络请求转换为 cURL 命令的方法
    private static String generateCurlCommand(Request request) {
        StringBuilder curl = new StringBuilder("curl ");
        curl.append("-X ").append(request.method()).append(" ");

        // 添加请求头
        for (Map.Entry<String,String> entry : request.headers().entrySet()) {
            curl.append("-H '").append(entry.getKey()).append(": ").append(entry.getValue()).append("' ");
        }

        // 添加请求体
        if (request.postData() != null) {
            curl.append("--data '").append(request.postData()).append("' ");
        }

        curl.append(request.url());
        return curl.toString();
    }
    public static void main(String[] args) throws InterruptedException, ScriptException, FileNotFoundException, NoSuchMethodException {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().connectOverCDP("http://localhost:9222");
            BrowserContext defaultContext = browser.contexts().get(0);
            Page page = defaultContext.newPage();

//            // 监听网络请求
//            page.onRequest(request -> {
////                System.out.println("Request URL: " + request.url());
//                String curlCommand = generateCurlCommand(request);
////                System.out.println("cURL Command: " + curlCommand);
//            });

            page.navigate("https://e-cloudstore.com/ec/api/applist/index.html#/");
            Thread.sleep(2 * 1000);


            page.locator("li").filter(new Locator.FilterOptions().setHasText("协作管理")).locator("span").first().click();
            Thread.sleep(1 * 1000);
            ElementHandle elementHandle2 = page.querySelector("ul[class='ant-tree-child-tree ant-tree-child-tree-open ant-tree-line']");
            List<ElementHandle> paragraphs = elementHandle2.querySelectorAll("div[class='ant-col-24 titleText text-elli']");
            for (ElementHandle paragraph : paragraphs) {
                paragraph.click();
                Thread.sleep(500);
                String url = page.querySelectorAll("//blockquote[p] ").get(2).innerText();
                System.out.println(paragraph.innerText()+"  "+url);
            }
            browser.close();
        }

    }
}



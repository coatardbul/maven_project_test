package robot;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import util.TongHuaShunUtil;

import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
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
public class jldfsj {

    public static void main(String[] args) throws InterruptedException, ScriptException, FileNotFoundException, NoSuchMethodException {
        System.setProperty("PWBROWSER", "Chrome");

        List<String> launchArgs = new ArrayList<String>();
        launchArgs.add("--start-maximized");

        try (Playwright playwright = Playwright.create()) {
            BrowserType.LaunchOptions launchOptions = new BrowserType.LaunchOptions()
                    .setExecutablePath(Paths.get("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"))
                    .setHeadless(false)
                    .setChannel("chrome")
                    .setArgs(launchArgs);
            Browser browser = playwright.chromium().launch(launchOptions);

            // 创建一个浏览器上下文
            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
            // 创建一个页面
            Page page = context.newPage();
            page.navigate("http://www.iwencai.com/unifiedwap/result?w=%E9%9D%9E%E5%88%9B%E4%B8%9A%E6%9D%BF%EF%BC%8C%E9%9D%9Est%E6%9D%BF%E5%9D%97%EF%BC%8C%E9%9D%9E%E7%A7%91%E5%88%9B%E6%9D%BF%EF%BC%8C2022%E5%B9%B401%E6%9C%8805%E6%97%A5%E6%B6%A8%E5%81%9C%EF%BC%8C2022%E5%B9%B401%E6%9C%8806%E6%97%A5%E6%B6%A8%E5%81%9C&querytype=stock");

            Map<String, String> headers = new HashMap<String, String>();
            headers.put("Hexin-V", TongHuaShunUtil.getHeXinStr());
            page.setExtraHTTPHeaders(headers);
            page.locator("span[class='login_btn nav_word']").click();
            Thread.sleep(3*1000);

            Frame frames = page.mainFrame().childFrames().get(0);
            frames.locator("a[class='a_gray pointer right-top-icon erweima client_abs']").click();
            Thread.sleep(1*1000);
            frames.locator("div[class='code-box']").screenshot(new Locator.ScreenshotOptions().setPath(Paths.get("/Users/coatardbul/Desktop/screenshot.png")));



            System.out.println(page.title());

            Thread.sleep(50*1000);
        }
    }
}

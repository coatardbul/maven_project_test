package robot;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import util.TongHuaShunUtil;

import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * <p>
 * Note:
 * <p>
 * Date: 2023/12/9
 *
 * @author Su Xiaolei
 */
public class jldfsjsdfsd {

    public static void main(String[] args) throws InterruptedException, ScriptException, FileNotFoundException, NoSuchMethodException {

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
            Path path = Paths.get("/Users/coatardbul/Desktop/cookie.json");
            BrowserContext context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null).setStorageStatePath(path));
            BrowserContext.StorageStateOptions storageState = new BrowserContext.StorageStateOptions();

            // 创建一个页面
            Page page = context.newPage();
            page.navigate("http://124.222.217.230:9627/login");
            page.getByPlaceholder("账号").fill("sxl14459048");
            page.getByPlaceholder("密码").fill("123456");
            page.getByText("登 录").click();

            System.out.println(page.title());

            storageState.setPath(path);
            context.storageState(storageState);
            Thread.sleep(50*1000);
        }
    }
}

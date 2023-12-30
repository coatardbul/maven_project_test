package robot;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Note:
 * <p>
 * Date: 2023/12/9
 *
 * @author Su Xiaolei
 */
public class jldfsjsdfs3333 {

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
            page.navigate("http://124.222.217.230:9627");

            storageState.setPath(path);
            context.storageState(storageState);
            Thread.sleep(50*1000);
        }
    }
}

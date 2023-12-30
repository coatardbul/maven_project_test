package robot;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * <p>
 * Note:
 * <p>
 * Date: 2023/12/21
 *
 * @author Su Xiaolei
 */
public class Fanwei {

    public static void main(String[] args) throws InterruptedException, ScriptException, FileNotFoundException, NoSuchMethodException {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().connectOverCDP("http://localhost:9222");
            BrowserContext defaultContext = browser.contexts().get(0);
            Page page = defaultContext.newPage();


            page.navigate("http://192.168.30.140:8081/");
            page.navigate("http://192.168.30.140:8081/wui/index.html#/?logintype=1&time=1703471279181");
            page.navigate("http://192.168.30.140:8081/wui/index.html#/?logintype=1&time=1703471279181&_key=kpj6q2");
            page.getByText("登录名").click();
            page.getByText("登录名").click();
            page.locator("#loginid").fill("sysadmin");
            page.getByText("登录密码").click();
            page.locator("#userpassword").fill("Ecology@140");
            page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("登 录")).click();
            page.getByTitle("考勤管理", new Page.GetByTitleOptions().setExact(true)).locator("i").click();


            browser.close();
        }

    }

}

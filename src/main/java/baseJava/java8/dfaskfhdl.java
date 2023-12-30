package baseJava.java8;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.bidi.browsingcontext.BrowsingContext;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * Note:
 * <p>
 * Date: 2023/6/23
 *
 * @author Su Xiaolei
 */
public class dfaskfhdl {
    public static void main(String[] args) throws InterruptedException {

        String baseUrl = "http://124.222.217.230:9627/index91";
        String chromedriverPath = "/usr/local/bin/chromedriver";

        System.setProperty("webdriver.chrome.driver",chromedriverPath);

        //初始化一个chrome浏览器实例，实例名称叫driver
        ChromeDriver driver = new ChromeDriver();

        //最大化窗口
        driver.manage().window().maximize();


        //get()打开一个站点
        driver.get(baseUrl);
        List<WebElement> elements = driver.findElements(By.className("el-input__inner"));
        new Actions(driver)
                .moveToElement(elements.get(0))
                .pause(Duration.ofSeconds(1))
                .clickAndHold()
                .pause(Duration.ofSeconds(1))
                .sendKeys("sxl14459048")
                .perform();

        new Actions(driver)
                .moveToElement(elements.get(1))
                .pause(Duration.ofSeconds(1))
                .clickAndHold()
                .pause(Duration.ofSeconds(1))
                .sendKeys("123456")
                .perform();
        WebElement login = driver.findElement(By.tagName("button"));
        new Actions(driver)
                .click(login)
                .perform();
        for(int i=1;i<4;i++){
            driver.executeScript("window.open('"+baseUrl+"','_blank');");
            Set<String> windowHandles = driver.getWindowHandles();
            Object[] objects = windowHandles.toArray();
            driver.switchTo().window(objects[i].toString());
            List<WebElement> elements0 = driver.findElements(By.className("el-input__suffix"));
            new Actions(driver).moveToElement(elements0.get(2)).pause(Duration.ofSeconds(1))
                    .click().perform();
            List<WebElement> elements1 = driver.findElements(By.className("el-input__inner"));
            new Actions(driver).moveToElement(elements1.get(2)).pause(Duration.ofSeconds(1))
                    .click()
                    .sendKeys("2023-07-07").perform();

            List<WebElement> button = driver.findElements(By.tagName("button"));
            new Actions(driver).moveToElement(button.get(3)).pause(Duration.ofSeconds(1))
                    .click().perform();
            int num=0;
            while (num<98){
                WebElement element = driver.findElement(By.className("el-progress-bar__inner"));
                String str = element.getAttribute("style");
                int endIndex = str.indexOf("%");
                str = str.substring(0,endIndex);
                str= str.replace("width:","").trim();
                num=Integer.valueOf(str);
                Thread.sleep(3000);
            }
            new Actions(driver).moveToElement(button.get(6)).pause(Duration.ofSeconds(1))
                    .click().perform();
            Thread.sleep(3000);
        }

    }

    private static void aaa( ChromeDriver driver){
        driver.get("https://www.selenium.dev/selenium/web/mouse_interaction.html");

        long start = System.currentTimeMillis();

        WebElement clickable = driver.findElement(By.id("clickable"));
        new Actions(driver)
                .moveToElement(clickable)
                .pause(Duration.ofSeconds(1))
                .clickAndHold()
                .pause(Duration.ofSeconds(1))
                .sendKeys("abc")
                .perform();

        long duration = System.currentTimeMillis() - start;
        Assertions.assertTrue(duration > 2000);
        Assertions.assertTrue(duration < 3000);
    }
}

package webdriver;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_39_Fluent_Wait {

    String pageUrl;
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    Alert alert;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        //driver = new ChromeDriver();

        jsExecutor = (JavascriptExecutor) driver;

        explicitWait = new WebDriverWait(driver, 10);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void TC_01() {
        pageUrl = "https://automationfc.github.io/fluent-wait/";
        driver.get(pageUrl);
        WebElement countDown = driver.findElement(By.cssSelector("div#javascript_countdown_time"));

        //wait cho den khi countdown time xuat hien
        explicitWait.until(ExpectedConditions.visibilityOf(countDown));

        //su dung fluent wait de kiem tra cu moi 1/10s (trong vong 15s) kiem tra xem
        //da dem nguoc ve :00 hay chua
        FluentWait<WebElement> fluentWait = new FluentWait<WebElement>(countDown);

        fluentWait.withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(NoSuchElementException.class)
                .until(new Function<WebElement, Boolean>() {
                    @Override
                    public Boolean apply(WebElement element) {
                        boolean countToZero = element.getText().endsWith("00");
                        System.out.println("Time = " + element.getText());
                        return countToZero;
                    }
                });
    }

    public void sleepInSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void AfterClass() {
        driver.quit();
    }
}

package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_37_Only_Explicit_Wait {

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

        pageUrl = "https://automationfc.github.io/dynamic-loading/";
        driver.get(pageUrl);
        driver.manage().window().maximize();
    }

    /* Explicit Wait (khoi tao thong qua instance cua WebDriverWait)
    * Explicit Wait dung de set thoi gian cho cac status/condition cua element
    * bao gom visible, invisible, presence va staleness
    * Explicit Wait chay theo dang bat dong bo (asynchronous) va
    * bi anh huong boi thoi chay cho cua Implicit Wait
    * */

    public void TC_01() {
        //set thoi gian timeout la 3s
        explicitWait = new WebDriverWait(driver, 3);
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start button"))).click();

        //doi cho den khi loading icon bien mat
        //vi chi set la 3s nen k du de element bien mat
        //element van con nen no se throw ra TimeOutException
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());
    }

    public void TC_02() {
        //set thoi gian timeout la 5s
        explicitWait = new WebDriverWait(driver, 5);
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start button"))).click();

        //doi cho den khi loading icon bien mat
        //vi da set du thoi gian cho den khi thoa man dieu kien
        //element invisible nen pass test case
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

        Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());
    }

    public void TC_03() {
        //set thoi gian timeout la 3s
        explicitWait = new WebDriverWait(driver, 3);
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start button"))).click();

       //set thoi gian cho la 3s
        //cho den khi dong chu Hello World xuat hien
        //ta dung method visibilityOfElementLocated tuy nhien vi thoi gian
        //cho k du nen test case van fail
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));

        Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());
    }

    @Test
    public void TC_04() {
        //set thoi gian timeout la 5s
        explicitWait = new WebDriverWait(driver, 5);
        explicitWait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div#start button"))).click();

        //set thoi gian cho la 5s
        //cho den khi dong chu Hello World xuat hien
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish h4")));

        Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());
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

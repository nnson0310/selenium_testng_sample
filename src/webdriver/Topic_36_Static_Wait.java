package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_36_Static_Wait {

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

        driver.manage().window().maximize();
    }

    /* Static Wait la 1 loai sleep cung su dung ham Thread.Sleep()
    cua Java. Static Wait thieu su linh hoat vi neu set thoi gian thieu se de
    dan den test case bi fail con neu set thua se dan den code k toi uu.
    Static Wait chi nen dung cho TH chay test case tren IE hoac dung de doi window/tab load xong
     */
    @Test
    public void TC_01() {
        pageUrl = "https://automationfc.github.io/dynamic-loading/";
        driver.get(pageUrl);

        driver.findElement(By.cssSelector("div#start button")).click();
        //set thoi gian sleep la 5s vua bang thoi gian cho loading
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement resultText = driver.findElement(By.cssSelector("div#finish h4"));

        //verify text hello world xuat hien
        Assert.assertTrue(resultText.isDisplayed());
        Assert.assertEquals(resultText.getText(), "Hello World!");
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

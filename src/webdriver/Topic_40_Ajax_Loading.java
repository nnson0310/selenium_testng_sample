package webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import java.util.List;

public class Topic_40_Ajax_Loading {

    String pageUrl;
    WebDriver driver;
    JavascriptExecutor jsExecutor;
    Alert alert;
    //driver = new ChromeDriver();
    Actions action;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();

        explicitWait = new WebDriverWait(driver, 10);
        jsExecutor = (JavascriptExecutor) driver;
        action = new Actions(driver);

        driver.manage().window().maximize();
    }

    public void TC_01() {
        pageUrl = "https://opensource-demo.orangehrmlive.com";
        driver.get(pageUrl);

        driver.findElement(By.cssSelector("input#txtUsername")).sendKeys("Admin");
        driver.findElement(By.cssSelector("input#txtPassword")).sendKeys("admin123");
        driver.findElement(By.cssSelector("input#btnLogin")).click();

        isPageLoadSuccess();
        Assert.assertTrue(driver.findElement(By.cssSelector("canvas.flot-overlay")).isDisplayed());
    }

    @Test
    public void TC_02() {
        pageUrl = "https://blog.testproject.io/";
        driver.get(pageUrl);

        if (driver.findElement(By.cssSelector("div#mailch-bg")).isDisplayed()) {
            driver.findElement(By.cssSelector("div#close-mailch")).click();
        }
        action.moveToElement(driver.findElement(By.cssSelector("section#search-2 input.search-field"))).perform();
        driver.findElement(By.cssSelector("section#search-2 input.search-field")).sendKeys("Selenium");
        driver.findElement(By.cssSelector("section#search-2 span.glass")).click();

        List<WebElement> titles = driver.findElements(By.cssSelector(".post-title a"));
        for (WebElement title: titles) {
            Assert.assertTrue(title.getText().contains("Selenium"));
        }
    }

    public Boolean isPageLoadSuccess() {
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @org.jetbrains.annotations.NotNull
            @Override
            public Boolean apply(WebDriver webDriver) {
                return jsExecutor.executeScript("return document.readyState").equals("complete");
            }
        };

        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                return (Boolean) jsExecutor.executeScript("return (window.Jquery != null) && (jQuery.active === 0)");
            }
        };

        return explicitWait.until(jsLoad) && explicitWait.until(jQueryLoad);
    }

    public void sleepInSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

//    @AfterClass
//    public void AfterClass() {
//        driver.quit();
//    }
}

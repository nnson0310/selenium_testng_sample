package webdriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
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

    @Test
    public void TC_02() {
        pageUrl = "https://automationfc.github.io/dynamic-loading/";
        driver.get(pageUrl);

//        WebElement startButton = explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div#start button"))));
//        startButton.click();

        /*
        //su dung Fluent Wait de kiem tra cu moi 0.1s xem tu Hello World da hien ra hay chua
        C1: wait cho den khi loading icon bien mat
                WebElement loading = driver.findElement(By.cssSelector("div#loading"));
                FluentWait<WebElement> fluentWait = new FluentWait<WebElement>(loading);
                fluentWait.withTimeout(Duration.ofSeconds(5))
                        .ignoring(TimeoutException.class)
                        .pollingEvery(Duration.ofMillis(100))
                        .until(new Function<WebElement, Boolean>() {
                            @Override
                            public Boolean apply(WebElement element) {
                                return !element.isDisplayed();
                            }
                        });
                Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
        */
        //C2: doi den khi dong chu hello world xuat hien
//        WebElement finishText = driver.findElement(By.cssSelector("div#finish h4"));
//        FluentWait<WebElement> fluentWait = new FluentWait<WebElement>(finishText);
//        fluentWait.withTimeout(Duration.ofSeconds(5))
//                .ignoring(NoSuchElementException.class)
//                .pollingEvery(Duration.ofMillis(100))
//                .until(new Function<WebElement, Boolean>() {
//                    @Override
//                    public Boolean apply(WebElement element) {
//                        System.out.println(element.getText());
//                        return element.isDisplayed();
//                    }
//                });

        //Fluent Wait giup ta linh hoat hon trong viec custom ham
        clickElement(By.cssSelector("div#start button"));

        elementIsDisplayed(By.cssSelector("div#finish h4"));

    }

    public WebElement findElement(By locator) {
        FluentWait<WebDriver> fluentWait = new FluentWait<WebDriver>(driver);
        return fluentWait.withTimeout(Duration.ofSeconds(10))
                .ignoring(NoSuchElementException.class)
                .pollingEvery(Duration.ofMillis(100))
                .until(new Function<WebDriver, WebElement>() {
                    @Override
                    public WebElement apply(WebDriver driver) {
                        return driver.findElement(locator);
                    }
                });
    }

    public void clickElement(By locator) {
        WebElement element = findElement(locator);
        element.click();
    }

    public boolean elementIsDisplayed(By locator) {
        WebElement element = findElement(locator);
        System.out.println(element.getText());
        return element.isDisplayed();
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

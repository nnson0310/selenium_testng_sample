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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_35_Implicit_Wait {

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

    /*Element có 4 trạng thái: visible, invisible, presence và staleness
    * Visible: element được nhìn thấy (xuất hiện trong DOM)
    * Invisible: element không được nhìn thấy (có thể xuất hiện hoặc không xuất hiện trong DOM)
    * Presence: element phải xuất hiện trong DOM (có thể nhìn thấy hoặc không nhìn thấy)
    * Staleness: element không attach vào DOM (không nhìn thấy)
    * */

    /* Implicit Wait sẽ set thời gian chờ cho việc tìm Element
    * và nó chỉ ảnh hưởng đến 2 hàm là findElement và findElements
    * Implicit Wait không bị ảnh hưởng bởi thời gian timeout của bất kì một loại
    * wait nào khác
    * */

    /* Test case nay bi fail vi set implicit wait qua ngan*/
    public void TC_01() {
        //set thời gian timeout cho implicit_wait là 2s
        //tat ca cac ham findElement ben duoi se bi anh huong
        //neu k tim thay Element no se tim di tim lai cu moi 0.5s
        //cho den khi het timeout
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        //click vao start button
        driver.findElement(By.cssSelector("div#start button")).click();

        //doi cho dong chu Hello World xuat hien va verify
        Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());

        //check result text xuat hien
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
    }

    /*
    * Testcase nay se pass vi set du thoi gian cho den khi
    * element xuat hien
    * */
    @Test
    public void TC_02() {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        //click vao start button
        driver.findElement(By.cssSelector("div#start button")).click();

        //doi cho dong chu Hello World xuat hien va verify
        Assert.assertTrue(driver.findElement(By.cssSelector("div#finish h4")).isDisplayed());

        //check result text xuat hien
        Assert.assertEquals(driver.findElement(By.cssSelector("div#finish h4")).getText(), "Hello World!");
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

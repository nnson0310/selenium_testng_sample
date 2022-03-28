package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.Set;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_38_Mixing_Explicit_Implicit_Wait {

    String pageUrl;
    String image1, image2, image3, pathToFile1, pathToFile2, pathToFile3;
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

        image1 = "cat_1.jpg";
        image2 = "cat_2.jpg";
        image3 = "cat_3.jpg";

        pathToFile1 = projectPath + "\\uploadImages\\" + image1;
        pathToFile2 = projectPath + "\\uploadImages\\" + image2;
        pathToFile3 = projectPath + "\\uploadImages\\" + image3;


    }

    /* Trong thuc te nen su dung ca implicitWait va explicitWait de co the
    * handle duoc tat ca cac ngoai le. Implicit Wait se k bi anh huong boi bat ki
    * wait nao con explicit wait se bi anh huong boi thoi gian cho cua implicit wait
    * Explicit Wait chay theo kieu async
    * Implicit Wait neu k duoc set se default la 0s (kha nguy hiem vi neu k tim thay element se fail test case luon)
    * */
    public void TC_01() {
        pageUrl = "https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx";
        driver.get(pageUrl);

        explicitWait = new WebDriverWait(driver, 10);

        //wait cho den khi datetime picker duoc hien thi
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.calendarContainer")));

        //verify khi chua chon ngay nao se in ra dong chu "No Selected Dates to display"
        //vi chua set implicitWait nen mac dinh se bang 0
        Assert.assertEquals(driver.findElement(By.cssSelector("fieldset span")).getText(), "No Selected Dates to display.");

        //chon 1 ngay bat ki trong thang nam hien tai
        driver.findElement(By.cssSelector("td[title='Tuesday, March 22, 2022'] a")).click();

        //wait cho den khi ajax loading icon bien mat
        explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div.raDiv")));

        //wait cho den khi ngay 22/3/2022 duoc selected (co class rcSelected)
        //explicitWait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("td.rcSelected"), 0));
        Assert.assertTrue(driver.findElement(By.cssSelector("td[title='Tuesday, March 22, 2022']")).getAttribute("class").contains("rcSelected"));

        //verify ngay duoc chon
        explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("fieldset span")));
        Assert.assertEquals(driver.findElement(By.cssSelector("fieldset span")).getText(), "Tuesday, March 22, 2022");
    }

    @Test
    public void TC_02() {
        pageUrl = "https://gofile.io/uploadFiles";
        driver.get(pageUrl);
        String parentTabId = driver.getWindowHandle();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        explicitWait = new WebDriverWait(driver, 40);

        //upload nhieu file va verify cac file da duoc upload thanh cong
        driver.findElement(By.cssSelector("input#uploadFile-Input")).sendKeys(pathToFile1 + "\n" + pathToFile2 + "\n" + pathToFile3);

        //Co 2 cach de wait trong TH nay
        // 1: wait loading icon bien mat (invisible)
        // 2: wait message upload thanh cong xuat hien
        explicitWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.progress"))));

        WebElement uploadSuccessText = explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.callout-success h5")));
        Assert.assertEquals(uploadSuccessText.getText(), "Your files have been successfully uploaded");

        //click nut show files
        driver.findElement(By.cssSelector("a#rowUploadSuccess-downloadPage")).click();

        //switch to tab moi
        Set<String> tabIds= driver.getWindowHandles();
        for (String tabId: tabIds) {
            if (!parentTabId.equals(tabId)) {
                driver.switchTo().window(tabId);
            }
        }

        int index = 1;
        List<WebElement> imageTitles = driver.findElements(By.cssSelector("span.contentName"));
        for (WebElement imageTitle: imageTitles) {
            Assert.assertEquals(imageTitle.getText(), "cat_" + index + ".jpg");
            index++;
        }
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

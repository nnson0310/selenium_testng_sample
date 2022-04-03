package TestNGFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

//@Listeners({TestNGFramework.Listener.class})
public class Depend_On_Test_Case {

    String projectPath = System.getProperty("user.dir");
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
        System.out.println("This is before class");
    }

    /*
    * Neu cac testcase khong doc lap ma co moi lien ket voi nhau
    * ta co the su dung attr dependsOnMethods de khien cac
    * test case rang buoc voi nhau. Vi du neu test case so
    * 2 phu thuoc vao test case so 1 thi khi test case so 1 bi fail
    * test case so 2 se tu dong bi skip (k chay) giup tiet kiem thoi gian
    *
    * */
    @Test
    public void TC_01() {
        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "TC_01")
    public void TC_02() {

    }

    @Test(dependsOnMethods = "TC_02")
    public void TC_03() {

    }

    public void sleepInSeconds(long seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}

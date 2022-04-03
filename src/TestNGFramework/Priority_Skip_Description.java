package TestNGFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

public class Priority_Skip_Description {

    String projectPath = System.getProperty("user.dir");
    WebDriver driver;

    /*
    * alwaysRun dung de set cho 2 annotation la
    * beforeClass va afterClass nham bat 2 method co
    * dinh kem 2 method nay luon phai chay. Vi du: dung de kich hoat driver.quit de tat toan bo driver
    * nham tiet kiem RAM phong TH beforeClass bi fail khien
    * driver k tat khien ngon RAM cua may tinh
    * */
    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
        System.out.println("This is before class");
    }

    /*
    * Priority dung de set thu tu uu tien khi chay test case
    * Tuy nhien rat hiem khi dung vi da set la phai set thu tu cho
    * tat ca cac method khac thay vao do ta co the dat ten theo thu
    * tu alphabet (A,B,C...)
    * */

    /*
    * Thuoc tinh enabled nhan 2 gia tri la true va false
    * True: run method, false: k run method
    * Tuy nhien cung k dung vi hoi bat tien khi phai khai bao nhieu
    * thay vao do ta chi can comment @Test annotation
    * */

    /*
     * Thuoc tinh description dung de mo ta method
     * nham cung cap them thong tin ve business hoac dung
     * de lien ket voi ID/Link/Test report tren cac
     * trang report bug nhu JIRA
     * */
    @Test(priority = 2, enabled = false)
    public void TC_01() {
        System.out.println("This is test case 01");
    }

    @Test(priority = 3, description = "This is test case 02")
    public void TC_02() {
        System.out.println("This is test case 02");
    }

    @Test(priority = 1)
    public void TC_03() {
        System.out.println("This is test case 03");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}

package TestNGFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Parallel_Firefox {

    String projectPath = System.getProperty("user.dir");
    WebDriver driver;

    /*
    * Parallel la thuoc tinh giup chay song song cac test suite
    * giup tiet kiem thoi gian. No nhan cac gia tri la: tests, classes. methods va instances
    * Khi chay parallel ta can chi dinh them attr thread-count
    * de biet so thread pool dc su dung de chay song song cac test case
    * Parallel duoc dung de chay song song cac test case tren nhieu trinh duyet, nhieu OS
    * cung 1 luc. Tuy nhien nen phan phoi qua Selenium Grid chu k nen chay song song
    * tren cung 1 may duy nhat vi se gay ton RAM
    * Nhuoc diem:
    * + Khong nen chay Parallel tren cac test case phu thuoc lan nhau
    * */
    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
        System.out.println("This is before class");
    }

    @Test()
    public void TC_Firefox() {
        System.out.println("The thread of Firefox is " + Thread.currentThread().getId());
        driver.get("https://www.seleniumeasy.com/");
        sleepInSeconds(3);
        System.out.println("Run parallel test on firefox");
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

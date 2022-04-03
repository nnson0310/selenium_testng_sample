package TestNGFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

public class Data_Provider {

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
    * DataProvider la 1 annotation cua TestNG giup chia tach bo data dung
    * de test va logic cua test case theo huong TDD (Data driven development)
    * Giup chung ta co the chay test case voi nhieu bo du lieu khac nhau
    * Co 2 cach de cung cap data ao cho viec run test case
    * C1: khai bao truc tiep trong class
    * C2: doc qua cac external file nhu excel (xlsx, xls) hoac csv
    * */
    @Test(dataProvider = "User/Password")
    public void TC_01(String userName, String password) {
        driver.get("https://opensource-demo.orangehrmlive.com/");

        driver.findElement(By.cssSelector("#txtUsername")).clear();
        driver.findElement(By.cssSelector("#txtUsername")).sendKeys(userName);
        driver.findElement(By.cssSelector("#txtPassword")).clear();
        driver.findElement(By.cssSelector("#txtPassword")).sendKeys(password);
        driver.findElement(By.cssSelector("#btnLogin")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("#spanMessage")).getText(), "Invalid credentials");
        sleepInSeconds(5);
    }

    @DataProvider(name = "User/Password")
    public static Object[][] userAndPassword() {
        return new Object[][] {
                {"admin", "123456"},
                {"nson0310", "tomanyeuem"}
        };
    }

    public void sleepInSeconds(long sleepSeconds) {
        try {
            Thread.sleep(sleepSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

}

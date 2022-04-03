package TestNGFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Parallel_Single_Method_Data_Provider {

    String projectPath = System.getProperty("user.dir");
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();

        driver.manage().window().maximize();
        System.out.println("This is before class");
    }

    @Test(dataProvider = "loginInfo")
    public void TC_Parallel_In_Single_Methods(String email, String pass) {
       driver.get("http://live.techpanda.org/index.php/customer/account/login/");

        driver.findElement(By.cssSelector("#email")).sendKeys(email);
        driver.findElement(By.cssSelector("#pass")).sendKeys(pass);
        sleepInSeconds(3);
    }

    @DataProvider(name = "loginInfo", parallel = true)
    public Object[][] dataProvider() {
        return new Object[][] {
                {"admin@gmail.com", "123456"}
        };
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

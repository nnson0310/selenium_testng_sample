package TestNGFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;


public class GroupB {

    String projectPath = System.getProperty("user.dir");
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", "\\browserDrivers\\geckodriver.exe");

        driver = new FirefoxDriver();

        driver.manage().window().maximize();
    }

    @Test()
    public void TC_01() {
        System.out.println("This is test case 01 of Group B class");
    }

    @Test(groups = "user")
    public void TC_02() {
        System.out.println("This is test case 02 of Group B class");
    }

    @Test(groups = "admin")
    public void TC_03() {
        System.out.println("This is test case 03 of Group B class");
    }

    @AfterClass
    public void afterClass( ){
        driver.quit();
    }
}

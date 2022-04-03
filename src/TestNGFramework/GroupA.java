package TestNGFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;


public class GroupA {

    String projectPath = System.getProperty("user.dir");
    WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", "\\browserDrivers\\geckodriver.exe");

        driver = new FirefoxDriver();

        driver.manage().window().maximize();
    }

    /*
    * Group attribute cua @Test annotations dung de nhom
    * cac testcase cua 1 nhom hoac cung 1 features vao
    * de chay dong loat. De chay dong loat cac methods cua cung
    * 1 group ta dung cu phap <groups> <run> <include name="ten_group">
    * */
    @Test(groups = "user")
    public void TC_01() {
        System.out.println("This is test case 01 of Group A class");
    }

    @Test(groups = "admin")
    public void TC_02() {
        System.out.println("This is test case 02 of Group A class");
    }

    @Test
    public void TC_03() {
        System.out.println("This is test case 03 of Group A class");
    }

    @AfterClass
    public void afterClass( ){
        driver.quit();
    }
}

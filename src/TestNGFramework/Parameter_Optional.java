package TestNGFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class Parameter_Optional {

    String projectPath = System.getProperty("user.dir");
    WebDriver driver;

    /*
    * @Parameter dung de chi dinh tham so truyen vao cac method
    * Bang cach them the <parameter name="" value=""> trong file test.xml
    * Neu gia tri ban dau cua Parameter khong duoc chi dinh
    * Test method se lay gia tri mac dinh tu @Optional
    * Chu y: Khi truyen Parameter thi phai run .xml file de run ca test suite
    * neu run truc tiep tu test class thi se bi loi
    * */

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Parameters("url")
    @Test
    public void TC_Run_on_Facebook(String url) {
        driver.get(url);
        System.out.println("Url = " + url);
    }

    @Parameters("sub_url")
    @Test
    public void TC_Run_on_Google(@Optional("https://www.google.com") String url) {
        driver.get(url);
        System.out.println("Url = " + url);
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}

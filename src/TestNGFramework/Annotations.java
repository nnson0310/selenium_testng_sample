package TestNGFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Annotations {

  String projectPath = System.getProperty("user.dir");
  WebDriver driver;

  @Test
  public void TC_01() {
      driver.get("https://blogtruyen.vn/");
      String currentUrl = driver.getTitle();

      Assert.assertEquals(currentUrl, "Đọc truyện tranh | BlogTruyen.VN");
  }

  /*
  * Thu tu chay se la @Suite > @Test > @Class > @Method
  * Dung de set up thu tu chay trong test case
  * trong do @Suite se chay dau tien truoc khi bat dau run cac test case trong test suite nay
  * */
  @BeforeMethod
  public void beforeMethod() {
    System.out.println("This is before method");
  }

  @AfterMethod
  public void afterMethod() {
    System.out.println("This is after method");
  }

  @BeforeClass
  public void beforeClass() {
    System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
    driver = new FirefoxDriver();

    driver.manage().window().maximize();
    System.out.println("This is before class");
  }

  @AfterClass
  public void afterClass() {
    System.out.println("This is after class");
  }

  @BeforeTest
  public void beforeTest() {
    System.out.println("This is before test");
  }

  @AfterTest
  public void afterTest() {
    System.out.println("This is after test");
  }

  @BeforeSuite
  public void beforeSuite() {
    System.out.println("This is before suite");
  }

  @AfterSuite
  public void afterSuite() {
    System.out.println("This is after suite");
  }

}

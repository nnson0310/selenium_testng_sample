
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_WebBrowser_Exercise {
	
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
	}
	
	@Test
	public void TC_01_Verify_Url() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//div[@class='links']//a[@title='My Account']")).click();
		
		//verify url cua page login
		String loginUrl = driver.getCurrentUrl();
		Assert.assertEquals(loginUrl, "http://live.techpanda.org/index.php/customer/account/login/");
		
		//verfiry url cua page account create
		driver.findElement(By.xpath("//a[@class='button' and @title='Create an Account']")).click();
		String accountUrl = driver.getCurrentUrl();
		Assert.assertEquals(accountUrl, "http://live.techpanda.org/index.php/customer/account/create/");
	}
	
	@Test
	public void TC_02_Verify_Title() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//div[@class='links']//a[@title='My Account']")).click();
		
		//verify title cua page login
		String loginTitle = driver.getTitle();
		Assert.assertEquals(loginTitle, "Customer Login");
		
		//verfiry url cua page account create
		driver.findElement(By.xpath("//a[@class='button' and @title='Create an Account']")).click();
		String accountTitle = driver.getTitle();
		Assert.assertEquals(accountTitle, "Create New Customer Account");
	}
	
	@Test
	public void TC_03_Navigation() {
		driver.get("http://live.techpanda.org/");
		//click vao link My Account
		driver.findElement(By.xpath("//div[@class='footer']//div[@class='links']//a[@title='My Account']")).click();
		//click button create an account
		driver.findElement(By.xpath("//a[@class='button' and @title='Create an Account']")).click();
		//verify url
		String pageUrl = driver.getCurrentUrl();
		Assert.assertEquals(pageUrl, "http://live.techpanda.org/index.php/customer/account/create/");
		//back lai trang truoc
		driver.navigate().back();
		//verify url
		pageUrl = driver.getCurrentUrl();
		Assert.assertEquals(pageUrl, "http://live.techpanda.org/index.php/customer/account/login/");
		//back lai trang register
		driver.navigate().forward();
		//verify title
		String pageTitle = driver.getTitle();
		Assert.assertEquals(pageTitle, "Create New Customer Account");
	}
	
	@Test
	public void TC_03_GetPageSource() {
		driver.get("http://live.techpanda.org/");
		//click vao link My Account
		driver.findElement(By.xpath("//div[@class='footer']//div[@class='links']//a[@title='My Account']")).click();
		//verify page chua text
		String pageSource = driver.getPageSource();
		Assert.assertTrue(pageSource.contains("Login or Create an Account"));
		//click button create an account
		driver.findElement(By.xpath("//a[@class='button' and @title='Create an Account']")).click();
		//verify page chua text
		pageSource = driver.getPageSource();
		Assert.assertTrue(pageSource.contains("Create an Account"));
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

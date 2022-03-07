import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_16_17_WebElement_Exercise {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		driver.get("http://live.techpanda.org/");
	}
	
	@Test
	public void TC_01() {
		//click vao link My Account de vao trang dang ki
		WebElement link = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
		link.click();
		
		//de trong email va password
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("pass")).clear();
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		//verify error message
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-email")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.id("advice-required-entry-pass")).getText(), "This is a required field.");
	}
	
	@Test
	public void TC_02() {
		//click vao link My Account de vao trang dang ki
		WebElement link = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
		link.click();
		
		//nhap sai email
		driver.findElement(By.id("email")).sendKeys("123@123");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		//verify error message
		Assert.assertEquals(driver.findElement(By.id("advice-validate-email-email")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test
	public void TC_03() {
		//click vao link My Account de vao trang dang ki
		WebElement link = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
		link.click();
		
		//nhap dung email con nhap password sai
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("12345");
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		//verify error message
		Assert.assertEquals(driver.findElement(By.id("advice-validate-password-pass")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	@Test
	public void TC_04() {
		//click vao link My Account de vao trang dang ki
		WebElement link = driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']"));
		link.click();
		
		//nhap email va password sai
		driver.findElement(By.id("email")).sendKeys("son@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		//verify error message
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span[text()='Invalid login or password.']")).getText(), "Invalid login or password.");
	}
	
	@Test
	public void TC_05() {
		//click vao link My Account de vao trang dang nhap
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		//click vao create an account de toi trang dang ki
		driver.findElement(By.cssSelector("a[title='Create an Account']")).click();
		//nhap thong tin hop le vao tat cac field
		driver.findElement(By.id("firstname")).sendKeys("Nguyen");
		driver.findElement(By.id("middlename")).sendKeys("Thu");
		driver.findElement(By.id("lastname")).sendKeys("Hang");
		//generate random email
		String randomEmail = generateRandomString();
		driver.findElement(By.id("email_address")).sendKeys(randomEmail + "@gmail.com");
		
		driver.findElement(By.id("password")).sendKeys("123456");
		driver.findElement(By.id("confirmation")).sendKeys("123456");
		
		driver.findElement(By.cssSelector("button[title='Register']")).click();
		
		//verify success msg
		Assert.assertEquals(driver.findElement(By.cssSelector(".success-msg span")).getText(), "Thank you for registering with Main Website Store.");
		//verify account info
		Assert.assertEquals(driver.findElement(By.cssSelector(".page-title h1")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.cssSelector(".hello strong")).getText(), "Hello, Nguyen Thu Hang!");
		String[] info = driver.findElement(By.xpath("//a[text()='Change Password']//parent::p")).getText().split("\n");
		Assert.assertEquals(info[0], "Nguyen Thu Hang");
		Assert.assertEquals(info[1], randomEmail + "@gmail.com");
		
		//logout khoi he thong
		driver.findElement(By.cssSelector(".account-cart-wrapper a.skip-account")).click();
		driver.findElement(By.cssSelector("a[title='Log Out']")).click();
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String title = driver.getTitle();
		Assert.assertEquals(title, "Home page");
	}
	
	@Test
	public void TC_06() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.id("email")).sendKeys("thuhang@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123456");
		driver.findElement(By.id("send2")).click();
		//verify account info
		Assert.assertEquals(driver.findElement(By.cssSelector(".page-title h1")).getText(), "MY DASHBOARD");
		Assert.assertEquals(driver.findElement(By.cssSelector(".hello strong")).getText(), "Hello, Nguyen Thu Hang!");
		String[] info = driver.findElement(By.xpath("//a[text()='Change Password']//parent::p")).getText().split("\n");
		Assert.assertEquals(info[0], "Nguyen Thu Hang");
		Assert.assertEquals(info[1], "thuhang@gmail.com");
	}
	
	public String generateRandomString() {
	    int leftLimit = 97; // letter 'a'
	    int rightLimit = 122; // letter 'z'
	    int targetStringLength = 10;
	    Random random = new Random();

	    String generatedString = random.ints(leftLimit, rightLimit + 1)
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();

	    return generatedString;
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

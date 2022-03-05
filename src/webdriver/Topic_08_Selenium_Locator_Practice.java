package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Selenium_Locator_Practice {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		//khoi tao object FirefoxDriver de tuong tac voi trinh duyet
		driver = new FirefoxDriver();
		
		//set thoi gian waiting de tim duoc element
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//mo trinh duyet toan man hinh
		// driver.manage().window().maximize();
		
		//mo trinh duyet len cung duong dan
		driver.get("https://github.com/login");
	}

	@Test
	public void TC_01_ValidateCurrentUrl() {
		
		//String loginUrl = driver.getCurrentUrl();
		//Assert.assertEquals(loginUrl, "https://github.com/login");
		
		//input username or email into login form
		//driver.findElement(By.id("login_field")).sendKeys("sonnn@axalize.vn");
		//sleepInSecond(2);
		
		//input username or email into login form
		//driver.findElement(By.id("password")).sendKeys("tomanyeuem");
		//sleepInSecond(2);
		
		//click log in button
		//driver.findElement(By.cssSelector("input[value='Sign in']")).click();

		//selenium 3 have introduced two main locators: css selector and xPath, others are id, className, name, tagName, link text, partial link text
		//all examples below will get elements from this url: https://github.com/login
		
		//id locator
		driver.findElement(By.id("login_field"));
		
		//class locator
		driver.findElement(By.className("js-login-field"));
		
		//name
		driver.findElement(By.name("login"));
		
		//tagName
		//find out how many there are anchor tags
		driver.findElement(By.tagName("a"));
		
		//link text
		driver.findElement(By.linkText("Forgot password?"));
		
		//partial link text
		driver.findElement(By.partialLinkText("Forgot"));
		
		//css selector can cover six other kinds of locators
		driver.findElement(By.cssSelector("div[role='contentinfo'] li:nth-child(2) a"));
		
		//xPath can cover all kinds but slower than css selector
		driver.findElement(By.xpath("//input[@name='login']"));
		
		driver.findElement(By.xpath("//a[contains(@value, 'Forgot')]"));
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	//make thread sleep in seconds to delay action
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
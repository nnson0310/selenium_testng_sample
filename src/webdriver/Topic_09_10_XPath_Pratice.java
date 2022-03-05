package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_09_10_XPath_Pratice {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_XPath_Locator() {
		driver.get("https://about.gitlab.com/");
		
		//Ex1: use text() function to locate element
		driver.findElement(By.xpath("//a[text()='Get free trial']")).isDisplayed();
		
		//Ex2: use contains() function to locate element
		driver.findElement(By.xpath("//div[contains(text(), 'Learn')]")).isDisplayed();
		
		//Ex3: use starts-with
		driver.findElement(By.xpath("//div[@class='footer-link-holder']//a[starts-with(text(), 'Open')]")).isDisplayed();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}

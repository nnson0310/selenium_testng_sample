package webdriver;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_23_Handle_Authentication_Alert {
	
	String pageUrl, username, password, firefoxAuthen;
	WebDriver driver;
	String projectPath  = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		driver = new FirefoxDriver();
		
		username = "admin";
		password = "admin";
		
		firefoxAuthen = projectPath + "\\authenScripts\\authen_firefox.exe";
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
//	@Test
//	public void TestCase() {
//		//de handle authentication alert can truyen param vao url
//		pageUrl = "http://" + username + ":" + password + "@the-internet.herokuapp.com/basic_auth";
//		driver.get(pageUrl);
//		
//		//verify if authentication is successfull
//		Assert.assertTrue(driver.findElement(By.cssSelector("div#content p")).isDisplayed());
//	}
	
	@Test
	public void TestCase_02() throws IOException {
		pageUrl = "http://the-internet.herokuapp.com/basic_auth";
		
		if (driver.toString().contains("firefox")) {
			Runtime.getRuntime().exec(new String[] { firefoxAuthen, username, password });
		} else {
			Runtime.getRuntime().exec(new String[] { firefoxAuthen, username, password });
		}
		
		driver.get(pageUrl);
		sleepInSeconds(8);
		
		//verify if authentication is successfull
		Assert.assertTrue(driver.findElement(By.cssSelector("div#content p")).isDisplayed());
	}
	
	public void sleepInSeconds(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
//	@AfterClass
//	public void AfterClass() {
//		driver.quit();
//	}
}
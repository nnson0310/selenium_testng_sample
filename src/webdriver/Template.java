package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Template {

	String pageUrl;
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	Actions action;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();

		jsExecutor = (JavascriptExecutor) driver;

		// khoi tao instance cua Action de tuong tac voi mouse va keyboard
		action = new Actions(driver);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public void TC_01() {
		pageUrl = "https://automationfc.github.io/jquery-tooltip/";
		driver.get(pageUrl);

		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).isDisplayed());
	}

	public void TC_02() {
		pageUrl = "https://www.myntra.com/";
		driver.get(pageUrl);
		
		action.moveToElement(driver.findElement(By.cssSelector("div.desktop-navLink a[data-group='kids']"))).perform();
		sleepInSeconds(5);
		action.moveToElement(driver.findElement(By.xpath("//div[@data-group='kids']//a[text()='T-Shirts']"))).click().perform();
		sleepInSeconds(5);
	
		Assert.assertEquals(driver.findElement(By.cssSelector("h1.title-title")).getText(), "Kids Wear Online Store");
	}
	
	@Test
	public void TC_03() {
		pageUrl = "https://www.fahasa.com/";
		driver.get(pageUrl);
		
		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		sleepInSeconds(5);
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Sách Trong Nước']"))).perform();
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Sách Trong Nước']")).getText(), "Sách Trong Nước");
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

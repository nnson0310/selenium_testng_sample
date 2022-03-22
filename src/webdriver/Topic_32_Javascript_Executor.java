package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_32_Javascript_Executor {

	String pageUrl;
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();

		jsExecutor = (JavascriptExecutor) driver;

		explicitWait = new WebDriverWait(driver, 10);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	// su dung javascriptExecutor trong TH: k the tuong tac voi element bang
	// cac ham co san cua Selenium
	// vi jsExecutor gan giong nhu trick va k phai la cac hanh dong thong thuong cua
	// end-user
	// dung trong TH: scroll to element, scroll to bottom page, hightlight element,
	// remove attribute...
	@Test
	public void TC_01() {
		pageUrl = "http://live.techpanda.org/";
		driver.get(pageUrl);

		// get domain va verify domain
		String domain = jsExecutor.executeScript("return document.domain").toString();
		System.out.println(domain);
		Assert.assertEquals(domain, "live.techpanda.org");

		// get domain va verify domain
		String URL = jsExecutor.executeScript("return document.URL").toString();
		System.out.println(URL);
		Assert.assertEquals(URL, "http://live.techpanda.org/");

		// open mobile page
		jsExecutor.executeScript("arguments[0].click()",
				driver.findElement(By.xpath("//div[@id='header-nav']//a[text()='Mobile']")));

		// add san pham bang nut add to cart
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath(
				"//a[@title='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//button[contains(@class,'btn-cart')]")));
		sleepInSeconds(3);
		
		//verify message add to cart thanh cong
		String msg = jsExecutor.executeScript("return arguments[0].innerText", driver.findElement(By.cssSelector("div.cart li.success-msg span"))).toString();
		System.out.println(msg);
		Assert.assertEquals(msg, "Samsung Galaxy was added to your shopping cart.");
		
		//click vao nut customer service
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[@class='footer']//a[text()='Customer Service']")));
	}

	public void sleepInSeconds(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
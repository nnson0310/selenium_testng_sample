package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.server.handler.FindElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
	
	public void TC_02() {
		pageUrl = "https://login.ubuntu.com/";
		driver.get(pageUrl);
		
		WebElement emailInput = driver.findElement(By.cssSelector("form#login-form input#id_email"));
		emailInput.sendKeys("a");
		sleepInSeconds(3);
		String errorMsg = jsExecutor.executeScript("return arguments[0].validationMessage", emailInput).toString();
		System.out.println(errorMsg);
		Assert.assertEquals(errorMsg, "Please enter an email address.");
		
		pageUrl = "https://sieuthimaymocthietbi.com/account/register";
		driver.get(pageUrl);
		
		WebElement lastNameInput = driver.findElement(By.cssSelector("input#lastName"));
		lastNameInput.clear();
		sleepInSeconds(3);
		errorMsg = jsExecutor.executeScript("return arguments[0].validationMessage", lastNameInput).toString();
		System.out.println(errorMsg);
		Assert.assertEquals(errorMsg, "Please fill out this field.");
	}
	
	public void TC_03() {
		pageUrl = "https://demo.guru99.com/v4/";
		driver.get(pageUrl);
		
		driver.findElement(By.cssSelector("input[name='uid']")).sendKeys("mngr394561");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("mApEjut");
		driver.findElement(By.cssSelector("input[name='btnLogin']")).click();
		
		//click chon menu new customer
		driver.findElement(By.xpath("//ul[@class='menusubnav']//a[text()='New Customer']")).click();
		driver.findElement(By.cssSelector(" input[name='name']")).sendKeys("automation");
		
		jsExecutor.executeScript("arguments[0].removeAttribute('type')", driver.findElement(By.cssSelector("input#dob")));
		sleepInSeconds(3);
		driver.findElement(By.cssSelector("input#dob")).sendKeys("03/12/2000");
	}
	
	@Test
	public void TC_04() {
		String url = "http://live.techpanda.org/";
		String randomEmail = "maria" + new Random().nextInt() + "@gmail.com";
		
		jsExecutor.executeScript("window.location=\'" + url + "\'");
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")));
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("div#header-account a[title='My Account']")));
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("a[title='Create an Account']")));
		
		//nhap thong tin vao cac truong
		jsExecutor.executeScript("arguments[0].setAttribute('value', 'Maria')", driver.findElement(By.cssSelector("input#firstname")));
		jsExecutor.executeScript("arguments[0].setAttribute('value', 'Ok')", driver.findElement(By.cssSelector("input#middlename")));
		jsExecutor.executeScript("arguments[0].setAttribute('value', 'Ozawa')", driver.findElement(By.cssSelector("input#lastname")));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + randomEmail + "')", driver.findElement(By.cssSelector("input#email_address")));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '123456')", driver.findElement(By.cssSelector("input#password")));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '123456')", driver.findElement(By.cssSelector("input#confirmation")));
		
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("button[title='Register']")));
		
		String successMsg = jsExecutor.executeScript("return arguments[0].innerText", driver.findElement(By.cssSelector("li.success-msg span"))).toString();
		System.out.println(successMsg);
		Assert.assertEquals(successMsg, "Thank you for registering with Main Website Store.");
		
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")));
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("div#header-account a[title='Log Out']")));
		
		explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div.page-title img"))));
		Assert.assertTrue(driver.findElement(By.cssSelector("div.page-title img")).isDisplayed());
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
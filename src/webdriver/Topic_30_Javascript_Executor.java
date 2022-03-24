package webdriver;

import java.util.List;
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
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_30_Javascript_Executor {

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

	@Test
	public void TC_02() {
		pageUrl = "https://automationfc.github.io/html5/index.html";
		driver.get(pageUrl);
		WebElement nameInput = driver.findElement(By.cssSelector("input#fname"));
		WebElement passInput = driver.findElement(By.cssSelector("input#pass"));
		WebElement emailInput = driver.findElement(By.cssSelector("input#em"));
		WebElement addressInput = driver.findElement(By.tagName("select"));

		// click nut submit va verify error message
		driver.findElement(By.cssSelector("input[name='submit-btn']")).click();
		String nameErrorMsg = jsExecutor.executeScript("return arguments[0].validationMessage", nameInput).toString();
		System.out.println(nameErrorMsg);
		Assert.assertEquals(nameErrorMsg, "Please fill out this field.");

		// input du lieu vao truong name va verify message tai truong password
		nameInput.sendKeys("son");
		String passErrorMsg = jsExecutor.executeScript("return arguments[0].validationMessage", passInput).toString();
		System.out.println(passErrorMsg);
		Assert.assertEquals(passErrorMsg, "Please fill out this field.");

		// input du lieu vao truong password va verify message tai truong email
		passInput.sendKeys("son");
		String emailErrorMsg = jsExecutor.executeScript("return arguments[0].validationMessage", emailInput).toString();
		System.out.println(emailErrorMsg);
		Assert.assertEquals(emailErrorMsg, "Please fill out this field.");
		
		//input du lieu khong hop le vao truong email va verify error message
		emailInput.sendKeys("auto@!$");
		emailErrorMsg = jsExecutor.executeScript("return arguments[0].validationMessage", emailInput).toString();
		System.out.println(emailErrorMsg);
		Assert.assertEquals(emailErrorMsg, "Please enter an email address.");
		
		emailInput.clear();
		emailInput.sendKeys("auto@gmail");
		emailErrorMsg = jsExecutor.executeScript("return arguments[0].validationMessage", emailInput).toString();
		System.out.println(emailErrorMsg);
		Assert.assertEquals(emailErrorMsg, "Please match the requested format.");
		
		//input du lieu hop le vao truong email va bam submit, verify error message
		emailInput.clear();
		emailInput.sendKeys("auto@gmail.com");
		String addressErrorMsg = jsExecutor.executeScript("return arguments[0].validationMessage", addressInput).toString();
		System.out.println(addressErrorMsg);
		Assert.assertEquals(addressErrorMsg, "Please select an item in the list.");
	}
	
	@Test
	public void TC_03() {
		pageUrl = "https://login.ubuntu.com/";
		driver.get(pageUrl);
		
		//verify error message 
		driver.findElement(By.cssSelector("form#login-form input#id_email")).sendKeys("a");
		driver.findElement(By.cssSelector("form#login-form button[name='continue']")).click();
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

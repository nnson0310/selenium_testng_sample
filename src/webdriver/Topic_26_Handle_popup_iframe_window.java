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

public class Topic_26_Handle_popup_iframe_window {

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
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	public void TC_01() {
		pageUrl = "https://ngoaingu24h.vn/";
		driver.get(pageUrl);
		//chi dung webElement trong TH modal/popup luon ton tai trong DOM HTML
		//con k chi khai bao nhu nay: By loginModal = By.cssSelector("div#modal-login-v1");
		WebElement loginModal = driver.findElement(By.cssSelector("div#modal-login-v1"));
		
		//verify that login dialog is not displayed
		//dialog/popup/modal co 2 loai: fixed (luon xuat hien trong DOM) va random (xuat hien ngau nhien)
		//loai random chi xuat hien trong DOM khi pop visible (xuat hien)
		Assert.assertFalse(loginModal.isDisplayed());
		
		//click login button and verify that dialog is displayed
		driver.findElement(By.xpath("//div[@id='button-login-dialog']//button[text()='Đăng nhập']")).click();
		Assert.assertTrue(loginModal.isDisplayed());
		
		//input credentials
		driver.findElement(By.cssSelector("div#modal-login-v1 input#account-input")).sendKeys("121214");
		driver.findElement(By.cssSelector("div#modal-login-v1 input#password-input")).sendKeys("123456");
		driver.findElement(By.cssSelector("button[data-text='Đăng nhập']")).click();
		
		//verify that error messages is displayed
		Assert.assertEquals(driver.findElement(By.cssSelector("div#modal-login-v1 div.error-login-panel")).getText(), "Tài khoản không tồn tại!");
	}
	
	@Test
	public void TC_02() {
		pageUrl = "https://jtexpress.vn/";
		driver.get(pageUrl);
		String billCodes = "84001177897";
		//verify that popup is displayed after access page
		Assert.assertTrue(driver.findElement(By.cssSelector("div#homepage-popup")).isDisplayed());
		//close popup
		driver.findElement(By.cssSelector("#homepage-popup button.close")).click();
		//verify that popup disappear after clicking close button
		Assert.assertFalse(driver.findElement(By.cssSelector("div#homepage-popup")).isDisplayed());
		//nhap ma van don va tien hanh tra cuu
		driver.findElement(By.id("billcodes")).sendKeys(billCodes);
		driver.findElement(By.cssSelector("form#formTrack button")).click();
		//xac nhan ma van don
		Assert.assertEquals(driver.findElement(By.cssSelector("form#formTrack textarea#billcodes")).getText(), billCodes);
		Assert.assertTrue(driver.findElement(By.cssSelector("div#accordion h5")).getText().contains(billCodes));
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
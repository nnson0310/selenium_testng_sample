package webdriver;

import java.util.Date;
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

public class Topic_27_Handle_random_popup {

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
		pageUrl = "https://blog.testproject.io/";
		driver.get(pageUrl);
		//Step 1: kiem tra popup co xuat hien
		//neu co xuat hien thi dong popup di roi chuyen bang step 2
		if (driver.findElement(By.cssSelector("div#mailch-bg")).isDisplayed()) {
			driver.findElement(By.cssSelector("div#close-mailch")).click();
		}
		//Step 2: nhap tu khoa tim kiem vao o input
		driver.findElement(By.cssSelector("aside#secondary input.search-field")).sendKeys("Selenium");
		driver.findElement(By.cssSelector("aside#secondary span.glass")).click();
		sleepInSeconds(5);
		//Step3: kiem tra tat ca cac title cua ket qua search deu chua tu khoa selenium
		List<WebElement> searchTitles = driver.findElements(By.cssSelector("h3.post-title"));
		for (WebElement searchTitle : searchTitles) {
			Assert.assertTrue(searchTitle.getText().contains("Selenium"));
		}
	}
	
	public void TC_02() {
		pageUrl = "https://vnk.edu.vn/";
		driver.get(pageUrl);
		sleepInSeconds(15);
		//verify xuat hien cua popup
		if (driver.findElement(By.cssSelector("div#tve-p-scroller")).isDisplayed()) {
			driver.findElement(By.cssSelector("div.tcb-icon-display")).click();
		}
		driver.findElement(By.cssSelector("a[title='Liên hệ']")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("div.title-content h1")).getText(), "Liên Hệ");
	}
	
	@Test
	public void TC_03() {
		pageUrl = "https://dehieu.vn/";
		driver.get(pageUrl);
		System.out.println("Find popup start:" + getRunTime());
		List<WebElement> popup = driver.findElements(By.cssSelector("section#popup"));
		System.out.println("Find popup end:" + getRunTime());
		//verify xuat hien cua popup
		//doi voi cac element k xuat hien trong DOM nen dung findElements va size de so sanh
		if (popup.size() > 0) {
			//close popup di
			driver.findElement(By.cssSelector("button#close-popup")).click();
			//verify popup k con hien thi
			System.out.println("Find popup start:" + getRunTime());
			popup = driver.findElements(By.cssSelector("section#popup"));
			System.out.println("Find popup end:" + getRunTime());
			Assert.assertEquals(popup.size(), 0);
		}
		driver.findElement(By.xpath("//nav[contains(@class, 'navbar-collapse')]//a[text()='Về VNK EDU']")).click();
		Assert.assertTrue(driver.findElement(By.cssSelector("span#Tam_nhin")).isDisplayed());
	}

	public void sleepInSeconds(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String getRunTime() {
		return new Date().toString();
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
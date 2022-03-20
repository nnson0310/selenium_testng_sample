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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_28_Handle_frame_iframe {

	String pageUrl;
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explicitWait;
	Select select;
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
	
	public void TC_01_Iframe() {
		pageUrl = "https://kyna.vn/";
		driver.get(pageUrl);
		//frame va iframe deu dung de nhung html vao website
		//frame: html cua website cung domain hay con goi la cac trang khac cua website
		//iframe: html cua website khac domain
		//de tuong tac frame va iframe ta su dung ham driver.switchTo().frame();
		
		//verify facebook iframe hien thi
		Assert.assertTrue(driver.findElement(By.cssSelector("div.face-content iframe")).isDisplayed());
		
		//switch qua trang facebook
		//vi ban chat cua iframe la nhung html cua website khac nen de tuong tac voi
		//cac the HTML cua cac website nay ta can su dung ham switchTo() cua driver
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content iframe")));
		
		//verify facebook fanpage co so luong like tuong ung
		Assert.assertEquals(driver.findElement(By.xpath("//a[@title='Kyna.vn']//parent::div//following-sibling::div")).getText(), "167K likes");
		
		//click vao chat iframe
		//de switch qua lai giua cac iframe cua cung 1 trang web
		//ta phai switch lai ve website goc bang ham ben duoi roi moi switch duoc den iframe khac duoc
		driver.switchTo().defaultContent();
		
		//switch qua chat iframe
		driver.switchTo().frame(driver.findElement(By.cssSelector("iframe#cs_chat_iframe")));
		
		//click vao khung chat
		driver.findElement(By.cssSelector("div.meshim_widget_Widget")).click();
		
		//nhap du lieu vao cac truong
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("automation");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("0987343434");
		select = new Select(driver.findElement(By.cssSelector("select#serviceSelect")));
		select.selectByVisibleText("HỖ TRỢ KỸ THUẬT");
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys("Hello World");
		
		//switch tro lai main page
		driver.switchTo().defaultContent();
		
		//tim kiem voi tu khoa "Excel"
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys("excel");
		sleepInSeconds(3);
		driver.findElement(By.cssSelector("button.search-button")).click();
		sleepInSeconds(3);
		
		//verify ket qua deu chua tu khoa "Excel"
		List<WebElement> resultTitles = driver.findElements(By.cssSelector("div.content h4"));
		
		for (WebElement resultTitle : resultTitles) {
			Assert.assertTrue(resultTitle.getText().contains("Excel"));
		}
		
	}
	
	@Test
	public void TC_02_Frame() {
		pageUrl = "https://netbanking.hdfcbank.com/netbanking/";
		driver.get(pageUrl);
		
		//switch to frame 
		driver.switchTo().frame(driver.findElement(By.cssSelector("frame[name='login_page']")));
		
		//input vao truong username
		driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys("124234");
		driver.findElement(By.cssSelector("a.login-btn")).click();;
		//verify truong password xuat hien
		Assert.assertTrue(driver.findElement(By.id("fldPasswordDispId")).isDisplayed());
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

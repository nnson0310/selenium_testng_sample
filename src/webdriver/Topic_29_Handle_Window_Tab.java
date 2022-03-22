package webdriver;

import java.util.List;
import java.util.Set;
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

public class Topic_29_Handle_Window_Tab {

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

	public void TC_01() {
		pageUrl = "https://automationfc.github.io/basic-form/index.html";
		driver.get(pageUrl);
		String parentId = driver.getWindowHandle();

		// click google link
		driver.findElement(By.xpath("//label[@for='open window']//following-sibling::a[text()='GOOGLE']")).click();
		sleepInSeconds(3);

		// switch qua tab moi va verify title moi cua trang google
		switchTabByPageTitle("Google");
		Assert.assertEquals(driver.getTitle(), "Google");

		// switch ve parent window
		switchTabByPageTitle("SELENIUM WEBDRIVER FORM DEMO");

		// click facebook link
		driver.findElement(By.xpath("//label[@for='open window']//following-sibling::a[text()='FACEBOOK']")).click();
		sleepInSeconds(3);

		// switch qua tab moi va verify title moi cua trang facebook
		switchTabByPageTitle("Facebook – log in or sign up");
		Assert.assertEquals(driver.getTitle(), "Facebook – log in or sign up");

		// switch ve parent window
		switchTabByPageTitle("SELENIUM WEBDRIVER FORM DEMO");

		// click facebook link
		driver.findElement(By.xpath("//label[@for='open window']//following-sibling::a[text()='TIKI']")).click();
		sleepInSeconds(3);

		// switch qua tab moi va verify title moi cua trang facebook
		switchTabByPageTitle("Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");
		Assert.assertEquals(driver.getTitle(), "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh");

		// switch ve parent window
		switchTabByPageTitle("SELENIUM WEBDRIVER FORM DEMO");

		// close tat ca cac tab ngoai tru parent tab
		closeAllTabsExceptParent(parentId);

	}

	public void TC_02() {
		pageUrl = "https://kyna.vn/";
		driver.get(pageUrl);
		String parentId = driver.getWindowHandle();

		// close popup neu co xuat hien

		// click lan luot cac link tai footer
		// jsExecutor.executeScript("window.open('https://kyna.vn/danh-sach-khoa-hoc',
		// '_blank')")
		driver.findElement(By.xpath("//div[@id='k-footer']//a[text()='Danh sách khóa học']")).click();
		sleepInSeconds(3);
		switchTabByPageTitle("Tổng hợp Tất Cả Khóa Học Online mới nhất tại Kyna");
		Assert.assertEquals(driver.getTitle(), "Tổng hợp Tất Cả Khóa Học Online mới nhất tại Kyna");

		driver.findElement(By.xpath("//div[@id='k-footer']//a[text()='Câu hỏi thường gặp']")).click();
		sleepInSeconds(3);
		switchTabByPageTitle("Câu hỏi thường gặp - Kyna.vn");
		Assert.assertEquals(driver.getTitle(), "Câu hỏi thường gặp - Kyna.vn");

		// switch ve parent window
		switchTabByPageTitle("Kyna.vn - Học online cùng chuyên gia");

		// close tat ca cac tab ngoai tru parent tab
		closeAllTabsExceptParent(parentId);
	}

	@Test
	public void TC_03() {
		pageUrl = "http://live.techpanda.org/";
		driver.get(pageUrl);
		String parentId = driver.getWindowHandle();

		// click vao Mobile Tab
		driver.findElement(By.xpath("//div[@id='header-nav']//a[text()='Mobile']")).click();

		// them san pham sony xperia vao cart (add to compare)
		driver.findElement(By.cssSelector("a[title='Xperia'] + div.product-info a.link-compare")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("ul.messages li.success-msg span")).getText(),
				"The product Sony Xperia has been added to comparison list.");

		// them san pham Samsung Galaxy vao cart (add to compare)
		driver.findElement(By.cssSelector("a[title='Samsung Galaxy'] + div.product-info a.link-compare")).click();
		Assert.assertEquals(driver.findElement(By.cssSelector("ul.messages li.success-msg span")).getText(),
				"The product Samsung Galaxy has been added to comparison list.");
	
		//click compare button
		driver.findElement(By.cssSelector("button[title='Compare']")).click();
		sleepInSeconds(3);
		
		//switch qua cua so moi
		switchTabByPageTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		
	}
	
	// ngoai switch tab bang page title ta con co the switch
		// tab bang link (driver.getCurrentUrl()) tuy nhien vi url co
		// the thay doi nen k recommend dung cach nay
		public void switchTabByPageTitle(String pageTitle) {
			// su dung ham getWindowHandle()
			// de get ra id cua tung tab tung window
			Set<String> tabId = driver.getWindowHandles();

			for (String id : tabId) {
				driver.switchTo().window(id);
				System.out.println(driver.getTitle());
				if (driver.getTitle().equals(pageTitle)) {
					break;
				}
			}
		}

	// dong tat ca cac tab ngoai tru tab ban dau
	public void closeAllTabsExceptParent(String parentId) {

		Set<String> tabId = driver.getWindowHandles();

		// so sanh id cua tung tab voi id cua parent tab (tab ban dau)
		// neu id khac voi id cua parent tab thi switch qua tab do roi close tab do
		// sau do phai switch lai driver ve tab parent
		for (String id : tabId) {
			if (!id.equals(parentId)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}

		// switch driver ve lai tab ban dau
		driver.switchTo().window(parentId);
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
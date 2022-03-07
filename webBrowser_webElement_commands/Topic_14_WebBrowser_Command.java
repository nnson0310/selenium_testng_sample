
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_WebBrowser_Command {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	
	@Test
	public void webBrowserCommands() {
		
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		WebDriver driver = new FirefoxDriver();
		
		//cac tuong tac co the xay ra voi webBrowser
		//la mo, dong, refresh trinh duyet
		//fullscreen, maximize, phong to, thu nho,
		//back, forward
		//close tab, mo tab moi, mo window moi
		
		//mo trinh duyet
		driver.get("https://kenh14.vn/");
		String pageUrl = driver.getCurrentUrl();
		Assert.assertEquals(pageUrl, "https://kenh14.vn/");
		
		//lay title cua trinh duyet
		String pageTitle= driver.getTitle();
		Assert.assertEquals(pageTitle, "Kênh tin tức giải trí - Xã hội - Kenh14.vn");
		
		//maximize window
		driver.manage().window().maximize();
		
		//refresh trinh duyet
		driver.navigate().refresh();
		
		//dong trinh duyet
		driver.close();
	}
	
	@Test
	public void TC_01_Verify_Url() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		WebDriver driver = new FirefoxDriver();
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//div[@class='links']//a[@title='My Account']")).click();
	}
}

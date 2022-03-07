
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_WebElement_Exercise {
	
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_IsDisplayed() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//kiem tra cac phan tu sau co hien thi
		WebElement emailInput = driver.findElement(By.id("mail"));
		Boolean emailIsDisplayed = emailInput.isDisplayed();
		if (emailIsDisplayed) {
			System.out.println("Có hiển thị");
		}
		//sleepInSecond(3);
		emailInput.sendKeys("Automation Testing");
		WebElement radio = driver.findElement(By.id("under_18"));
		radio.isDisplayed();
		radio.click();
		//sleepInSecond(3);
		WebElement educationInput = driver.findElement(By.id("edu"));
		educationInput.isDisplayed();
		educationInput.sendKeys("Automation Testing");
		//sleepInSecond(3);
		//kiem tra phan tu khong hien thi
		Assert.assertFalse(driver.findElement(By.xpath("//div[@class='figcaption']//h5[contains(text(), 'User5')]")).isDisplayed());
	}
	
	@Test
	public void TC_02_IsEnable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//kiem tra cac phan tu sau co enable khong
		System.out.println(driver.findElement(By.id("mail")).isEnabled());
		System.out.println(driver.findElement(By.id("under_18")).isEnabled());
		System.out.println(driver.findElement(By.id("edu")).isEnabled());
		System.out.println(driver.findElement(By.id("job1")).isEnabled());
		System.out.println(driver.findElement(By.id("job2")).isEnabled());
		System.out.println(driver.findElement(By.id("slider-1")).isEnabled());
		
		
		//kiem tra cac phan tu co disable khong
		System.out.println(driver.findElement(By.id("disable_password")).isEnabled());
		System.out.println(driver.findElement(By.id("radio-disabled")).isEnabled());
		System.out.println(driver.findElement(By.id("bio")).isEnabled());
		System.out.println(driver.findElement(By.id("job3")).isEnabled());
		System.out.println(driver.findElement(By.id("check-disbaled")).isEnabled());
		System.out.println(driver.findElement(By.id("slider-2")).isEnabled());
	}
	
	@Test
	public void TC_03_IsSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		//kiem tra cac phan tu sau co selected chua
		WebElement elementA =  driver.findElement(By.id("under_18"));
		elementA.click();
		System.out.println(elementA.isSelected());
		WebElement elementB = driver.findElement(By.id("java"));
		elementB.click();
		System.out.println(elementB.isSelected());
		elementB.click();
		System.out.println(elementB.isSelected());
	}
	
	@Test
	public void TC_04_IntergrationTest() {
		driver.get("https://login.mailchimp.com/signup/");
		
		//nhap du lieu hop le vao email va username
		driver.findElement(By.id("email")).sendKeys("son123@gmail.com");
		sleepInSecond(3);
		driver.findElement(By.id("new_username")).sendKeys("tomanyeuem");
		sleepInSecond(3);
		driver.findElement(By.id("new_password")).sendKeys("T");
		driver.findElement(By.id("new_password")).sendKeys("omanyeuem");
		driver.findElement(By.id("new_password")).sendKeys("@");
		driver.findElement(By.id("new_password")).sendKeys("123");
		sleepInSecond(3);
		System.out.println(driver.findElement(By.id("create-account")).isEnabled());
		sleepInSecond(3);
		WebElement checkbox = driver.findElement(By.id("marketing_newsletter"));
		checkbox.click();
		System.out.println(checkbox.isSelected());
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	//make thread sleep in seconds to delay action
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

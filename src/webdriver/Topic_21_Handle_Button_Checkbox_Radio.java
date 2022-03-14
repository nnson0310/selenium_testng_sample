package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_21_Handle_Button_Checkbox_Radio {

	String pageUrl;
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();

		// cast value of driver to type of javascriptExecutor
		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

//	@Test
//	public void TC_01() {
//		pageUrl = "https://www.fahasa.com/customer/account/create";
//		driver.get(pageUrl);
//
//		By loginButton = By.cssSelector("div.popup-login-content button[title='Đăng nhập']");
//		By username = By.cssSelector("input#login_username");
//		By password = By.cssSelector("input#login_password");
//		
//		// navigate to login tab
//		driver.findElement(By.cssSelector("ul#popup-login-tab_list li.popup-login-tab-login")).click();
//
//		// verify that login button is disabled
//		Assert.assertFalse(driver.findElement(loginButton).isEnabled());
//		
//		//input valid data to credential fields
//		driver.findElement(username).sendKeys("thuminh@gmail.com");
//		driver.findElement(password).sendKeys("123456");
//		
//		//verify that login button is enabled
//		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
//		
//		//convert rgb color to hex color, verify that login button has red background color
//		String loginButtonColor = Color.fromString(driver.findElement(loginButton).getCssValue("background-color")).asHex().toUpperCase();
//		Assert.assertEquals(loginButtonColor, "#C92127");
//		System.out.println(loginButtonColor);
//		
//		//refresh page
//		driver.navigate().refresh();
//		driver.findElement(By.cssSelector("ul#popup-login-tab_list li.popup-login-tab-login")).click();
//		
//		// remove disabled attribute and click login button
//		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(loginButton));
//		Assert.assertTrue(driver.findElement(loginButton).isEnabled());	
//		driver.findElement(loginButton).click();
//
//		// verify that error message is displayed
//		Assert.assertEquals(driver
//				.findElement(By.xpath(
//						"//input[@id='login_username']//parent::div//following-sibling::div[@class='fhs-input-alert']"))
//				.getText(), "Thông tin này không thể để trống");
//		Assert.assertEquals(driver
//				.findElement(By.xpath(
//						"//input[@id='login_password']//parent::div//following-sibling::div[@class='fhs-input-alert']"))
//				.getText(), "Thông tin này không thể để trống");	
//	}
	
	@Test
	public void TC_02() {
		pageUrl = "https://demos.telerik.com/kendo-ui/checkbox/index";
		driver.get(pageUrl);
		
		By dualZoneCheckbox = By.xpath("//label[text()='Dual-zone air conditioning']//preceding-sibling::input");
		
		//click "Dual-zone air conditioning" checkbox
		checkIfSelected(dualZoneCheckbox);
		
		//verify that corresponding checkbox is selected
		Assert.assertTrue(driver.findElement(dualZoneCheckbox).isSelected());
		
		//deselect checkbox and verify that it is unselected
		checkIfSelected(dualZoneCheckbox);
		
		Assert.assertFalse(driver.findElement(dualZoneCheckbox).isSelected());
		
		pageUrl = "https://demos.telerik.com/kendo-ui/radiobutton/index";
		driver.get(pageUrl);
		
		By carEngineCheckbox = By.xpath("//label[text()='2.0 Petrol, 147kW']//preceding-sibling::input");
		
		checkIfSelected(carEngineCheckbox);
	}
	
	public void checkIfSelected(By element) {
		if (!driver.findElement(element).isSelected()) {
			driver.findElement(element).click();
		}
		else if (driver.findElement(element).isSelected()) {
			driver.findElement(element).click();
		}
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
package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_19_Handle_Dropdown {
	
	String pageUrl;
	WebDriver driver;
	Select select, date, month, year;
	String projectPath  = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		
		driver = new FirefoxDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void TestCase_01() {
		pageUrl = "https://www.rode.com/wheretobuy";
		driver.get(pageUrl);
		
		//indentify that dropdown is not multi-select type
		select =  new Select(driver.findElement(By.id("where_country")));
		Assert.assertFalse(select.isMultiple());
		
		//select "VietName" in dropdown list
		select.selectByValue("Vietnam");
		sleepInSeconds(3);
		
		//verify if corresponding value is selected
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Vietnam");
		
		//click search button
		driver.findElement(By.id("search_loc_submit")).click();
		
		//verify if there is 33 results which return back when search with "Vietnam" keyword
		Assert.assertEquals(driver.findElement(By.cssSelector(".result_count span")).getText(), "33");
		
		//print all distributor name
		List<WebElement> storeNames = driver.findElements(By.cssSelector("#search_results .store_name"));
		for (WebElement storeName : storeNames) {
			System.out.println(storeName.getText());
		}
	}
	
	@Test
	public void TestCase_02() {
		pageUrl = "https://demo.nopcommerce.com/register";
		driver.get(pageUrl);
		
		//click register button in header
		driver.findElement(By.className("ico-register")).click();
		
		String email = "thuminh" + generateRandom() + "@gmail.com";
		
		//input info into form
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Thu");
		driver.findElement(By.id("LastName")).sendKeys("Minh");
		
		//handle select
		date = new Select(driver.findElement(By.name("DateOfBirthDay")));
		month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		year = new Select(driver.findElement(By.name("DateOfBirthYear")));
		
		Assert.assertEquals(date.getOptions().size(), 32);
		Assert.assertEquals(month.getOptions().size(), 13);
		Assert.assertEquals(year.getOptions().size(), 112);
		
		//select date=1 , month=May, year=1980
		date.selectByVisibleText("1");
		month.selectByVisibleText("May");
		year.selectByVisibleText("1980");
		
		driver.findElement(By.id("Email")).sendKeys(email);
		driver.findElement(By.id("Company")).sendKeys("Axalize");
		driver.findElement(By.id("Password")).sendKeys("123456");	
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123456");	
		
		//click register button
		driver.findElement(By.id("register-button")).click();
		
		//verify if register successfully
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='result' and text()='Your registration completed']")).isDisplayed());
		
		//click to my account in header
		driver.findElement(By.className("ico-account")).click();
		
		//verify if date, month and year is correct
		date = new Select(driver.findElement(By.name("DateOfBirthDay")));
		month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		year = new Select(driver.findElement(By.name("DateOfBirthYear")));
		Assert.assertEquals(date.getFirstSelectedOption().getText(), "1");
		Assert.assertEquals(month.getFirstSelectedOption().getText(), "May");
		Assert.assertEquals(year.getFirstSelectedOption().getText(), "1980");
	}
	
	public int generateRandom() {
		Random randomNum = new Random();
		return randomNum.nextInt(9999);
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
	
	public void sleepInSeconds(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

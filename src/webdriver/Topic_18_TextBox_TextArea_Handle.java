package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_18_TextBox_TextArea_Handle {
	
	WebDriver driver;
	String accessEmail, pageUrl, userId, accessPassword;
	By btnLogin, addressInput, cityInput, stateInput, pinInput, mobileNumberInput, emailInput;
	String customerId, customerName, gender, dateOfBirth, address, city, state, pin, mobileNumber, email, password;
	String editAddress, editCity, editState, editPin, editMobileNumber, editEmail;
	String projectPath = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		
		driver  = new ChromeDriver();
		accessEmail = "thuminh" + generateRandomNum() + "@gmail.com";
		pageUrl = "http://demo.guru99.com/v4";
		btnLogin = By.cssSelector("input[name='btnLogin']");
		
		//new customer info
		customerName = "Maria";
		gender = "male";
		dateOfBirth = "1994/10/04";
		address = "13 Bo Dao Nha";
		city = "Ha Noi";
		state = "Long Bien";
		pin = "123456";
		mobileNumber = "098234234";
		email = "maria" + generateRandomNum() + "@hotmail.com";
		password = "123456";
		
		//edit customer info
		editAddress = "15 Hoan Kiem";
		editCity = "HCM";
		editState = "Ha Long";
		editPin = "666666";
		editMobileNumber = "09123213";
		editEmail = "maria" + generateRandomNum() + "@sendgrid.com";
		
		driver.manage().window().maximize();                                                                                                                                                                                                                                  
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC_01() {
		driver.get(pageUrl);
		//Click visit here to generate access
		driver.findElement(By.xpath("//a[text()='here']")).click();
		
		//Enter email to generate new access
		driver.findElement(By.name("emailid")).sendKeys(accessEmail);
		driver.findElement(btnLogin).click();
		
		//get userID and password to access page
		userId = driver.findElement(By.xpath("//td[contains(text(), 'User ID')]//following-sibling::td")).getText();
		accessPassword = driver.findElement(By.xpath("//td[contains(text(), 'Password')]//following-sibling::td")).getText();
		
		//return to login page
		driver.get(pageUrl);
		
		//input credentials to access page
		driver.findElement(By.name("uid")).sendKeys(userId);
		driver.findElement(By.name("password")).sendKeys(accessPassword);
		driver.findElement(btnLogin).click();
		
		//verify homepage
		Assert.assertEquals(driver.findElement(By.cssSelector("marquee.heading3")).getText(), "Welcome To Manager's Page of Guru99 Bank");
		
		//click to choose new customer
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		//input new customer info
		driver.findElement(By.name("name")).sendKeys(customerName);
		
		//handle date picker
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('type')", driver.findElement(By.name("dob")));
		driver.findElement(By.name("dob")).sendKeys(dateOfBirth);
		
		//init web element
		addressInput = By.name("addr");
		cityInput = By.name("city");
		stateInput = By.name("state");
		pinInput = By.name("pinno");
		mobileNumberInput = By.name("telephoneno");
		emailInput = By.name("emailid");
		
		//input new customer's info
		driver.findElement(addressInput).sendKeys(address);
		driver.findElement(cityInput).sendKeys(city);
		driver.findElement(stateInput).sendKeys(state);
		driver.findElement(pinInput).sendKeys(pin);
		driver.findElement(mobileNumberInput).sendKeys(mobileNumber);
		driver.findElement(emailInput).sendKeys(email);
		driver.findElement(By.name("password")).sendKeys(password);
		
		//click submit button to register new customer
		driver.findElement(By.name("sub")).click();
		
		//get generated customer id
		customerId = driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText();
		
		//verify all customer info which has been created
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']//following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText(), String.join("-", dateOfBirth.split("\\/")));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText(), mobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText(), email);
		
		//choose edit customer
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		
		//input customerid to access edit form
		driver.findElement(By.name("cusid")).sendKeys(customerId);
		driver.findElement(By.name("AccSubmit")).click();
		
		//verify info in edit form
		Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(By.name("addr")).getAttribute("value"), address);
		
		//input new value to all fields except disabled field
		driver.findElement(addressInput).clear();
		driver.findElement(addressInput).sendKeys(editAddress);
		driver.findElement(cityInput).clear();
		driver.findElement(cityInput).sendKeys(editCity);
		driver.findElement(stateInput).clear();
		driver.findElement(stateInput).sendKeys(editState);
		driver.findElement(pinInput).clear();
		driver.findElement(pinInput).sendKeys(editPin);
		driver.findElement(mobileNumberInput).clear();
		driver.findElement(mobileNumberInput).sendKeys(editMobileNumber);
		driver.findElement(emailInput).clear();
		driver.findElement(emailInput).sendKeys(editEmail);
		driver.findElement(By.name("sub")).click(); 
		//sleepInSeconds(10);
		
	}
	
	
	public int generateRandomNum() {
		Random randomNum = new Random();
		return randomNum.nextInt(9999);
	}
	
	public void sleepInSeconds(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}

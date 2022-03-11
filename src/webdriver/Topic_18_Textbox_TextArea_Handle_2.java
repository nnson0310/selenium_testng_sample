package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.ajbrown.namemachine.NameGenerator;
import org.ajbrown.namemachine.Name;

public class Topic_18_Textbox_TextArea_Handle_2 {
	
	String pageUrl = "https://opensource-demo.orangehrmlive.com/";
	WebDriver driver;
	String[] randomName;
	String projectPath  = System.getProperty("user.dir");
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	@Test
	public void TestCase() {
		driver.get(pageUrl);
		
		By firstName = By.id("personal_txtEmpFirstName");
		By lastName = By.id("personal_txtEmpLastName");
		By id = By.id("personal_txtEmployeeId");
		By saveButton = By.id("btnSave");
		
		//login with credentials
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
		
		//navigate to Add Employee page
		driver.findElement(By.id("menu_pim_viewPimModule")).click();
		driver.findElement(By.id("menu_pim_addEmployee")).click();
		
		//get employee id
		String employeeId = driver.findElement(By.id("employeeId")).getAttribute("value");
		
		//generate random name
		randomName = generateRandomName();
		
		//input firstName, lastName
		driver.findElement(By.id("firstName")).sendKeys(randomName[0]);
		driver.findElement(By.id("lastName")).sendKeys(randomName[1]);
		driver.findElement(saveButton).click();
		
		//verify info
		Assert.assertEquals(driver.findElement(firstName).getAttribute("value"), randomName[0]);
		Assert.assertEquals(driver.findElement(lastName).getAttribute("value"), randomName[1]);
		Assert.assertEquals(driver.findElement(id).getAttribute("value"), employeeId);
		
		//verify input field is disabled
		Assert.assertFalse(driver.findElement(firstName).isEnabled());
		Assert.assertFalse(driver.findElement(lastName).isEnabled());
		Assert.assertFalse(driver.findElement(id).isEnabled());
		
		//click edit button
		driver.findElement(saveButton).click();
		
		//verify input is enabled
		Assert.assertTrue(driver.findElement(firstName).isEnabled());
		Assert.assertTrue(driver.findElement(lastName).isEnabled());
		
		//generate random name
		randomName = generateRandomName();
		
		//input new value
		driver.findElement(firstName).clear();
		driver.findElement(firstName).sendKeys(randomName[0]);
		driver.findElement(lastName).clear();
		driver.findElement(lastName).sendKeys(randomName[1]);
		driver.findElement(saveButton).click();
		
		//verify new updated info
		Assert.assertEquals(driver.findElement(firstName).getAttribute("value"), randomName[0]);
		Assert.assertEquals(driver.findElement(lastName).getAttribute("value"), randomName[1]);
		
		//verify input field is disabled
		Assert.assertFalse(driver.findElement(id).isEnabled());
		Assert.assertFalse(driver.findElement(firstName).isEnabled());
		Assert.assertFalse(driver.findElement(lastName).isEnabled());
	}
	
//	@AfterClass
//	public void AfterClass() {
//		driver.quit();
//	}
	
	public String[] generateRandomName() {
		NameGenerator generator = new NameGenerator();
		List<Name> randomName = generator.generateNames(1);
		String name = randomName.stream().map(Name::toString).collect(Collectors.joining(", "));
		String[] nameCompo = name.split(" ");
		return nameCompo;
	}
}

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

public class Topic_33_Upload_File_By_SendKeys {

	String pageUrl, image1, image2, image3, pathToImage1, pathToImage2, pathToImage3;
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	Alert alert;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();

		jsExecutor = (JavascriptExecutor) driver;
		
		explicitWait = new WebDriverWait(driver, 10);
		
		image1 = "cat_1.jpg";
		image2 = "cat_2.jpg";
		image3 = "cat_3.jpg";
		
		pathToImage1 = projectPath + "\\uploadImages\\" + image1;
		pathToImage2 = projectPath + "\\uploadImages\\" + image2;
		pathToImage3 = projectPath + "\\uploadImages\\" + image3;

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		pageUrl = "https://blueimp.github.io/jQuery-File-Upload/";
		driver.get(pageUrl);
	}
	
	public void TC_01_One_File_Per_Time() {
		//upload one file per time by sendKeys method
		driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(pathToImage1);
		driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(pathToImage2);
		driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(pathToImage3);
		
		//click start button to begin upload
		List<WebElement> uploadButtons = driver.findElements(By.cssSelector("table[role='presentation'] button.start"));
		for (WebElement uploadButton : uploadButtons) {
			uploadButton.click();
		}
		
		//verify images upload successfully
		Assert.assertTrue(driver.findElement(By.cssSelector("table[role='presentation'] p.name a[title='" + image1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("table[role='presentation'] p.name a[title='" + image2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("table[role='presentation'] p.name a[title='" + image3 + "']")).isDisplayed());
	}
	
	@Test
	public void TC_01_Multi_File_Per_Time() {
		//to upload multi file per time we need to use "\n" - special char
		//to separate between links of images
		driver.findElement(By.cssSelector("input[name='files[]']")).sendKeys(pathToImage1 + "\n" + pathToImage2 + "\n" + pathToImage3);
		
		//click start button to begin upload
		List<WebElement> uploadButtons = driver.findElements(By.cssSelector("table[role='presentation'] button.start"));
		for (WebElement uploadButton : uploadButtons) {
			uploadButton.click();
		}
		
		//verify images upload successfully
		Assert.assertTrue(driver.findElement(By.cssSelector("table[role='presentation'] p.name a[title='" + image1 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("table[role='presentation'] p.name a[title='" + image2 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.cssSelector("table[role='presentation'] p.name a[title='" + image3 + "']")).isDisplayed());
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

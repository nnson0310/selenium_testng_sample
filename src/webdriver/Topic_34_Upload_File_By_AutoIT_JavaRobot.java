package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
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

public class Topic_34_Upload_File_By_AutoIT_JavaRobot {

	String pageUrl, image1, image2, image3, pathToImage1, pathToImage2, pathToImage3;
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	Alert alert;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");

		driver = new FirefoxDriver();
		// driver = new ChromeDriver();

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
	}

	public void TC_01_By_AutoIT() {
		pageUrl = "https://blueimp.github.io/jQuery-File-Upload/";
		driver.get(pageUrl);

		// de upload file bang autoIT
		// ta can viet 1 doan script (.exe) de nhung
		// duong dan vao download dialog. Chay script tu dong bang runTime.exec

		// mo upload dialog bang jsExecutor
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("input[name='files[]']")));
		sleepInSeconds(3);

		// paste duong dan nhieu file bang autoIT
		// thay vi throw Exception ta co the dung try catch de
		// catch exception cua Runtime
		try {
			Runtime.getRuntime().exec(projectPath + "\\AutoItScripts\\UploadFile.exe");
		} catch (IOException e) {
			e.printStackTrace();
		}

		// click start button to begin upload
		List<WebElement> uploadButtons = driver.findElements(By.cssSelector("table[role='presentation'] button.start"));
		for (WebElement uploadButton : uploadButtons) {
			uploadButton.click();
		}

		// verify images upload successfully
		Assert.assertTrue(
				driver.findElement(By.cssSelector("table[role='presentation'] p.name a[title='" + image1 + "']"))
						.isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.cssSelector("table[role='presentation'] p.name a[title='" + image2 + "']"))
						.isDisplayed());
	}

	@Test
	public void TC_02_By_RobotClass() throws AWTException {
		pageUrl = "https://blueimp.github.io/jQuery-File-Upload/";
		driver.get(pageUrl);

		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("input[name='files[]']")));
		sleepInSeconds(3);

		// copy duong dan den file vao clipboard
		StringSelection stringSelection = new StringSelection(pathToImage1);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);

		// dung java robot de upload file
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		
		sleepInSeconds(5);;

		// click start button to begin upload
		List<WebElement> uploadButtons = driver.findElements(By.cssSelector("table[role='presentation'] button.start"));
		for (WebElement uploadButton : uploadButtons) {
			uploadButton.click();
		}
		sleepInSeconds(3);

		// verify images upload successfully
		Assert.assertTrue(
				driver.findElement(By.cssSelector("table[role='presentation'] p.name a[title='" + image1 + "']"))
						.isDisplayed());
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

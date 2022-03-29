package webdriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_33_Upload_Download_File {

	String pageUrl, pathToFile;
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

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	// co 4 cach chinh de upload file voi automation
	// C1: su dung method sendKeys() mac dinh cua Selenium - luu y chi su dung voi
	// <input type="file"> - tham so la duong dan den file
	// C2: su dung Java Robot - thu vien mac dinh cua Java de ho tro gia lap cac
	// thao tac cua nguoi dung
	// C3: su dung AutoITScript - nhuoc diem la khong chay duoc neu duong dan den
	// file qua dai
	// C4: su dung thu vien Sikuli - gio it su dung

	public void TC_01_SendKeys() {
		pathToFile = projectPath + "\\files\\image.png";
		pageUrl = "https://demo.guru99.com/test/upload/";
		driver.get(pageUrl);

		driver.findElement(By.cssSelector("input#uploadfile_0")).sendKeys(pathToFile);
		sleepInSeconds(3);
		driver.findElement(By.cssSelector("button#submitbutton")).click();
		sleepInSeconds(3);
		Assert.assertTrue(driver.findElement(By.cssSelector("h3#res center")).getText()
				.contains("has been successfully uploaded."));
	}

	public void TC_02_JavaRobot() throws AWTException {
		pathToFile = projectPath + "\\files\\image.png";
		pageUrl = "https://demo.guru99.com/test/upload/";
		driver.get(pageUrl);

		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("input#uploadfile_0")));
		sleepInSeconds(3);
		uploadFileByRobot(pathToFile);
		sleepInSeconds(3);
		driver.findElement(By.cssSelector("button#submitbutton")).click();
		sleepInSeconds(3);
		Assert.assertTrue(driver.findElement(By.cssSelector("h3#res center")).getText()
				.contains("has been successfully uploaded."));
	}

	// Java Robot la 1 AWT (abtract window toolkit - 1 API de phat trien ung dung
	// dua tren GUI hoac cua so trong Java)
	// package mac dinh cua Java dung de gia lap cac thao tac cua nguoi dung
	// nhuoc diem la kho co the chay tren nhieu trinh duyet vi thao tac upload file
	// tren moi trinh duyet rat khac nhau
	public void uploadFileByRobot(String pathToFile) throws AWTException {
		// tao 1 instance cua class StringSelection, truyen duong dan file
		// duoi dang tham so (String)
		StringSelection stringSelection = new StringSelection(pathToFile);

		// lay clipboard cua he thong
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();

		// luu duong dan file vao clipboard
		clipboard.setContents(stringSelection, null);

		Robot javaRobot = new Robot();
		// an nut enter
		javaRobot.keyPress(KeyEvent.VK_ENTER);
		javaRobot.keyRelease(KeyEvent.VK_ENTER);

		// paste duong dan den file dang luu o clipboard vao
		javaRobot.keyPress(KeyEvent.VK_CONTROL);
		javaRobot.keyPress(KeyEvent.VK_V);
		javaRobot.keyRelease(KeyEvent.VK_CONTROL);
		javaRobot.keyRelease(KeyEvent.VK_V);

		javaRobot.keyPress(KeyEvent.VK_ENTER);
		javaRobot.delay(200);
		javaRobot.keyRelease(KeyEvent.VK_ENTER);

	}
	
	//AutoIt is a scripting tool which can handle many cases that Selenium can not handle like
	//Window GUI and non-HTML popup... but this drawback is AutoIT can only run in window 
	//because it will be generated as .exe file
	@Test
	public void TC_03_AutoIT() throws IOException {
		pageUrl = "https://demo.guru99.com/test/upload/";
		driver.get(pageUrl);

		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("input#uploadfile_0")));
		sleepInSeconds(5);
		
		//su dung Runtime class de tuong tac voi moi truong ma scripts chay
		Runtime.getRuntime().exec(projectPath + "\\files\\FileUpload.exe");
		sleepInSeconds(5);
		
		//click submit button
		driver.findElement(By.cssSelector("button#submitbutton")).click();
		sleepInSeconds(3);
		
		Assert.assertTrue(driver.findElement(By.cssSelector("h3#res center")).getText()
				.contains("has been successfully uploaded."));
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
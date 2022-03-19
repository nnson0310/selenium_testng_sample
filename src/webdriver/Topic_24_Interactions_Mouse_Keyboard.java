package webdriver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
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
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.Colors;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_24_Interactions_Mouse_Keyboard {

	String pageUrl;
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	Actions action;
	Keys key;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();

		jsExecutor = (JavascriptExecutor) driver;
		
		action = new Actions(driver);
		
		explicitWait = new WebDriverWait(driver, 10);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void TC_01() {
		pageUrl = "https://automationfc.github.io/jquery-tooltip/";
		driver.get(pageUrl);

		action.moveToElement(driver.findElement(By.cssSelector("input#age"))).perform();
		Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).isDisplayed());
	}

	public void TC_02() {
		pageUrl = "https://www.myntra.com/";
		driver.get(pageUrl);

		action.moveToElement(driver.findElement(By.cssSelector("div.desktop-navLink a[data-group='kids']"))).perform();
		sleepInSeconds(5);
		action.moveToElement(driver.findElement(By.xpath("//div[@data-group='kids']//a[text()='T-Shirts']"))).click()
				.perform();
		sleepInSeconds(5);

		Assert.assertEquals(driver.findElement(By.cssSelector("h1.title-title")).getText(), "Kids Wear Online Store");
	}

	public void TC_03() {
		pageUrl = "https://www.fahasa.com/";
		driver.get(pageUrl);

		action.moveToElement(driver.findElement(By.cssSelector("span.icon_menu"))).perform();
		sleepInSeconds(5);
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Sách Trong Nước']"))).perform();
		Assert.assertEquals(driver.findElement(By.xpath("//span[text()='Sách Trong Nước']")).getText(),
				"Sách Trong Nước");
	}

	
	public void TC_04() {
		pageUrl = "https://automationfc.github.io/jquery-selectable/";
		driver.get(pageUrl);
		// choose all elements from 1 to 4 and verify that all corresponding elements
		// are selected
		action.moveToElement(driver.findElement(By.xpath("//li[text()='1']"))).clickAndHold().perform();
		action.moveToElement(driver.findElement(By.xpath("//li[text()='4']"))).clickAndHold().release().perform();
		Assert.assertEquals(driver.findElements(By.cssSelector("li.ui-selected")).size(), 4);
	}

	public void TC_05() {
		pageUrl = "https://automationfc.github.io/jquery-selectable/";
		driver.get(pageUrl);
		//indetify os name and choose suitable keys
		//window, linux is CONTROL: ctrl
		//MAC: COMMAND
		if (osName.contains("win") || osName.contains("nux")) {
			key = Keys.CONTROL;
		}
		else {
			key = Keys.COMMAND;
		}
		
		// choose random element like 1,3,11,9,12 and verify all corresponding elements
		// are selected
		List<WebElement> allElements = driver.findElements(By.cssSelector("ol#selectable li"));
		action.keyDown(key).perform();
		action.click(allElements.get(0)).click(allElements.get(3)).click(allElements.get(11)).click(allElements.get(5)).perform();
		action.keyUp(key).perform();
		Assert.assertEquals(driver.findElements(By.cssSelector("li.ui-selected")).size(), 4);
	}
	
	public void TC_06() {
		pageUrl = "https://automationfc.github.io/basic-form/index.html";
		driver.get(pageUrl);
		By dbClickButton = By.xpath("//button[text()='Double click me']");
		
		//chu y tren Firefox phai dung ham cua javascript de scroll xuong element
		//neu khong se bi thrown ra exception MoveTargetOutOfBoundsException: (x, y) is out of bounds of viewport width (x) and height (y)
		//tren chrome thi khong bi
		//action.moveToElement(driver.findElement(By.xpath("//button[text()='Double click me']"))).doubleClick().perform();
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", driver.findElement(dbClickButton));
		action.moveToElement(driver.findElement(dbClickButton)).doubleClick().perform();
		Assert.assertEquals(driver.findElement(By.cssSelector("p#demo")).getText(), "Hello Automation Guys!");
	}
	
	public void TC_07() {
		pageUrl = "http://swisnl.github.io/jQuery-contextMenu/demo.html";
		driver.get(pageUrl);
		
		//thuc hien right click
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();
		action.moveToElement(driver.findElement(By.xpath("//li[contains(@class,'context-menu-icon-quit')]"))).perform();
		Assert.assertTrue(driver.findElement(By.cssSelector("li.context-menu-icon-quit.context-menu-hover.context-menu-visible")).isDisplayed());
		action.click().perform();
		
		//verify if alert is displayed
		Alert alert =  explicitWait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		Assert.assertFalse(driver.findElement(By.cssSelector("li.context-menu-icon-quit")).isDisplayed());
	}
	
	@Test
	public void TC_08() {
		pageUrl = "https://automationfc.github.io/kendo-drag-drop/";
		driver.get(pageUrl);
		
		WebElement smallCircle = driver.findElement(By.id("draggable"));
		WebElement bigCircle = driver.findElement(By.id("droptarget"));
		
		//keo hinh tron nho vao hinh tron lon
		//thuc hien thao tac drag and drop
		action.dragAndDrop(smallCircle, bigCircle).perform();
		//verify text chuyen sang thanh "You did great!"
		//verify bg color chuyen thanh mau xanh
		Assert.assertEquals(bigCircle.getText(), "You did great!");
		String color = Color.fromString(bigCircle.getCssValue("background-color")).asHex().toLowerCase();
		Assert.assertEquals(color, "#03a9f4");
	}
	
	//drag va drop tren HTML5 la 1 case kho va can su dung
	//javascript (trick) de thao tac nen can nhac value va time xem co
	//nen thuc hien hay khong vi rat mat thoi gian va test case co the
	//bi fail vi chay k on dinh
	@Test
	public void TC_09() throws IOException {
		pageUrl = "https://automationfc.github.io/drag-drop-html5/";
		driver.get(pageUrl);
		
		String columnA = "#column-a";
		String columnB = "#column-b";
		
		String jsContent = getContentFile(projectPath + "\\jsHelpers\\drag_and_drop_helper.js");
		
		jsContent = jsContent + "$(\"" + columnA + "\").simulateDragDrop({ dropTarget: \"" + columnB + "\"});";
		jsExecutor.executeScript(jsContent);
		sleepInSeconds(3);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='column-a']/header")).getText(), "B");
	}
	
	//
	public String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	public void sleepInSeconds(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

//	@AfterClass
//	public void AfterClass() {
//		driver.quit();
//	}
}

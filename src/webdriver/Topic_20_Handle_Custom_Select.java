package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_20_Handle_Custom_Select {

	String pageUrl;
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = new FirefoxDriver();

		// waiting time for status of elements: visible/invisible/presence/staleness
		explicitWait = new WebDriverWait(driver, 10);

		jsExecutor = (JavascriptExecutor) driver;

		driver.manage().window().maximize();

		// waiting time for finding elements
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

//	@Test
//	public void TC_Jquery() {
//		pageUrl = "http://jqueryui.com/resources/demos/selectmenu/default.html";
//		driver.get(pageUrl);
//
//		// to interact with custom select
//		// we need to write function to follow exactly as all the steps that end-user
//		// need to do
//
//		// step 1: click the select dropdown to make all options appear
//
//		// step 2: wait until all elements is loaded
//		// note: + Locator phai chua het tat ca cac elements
//		// + Locator phai di den node cuoi cung chua text (chua text cua cac options)
//		// explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".ui-menu-item
//		// div")));
//
//		// get all options
//		// List<WebElement> allOptions =
//		// driver.findElements(By.cssSelector(".ui-menu-item div"));
//
//		// duyet qua tat cac phan tu
//		//		for (WebElement allOption : allOptions) {
//		//			System.out.println(allOption.getText());
//		//			if (allOption.getText().equals("19")) {
//		//				allOption.click();
//		//
//		//				// break ra khoi vong lap de khong xet cac phan tu ke tiep
//		//				break;
//		//			}
//		//		}
//		//		sleepInSeconds(5);
//		
//		handleCustomSelect("span#number-button>span.ui-selectmenu-icon", ".ui-menu-item div", "3");
//		System.out.println(driver.findElement(By.cssSelector("#number-button span.ui-selectmenu-text")).getText());
//		Assert.assertEquals(driver.findElement(By.cssSelector("#number-button span.ui-selectmenu-text")).getText(), "3");
//		
//		handleCustomSelect("span#number-button>span.ui-selectmenu-icon", ".ui-menu-item div", "15");
//		System.out.println(driver.findElement(By.cssSelector("#number-button span.ui-selectmenu-text")).getText());
//		Assert.assertEquals(driver.findElement(By.cssSelector("#number-button span.ui-selectmenu-text")).getText(), "15");
//		
//		handleCustomSelect("span#number-button>span.ui-selectmenu-icon", ".ui-menu-item div", "11");
//		System.out.println(driver.findElement(By.cssSelector("#number-button span.ui-selectmenu-text")).getText());
//		Assert.assertEquals(driver.findElement(By.cssSelector("#number-button span.ui-selectmenu-text")).getText(), "11");
//		
//		handleCustomSelect("span#number-button>span.ui-selectmenu-icon", ".ui-menu-item div", "19");
//		System.out.println(driver.findElement(By.cssSelector("#number-button span.ui-selectmenu-text")).getText());
//		Assert.assertEquals(driver.findElement(By.cssSelector("#number-button span.ui-selectmenu-text")).getText(), "19");
//	}

//	@Test
//	public void TC_ReactJs() {
//		pageUrl = "https://react.semantic-ui.com/maximize/dropdown-example-selection/";
//		driver.get(pageUrl);
//		
//		handleCustomSelect("div.dropdown", "div.item", "Jenny Hess");
//		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Jenny Hess");
//		
//		handleCustomSelect("div.dropdown", "div.item", "Stevie Feliciano");
//		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Stevie Feliciano");
//		
//		handleCustomSelect("div.dropdown", "div.item", "Justen Kitsune");
//		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Justen Kitsune");
//		
//		handleCustomSelect("div.dropdown", "div.item", "Elliot Fu");
//		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Elliot Fu");
//	}

//	@Test
//	public void TC_VueJs() {
//		pageUrl = "https://mikerodham.github.io/vue-dropdowns/";
//		driver.get(pageUrl);
//		
//		handleCustomSelect("li.dropdown-toggle", "ul.dropdown-menu li", "First Option");
//		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "First Option");
//		
//		handleCustomSelect("li.dropdown-toggle", "ul.dropdown-menu li", "Third Option");
//		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), "Third Option");
//	}

//	@Test
//	public void TC_Angular() {
//		pageUrl = "https://tiemchungcovid19.gov.vn/portal/register-person";
//		driver.get(pageUrl);
//
//		handleCustomSelect("ng-select[bindvalue='provinceCode'] span.ng-arrow-wrapper", "div[role='option']>span.ng-option-label",
//				"Tỉnh Lâm Đồng");
//		String selectedProvince = (String) jsExecutor.executeScript(
//				"return document.querySelector(\"ng-select[bindvalue='provinceCode'] span.ng-value-label\").innerText",
//				driver.findElement(By.cssSelector("ng-select[bindvalue='provinceCode'] span.ng-value-label")));
//		//System.out.println(selectedProvince);
//		Assert.assertEquals(selectedProvince, "Tỉnh Lâm Đồng");
//		
//		handleCustomSelect("ng-select[bindvalue='districtCode'] span.ng-arrow-wrapper", "div[role='option']>span.ng-option-label",
//				"Huyện Cát Tiên");
//		String selectedDistrict = (String) jsExecutor.executeScript(
//				"return document.querySelector(\"ng-select[bindvalue='districtCode'] span.ng-value-label\").innerText",
//				driver.findElement(By.cssSelector("ng-select[bindvalue='districtCode'] span.ng-value-label")));
//		Assert.assertEquals(selectedDistrict, "Huyện Cát Tiên");
//		
//		handleCustomSelect("ng-select[bindvalue='wardCode'] span.ng-arrow-wrapper", "div[role='option']>span.ng-option-label",
//				"Xã Gia Viễn");
//		String selectedWard = (String) jsExecutor.executeScript(
//				"return document.querySelector(\"ng-select[bindvalue='wardCode'] span.ng-value-label\").innerText",
//				driver.findElement(By.cssSelector("ng-select[bindvalue='wardCode'] span.ng-value-label")));
//		Assert.assertEquals(selectedWard, "Xã Gia Viễn");
//
//	}

//	@Test
//	public void TC_React_Editable() {
//		pageUrl = "https://react.semantic-ui.com/maximize/dropdown-example-search-selection/";
//		driver.get(pageUrl);
//		
//		driver.findElement(By.cssSelector("input.search")).sendKeys("Afghanistan");
//		driver.findElement(By.cssSelector("div.selected span")).click();
//		
//		Assert.assertEquals(driver.findElement(By.cssSelector("div.divider")).getText(), "Afghanistan");
//
//	}

	@Test
	public void TC_React_Editable() {
		pageUrl = "http://indrimuska.github.io/jquery-editable-select/";
		driver.get(pageUrl);

		driver.findElement(By.cssSelector("div#default-place input")).sendKeys("s");
		handleCustomSelect("div#default-place input", "div#default-place li.es-visible", "Nissan");

		Assert.assertEquals(driver.findElement(By.cssSelector("div#default-place li.selected")).getText(), "Nissan");

	}

	public void sleepInSeconds(long seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void handleCustomSelect(String parentLocator, String locator, String option) {
		// step 1
		driver.findElement(By.cssSelector(parentLocator)).click();
		// sleepInSeconds(3);

		// step 2: wait until all elements is loaded
		// note: + Locator must contains all elements (all options)
		// + Locator phai di den node cuoi cung chua text (chua text cua cac options)
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(locator)));

		// get all options
		List<WebElement> allItems = driver.findElements(By.cssSelector(locator));

		// duyet qua tat cac phan tu
		for (WebElement item : allItems) {
			if (item.getText().equals(option)) {
				// neu element chua visible thi phai scroll xuong de visible
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				
				//wait until element to be clickable
				explicitWait.until(ExpectedConditions.elementToBeClickable(item));

				item.click();
				// sleepInSeconds(3);

				// break ra khoi vong lap de khong xet cac phan tu ke tiep
				break;
			}
		}

	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}

package com.selenium.TestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;


import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestCases {
	WebDriver driver = null;

	@Test(priority = 0)
	public void verifyGmoOnlineLoadedSuccusessfully() {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("inside test1");
		String ActualTitle = driver.findElement(By.xpath("//font[contains(text(),'GMO OnLine')]")).getText();
		System.out.println("tile of gmo online " + ActualTitle);
		String expectedTitle = "GMO OnLine";
		softAssert.assertEquals(ActualTitle, expectedTitle);
		Boolean AcutaEnterGmoOnlineflag = driver.findElement(By.xpath("//input[@name='bSubmit']")).isEnabled();
		Boolean ExpectedEnterGmoOnlineflag = true;
		System.out.println(AcutaEnterGmoOnlineflag);
		Assert.assertEquals(AcutaEnterGmoOnlineflag, ExpectedEnterGmoOnlineflag);
		softAssert.assertAll();
	}

	@Test(priority = 1, dependsOnMethods = { "verifyGmoOnlineLoadedSuccusessfully" }, enabled = false)
	public void verifyEnterGMOOnlineLoadedSuccessfully() {
		waitForPageToLoad(driver);
		WebElement ele = driver.findElement(By.xpath("//input[@name='bSubmit' and @value='Enter GMO OnLine']"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", ele);
		ele.click();
		// driver.findElement(By.xpath("//input[@name='bSubmit' and
		// @value='Enter GMO OnLine']")).click();
		String OnlineCatalogueheading = driver.findElement(By.xpath("//*[contains(text(),'currently')]")).getText();
		System.out.println(OnlineCatalogueheading);
		String Expected = "These are the items currently available through our online catalog. Select the quantity of each item and then press the \"Place An Order\" button at the bottom of the page.";
		/*
		 * if(OnlineCatalogueheading.contains("Place An Order")){ }else{ }
		 */
		System.out.println("Expected: " + Expected);
		Assert.assertEquals(OnlineCatalogueheading, Expected);

	}

	@Test(priority = 2, dependsOnMethods = { "verifyEnterGMOOnlineLoadedSuccessfully" }, enabled = false)
	public void verifyExternalFrameBackpackOrder() {
		waitForPageToLoad(driver);
		try {
			driver.findElement(By.xpath("//input[@name='QTY_BACKPACKS']")).sendKeys("4");
		} catch (Exception e) {
			// Waiting 30 seconds for an element to be present on the page,
			// checking
			// for its presence once every 5 seconds.

			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(90))
					.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

			WebElement element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return driver.findElement(By.xpath("//input[@name='QTY_BACKPACKS']"));
				}
			});
			element.click();
			e.printStackTrace();
		}
		Boolean PlaceAnOrderAcutal = driver.findElement(By.xpath("//input[@name='bSubmit']")).isEnabled();
		Boolean PlaceAnOrderExpected = true;
		Assert.assertEquals(PlaceAnOrderAcutal, PlaceAnOrderExpected);
		driver.findElement(By.xpath("//input[@name='bSubmit']")).click();

		Boolean ProceedWithOrderActual = driver.findElement(By.xpath("//input[@name='bSubmit']")).isEnabled();
		Boolean ProceedWithOrderExpected = true;
		Assert.assertEquals(ProceedWithOrderActual, ProceedWithOrderExpected);
		String ExternalFrameBackpackOrderUnitPrice = driver.findElement(By.xpath("//td[contains(text(),'$ 179.95 ')]"))
				.getText();
		System.out.println(ExternalFrameBackpackOrderUnitPrice);
		String value = ExternalFrameBackpackOrderUnitPrice.substring(2);
		System.out.println("value: " + value);

		float Unitprice = Float.parseFloat(value.trim());
		float ExpectedTotalPrice = Unitprice * 4;
		System.out.println("ExpectedTotalPrice:" + ExpectedTotalPrice);
		String Expected = Float.toString(ExpectedTotalPrice);
		String AutalTotalPrice = driver.findElement(By.xpath("//table/tbody/tr[2]/td[5]")).getText();
		System.out.println("AutalTotalPrice from script: " + AutalTotalPrice);
		float AutalTotalPricefromScript = Float.parseFloat(AutalTotalPrice.substring(2));
		String Actual = Float.toString(AutalTotalPricefromScript);
		System.out.println("AutalTotalPricefromScript:" + AutalTotalPricefromScript);
		Assert.assertEquals(Actual, Expected);

	}

	public static void waitForPageToLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(pageLoadCondition);
	}

	@Test(priority = 3, dependsOnMethods = { "verifyExternalFrameBackpackOrder" }, enabled = false)
	public void HandlingAlerts() {
		driver.navigate().back();
		System.out.println("Inside test case HandlingAlerts");
		driver.findElement(By.xpath("//input[@name='QTY_BACKPACKS']")).clear();
		driver.findElement(By.xpath("//input[@name='bSubmit']")).click();
		Alert alert = driver.switchTo().alert();
		String Alerttext = alert.getText();
		System.out.println(Alerttext);
		Assert.assertEquals(Alerttext, "Please Order Something First");
		alert.accept();
		driver.navigate().to("https://demoqa.com/alerts");
		waitForPageToLoad(driver);
		driver.findElement(By.id("confirmButton")).click();
		Alert alert1 = driver.switchTo().alert();
		alert1.dismiss();
		String ToolsQaAlertResult = driver.findElement(By.className("text-success")).getText();
		System.out.println("ToolsQaAlertResult: " + ToolsQaAlertResult);
		Assert.assertEquals(ToolsQaAlertResult, "You selected Cancel");
		driver.findElement(By.id("promtButton")).click();
		driver.switchTo().alert();
		alert1.sendKeys("hi hello is am fine");
		alert1.accept();
		String promptResultText = driver.findElement(By.xpath("//span[@class='text-success' and @id='promptResult']"))
				.getText();
		Assert.assertEquals(promptResultText, "You entered hi hello is am fine");
	}

	@Test(priority = 4, enabled = false)
	public void HandlingFrames() {
		driver.navigate().to("http://demo.automationtesting.in/Frames.html");
		waitForPageToLoad(driver);
		driver.findElement(By.xpath("//a[@href='#Single']")).click();
		driver.switchTo().frame("SingleFrame");
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("hello");
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//a[contains(text(),'Iframe with in an Iframe')]")).click();
		WebElement ele = driver.findElement(By.xpath("//iframe[@src='MultipleFrames.html']"));
		driver.switchTo().frame(ele);
		WebElement ele1 = driver.findElement(By.xpath("//iframe[@src='SingleFrame.html']"));
		driver.switchTo().frame(ele1);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("inside second frame");
		driver.switchTo().defaultContent();
	}

	@Test(priority = 5, enabled = false)
	public void handlingWindows() {
		driver.navigate().to("http://www.naukri.com/");
		waitForPageToLoad(driver);
		String mainWindow = driver.getWindowHandle();
		Set<String> MultipleWindows = driver.getWindowHandles();
		for (String getsingleWindow : MultipleWindows) {
			driver.switchTo().window(getsingleWindow);
			String Title = driver.getTitle();
			System.out.println(Title);
			if (Title.equals("Sierra Cedar")) {
				driver.manage().window().maximize();
				driver.close();
			} else if (Title.equals("Sykes")) {
				driver.manage().window().maximize();
				driver.close();
			} else if (Title.equals("Fujitsu")) {
				driver.manage().window().maximize();
				driver.close();
			}
		}

	}

	@Test(priority = 6, enabled = false)
	public String handlingWebtable() {
		String Salary = null;
		driver.navigate().to("https://editor.datatables.net/examples/inline-editing/simple");
		waitForPageToLoad(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		String LastNameProvidedByUser = "Vance";
		List<WebElement> AllNames = driver
				.findElements(By.xpath("//div[@class='dataTables_wrapper no-footer']/table/tbody/tr/td[3]"));
		System.out.println(AllNames.size());
		for (int i = 1; i <= AllNames.size(); i++) {
			String lastname = driver
					.findElement(
							By.xpath("//div[@class='dataTables_wrapper no-footer']/table/tbody/tr[" + i + "]/td[3]"))
					.getText();
			System.out.println(lastname);
			if (lastname.equals(LastNameProvidedByUser)) {
				Salary = driver
						.findElement(By
								.xpath("//div[@class='dataTables_wrapper no-footer']/table/tbody/tr[" + i + "]/td[7]"))
						.getText();
				System.out.println(Salary);
				Assert.assertEquals(lastname, LastNameProvidedByUser);
			}

		}
		return Salary;

	}

	@Test(priority = 7)
	public void mouseOperations() {
		// right click operation
		driver.navigate().to("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		waitForPageToLoad(driver);
		Actions action = new Actions(driver);// to perform mouse and key board
												// operations
		WebElement ele = driver.findElement(By.xpath("//span[contains(text(),'right click me')]"));
		action.contextClick(ele).build().perform();// right click operation
		// driver.findElement(By.xpath("//span[contains(text(),'right click
		// me')]")).click();
		driver.findElement(By.xpath("(//span[contains(text(),'Copy')])[2]")).click();
		Alert alert = driver.switchTo().alert();
		// String AlertText=alert.getText();
		System.out.println("AlertText:" + alert.getText());
		Assert.assertEquals(alert.getText(), "clicked: copy");
		alert.accept();
		// end of right click operation validation
		// doubleCLick operation
		driver.navigate().to("https://api.jquery.com/dblclick/");
		waitForPageToLoad(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("window.scrollBy(0,500)"); //for scrolling
		// vertically down
		// js.executeScript("window.scrollBy(0,-500)");//for scrolling
		// vertically up
		// js.executeScript("window.scrollBy(1000,0)");////for scrolling
		// horizontally right side
		// js.executeScript("window.scrollBy(-400,0)");////for scrolling
		// horizontally left side
		// js.executeScript("window.scrollTo(document.body.scrollHeight,0)");//to
		// scroll vertically up completely
		// js.executeScript("0,window.scrollTo(document.body.scrollHeight)");//to
		// scroll vertically down completely
		// js.executeScript("document.querySelector(scroll).scrollLeft=1000");
		WebElement frameElement = driver.findElement(By.xpath("//iframe"));
		driver.switchTo().frame(frameElement);
		WebElement doubleclickElement = driver
				.findElement(By.xpath("//span[contains(text(),'Double click the block')]/preceding::div"));
		js.executeScript("arguments[0].scrollIntoView()", doubleclickElement);// mostly
																				// we
																				// uses
																				// this
		action.doubleClick(doubleclickElement).build().perform();// performs
																	// double
																	// click
																	// operation
		driver.switchTo().defaultContent();
		js.executeScript("window.scrollBy(0,-1000)");
		// drag and drop
		driver.navigate().to("https://jqueryui.com/droppable/");
		waitForPageToLoad(driver);
		js.executeScript("window.scrollBy(0,500)");
		WebElement frameEle = driver.findElement(By.xpath("//iframe"));
		driver.switchTo().frame(frameEle);
		WebElement source = driver.findElement(By.id("draggable"));
		WebElement target = driver.findElement(By.id("droppable"));
		action.dragAndDrop(source, target).build().perform();
		System.out.println(target.getAttribute("background"));
		System.out.println(target.getAttribute("style"));
		System.out.println(target.getAttribute("background-color"));
		System.out.println(target.getCssValue("style"));
		System.out.println(target.getCssValue("color"));
		System.out.println(target.getCssValue("background-color"));
		System.out.println(target.getText());
		Assert.assertEquals(target.getText(), "Dropped!");
		screenShot(driver);

	}

	@Test(priority = 0)
	public void FileUpload() throws AWTException, InterruptedException {
		driver.navigate().to("http://demo.automationtesting.in/FileUpload.html");
		waitForPageToLoad(driver);
	
		// open upload window
		WebElement ele = driver.findElement(By.xpath("//input[@id='input-4']"));
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].scrollIntoView()", ele);
		Actions obj = new Actions(driver);
		obj.click(ele).build().perform();
		System.out.println(System.getProperty("user.dir"));
		
		StringSelection stringSelection = new StringSelection(System.getProperty("user.dir") + "\\src\\test\\ressources\\Sample.jpg");
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
		try {
			Transferable t = clipboard.getContents(null);
			if (t.isDataFlavorSupported(DataFlavor.stringFlavor))
				System.out.println(t.getTransferData(DataFlavor.stringFlavor));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// imitate mouse events like ENTER, CTRL+C, CTRL+V
		Robot robot = new Robot();
		robot.delay(250);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		Thread.sleep(2000);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		Thread.sleep(2000);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(2000);
		// robot.delay(90);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	// helper methods
	public String screenShot(WebDriver driver) {

		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		System.out.println(dateName);
		String destination = System.getProperty("user.dir") + "//src//test//ressources//screenshots//" + dateName
				+ "captured.png";
		System.out.println(destination);
		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destination;
	}

	/*
	 * //@Test(priority=-4,enabled=false)
	 * 
	 * @Test(priority=-4) public void Test2() { System.out.println(
	 * "inside test2");
	 * 
	 * }
	 * 
	 * @Test(priority=4) public void Test3() { System.out.println("inside test3"
	 * ); }
	 * 
	 * @Test(priority=0,invocationCount=6) public void Test4() {
	 * System.out.println("inside test4"); }
	 */

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("inside beforeMethod");
	}

	@AfterMethod
	public void afterMethod() {
		System.out.println("inside afterMethod");
	}

	@BeforeClass
	public void beforeClass() {
		System.out.println("inside beforeClass");
	}

	@AfterClass
	public void afterClass() {
		System.out.println("inside afterClass");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("inside beforeTest");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("inside afterTest");
	}

	@BeforeSuite
	public void beforeSuite() {
		System.out.println("inside beforeSuite");
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();// it maximizes our browser
		// global waiting mechanism .To tell webdriver to wait for all
		// webelements that
		// is declared below the this statement

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.borland.com/gmopost/");

		// driver.findElement(By.name("username")).sendKeys("user@phptravels.com");
		// driver.findElement(By.tagName("input")).sendKeys("user@phptravels.com");

	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("inside afterSuite");
		driver.quit();
	}

}

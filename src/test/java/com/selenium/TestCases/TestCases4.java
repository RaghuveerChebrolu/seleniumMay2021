package com.selenium.TestCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;
import utility.ObjectRepository;
import utility.constants;
import utility.lib;

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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestCases4 extends lib {
	HashMap<String, String> hm = new HashMap<String, String>();

	@Test(priority = 0, enabled = true)
	public void verifyGmoOnlineLoadedSuccusessfully() {
		System.out.println("inside test1");
		SoftAssert softAssert = new SoftAssert();
		Extenttest = ExtentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		String ActualTitle = lib.findElement(driver, ObjectRepository.GMOonlineTitle).getText();
		System.out.println("tile of gmo online " + ActualTitle);
		String expectedTitle = "GMO OnLine";
		softAssert.assertEquals(ActualTitle, expectedTitle);
		Boolean AcutaEnterGmoOnlineflag = lib.findElement(driver, ObjectRepository.EnterGmoOnlineFlag).isEnabled();
		Boolean ExpectedEnterGmoOnlineflag = true;
		System.out.println(AcutaEnterGmoOnlineflag);
		Assert.assertEquals(AcutaEnterGmoOnlineflag, ExpectedEnterGmoOnlineflag);
		softAssert.assertAll();
	}

	@Test(priority = 1, dependsOnMethods = { "verifyGmoOnlineLoadedSuccusessfully" }, enabled = true)
	public void verifyEnterGMOOnlineLoadedSuccessfully() {
		lib.waitForPageToLoad(driver);
		Extenttest = ExtentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		WebElement ele = lib.findElement(driver, ObjectRepository.submitEnterGmoOnline);
		lib.javascriptExecutorScroolIntoView(ele);
		ele.click();
		// driver.findElement(By.xpath("//input[@name='bSubmit' and
		// @value='Enter GMO OnLine']")).click();
		String OnlineCatalogueheading = lib.findElement(driver, ObjectRepository.onlineCatalogueHeading).getText();
		System.out.println(OnlineCatalogueheading);
		String Expected = "These are the items currently available through our online catalog. Select the quantity of each item and then press the \"Place An Order\" button at the bottom of the page.";
		/*
		 * if(OnlineCatalogueheading.contains("Place An Order")){ }else{ }
		 */
		System.out.println("Expected: " + Expected);
		Assert.assertEquals(OnlineCatalogueheading, Expected);

	}

	@Test(priority = 2, dependsOnMethods = { "verifyEnterGMOOnlineLoadedSuccessfully" }, enabled = true)
	public void verifyExternalFrameBackpackOrder() {
		lib.waitForPageToLoad(driver);
		Extenttest = ExtentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		try {
			lib.findElement(driver, ObjectRepository.QTY_BACKPACKS).sendKeys(constants.orderQnty);
		} catch (Exception e) {
			// Waiting 30 seconds for an element to be present on the page,
			// checking
			// for its presence once every 5 seconds.

			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(90))
					.pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

			WebElement element = wait.until(new Function<WebDriver, WebElement>() {
				public WebElement apply(WebDriver driver) {
					return lib.findElement(driver, ObjectRepository.QTY_BACKPACKS);
				}
			});
			element.click();
			e.printStackTrace();
		}
		Boolean PlaceAnOrderAcutal = lib.findElement(driver, ObjectRepository.PlaceAnOrder).isEnabled();
		Boolean PlaceAnOrderExpected = true;
		Assert.assertEquals(PlaceAnOrderAcutal, PlaceAnOrderExpected);
		lib.findElement(driver, ObjectRepository.PlaceAnOrder).click();

		Boolean ProceedWithOrderActual = lib.findElement(driver, ObjectRepository.PlaceAnOrder).isEnabled();
		Boolean ProceedWithOrderExpected = true;
		Assert.assertEquals(ProceedWithOrderActual, ProceedWithOrderExpected);
		String ExternalFrameBackpackOrderUnitPrice = lib
				.findElement(driver, ObjectRepository.ExternalFrameBackpackOrderUnitprice).getText();
		System.out.println(ExternalFrameBackpackOrderUnitPrice);
		String value = ExternalFrameBackpackOrderUnitPrice.substring(2);
		System.out.println("value: " + value);

		float Unitprice = Float.parseFloat(value.trim());
		float ExpectedTotalPrice = Unitprice * Integer.parseInt(constants.orderQnty);
		System.out.println("ExpectedTotalPrice:" + ExpectedTotalPrice);
		String Expected = Float.toString(ExpectedTotalPrice);

		String AutalTotalPrice = lib.findElement(driver, ObjectRepository.AutalTotalPrice).getText();
		System.out.println("AutalTotalPrice from script: " + AutalTotalPrice);
		float AutalTotalPricefromScript = Float.parseFloat(AutalTotalPrice.substring(2));
		String Actual = Float.toString(AutalTotalPricefromScript);
		System.out.println("AutalTotalPricefromScript:" + AutalTotalPricefromScript);
		Assert.assertEquals(Actual, Expected);

	}

	@Test(priority = 3)
	public void HandlingAlerts() {
		Extenttest = ExtentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		/*
		 * driver.navigate().back(); System.out.println(
		 * "Inside test case HandlingAlerts");
		 * driver.findElement(By.xpath("//input[@name='QTY_BACKPACKS']")).clear(
		 * ); driver.findElement(By.xpath("//input[@name='bSubmit']")).click();
		 * Alert alert = driver.switchTo().alert(); String Alerttext =
		 * alert.getText(); System.out.println(Alerttext);
		 * Assert.assertEquals(Alerttext, "Please Order Something First");
		 * alert.accept();
		 */
		// System.out.println(property.getProperty("AlertURL"));
		lib.navigateToUrl("AlertURL", driver);
		lib.waitForPageToLoad(driver);
		lib.findElement(driver, ObjectRepository.AlertConfirmButton).click();
		Alert alert1 = driver.switchTo().alert();
		alert1.dismiss();
		String ToolsQaAlertResult = lib.findElement(driver, ObjectRepository.ToolsQaAlert).getText();
		System.out.println("ToolsQaAlertResult: " + ToolsQaAlertResult);
		Assert.assertEquals(ToolsQaAlertResult, "You selected Cancel");
		lib.findElement(driver, ObjectRepository.promtButton).click();
		driver.switchTo().alert();
		alert1.sendKeys("hi hello is am fine");
		alert1.accept();
		String promptResultText = lib.findElement(driver, ObjectRepository.promptResultText).getText();
		Assert.assertEquals(promptResultText, "You entered hi hello is am fine");
	}

	@Test(priority = 4)
	public void HandlingFrames() {
		Extenttest = ExtentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		lib.navigateToUrl("FramesURL", driver);
		lib.waitForPageToLoad(driver);
		lib.findElement(driver, ObjectRepository.singleFrame).click();
		//driver.switchTo().frame("SingleFrame");
		lib.switchToFrameWithString("SingleFrame");
		lib.findElement(driver, ObjectRepository.singleframeTextbox).sendKeys("hello");
		driver.switchTo().defaultContent();
		lib.findElement(driver, ObjectRepository.frameWithInFrame).click();
		WebElement ele = lib.findElement(driver, ObjectRepository.multipleFrames);
		//driver.switchTo().frame(ele);
		lib.switchToFrameWithWebElement(ele);
		WebElement ele1 = lib.findElement(driver, ObjectRepository.singleframInmultipleFrames);
		//driver.switchTo().frame(ele1);
		lib.switchToFrameWithWebElement(ele1);
		lib.findElement(driver, ObjectRepository.secondframetextbox).sendKeys("inside second frame");
		driver.switchTo().defaultContent();
	}

	@Test(priority = 10)
	public void handlingWindows() {
		Extenttest = ExtentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		lib.navigateToUrl("WindowsURL", driver);
		lib.waitForPageToLoad(driver);
		String mainWindow = driver.getWindowHandle();
		Set<String> MultipleWindows = driver.getWindowHandles();
		for (String getsingleWindow : MultipleWindows) {
			driver.switchTo().window(getsingleWindow);
			String Title = driver.getTitle();
			System.out.println(Title);
			if (Title.equals("JP Morgan")) {
				driver.manage().window().maximize();
				lib.waitForPageToLoad(driver);
				driver.close();
			} else if (Title.equals("Cognizant")) {
				driver.manage().window().maximize();
				lib.waitForPageToLoad(driver);
				driver.close();
			} else if (Title.equals("Fujitsu")) {
				driver.manage().window().maximize();
				lib.waitForPageToLoad(driver);
				driver.close();
			}
		}
		driver.switchTo().window(mainWindow);

	}
	
	@Test(priority = 5)
	public void handlingWebtable() {
		Extenttest = ExtentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		String Salary = null;
		lib.navigateToUrl("WebTableURL", driver);
		lib.waitForPageToLoad(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		String LastNameProvidedByUser = "Vance";
		List<WebElement> AllNames = lib.findElements(driver, ObjectRepository.webtable);
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
				System.out.println("Salary: "+Salary);
				Assert.assertEquals(lastname, LastNameProvidedByUser);
			}

		}
		//return Salary;

	}

	@Test(priority = 7)
	public void mouseOperations() {
		Extenttest = ExtentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		// right click operation
		lib.navigateToUrl("mouseOpeartionRightClick", driver);
		lib.waitForPageToLoad(driver);
		Actions action = new Actions(driver);// to perform mouse and key board
												// operations
		WebElement ele = lib.findElement(driver, ObjectRepository.rightClick);
		action.contextClick(ele).build().perform();// right click operation
		// driver.findElement(By.xpath("//span[contains(text(),'right click
		// me')]")).click();
		lib.findElement(driver, ObjectRepository.copy).click();
		Alert alert = driver.switchTo().alert();
		// String AlertText=alert.getText();
		System.out.println("AlertText:" + alert.getText());
		Assert.assertEquals(alert.getText(), "clicked: copy");
		alert.accept();
		// end of right click operation validation
		// doubleCLick operation
		lib.navigateToUrl("mouseOpeartionDoubleClick", driver);
		// driver.navigate().to(property.getProperty("mouseOpeartionDoubleClick"));
		lib.waitForPageToLoad(driver);
		
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
		WebElement frameElement = lib.findElement(driver, ObjectRepository.frame);
		driver.switchTo().frame(frameElement);
		
		WebElement doubleclickElement = lib.findElement(driver, ObjectRepository.doubleclick);
		lib.javascriptExecutorScroolIntoView(doubleclickElement,action);
		//js.executeScript("arguments[0].scrollIntoView()", doubleclickElement);// mostly
																				// we
																				// uses
																				// this
		//action.doubleClick(doubleclickElement).build().perform();// performs
																	// double
																	// click
																	// operation
		driver.switchTo().defaultContent();
		//js.executeScript("window.scrollBy(0,-1000)");
		lib.javascriptExecutorScroolUP(driver, 0, -1000);
		// drag and drop
		lib.navigateToUrl("mouseOperationDragAndDrop", driver);
		// driver.navigate().to(property.getProperty("mouseOperationDragAndDrop"));
		lib.waitForPageToLoad(driver);
		//js.executeScript("window.scrollBy(0,500)");
		lib.javascriptExecutorScroolDown(driver, 0, 500);
		WebElement frameEle = lib.findElement(driver, ObjectRepository.frame);
		//driver.switchTo().frame(frameEle);
		lib.switchToFrameWithWebElement(frameEle);
		WebElement source = lib.findElement(driver, ObjectRepository.draggable);
		WebElement target = lib.findElement(driver, ObjectRepository.droppable);
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

	@Test(priority = 6)
	public void FileUpload() throws AWTException, InterruptedException {
		Extenttest = ExtentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		lib.navigateToUrl("FileUpload", driver);
		lib.waitForPageToLoad(driver);

		// open upload window
		WebElement ele = lib.findElement(driver, ObjectRepository.fileUpload);
		// JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].scrollIntoView()", ele);
		Actions obj = new Actions(driver);
		obj.click(ele).build().perform();
		System.out.println(System.getProperty("user.dir"));

		StringSelection stringSelection = new StringSelection(
				System.getProperty("user.dir") + "\\src\\test\\ressources\\Sample.jpg");
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

	@Test(priority=9)
	public void DataDrivenAutomationDemoSite() throws Exception{
		Extenttest = ExtentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		lib.navigateToUrl("AutomationRegister", driver);
		lib.waitForPageToLoad(driver);
		try {
			File objFile= new File(System.getProperty("user.dir") + "//src/test//ressources//AutomationDemoSite.xlsx");
			FileInputStream objFileInput = new FileInputStream(objFile);
					
			System.out.println("objFileFileInputStream: " + objFileInput);
			XSSFWorkbook objXSSFWorkbook = new XSSFWorkbook(objFileInput);
			XSSFSheet objXSSFSheet = objXSSFWorkbook.getSheet("TestData");
			int RowsCount = objXSSFSheet.getLastRowNum();
			System.out.println("RowsCount: " + RowsCount);
			for (int rowNumber = 1; rowNumber <= RowsCount; rowNumber++) {
				readTestData(rowNumber, objXSSFSheet);
				if (hm.get("RunMode").equals("Yes")) {
					
					/*if (rowNumber>1){
						boolean closeiconLanguage = lib.findElement(driver, ObjectRepository.AutoamtionRegistercloseIcon).isDisplayed();
						if(closeiconLanguage==true){
							lib.findElement(driver, ObjectRepository.AutoamtionRegistercloseIcon).click();
						}
					}*/
					
					if (rowNumber>1){
						String Identifier= ObjectRepository.AutoamtionRegistercloseIcon;
						String locator = Identifier.split("&")[0].trim();
						String description = Identifier.split("&")[1].trim();
						 if (locator.equals("xpath")) {
							 boolean closeiconLanguage=driver.findElement(By.xpath(description)).isDisplayed();
						if(closeiconLanguage==true){
							driver.findElement(By.xpath(description)).click();
						}
						 }
					}
					
					//driver.findElement(By.xpath("(//input[@type='text'])[1]")).clear();
					//driver.findElement(By.xpath("(//input[@type='text'])[1]")).sendKeys(hm.get("FirstName"));
					
					lib.findElement(driver, ObjectRepository.AutoamtionRegisterFirstName).clear();
					lib.findElement(driver, ObjectRepository.AutoamtionRegisterFirstName).sendKeys(hm.get("FirstName"));
					
					lib.findElement(driver, ObjectRepository.AutoamtionRegisterLastName).clear();
					lib.findElement(driver, ObjectRepository.AutoamtionRegisterLastName).sendKeys(hm.get("LastName"));
					
					lib.findElement(driver, ObjectRepository.AutoamtionRegisterAddress).clear();
					lib.findElement(driver, ObjectRepository.AutoamtionRegisterAddress).sendKeys(hm.get("Address"));
					
					lib.findElement(driver, ObjectRepository.AutoamtionRegisteremail).clear();
					lib.findElement(driver, ObjectRepository.AutoamtionRegisteremail).sendKeys(hm.get("EmailAddress"));
					
					
					lib.findElement(driver, ObjectRepository.AutoamtionRegisterphonenumber).clear();
					lib.findElement(driver, ObjectRepository.AutoamtionRegisterphonenumber).sendKeys(hm.get("PhoneNumber"));
					
					if(hm.get("Gender").equalsIgnoreCase("Male")){
						
						lib.findElement(driver, ObjectRepository.AutoamtionRegisterGenderMale).click();
					}else if(hm.get("Gender").equalsIgnoreCase("female")){
						
						lib.findElement(driver, ObjectRepository.AutoamtionRegisterGenderFemale).click();
					}
					
					if(hm.get("Hobbies").equalsIgnoreCase("cricket")){
						lib.findElement(driver, ObjectRepository.AutoamtionRegisterHobbiesCricket).click();
					}else if(hm.get("Hobbies").equalsIgnoreCase("movies")){
						lib.findElement(driver, ObjectRepository.AutoamtionRegisterHobbiesMovies).click();
					}else if(hm.get("Hobbies").equalsIgnoreCase("hockey")){
						lib.findElement(driver, ObjectRepository.AutoamtionRegisterHobbiesHockey).click();
					}
					String Langdropdown = ObjectRepository.AutoamtionRegisterLangDrpDwn;
					System.out.println(Langdropdown);
					String LangdrpdwnValue= ObjectRepository.AutoamtionRegisterlanguagevalue;
					System.out.println(LangdrpdwnValue);
					SelectValueFromDropdown(driver,Langdropdown,LangdrpdwnValue,hm.get("Languages"));
					
					lib.findElement(driver, ObjectRepository.AutoamtionRegisterLastName).click();
					SelectValueFromDropdown(driver,ObjectRepository.AutoamtionRegisterskills,hm.get("Skills"));
					SelectValueFromDropdown(driver,ObjectRepository.AutoamtionRegisterCountry,hm.get("Country"));
					
					
					
					boolean selectcountry = lib.findElement(driver, ObjectRepository.AutoamtionRegisterselectCountry).isEnabled();
					if(selectcountry==true){
						WebElement ele= lib.findElement(driver, ObjectRepository.AutoamtionRegisterselectCountry);
						WebDriverWait wait = new WebDriverWait(driver,90);
						wait.until(ExpectedConditions.elementToBeClickable(ele));
						ele.click();
						lib.findElement(driver, ObjectRepository.AutoamtionRegisterselectcountryTextbox).sendKeys(hm.get("SelectCountry"));
					}
					
					
					SelectValueFromDropdown(driver,ObjectRepository.AutoamtionRegisterDOBYY,hm.get("DOB_YY"));
					SelectValueFromDropdown(driver,ObjectRepository.AutoamtionRegisterDOBMM,hm.get("DOB_MM"));
					SelectValueFromDropdown(driver,ObjectRepository.AutoamtionRegisterDOBDD,hm.get("DOB_DD"));
					
					lib.findElement(driver, ObjectRepository.AutoamtionRegisterfirstpassword).clear();
					lib.findElement(driver, ObjectRepository.AutoamtionRegisterfirstpassword).sendKeys(hm.get("Password"));
					
					lib.findElement(driver, ObjectRepository.AutoamtionRegistersecondpassword).clear();
					lib.findElement(driver, ObjectRepository.AutoamtionRegistersecondpassword).sendKeys(hm.get("ConfirmPassword"));
					
					FileOutputStream objfileoutput = new FileOutputStream(objFile);
					
					WriteTheResultToExcel(objXSSFWorkbook,rowNumber);
					objXSSFWorkbook.write(objfileoutput);
					objfileoutput.close();
				} else {
					System.out.println("RunMode is not marked as Yes for row number " + rowNumber);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test(priority = 8)
	public void brokenLinks() {
		Extenttest = ExtentReport.createTest(new Object() {}.getClass().getEnclosingMethod().getName());
		driver.get("https://demoqa.com/broken");
		// driver.findElement(By.xpath("https://editor.datatables.net/examples/inline-editing/simple")).click();

		// Storing the links in a list and traversing through the links
		List<WebElement> links1 = lib.findElements(driver, ObjectRepository.brokenlinks);

		// This line will print the number of links and the count of links.
		System.out.println("No of links are " + links1.size());

		// checking the links fetched.
		for (int i = 0; i < links1.size(); i++) {
			WebElement E1 = links1.get(i);
			String url = E1.getAttribute("href");
			System.out.println("url from browser : " + url);
			verifyLinks(url);
		}
	}

	// helper methods

	private static void verifyLinks(String linkUrl) {
		try {
			URL url1 = new URL(linkUrl);

			// Now we will be creating url connection and getting the response
			// code
			HttpURLConnection httpURLConnect = (HttpURLConnection) url1.openConnection();
			httpURLConnect.setConnectTimeout(5000);
			httpURLConnect.connect();
			int responsecode = httpURLConnect.getResponseCode();
			if (responsecode >= 400) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " is a broken link");
			}

			// Fetching and Printing the response code obtained
			else if (responsecode >= 200 && responsecode < 400) {
				System.out.println(linkUrl + " - " + httpURLConnect.getResponseMessage() + " is a valid link");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void WriteTheResultToExcel(XSSFWorkbook objXSSFWorkbook, int rowNumber) {
		XSSFSheet objSheet = objXSSFWorkbook.getSheet("TestData");
		XSSFCellStyle CellStyle = objXSSFWorkbook.createCellStyle();
		// CellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
		System.out.println("Row Number in excel is :" + rowNumber);

		objSheet.getRow(rowNumber).createCell(18).setCellValue("PASS");
		objSheet.getRow(rowNumber).getCell(18).setCellStyle(CellStyle);

	}

	private void readTestData(int rowNumber, XSSFSheet objXSSFSheet) {
		DataFormatter DataFormatterObj = new DataFormatter();
		hm.put("RunMode", objXSSFSheet.getRow(rowNumber).getCell(0).getStringCellValue());
		hm.put("TestCaseName", objXSSFSheet.getRow(rowNumber).getCell(1).getStringCellValue());
		hm.put("FirstName", objXSSFSheet.getRow(rowNumber).getCell(2).getStringCellValue());
		hm.put("LastName", objXSSFSheet.getRow(rowNumber).getCell(3).getStringCellValue());
		hm.put("Address", objXSSFSheet.getRow(rowNumber).getCell(4).getStringCellValue());
		hm.put("EmailAddress", objXSSFSheet.getRow(rowNumber).getCell(5).getStringCellValue());
		String phoneNumber = DataFormatterObj.formatCellValue(objXSSFSheet.getRow(rowNumber).getCell(6));
		hm.put("PhoneNumber", phoneNumber);
		hm.put("Gender", objXSSFSheet.getRow(rowNumber).getCell(7).getStringCellValue());
		hm.put("Hobbies", objXSSFSheet.getRow(rowNumber).getCell(8).getStringCellValue());
		hm.put("Languages", objXSSFSheet.getRow(rowNumber).getCell(9).getStringCellValue());
		hm.put("Skills", objXSSFSheet.getRow(rowNumber).getCell(10).getStringCellValue());
		hm.put("Country", objXSSFSheet.getRow(rowNumber).getCell(11).getStringCellValue());
		hm.put("SelectCountry", objXSSFSheet.getRow(rowNumber).getCell(12).getStringCellValue());

		String DOB_YY = DataFormatterObj.formatCellValue(objXSSFSheet.getRow(rowNumber).getCell(13));
		hm.put("DOB_YY", DOB_YY);

		hm.put("DOB_MM", objXSSFSheet.getRow(rowNumber).getCell(14).getStringCellValue());

		String DOB_DD = DataFormatterObj.formatCellValue(objXSSFSheet.getRow(rowNumber).getCell(15));
		hm.put("DOB_DD", DOB_DD);

		hm.put("Password", objXSSFSheet.getRow(rowNumber).getCell(16).getStringCellValue());
		hm.put("ConfirmPassword", objXSSFSheet.getRow(rowNumber).getCell(17).getStringCellValue());

	}

	/*// return test case name
	private String testcaseName() {
		String NameoftTestCase = new Object() {}.getClass().getEnclosingMethod().getName();
		return NameoftTestCase;
	}*/

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
	public void afterMethod(ITestResult result) throws Exception {
		System.out.println("inside afterMethod");
		if (result.getStatus() == ITestResult.FAILURE) {
			Extenttest.log(Status.FAIL, "Test Case Failed is : " + result.getName());
			Extenttest.log(Status.FAIL, "Test Case Failed is : " + result.getThrowable());
			String SSpath = lib.takescreeshot(driver, result.getName());
			Extenttest.addScreenCaptureFromPath(SSpath);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			Extenttest.log(Status.PASS, "Test Case pass is : " + result.getName());
		} else if (result.getStatus() == ITestResult.SKIP) {
			Extenttest.log(Status.SKIP, "Test Case Skipped is : " + result.getName());
		}

	}

	@BeforeClass
	public void beforeClass() throws AWTException, InterruptedException {
		System.out.println("inside beforeClass");
		lib.launchBrowser();
	}

	@AfterClass
	public void afterClass() {
		System.out.println("inside afterClass");
	}

	@BeforeTest
	public void beforeTest() {
		System.out.println("inside beforeTest");
		lib.startReport();
	}

	@AfterTest
	public void afterTest() {
		System.out.println("inside afterTest");
		ExtentReport.flush();
	}

	@BeforeSuite
	public void beforeSuite() throws IOException {
		System.out.println("inside beforeSuite");
		lib.readingConfigurationProprtyFile();
	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("inside afterSuite");
		driver.quit();
	}

}

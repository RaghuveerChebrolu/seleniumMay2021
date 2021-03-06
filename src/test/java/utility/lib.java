package utility;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class lib {
	public static Properties property = new Properties();
	public static WebDriver driver;
	public static ExtentHtmlReporter htmlReporter;
	public static ExtentReports ExtentReport;
	public static ExtentTest Extenttest;

	/*
	 * ExtentHtmlReporter : responsible for look and feel of the report ,we can
	 * specify the report name , document title , theme of the report.
	 * 
	 * ExtentReports : used to create entries in your report , create test cases
	 * in report , who executed the test case, environment name , browser
	 * 
	 * ExtentTest : update pass fail and skips and logs the test cases results
	 */

	public static void readingConfigurationProprtyFile() throws IOException {
		FileInputStream objFileInput = new FileInputStream(
				new File(System.getProperty("user.dir") + "//src/test//ressources//configurationProperty.properties"));
		property.load(objFileInput);

	}

	/*
	 * Raghu: Below method is used to start the report and store the
	 * configuration information inside the report
	 */
	public static void startReport() {
		System.out.println("Extent report started");
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "//test-output//ExtentReportV4.html");
		htmlReporter.config().setDocumentTitle("Automation Report-ExtentReportV4.0");
		htmlReporter.config().setReportName("Functional Testing-ExtentReportV4.0");
		htmlReporter.config().setTheme(Theme.STANDARD);

		ExtentReport = new ExtentReports();
		ExtentReport.attachReporter(htmlReporter);
		ExtentReport.setSystemInfo("Host Name", "localhost");
		ExtentReport.setSystemInfo("user", "raghu");
		ExtentReport.setSystemInfo("Environment", property.getProperty("environment"));
		ExtentReport.setSystemInfo("Browser", property.getProperty("browser"));
	}

	public static void launchBrowser() throws AWTException, InterruptedException {
		if (property.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (property.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (property.getProperty("browser").equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (property.getProperty("browser").equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else if (property.getProperty("browser").equalsIgnoreCase("opera")) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		}
		driver.manage().window().maximize();// it maximizes our browser
		// global waiting mechanism .To tell web driver to wait for all
		// webelements that
		// is declared below the this statement
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		if (property.getProperty("environment").equalsIgnoreCase("SIT")) {
			System.out.println(property.getProperty("GmoOnloneURL_SIT"));
			driver.get(property.getProperty("GmoOnloneURL_SIT"));
			waitForPageToLoad(driver);
			boolean flag = driver.findElement(By.xpath("//*[@id='primary-button']")).isDisplayed();
			if (flag == true) {
				
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				/*driver.findElement(By.xpath("//*[@id=details-button']")).click();
				driver.findElement(By.xpath("//*[@id='proceed-link']")).click();
				waitForPageToLoad(driver);*/
			}
		} else if (property.getProperty("environment").equalsIgnoreCase("UAT")) {
			System.out.println(property.getProperty("GmoOnloneURL_UAT"));
			driver.get(property.getProperty("GmoOnloneURL_UAT"));
			waitForPageToLoad(driver);
			boolean flag = driver.findElement(By.xpath("//*[@id='primary-button']")).isDisplayed();
			if (flag == true) {
				Robot robot = new Robot();
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				/*WebElement ele=driver.findElement(By.xpath("//*[@id='primary-button']"));
				ele.sendKeys(Keys.TAB);
				Thread.sleep(2000);
				ele.sendKeys(Keys.TAB);
				ele.sendKeys(Keys.ENTER)*/;
				/*driver.findElement(By.xpath("//*[@id=details-button']")).click();
				driver.findElement(By.xpath("//*[@id='proceed-link']")).click();
				waitForPageToLoad(driver);*/
			}
		}

	}

	public static void navigateToUrl(String URL, WebDriver driver) {
		switch (URL) {
		case "AlertURL":
			System.out.println(property.getProperty("AlertURL"));
			driver.navigate().to(property.getProperty("AlertURL"));
			break;
		case "FramesURL":
			driver.navigate().to(property.getProperty("FramesURL"));
			break;
		case "WindowsURL":
			driver.navigate().to(property.getProperty("WindowsURL"));
			break;
		case "WebTableURL":
			driver.navigate().to(property.getProperty("WebTableURL"));
			break;
		case "mouseOpeartionRightClick":
			driver.navigate().to(property.getProperty("mouseOpeartionRightClick"));
			break;
		case "mouseOpeartionDoubleClick":
			driver.navigate().to(property.getProperty("mouseOpeartionDoubleClick"));
			break;
		case "mouseOperationDragAndDrop":
			driver.navigate().to(property.getProperty("mouseOperationDragAndDrop"));
			break;
		case "FileUpload":
			driver.navigate().to(property.getProperty("FileUpload"));
			break;
		case "AutomationRegister":
			driver.navigate().to(property.getProperty("AutomationRegister"));
			break;
		default:
			System.out.println("this is default switch satement");
		}
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

	public static String takescreeshot(WebDriver driver, String name) throws Exception {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		System.out.println(dateName);
		String destination = System.getProperty("user.dir") + "//src-test-ressources//screenshots//" + dateName + name
				+ "captured.png";
		FileUtils.copyFile(source, new File(destination));
		return destination;
	}

	protected static void SelectValueFromDropdown(WebDriver driver, String xpath, String dropdownvalue) {
		String locator = xpath.split("&")[0].trim();
		String description = xpath.split("&")[1].trim();
		By search = null;
		if (locator.equals("id")) {
			search = By.id(description);
		} else if (locator.equals("name")) {
			search = By.name(description);
		} else if (locator.equals("classname")) {
			search = By.className(description);
		} else if (locator.equals("linktext")) {
			search = By.linkText(description);
		} else if (locator.equals("partiallinktext")) {
			search = By.partialLinkText(description);
		} else if (locator.equals("xpath")) {
			search = By.xpath(description);
		} else if (locator.equals("tagname")) {
			search = By.tagName(description);
		} else if (locator.equals("css")) {
			search = By.cssSelector(description);
		}
		WebElement objweb = driver.findElement(search);
		objweb.click();
		Select objselect = new Select(objweb);
		objselect.selectByVisibleText(dropdownvalue);

	}
	
	/*protected static void SelectValueFromDropdown(WebDriver driver, String xpath, String xpath2, String dropdownvalue) {
		driver.findElement(By.xpath(xpath)).click();
		List<WebElement> AllValues = driver.findElements(By.xpath(xpath2));
		System.out.println(AllValues.size());
		for (int i = 0; i < AllValues.size(); i++) {
			String language = AllValues.get(i).getText();
			System.out.println(language);
			if (language.equals(dropdownvalue)) {
				AllValues.get(i).click();
			}
		}
	}*/

	protected static void SelectValueFromDropdown(WebDriver driver, String xpath, String xpath2, String dropdownvalue) {
		String locator = xpath.split("&")[0].trim();
		String description = xpath.split("&")[1].trim();
		By search = null;
		if (locator.equals("id")) {
			search = By.id(description);
		} else if (locator.equals("name")) {
			search = By.name(description);
		} else if (locator.equals("classname")) {
			search = By.className(description);
		} else if (locator.equals("linktext")) {
			search = By.linkText(description);
		} else if (locator.equals("partiallinktext")) {
			search = By.partialLinkText(description);
		} else if (locator.equals("xpath")) {
			search = By.xpath(description);
		} else if (locator.equals("tagname")) {
			search = By.tagName(description);
		} else if (locator.equals("css")) {
			search = By.cssSelector(description);
		}
		driver.findElement(search).click();
		String locator1 = xpath2.split("&")[0].trim();
		String description1 = xpath2.split("&")[1].trim();
		By search1 = null;
		if (locator.equals("id")) {
			search1 = By.id(description1);
		} else if (locator.equals("name")) {
			search1 = By.name(description1);
		} else if (locator.equals("classname")) {
			search1 = By.className(description1);
		} else if (locator.equals("linktext")) {
			search1 = By.linkText(description1);
		} else if (locator.equals("partiallinktext")) {
			search1 = By.partialLinkText(description1);
		} else if (locator.equals("xpath")) {
			search1 = By.xpath(description1);
		} else if (locator.equals("tagname")) {
			search1 = By.tagName(description1);
		} else if (locator.equals("css")) {
			search1 = By.cssSelector(description1);
		}
		List<WebElement> AllValues = driver.findElements(search1);
		System.out.println(AllValues.size());
		for (int i = 0; i < AllValues.size(); i++) {
			String language = AllValues.get(i).getText();
			System.out.println(language);
			if (language.equals(dropdownvalue)) {
				AllValues.get(i).click();
			}
		}
	}
	
	public static WebElement findElement(WebDriver driver, String Identifier) {
		WebElement element = null;
		WebDriverWait wait1 = new WebDriverWait(driver, 90);

		By search = null;
		// System.out.println("Identifier : "+Identifier);
		String locator = Identifier.split("&")[0].trim();
		String description = Identifier.split("&")[1].trim();
		if (locator.equals("id")) {
			search = By.id(description);
		} else if (locator.equals("name")) {
			search = By.name(description);
		} else if (locator.equals("classname")) {
			search = By.className(description);
		} else if (locator.equals("linktext")) {
			search = By.linkText(description);
		} else if (locator.equals("partiallinktext")) {
			search = By.partialLinkText(description);
		} else if (locator.equals("xpath")) {
			search = By.xpath(description);
		} else if (locator.equals("tagname")) {
			search = By.tagName(description);
		} else if (locator.equals("css")) {
			search = By.cssSelector(description);
		}
		System.out.println("locator : " + locator);
		System.out.println("description : " + description);
		// return element;
		// element=driver.findElement(search);
		wait1.until(ExpectedConditions.elementToBeClickable(search));
		return driver.findElement(search);

	}
	
	public static List<WebElement> findElements(WebDriver driver, String Identifier) {
		WebElement element = null;
		WebDriverWait wait1 = new WebDriverWait(driver, 90);

		By search = null;
		// System.out.println("Identifier : "+Identifier);
		String locator = Identifier.split("&")[0].trim();
		String description = Identifier.split("&")[1].trim();
		if (locator.equals("id")) {
			search = By.id(description);
		} else if (locator.equals("name")) {
			search = By.name(description);
		} else if (locator.equals("classname")) {
			search = By.className(description);
		} else if (locator.equals("linktext")) {
			search = By.linkText(description);
		} else if (locator.equals("partiallinktext")) {
			search = By.partialLinkText(description);
		} else if (locator.equals("xpath")) {
			search = By.xpath(description);
		} else if (locator.equals("tagname")) {
			search = By.tagName(description);
		} else if (locator.equals("css")) {
			search = By.cssSelector(description);
		}
		System.out.println("locator : " + locator);
		System.out.println("description : " + description);
		// return element;
		// element=driver.findElement(search);
		wait1.until(ExpectedConditions.elementToBeClickable(search));
		return driver.findElements(search);	

	}
	
	public static void javascriptExecutorScroolDown(String str) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(str);
	}

	public static void javascriptExecutorScroolDown(WebDriver driver,int i, int j) {
		//Object x = (Integer) j;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + Integer.toString(i) + "," + Integer.toString(j) + ")");
		 
	}

	public static void javascriptExecutorScroolUP(WebDriver driver,String str) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript(str);
	}

	public static void javascriptExecutorScroolUP(WebDriver driver,int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + Integer.toString(x) + "," + Integer.toString(y) + ")", "");
	}

	public static void javascriptExecutorScroolLeft(WebDriver driver,int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + Integer.toString(x) + "," + Integer.toString(y) + ")", "");
	}

	public static void javascriptExecutorScroolRight(WebDriver driver,int x, int y) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(" + Integer.toString(x) + "," + Integer.toString(y) + ")", "");
	}

	public static void javascriptExecutorScroolIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
	}

	public static void javascriptExecutorScroolIntoView(WebElement element, Actions obj) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView()", element);
		obj.doubleClick(element).build().perform();
	}

	public static void switchToFrameWithIndex(int index) {
		driver.switchTo().frame(index);
	}

	public static void switchToFrameWithString(String name) {
		driver.switchTo().frame(name);
	}

	public static void switchToFrameWithWebElement(WebElement element) {
		driver.switchTo().frame(element);
	}

}

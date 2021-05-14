package testNg;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestCases {
	WebDriver driver= null;
	@Test()
	public void verifyGmoOnlineLoadedSuccusessfully() {
		SoftAssert softAssert = new SoftAssert();
		System.out.println("inside test1");
		String ActualTitle=driver.findElement(By.xpath("//font[contains(text(),'GMO OnLine')]")).getText();
		System.out.println("tile of gmo online "+ActualTitle);
		String expectedTitle="GMO OnLine";
		softAssert.assertEquals(ActualTitle, expectedTitle);
		Boolean AcutaEnterGmoOnlineflag=driver.findElement(By.xpath("//input[@name='bSubmit']")).isEnabled();
		Boolean ExpectedEnterGmoOnlineflag=true;
		System.out.println(AcutaEnterGmoOnlineflag);
		Assert.assertEquals(AcutaEnterGmoOnlineflag, ExpectedEnterGmoOnlineflag);
		softAssert.assertAll();
	}

	@Test(dependsOnMethods={"verifyGmoOnlineLoadedSuccusessfully"})
	public void verifyEnterGMOOnlineLoadedSuccessfully(){
		driver.findElement(By.xpath("//input[@name='bSubmit']")).click();
		String OnlineCatalogueheading=driver.findElement(By.xpath("//*[contains(text(),'currently')]")).getText();
		System.out.println(OnlineCatalogueheading);
		String Expected ="These are the items currently available through our online catalog. Select the quantity of each item and then press the \"Place An Order\" button at the bottom of the page.";
		/*if(OnlineCatalogueheading.contains("Place An Order")){
			
		}else{
			
		}*/
		System.out.println("Expected: "+Expected);
		Assert.assertEquals(OnlineCatalogueheading, Expected);
		
	}
	/*//@Test(priority=-4,enabled=false)
	@Test(priority=-4)
	public void Test2() {
		System.out.println("inside test2");
	
	}

	@Test(priority=4)
	public void Test3() {
		System.out.println("inside test3");
	}

	@Test(priority=0,invocationCount=6)
	public void Test4() {
		System.out.println("inside test4");
	}*/

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
		driver=new ChromeDriver();
		driver.manage().window().maximize();//it maximizes our browser
		//global waiting mechanism .To tell webdriver to wait for all webelements that 
		//is declared below the this statement
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://demo.borland.com/gmopost/");
		
	//	driver.findElement(By.name("username")).sendKeys("user@phptravels.com");
	//	driver.findElement(By.tagName("input")).sendKeys("user@phptravels.com");

	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("inside afterSuite");
	}

}

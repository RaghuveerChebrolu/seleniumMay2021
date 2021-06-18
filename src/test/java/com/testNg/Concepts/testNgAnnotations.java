package com.testNg.Concepts;

import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class testNgAnnotations {

	@Test(priority=-2,dependsOnMethods={"Test2"})
	public void Test1() {
		System.out.println("inside test1");
	}

	//@Test(priority=-4,enabled=false)
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
	}

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
		WebDriver driver= null;
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.manage().window().maximize();//it maximizes our browser
		//global waiting mechanism .To tell webdriver to wait for all webelements that 
		//is declared below the this statement
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://phptravels.net/login");
		
	//	driver.findElement(By.name("username")).sendKeys("user@phptravels.com");
	//	driver.findElement(By.tagName("input")).sendKeys("user@phptravels.com");

	}

	@AfterSuite
	public void afterSuite() {
		System.out.println("inside afterSuite");
	}

}

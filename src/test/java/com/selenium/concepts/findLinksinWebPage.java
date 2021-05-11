package com.selenium.concepts;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class findLinksinWebPage {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver= null;
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get("https://demo.borland.com/gmopost/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//input[@name='bSubmit']")).click();
		List<WebElement> list=driver.findElements(By.xpath("//a"));
		System.out.println("number of links preseny in this page: "+list.size());
		int size= list.size();
		for(int i=0;i<size;i++){
			System.out.println(list.get(i).getText());
		}
	}

}

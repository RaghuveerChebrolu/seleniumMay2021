package testNg;

import java.io.IOException;

import org.testng.annotations.Test;

public class MLInheritance extends TestCases2{
  @Test
  public void test1() throws IOException {
	  MLInheritance obj= new MLInheritance();
	 // obj.beforeSuite();
	//  obj.launchBrowser();
	 obj.HandlingAlerts();
  }
}

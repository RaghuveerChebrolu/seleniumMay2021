package com.testNg.Concepts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestngParalletTestCases {
	@BeforeMethod
    public void beforeMethod() {
        long id = Thread.currentThread().getId();
        System.out.println("inside Before Method. Thread id is: " + id);
    }
 
    @Test
    public void testMethodsOne() {
        long id = Thread.currentThread().getId();
        System.out.println("inside testMethodsOne. Thread id is: " + id);
    }
 
    @Test
    public void testMethodsTwo() {
        long id = Thread.currentThread().getId();
        System.out.println("inside testMethodsTwo. Thread id is: " + id);
    }
 
    @AfterMethod
    public void afterMethod() {
        long id = Thread.currentThread().getId();
        System.out.println("inside After Method. Thread id is: " + id);
    }

}

package example;

import org.testng.annotations.Test;	

import org.testng.Assert;

public class TestCase_1 extends NewTest {

	@Test				
	public void testEasy() {	
		driver.get("http://www.guru99.com/selenium-tutorial.html");  
		String title = driver.getTitle();				 
		Assert.assertTrue(title.contains("Free Selenium Tutorials")); 		
	}	
	
}

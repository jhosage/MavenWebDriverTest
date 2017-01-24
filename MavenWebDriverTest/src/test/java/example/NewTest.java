package example;		

import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;		

import org.testng.annotations.Optional;	
import org.testng.annotations.Test;	
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;		


public class NewTest {		
	    private WebDriver driver;		
		@Test				
		public void testEasy() {	
			driver.get("http://www.guru99.com/selenium-tutorial.html");  
			String title = driver.getTitle();				 
			Assert.assertTrue(title.contains("Free Selenium Tutorials")); 		
		}	

	    @BeforeTest 
	    @Parameters("browser")
	    public void launchBrowser(@Optional("Firefox") String browser) {
	    	switch (browser) {
	    	case "Chrome":
	    	    System.setProperty("webdriver.chrome.driver", "C:\\Users\\John\\Documents\\source\\chrome_driver\\chromedriver.exe");
	    		driver = new ChromeDriver();
	        	break;
	    	case "Firefox":
	        	System.setProperty("webdriver.gecko.driver","C:\\Users\\John\\Documents\\source\\gecko_driver\\geckodriver.exe");
	      	    driver = new FirefoxDriver();
	        	break;
	    	case "Edge":
	    	    System.setProperty("webdriver.edge.driver", "C:\\Users\\John\\Documents\\source\\edge_driver\\MicrosoftWebDriver.exe");
	    		driver = new EdgeDriver();
	    	    break;
	    	case "IE":
	        	System.setProperty("webdriver.ie.driver", "C:\\Users\\John\\Documents\\source\\ie_driver_32\\IEDriverServer.exe");
	        	driver = new InternetExplorerDriver();
	        	break;
	        default:
	        	System.setProperty("webdriver.gecko.driver","C:\\Users\\John\\Documents\\source\\gecko_driver\\geckodriver.exe");
	      	    driver = new FirefoxDriver();
	        	break;
	    	}
	    } 
		
		@AfterTest
		public void afterTest() {
			driver.quit();			
		}		
}	
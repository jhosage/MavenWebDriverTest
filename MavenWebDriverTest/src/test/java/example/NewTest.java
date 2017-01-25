package example;		

import java.util.Properties;

import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
		
import org.testng.annotations.Optional;	
import org.testng.annotations.Test;	
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;

import org.testng.Assert;
import org.testng.SkipException;	


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
	    	Properties props = System.getProperties();
	    	String os = props.getProperty("os.name");
	    	boolean windows = os.startsWith("Windows");
	    	System.out.println("Running " + browser + " on operating system: " + os);
	    	
	    	switch (browser) {
	    	case "Chrome":
	    		if (windows) {
	    			System.setProperty("webdriver.chrome.driver", "C:\\Users\\John\\Documents\\source\\chrome_driver\\chromedriver.exe");
	    		} else {
	    			System.setProperty("webdriver.chrome.driver", "/home/jhosage/chromedriver");
	    		}
	    		driver = new ChromeDriver();
	        	break;
	    	case "Firefox":
	    		if (windows) {
	    			System.setProperty("webdriver.gecko.driver","C:\\Users\\John\\Documents\\source\\gecko_driver\\geckodriver.exe");
	    		} else {
	    			System.setProperty("webdriver.gecko.driver","/home/jhosage/geckodriver");
	    		}
	      	    driver = new FirefoxDriver();
	        	break;
	    	case "Edge":
	    		if (windows) {
	    			System.setProperty("webdriver.edge.driver", "C:\\Users\\John\\Documents\\source\\edge_driver\\MicrosoftWebDriver.exe");
	    		} else {
	    			throw new SkipException("Browser " + browser + " not valid on " + os);
	    		}
	    		driver = new EdgeDriver();
	    	    break;
	    	case "IE":
	    		if (windows) {
	    			System.setProperty("webdriver.ie.driver", "C:\\Users\\John\\Documents\\source\\ie_driver_32\\IEDriverServer.exe");
	    		} else {
	    			throw new SkipException("Browser " + browser + " not valid on " + os);
	    		}
	        	driver = new InternetExplorerDriver();
	        	break;
	        default:
	        	System.err.println("Invalid browser selected: " + browser);
	        	break;
	    	}
	    } 
		
		@AfterTest
		public void afterTest() {
			driver.quit();			
		}		
}	
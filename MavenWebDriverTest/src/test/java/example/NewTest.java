package example;		

import java.net.URL;
import java.net.MalformedURLException;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.testng.annotations.Optional;	
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;

import org.testng.TestException;

public abstract class NewTest  {		

		protected static WebDriver driver;	
	    private DesiredCapabilities capability;

	    /* Structured this class to be extended with the test added
	     * to a separate class.
	     * 
		@Test				
		public void testEasy() {	
			driver.get("http://www.guru99.com/selenium-tutorial.html");  
			String title = driver.getTitle();				 
			Assert.assertTrue(title.contains("Free Selenium Tutorials")); 		
		}	
		*/

	    @BeforeTest 
	    @Parameters({"browser", "platform", "version", "gridhub"})
		/**
		 * 
		 * @param browser	the type of browser to use for the test.  Defaults to "Firefox" if not provided.
		 * @param platform	the platform on which to run the test.  Defaults to "Windows" if not provided.
		 * @param version	the version of the browser requested.   Defulats to "" if not provided.
		 * @param gridhub	the URL for the grid hub if running remote.  Defaults to "http://localhost:4444/wd/hub" if not provided.
		 */
	    public void launchBrowser(@Optional("Firefox") String browser, 
	    						  @Optional("Windows") String platform,
	    						  @Optional("") String version,
			  					  @Optional("http://localhost:4444/wd/hub") String gridhub) {
	    
	    	/*
	    	 * Key 	Type 	Description
	    	 * 
	    	 * browserName 	string 	The name of the browser being used; 
	    	 * 						should be one of {android|chrome|firefox|htmlunit|internet explorer|iPhone|iPad|opera|safari}.
	    	 * version 		string 	The browser version, or the empty string if unknown.
			 * platform 	string 	A key specifying which platform the browser should be running on. 
			 * 						This value should be one of {WINDOWS|XP|VISTA|MAC|LINUX|UNIX|ANDROID}. When requesting a new session, the client may specify ANY to indicate any available platform may be used. For more information see [GridPlatforms]
	    	 */
	    	try {
		    	switch (browser.toLowerCase()) {
		    	case "chrome":
	    			System.setProperty("webdriver.chrome.driver", "C:\\Users\\John\\Documents\\source\\chrome_driver\\chromedriver.exe");
		    		driver = new ChromeDriver();
		    		System.out.println("Setting driver for local chrome.");
		        	break;
		    	case "firefox":
	    			System.setProperty("webdriver.gecko.driver","C:\\Users\\John\\Documents\\source\\gecko_driver\\geckodriver.exe");
		      	    driver = new FirefoxDriver();
		    		System.out.println("Setting driver for local firefox.");
		        	break;
		    	case "edge":
	    			System.setProperty("webdriver.edge.driver", "C:\\Users\\John\\Documents\\source\\edge_driver\\MicrosoftWebDriver.exe");
		    		driver = new EdgeDriver();
		    		System.out.println("Setting driver for local edge.");
		    	    break;
		    	case "ie":
	    			System.setProperty("webdriver.ie.driver", "C:\\Users\\John\\Documents\\source\\ie_driver_32\\IEDriverServer.exe");
		        	driver = new InternetExplorerDriver();
		    		System.out.println("Setting driver for local IE.");
		        	break;
		    	case "remotechrome":
		    		System.out.println("Setting driver for: remote chrome, " + 
		    				" platform: " + platform + "," +
		    				" version: " + version);
		        	capability = DesiredCapabilities.chrome();
		        	//capability.setBrowserName("chrome");
		        	capability.setVersion(version);
		        	setCapabilityPlatform(capability, platform);
		    		driver = new RemoteWebDriver(new URL(gridhub), capability);
		        	break;
		    	case "remotefirefox":
		    		System.out.println("Setting driver for: remote firefox, " + 
		    				" platform: " + platform + "," +
		    				" version: " + version);
		    		capability = DesiredCapabilities.firefox();
		        	//capability.setBrowserName("firefox");
		        	capability.setVersion(version);
		        	setCapabilityPlatform(capability, platform);
		    		driver = new RemoteWebDriver(new URL(gridhub), capability);	
		        	break;
		    	case "remoteedge":
		    		System.out.println("Setting driver for: remote edge, " + 
		    				" platform: " + platform + "," +
		    				" version: " + version);
		        	capability = DesiredCapabilities.edge();
		        	//capability.setBrowserName("edge");
		        	capability.setVersion(version);
		        	setCapabilityPlatform(capability, platform);
		    		driver = new RemoteWebDriver(new URL(gridhub), capability);
		    	    break;
		    	case "remoteie":
		    		System.out.println("Setting driver for: remote IE, " + 
		    				" platform: " + platform + "," +
		    				" version: " + version);
		        	capability = DesiredCapabilities.internetExplorer();
		        	//capability.setBrowserName("internet explorer");
		        	capability.setVersion(version);
		        	setCapabilityPlatform(capability, platform);
		    		driver = new RemoteWebDriver(new URL(gridhub), capability);
		        	break;
		    	default:
		        	System.err.println("Invalid browser selected: " + browser);
		        	break;
		    	} // end of the switch statement
		    	
	    	} catch (MalformedURLException e) {
	        	System.err.println("Malformed URL Exception: " + gridhub);
	        	throw new TestException("Malformed URL Exception while setting up the web driver.");
	    	} catch (Exception e) {
	        	System.err.println("Exception while setting up web driver. " + 
	        			"Browser: " + browser +
	        			"Operating System: " + platform +
	        			"Grid Hub: " + gridhub + e);
	        	throw new TestException("Exception while setting up the web driver." + e);
	    	} // end of the try catch
	    	
	    } // end of launchBrowser
		
		@AfterTest
		public void afterTest() {
			driver.quit();			
		}		

		/**
		 * Sets the platform desired capability in relation to Selenium Grid.
		 * 
		 * @param c 	the capability object on which the platform will be set.
		 * @param platform	a string with the name of the platform that will translated into platform enumeration.  
		 * 				The valid values are android, el capitan, linux, mac, mavericks, mountain lion, snow leopard,
		 * 				unix, vista, windows, windows 10, windows 8, windows 8.1, windows vista, windows xp,
		 * 				and yosemite.
		 * @see			org.openqa.selenium.Platform
		 */
		private void setCapabilityPlatform (DesiredCapabilities c, String platform) {
			switch (platform.toLowerCase()) {
			case "android": 
				c.setPlatform(Platform.ANDROID);
				break;
			case "el capitan": 
				c.setPlatform(Platform.EL_CAPITAN);
				break;
			case "linux": 
				c.setPlatform(Platform.LINUX);
				break;
			case "mac": 
				c.setPlatform(Platform.MAC);
				break;
			case "mavericks": 
				c.setPlatform(Platform.MAVERICKS);
				break;
			case "mountain lion": 
				c.setPlatform(Platform.MOUNTAIN_LION);
				break;
			case "snow leopard": 
				c.setPlatform(Platform.SNOW_LEOPARD);
				break;
			case "unix": 
				c.setPlatform(Platform.UNIX);
				break;
			case "windows vista": 
				c.setPlatform(Platform.VISTA);
				break;
			case "windows 10": 
				c.setPlatform(Platform.WIN10);
				break;
			case "windows 8": 
				c.setPlatform(Platform.WIN8);
				break;
			case "windows 8.1": 
				c.setPlatform(Platform.WIN8_1);
				break;
			case "windows": 
				c.setPlatform(Platform.WINDOWS);
				break;
			case "windows XP": 
				c.setPlatform(Platform.XP);
				break;
			case "yosemite": 
				c.setPlatform(Platform.YOSEMITE);
				break;
	    	default:
	        	System.err.println("Invalid platform for remote grid capabilities specified: " 
	        			+ platform);
	        	break;
			}
		}

		/**
		 * 
		 * @return
		 */
		public static WebDriver getDriver() {
			return driver;
		}
	    
}	
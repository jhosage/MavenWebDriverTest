package example;		

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Date;
import java.util.logging.Level;

import java.net.URL;
import java.net.MalformedURLException;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import org.openqa.selenium.support.events.EventFiringWebDriver;

import org.testng.annotations.Optional;	
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterTest;

import org.testng.TestException;

import utilities.WebDriverEventHandler;

public abstract class NewTest  {		

		private static WebDriver wdriver;
		protected static EventFiringWebDriver driver = null;
	    protected DesiredCapabilities capability;
	    protected LoggingPreferences loggingPreferences = new LoggingPreferences();

		static final Logger log = LogManager.getLogger("DriverLog");
		
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
	    @Parameters({"browser", "platform", "version", "gridhub", "logging"})
		/**
		 * 
		 * @param browser	the type of browser to use for the test.  Defaults to "Firefox" if not provided.
		 * @param platform	the platform on which to run the test.  Defaults to "Windows" if not provided.
		 * @param version	the version of the browser requested.   Defaults to "" if not provided.
		 * @param gridhub	the URL for the grid hub if running remote.  Defaults to "http://localhost:4444/wd/hub" if not provided.
		 */
	    public void launchBrowser(@Optional("Firefox") String browser, 
	    						  @Optional("Windows") String platform,
	    						  @Optional("") String version,
			  					  @Optional("http://localhost:4444/wd/hub") String gridhub,
			  					  @Optional("") String logging) {
	    
	    	
	    	setLogging(logging);
	    	setBrowser(browser, platform, version, gridhub);
	    	
	    	// Encase the webdriver for logging.
	    	driver = new EventFiringWebDriver(wdriver);
	    	driver.register(new WebDriverEventHandler());
	    }

		@AfterTest
		public void afterTest() {
			printLogs();
			driver.quit();			
		}		

	    
	    /**
	     * 
	     * @param browser
	     * @param platform
	     * @param version
	     * @param gridhub
	     */
	    private void setBrowser(String browser, String platform, String version, String gridhub) {
	    	try {
		    	switch (browser.toLowerCase()) {
		    	case "chrome":
	    			System.setProperty("webdriver.chrome.driver", "C:\\Users\\John\\Documents\\source\\chrome_driver\\chromedriver.exe");
	    			capability = DesiredCapabilities.chrome();
	    			capability.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
		    		wdriver = new ChromeDriver(capability);
		    		System.out.println("Setting driver for local chrome.");
		        	break;
		    	case "firefox":
	    			System.setProperty("webdriver.gecko.driver","C:\\Users\\John\\Documents\\source\\gecko_driver\\geckodriver.exe");
	    			capability = DesiredCapabilities.firefox();
	    			capability.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
	    			wdriver = new FirefoxDriver(capability);
		    		System.out.println("Setting driver for local firefox.");
		        	break;
		    	case "edge":
	    			System.setProperty("webdriver.edge.driver", "C:\\Users\\John\\Documents\\source\\edge_driver\\MicrosoftWebDriver.exe");
	    			capability = DesiredCapabilities.edge();
	    			//cedge.setCapability("requireWindowFocus", false);
	    			capability.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
	    			wdriver = new EdgeDriver(capability);
		    		System.out.println("Setting driver for local edge.");
		    	    break;
		    	case "ie":
	    			System.setProperty("webdriver.ie.driver", "C:\\Users\\John\\Documents\\source\\ie_driver_32\\IEDriverServer.exe");
	    			//cie.setCapability("requireWindowFocus", true);
	    			//cie.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
	    			//cie.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
	    			capability = DesiredCapabilities.internetExplorer();
	    			capability.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
	    			wdriver = new InternetExplorerDriver(capability);
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
	    			capability.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
		    		wdriver = new RemoteWebDriver(new URL(gridhub), capability);
		        	break;
		    	case "remotefirefox":
		    		System.out.println("Setting driver for: remote firefox, " + 
		    				" platform: " + platform + "," +
		    				" version: " + version);
		    		capability = DesiredCapabilities.firefox();
		        	//capability.setBrowserName("firefox");
		        	capability.setVersion(version);
		        	setCapabilityPlatform(capability, platform);
	    			capability.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
		    		wdriver = new RemoteWebDriver(new URL(gridhub), capability);	
		        	break;
		    	case "remoteedge":
		    		System.out.println("Setting driver for: remote edge, " + 
		    				" platform: " + platform + "," +
		    				" version: " + version);
		        	capability = DesiredCapabilities.edge();
		        	//capability.setBrowserName("edge");
		        	capability.setVersion(version);
		        	setCapabilityPlatform(capability, platform);
	    			capability.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
		    		wdriver = new RemoteWebDriver(new URL(gridhub), capability);
		    	    break;
		    	case "remoteie":
		    		System.out.println("Setting driver for: remote IE, " + 
		    				" platform: " + platform + "," +
		    				" version: " + version);
		        	capability = DesiredCapabilities.internetExplorer();
		        	//capability.setBrowserName("internet explorer");
		        	capability.setVersion(version);
		        	setCapabilityPlatform(capability, platform);
	    			capability.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
		    		wdriver = new RemoteWebDriver(new URL(gridhub), capability);
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

			// Set Logging capabilities
			c.setCapability(CapabilityType.LOGGING_PREFS, loggingPreferences);
			
			// Set the preferred platform for remote testing.
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
	        	System.out.println("Before Test Driver setup: no platform specified for driver capabilities."); 
	        	break;
			}
		}

		/**
		 * 
		 * @return
		 */
		public static WebDriver getDriver() {
			return wdriver;
		}
	
		
		/**
		 * Sets the logging levels for the web driver.
		 * 
		 * 
		 */
		private void setLogging(String level) {
			
			switch (level.toLowerCase()) {
			case "": 
				break;
			case "browser":
				loggingPreferences.enable(LogType.BROWSER, Level.ALL);
				break;
			case "client":
				loggingPreferences.enable(LogType.CLIENT, Level.ALL);
				break;
			case "driver":
				loggingPreferences.enable(LogType.DRIVER, Level.ALL);
				break;
			case "performance":
				loggingPreferences.enable(LogType.PERFORMANCE, Level.ALL);
				break;
			case "profiler":
				loggingPreferences.enable(LogType.PROFILER, Level.ALL);
				break;
			case "server":
				loggingPreferences.enable(LogType.SERVER, Level.ALL);
				break;
			default:
				System.err.println("Setting logging level: unknown logging level -> " + level);
			}
		
		}
		
		/**
		 * 
		 */
		private void printLogs() {
			Logs logs = driver.manage().logs();
			LogEntries logEntries;

			if (loggingPreferences.getEnabledLogTypes().contains(LogType.BROWSER)) {
				System.out.println("Printing BROWSER logs.");
				logEntries = logs.get(LogType.BROWSER);
				for (LogEntry logEntry : logEntries) {
				    System.out.println("BROWSER: " + logEntry.getMessage());
				}
			}

			if (loggingPreferences.getEnabledLogTypes().contains(LogType.CLIENT)) {
				System.out.println("Printing CLIENT logs.");
				logEntries = logs.get(LogType.CLIENT);
				for (LogEntry logEntry : logEntries) {
				    System.out.println("CLIENT: " + logEntry.getMessage());
				}
			}
			
			if (loggingPreferences.getEnabledLogTypes().contains(LogType.DRIVER)) {
				System.out.println("Printing DRIVER logs.");
				logEntries = logs.get(LogType.DRIVER);
				for (LogEntry logEntry : logEntries) {
				    System.out.println("DRIVER: " + logEntry.getMessage());
				}
			}
			
			if (loggingPreferences.getEnabledLogTypes().contains(LogType.PERFORMANCE)) {
				System.out.println("Printing PERFORMANCE logs.");
				logEntries = logs.get(LogType.PERFORMANCE);
				for (LogEntry logEntry : logEntries) {
				    System.out.println("PERFORMANCE: " + logEntry.getMessage());
				}
			}
			
			if (loggingPreferences.getEnabledLogTypes().contains(LogType.PROFILER)) {
				System.out.println("Printing PROFILER logs.");
				logEntries = logs.get(LogType.PROFILER);
				for (LogEntry logEntry : logEntries) {
				    System.out.println("PROFILER: " + logEntry.getMessage());
				}
			}
			
			if (loggingPreferences.getEnabledLogTypes().contains(LogType.SERVER)) {
				System.out.println("Printing SERVER logs.");
				logEntries = logs.get(LogType.SERVER);
				for (LogEntry logEntry : logEntries) {
				    //System.out.println("SERVER: " + logEntry.getMessage());
				    log.debug("SERVER: " + new Date(logEntry.getTimestamp()).toString() + 
				    						" " + logEntry.getLevel() + 
				    						" " + logEntry.getMessage());
				}
			}
			
		}
		
}	
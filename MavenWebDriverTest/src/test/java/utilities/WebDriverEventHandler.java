package utilities;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverEventListener;
 
public class WebDriverEventHandler implements WebDriverEventListener{

	static final Logger log = LogManager.getLogger("EventLog");


    public void afterAlertAccept(WebDriver arg1) {
        log.traceEntry();
        log.traceExit();
    }

    public void afterAlertDismiss(WebDriver arg1) {
        log.traceEntry();
        log.traceExit();
    }

	public void afterChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		log.traceEntry();
		log.traceExit();
	}
 
	public void afterClickOn(WebElement arg0, WebDriver arg1) {
		log.traceEntry();
		log.traceExit();
		//System.out.println("inside method afterClickOn on " + arg0.toString());
	}
 
	public void afterFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		if (arg1 == null) {
			log.traceEntry("By: " +arg0.toString());			
		} else {
			log.traceEntry("By: " +arg0.toString() + ", WebElement: " + arg1.toString());
		}
		
		log.traceExit();
	}
 
	public void afterNavigateBack(WebDriver arg0) {
		log.traceEntry();
		//System.out.println("Inside the after navigateback to " + arg0.getCurrentUrl());
		log.traceExit();
	}
 
	public void afterNavigateForward(WebDriver arg0) {
		log.traceEntry();
		log.traceExit();
		//System.out.println("Inside the afterNavigateForward to " + arg0.getCurrentUrl());
	}
 
	public void afterNavigateTo(String arg0, WebDriver arg1) {
		log.traceEntry("URL: " + arg0);
		//System.out.println("Inside the afterNavigateTo to " + arg0);
		log.traceExit();
	}
	
	public void afterNavigateRefresh(WebDriver arg0) {
		log.traceEntry();
		log.traceExit();
		//System.out.println("Inside the afterNavigateRefresh");
	}
	
	public void afterScript(String arg0, WebDriver arg1) {
		log.traceEntry();
		log.traceExit();
		//System.out.println("Inside the afterScript to, Script is " + arg0);
	}
 
    public void beforeAlertAccept(WebDriver arg1) {
        log.traceEntry();
        log.traceExit();
    }

    public void beforeAlertDismiss(WebDriver arg1) {
        log.traceEntry();
        log.traceExit();
    }

	public void beforeChangeValueOf(WebElement arg0, WebDriver arg1, CharSequence[] arg2) {
		log.traceEntry();
		log.traceExit();
		//System.out.println("Inside the beforeChangeValueOf method on " + arg0.toString() + " to " + arg2.toString());
	}
 
	public void beforeClickOn(WebElement arg0, WebDriver arg1) {
		log.traceEntry();
		log.traceExit();
		//System.out.println("About to click on the " + arg0.toString());
 
	}
 
	public void beforeFindBy(By arg0, WebElement arg1, WebDriver arg2) {
		if (arg1 == null) {
			log.traceEntry("By: " +arg0.toString());			
		} else {
			log.traceEntry("By: " +arg0.toString() + ", WebElement: " + arg1.toString());
		}
		
		log.traceExit();
	}
 
	public void beforeNavigateBack(WebDriver arg0) {
		log.traceEntry();
		log.traceExit();
		//System.out.println("Just before beforeNavigateBack " + arg0.getCurrentUrl());
 
	}
 
	public void beforeNavigateForward(WebDriver arg0) {
		log.traceEntry();
		log.traceExit();
		//System.out.println("Just before beforeNavigateForward " + arg0.getCurrentUrl());
 
	}
 
	public void beforeNavigateRefresh(WebDriver arg0) {
		log.traceEntry();
		log.traceExit();
		//System.out.println("Just before beforeNavigateRefresh ");
	}
 
	public void beforeNavigateTo(String arg0, WebDriver arg1) {
		log.traceEntry("URL: " + arg0);
		log.traceExit();
	}
 
	public void beforeScript(String arg0, WebDriver arg1) {
		log.traceEntry();
		log.traceExit();
		//System.out.println("Just before beforeScript " + arg0);
	}
 
	public void onException(Throwable arg0, WebDriver arg1) {
		log.traceEntry();
		log.traceExit();
		//System.out.println("Exception occurred at " + arg0.getMessage());
 	}
 }
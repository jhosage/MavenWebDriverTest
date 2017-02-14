package utilities;

import java.io.File;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Utilities {

	public static void takeSnapShot(WebDriver webdriver, String fileWithPath) {

		//Convert web driver object to TakeScreenshot
        TakesScreenshot scrShot =((TakesScreenshot)webdriver);

        //Call getScreenshotAs method to create image file
        File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);

        //Move image file to new destination
        File DestFile=new File(fileWithPath);

        try {
	        //Copy file at destination
	        FileUtils.copyFile(SrcFile, DestFile);
        } catch (Exception e) {
        	System.err.println("Utilities.takeSnapShot: caught exception while copying screenshot.");
        	System.err.println(e);
        }
    }
	
}

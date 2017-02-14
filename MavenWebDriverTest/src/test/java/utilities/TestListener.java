package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import example.NewTest;

public class TestListener implements ITestListener {

	
	// This section has the methods for the TestNG ITestListener
    @Override
    public void onStart(ITestContext arg0) {
         System.out.println("Start Of Execution(TEST)->"+arg0.getName());
    }

    @Override

    public void onTestStart(ITestResult arg0) {
        System.out.println("Test Started->"+arg0.getName());
    }

    @Override
    public void onTestSuccess(ITestResult arg0) {
        System.out.println("Test Pass->"+arg0.getName());
    }

    @Override
    public void onTestFailure(ITestResult arg0) {
        System.out.println("Test Failed->"+arg0.getName());
        
        String target = "target/surefire-reports/screenshots/screenshot-" + 
        		arg0.getName() + "-" +
        		new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()) +
        		".png";
        Utilities.takeSnapShot(NewTest.getDriver(), target);
    }


    @Override
    public void onTestSkipped(ITestResult arg0) {
        System.out.println("Test Skipped->"+arg0.getName());
    }

    @Override

    public void onFinish(ITestContext arg0) {
    	System.out.println("END Of Execution(TEST)->"+arg0.getName());
    }

    @Override

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
        // TODO Auto-generated method stub
    }

	
}

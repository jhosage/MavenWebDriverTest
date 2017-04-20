package example;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.GoogleCalendarPage;
import pages.GoogleCalendarEventPage;
import pages.GoogleCalendarUserPage;
import pages.GoogleCalendarPasswordPage;

public class TestCase_GoogleCalendar extends NewTest {

    //@Test (enabled=true, dataProvider="SearchProvider", dataProviderClass=dataproviders.ExcelDataProvider.class)
    @Test (priority=1)
    public void login() {  
        driver.get("https://calendar.google.com");  
 
        GoogleCalendarUserPage userPage = new GoogleCalendarUserPage(driver);
        userPage.login("xxxx@xxxx.com");

        GoogleCalendarPasswordPage pwdPage = new GoogleCalendarPasswordPage(driver);
        pwdPage.login("ENTER_PASSWORD_HERE");

        //GoogleCalendarPage calPage = new GoogleCalendarPage(driver);
        //calPage.clickCreateButton();
        
    }   

    @Test (priority=2)
    public void createEvent() {  
        //driver.get("https://calendar.google.com");  

        GoogleCalendarPage calPage = new GoogleCalendarPage(driver);
        calPage.clickCreateButton();

        GoogleCalendarEventPage ePage = new GoogleCalendarEventPage(driver);
        ePage.createEvent("Natalie - Soccer", "04/22/2017", "10:45am", "04/22/2017", "11:45am", 
                "Kilmer Middle School, Vienna, VA", "Soccer", "yellow");
    }   

    
}

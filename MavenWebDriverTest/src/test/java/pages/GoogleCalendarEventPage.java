package pages;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import example.NewTest;
import utilities.Utilities;

public class GoogleCalendarEventPage {

    EventFiringWebDriver driver;

    By titleField =     By.xpath("//input[@title='Event title']");
    By startDateField = By.xpath("//input[@title='From date']");
    By startTimeField = By.xpath("//input[@title='From time']");
    By endDateField =   By.xpath("//input[@title='Until date']");
    By endTimeField =   By.xpath("//input[@title='Until time']");
    By whereField =     By.xpath("//input[@placeholder='Enter a location']");
    By descriptionField = By.xpath("//td/div/div/textarea");
    By colorYellowButton = By.xpath("//div[@class='hdn' and text()='yellow']");
    By colorTurquoiseButton = By.xpath("//div[@class='hdn' and text()='turquoise']");
    By saveButton =     By.xpath("//div[@class='goog-imageless-button-content' and text()='Save']");
    
    public GoogleCalendarEventPage (EventFiringWebDriver driver) {
        this.driver = driver;
    }

    public GoogleCalendarEventPage createEvent (String title, String sDate, String sTime,
                                            String eDate, String eTime, String where,
                                            String description, String color) {
        
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(saveButton));

        //driver.findElement(titleField).clear();
        driver.findElement(titleField).sendKeys(title);
        
        driver.findElement(startDateField).clear();
        driver.findElement(startDateField).sendKeys(sDate);
        
        driver.findElement(startTimeField).clear();
        driver.findElement(startTimeField).sendKeys(sTime);
        
        driver.findElement(endDateField).clear();
        driver.findElement(endDateField).sendKeys(eDate);
        
        driver.findElement(endTimeField).clear();
        driver.findElement(endTimeField).sendKeys(eTime);
        
        //driver.findElement(whereField).clear();
        driver.findElement(whereField).sendKeys(where);
        
        //driver.findElement(descriptionField).clear();
        driver.findElement(descriptionField).sendKeys(description);
        
        //driver.findElement(colorYellowButton).click();
        //driver.findElement(colorTurquoiseButton).submit();
        WebElement colorButton = driver.findElement(colorTurquoiseButton);
        Actions builder = new Actions(driver);
        Action seriesOfActions = builder.moveToElement(colorButton).doubleClick().build();
        seriesOfActions.perform();
        
        
        String target = "target/surefire-reports/screenshots/screenshot-" + 
                "debug-" +
                new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date()) +
                ".png";
        Utilities.takeSnapShot(driver, target);
        
        driver.findElement(saveButton).click();
        return this;
    }

}
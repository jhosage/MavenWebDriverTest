package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCalendarPage {

    EventFiringWebDriver driver;

    By createButton = By.xpath("//div[@class='goog-imageless-button-content' and text()='Create']");
    
    public GoogleCalendarPage (EventFiringWebDriver driver) {
        this.driver = driver;
    }

    public GoogleCalendarPage clickCreateButton () {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(createButton));
        
        //System.out.println("Alert message" + driver.switchTo().alert().getText());
        //driver.switchTo().alert().dismiss();
        
        button.click();
        return this;
    }

}
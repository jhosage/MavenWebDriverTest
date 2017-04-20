package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class GoogleCalendarUserPage {

    EventFiringWebDriver driver;

    By emailField = By.id("Email");
    By nextButton = By.id("next");
    
    public GoogleCalendarUserPage (EventFiringWebDriver driver) {
        this.driver = driver;
    }

    public GoogleCalendarUserPage login (String email) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(nextButton).click();
        return this;
    }

}
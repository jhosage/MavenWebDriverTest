package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCalendarPasswordPage {

    EventFiringWebDriver driver;

    By passwordField = By.id("Passwd");
    By signInButton = By.id("signIn");
    
    public GoogleCalendarPasswordPage (EventFiringWebDriver driver) {
        this.driver = driver;
    }

    public GoogleCalendarPasswordPage login (String password) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        field.sendKeys(password);
        driver.findElement(signInButton).click();
        return this;
    }

}
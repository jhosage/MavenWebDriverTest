package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class YahooSearchPage {

	WebDriver driver;

	By searchText = By.id("yschsp");

	public YahooSearchPage (WebDriver driver) {
		this.driver = driver;
	}

	public YahooSearchPage setSearchText (String text) {
		driver.findElement(searchText).sendKeys(text);
		return this;
	}
	
	public YahooSearchPage submitSearch () {
		driver.findElement(searchText).submit();
		return this;
	}

}



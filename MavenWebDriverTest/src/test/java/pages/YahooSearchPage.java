package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class YahooSearchPage {

	EventFiringWebDriver driver;

	By searchText = By.id("yschsp");

	public YahooSearchPage (EventFiringWebDriver driver) {
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

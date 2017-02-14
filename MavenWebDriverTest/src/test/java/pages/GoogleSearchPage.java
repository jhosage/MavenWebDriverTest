package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.support.events.EventFiringWebDriver;


public class GoogleSearchPage {

	EventFiringWebDriver driver;

	By searchText = By.id("lst-ib");

	public GoogleSearchPage (EventFiringWebDriver driver) {
		this.driver = driver;
	}

	public GoogleSearchPage setSearchText (String text) {
		driver.findElement(searchText).sendKeys(text);
		return this;
	}
	
	public GoogleSearchPage submitSearch () {
		driver.findElement(searchText).submit();
		return this;
	}

}



package pages;

import org.openqa.selenium.By;

import org.openqa.selenium.support.events.EventFiringWebDriver;

public class DuckDuckGoSearchPage {

	//WebDriver driver;
	EventFiringWebDriver driver;

	By searchText = By.id("search_form_input_homepage");

	public DuckDuckGoSearchPage (EventFiringWebDriver driver) {
		System.out.println("DuckDuckGoSearchPage: creating instance.");
		this.driver = driver;
	}

	public DuckDuckGoSearchPage setSearchText (String text) {
		System.out.println("DuckDuckGoSearchPage: setting search text to: " + text);
		driver.findElement(searchText).sendKeys(text);
		return this;
	}
	
	public DuckDuckGoSearchPage submitSearch () {
		System.out.println("DuckDuckGoSearchPage: submitting search.");
		driver.findElement(searchText).submit();
		return this;
	}

}



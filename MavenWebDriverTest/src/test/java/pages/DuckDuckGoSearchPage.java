package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class DuckDuckGoSearchPage {

	WebDriver driver;

	By searchText = By.id("search_form_input_homepage");

	public DuckDuckGoSearchPage (WebDriver driver) {
		this.driver = driver;
	}

	public DuckDuckGoSearchPage setSearchText (String text) {
		driver.findElement(searchText).sendKeys(text);
		return this;
	}
	
	public DuckDuckGoSearchPage submitSearch () {
		driver.findElement(searchText).submit();
		return this;
	}

}



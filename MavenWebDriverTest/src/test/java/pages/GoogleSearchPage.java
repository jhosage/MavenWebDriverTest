package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class GoogleSearchPage {

	WebDriver driver;

	By searchText = By.id("lst-ib");

	public GoogleSearchPage (WebDriver driver) {
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



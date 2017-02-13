package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.events.EventFiringWebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DuckDuckGoSearchResultsPage {

	EventFiringWebDriver driver;

	//By searchResult = By.className("result__a");
	By searchResult = By.cssSelector("a[class=\"result__url\"");

	public DuckDuckGoSearchResultsPage (EventFiringWebDriver driver) {
		this.driver = driver;
	}

	public DuckDuckGoSearchResultsPage waitForPageLoad() {
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(searchResult) );
		return this;
	}
	
	public ArrayList<String> getSearchResultsLinks () {
		
		List<WebElement> resultList = driver.findElements(searchResult);
		ArrayList<String> results = new ArrayList<String>(resultList.size());

		for (WebElement w : resultList) {
			//results.add(w.getText());
			results.add(w.getAttribute("href"));
			//System.out.println("result[" +i+ "] is " + results.get(i++));
		}

		return results;
	}

	public boolean searchResultsContains(String result) {
		return getSearchResultsLinks().contains(result);
	}

}



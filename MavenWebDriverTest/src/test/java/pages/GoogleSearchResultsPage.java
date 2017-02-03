package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchResultsPage {

	WebDriver driver;

	//By searchResult = By.cssSelector("a");
	By searchResult = By.xpath("//h3[@class='r']/a");
	By xgamesLinkText = By.linkText("xgames.espn.com");

	public GoogleSearchResultsPage (WebDriver driver) {
		this.driver = driver;
	}

	public GoogleSearchResultsPage waitForPageLoad() {
	/*	try {
			Thread.sleep(1000);
		} catch (Exception e) {
			System.err.println("Caught exception in sleep: " + e);
		}
	 */
		
		WebDriverWait wait = new WebDriverWait(driver, 5);
		//wait.until(ExpectedConditions.presenceOfElementLocated(xgamesLinkText));
		wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(searchResult, 5));
		return this;
	}
	
	public ArrayList<String> getSearchResultsLinks () {
		
		List<WebElement> resultList = driver.findElements(searchResult);
		ArrayList<String> results = new ArrayList<String>(resultList.size());

		int i = 0;
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



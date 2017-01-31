package example;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.DuckDuckGoSearchPage;
import pages.DuckDuckGoSearchResultsPage;
import pages.GoogleSearchPage;
import pages.GoogleSearchResultsPage;
import pages.YahooSearchPage;
import pages.YahooSearchResultsPage;

public class TestCaseSearches extends NewTest {

	//@Test (enabled=false) 
	@Test (enabled=true, dataProvider="SearchProvider", dataProviderClass=dataProviders.ListDataProvider.class)
	public void testDuckDuckGo(String searchText, String resultText) {	
		driver.get("https://duckduckgo.com");  
		
		DuckDuckGoSearchPage searchPage = new DuckDuckGoSearchPage(driver);
		searchPage.setSearchText(searchText).submitSearch();
		
		DuckDuckGoSearchResultsPage resultsPage = new DuckDuckGoSearchResultsPage(driver);
		resultsPage.waitForPageLoad();
		Assert.assertTrue(resultsPage.searchResultsContains(resultText)); 		
	}	

	@Test (enabled=true, dataProvider="SearchProvider", dataProviderClass=dataProviders.ListDataProvider.class)			
	public void testGoogle(String searchText, String resultText) {	
		driver.get("https://www.google.com");  
		
		GoogleSearchPage searchPage = new GoogleSearchPage(driver);
		searchPage.setSearchText(searchText).submitSearch();

		
		GoogleSearchResultsPage resultsPage = new GoogleSearchResultsPage(driver);
		resultsPage.waitForPageLoad();
		Assert.assertTrue(resultsPage.searchResultsContains(resultText)); 		
	}	

	@Test (enabled=true, dataProvider="SearchProvider", dataProviderClass=dataProviders.ListDataProvider.class)		
	public void testYahoo(String searchText, String resultText) {	
		driver.get("https://search.yahoo.com");  
		
		YahooSearchPage searchPage = new YahooSearchPage(driver);
		searchPage.setSearchText(searchText).submitSearch();

		
		YahooSearchResultsPage resultsPage = new YahooSearchResultsPage(driver);
		resultsPage.waitForPageLoad();
		Assert.assertTrue(resultsPage.searchResultsContains(resultText)); 		
	}	


}

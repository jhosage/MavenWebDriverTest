package dataProviders;

import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;

public class ListDataProvider {

	@DataProvider(name="SearchProvider")
    public static Object[][] getDataFromDataprovider(Method m){
		Object [][] groupArray = null;
		if (m.getName().equalsIgnoreCase("testduckduckgo") || 
			m.getName().equalsIgnoreCase("testgoogle") 	) {
			groupArray = new Object[][] {
              { "XGames", "http://xgames.espn.com/" },
              { "Rolex 24 Wikipedia", "https://en.wikipedia.org/wiki/24_Hours_of_Daytona" },
              { "Tom Brokaw IMDb", "http://www.imdb.com/name/nm0111232/" }
              };
		} else if (m.getName().equalsIgnoreCase("testyahoo")) {
				groupArray =  new Object[][] {
		              { "XGames", "X Games and action sports videos" },
		              { "Rolex 24", "24 Hours of Daytona - Wikipedia" },
		              { "Tom Brokaw IMDb", "Tom Brokaw - IMDb" }
		              };
		} 
		return groupArray;

    }
}
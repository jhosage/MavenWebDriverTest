package dataProviders;

import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;

import java.util.stream.Stream;

public class FileDataProvider {

	@DataProvider(name="SearchProvider")
    public static Object[][] getDataFromDataprovider(Method m) throws IOException {

		String fileName = "src/test/resources/data/SearchData.csv";
		
		FileDataProvider fdp = new FileDataProvider();
		//return fdp.readFileUsingReader(fileName);
		return fdp.readFileUsingStream(fileName);
		
	}
	
	public String[][] readFileUsingReader(String fileName) throws IOException {
		
		String [][] groupArray = null;
		ArrayList<String[]> alist = new ArrayList<String[]>();
		
		File f = new File(fileName);
		System.out.println("File path is: " + f.getAbsolutePath());

		// Attempt using older style BufferedReader
		String line = null;
		Path path = Paths.get(fileName);
		try (BufferedReader reader = Files.newBufferedReader(path)) {

			while ((line = reader.readLine()) != null) {
		    	System.out.println(line);
		    	alist.add(line.split(","));
		    }
		    
		    //remove the header line
		    alist.remove(0);
		}

		groupArray = alist.toArray(new String[alist.size()][]);
		return groupArray;
		
	} // method

	public String[][] readFileUsingStream(String fileName) throws IOException {
		
		String [][] groupArray = null;
		ArrayList<String[]> alist = new ArrayList<String[]>();
		
		File f = new File(fileName);
		System.out.println("File path is: " + f.getAbsolutePath());

		//  Attempt using file stream
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
		    stream
		        .map(s -> s.split(","))
		    	.forEach(s -> alist.add(s));
		}

		// Remove the header row.
		alist.remove(0);
		
		// Convert from the array list to the object array and return.
		groupArray = alist.toArray(new String[alist.size()][]);
		return groupArray;
		
	} // method

}
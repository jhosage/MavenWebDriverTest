package dataproviders;

import org.testng.annotations.DataProvider;
import java.lang.reflect.Method;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;

public class MysqlDataProvider {

	@DataProvider(name="SearchProvider")
    public static Object[][] getDataFromDataprovider(Method m)  {

		String dbHost	 	= "192.168.1.3";
		String dbTable		= "selenium";
		String user 		= "selenium";
		String password 	= "selenium";
		String table		= "search_data";
		String columns		= "searchText,searchResult,searchResultYahoo";
		
		MysqlDataProvider mdp = new MysqlDataProvider();
		return mdp.readDatabase(dbHost, dbTable, user, password, table, columns);
		
	}
	
	public String[][] readDatabase(String dbHost, String dbTable, 
									String user, String password,
									String table, String columns) {

        Connection connect = null;
        Statement statement = null;
        ResultSet resultSet = null;

		ArrayList<String[]> alist = new ArrayList<String[]>();
		
        try {

        	// This will load the MySQL driver, each DB has its own driver
            //Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Setup the connection with the DB
            String connectString = "jdbc:mysql://" + dbHost + "/" + dbTable +
            		"?user=" + user + "&password=" + password;
            connect = DriverManager
                            .getConnection(connectString);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();

            // Result set get the result of the SQL query
            String query = "select " + columns + " from " + table;
            resultSet = statement
                            .executeQuery(query);
            
            // ResultSet is initially before the first data set
            while (resultSet.next()) {
                    // It is possible to get the columns via name
                    // also possible to get the columns via the column number
                    // which starts at 1
                    // e.g. resultSet.getSTring(2);

            		String[] sArray = new String[3];
            		
            		sArray[0] = resultSet.getString("searchText");
            		sArray[1] = resultSet.getString("searchResult");
            		sArray[2] = resultSet.getString("searchResultYahoo");

            		System.out.println("MysqlDataProvider: adding: " + sArray[0] + 
            				"," + sArray[1] + "," + sArray[2]);  
            		alist.add(sArray);
            } // while
            
        } catch (Exception e) {
        	System.err.println("Caught exception in SQL processing: " + e);
        } finally {
        	try {
	            if (resultSet != null) resultSet.close();
	            if (statement != null) statement.close();
	            if (connect != null)   connect.close();
        	} catch (Exception e) {
            	System.err.println("Caught exception while closing SQL objects: " + e);
        	}
        } // try

        // Convert the list to an array and return it.
		return alist.toArray(new String[alist.size()][]);
		
	} // method

}
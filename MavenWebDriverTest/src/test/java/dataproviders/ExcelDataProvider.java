package dataproviders;

import java.lang.reflect.Method;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.testng.annotations.DataProvider;

/**
 * The ExcelDataProvider reads data from an Excel spreadsheet using the Apache
 * POI and POI-OOXML libraries.
 *
 * @author John
 *
 */
public class ExcelDataProvider {


    /**
     * 
     * @param m
     * @return
     * @throws IOException
     */
    @DataProvider(name = "SearchProvider")
    public static Object[][] getDataFromExcel(Method m) throws IOException {

        String filePath = "src/test/resources/data";
        String fileName = "SearchData.xls";
        String sheetName = "SearchData";

        ExcelDataProvider dataProvider = new ExcelDataProvider();
        return dataProvider.readExcel(filePath, fileName, sheetName);
    }

    public String[][] readExcel(String filePath, String fileName, String sheetName) throws IOException {

        // Create an object of File class to open xlsx file
        File file = new File(filePath + "/" + fileName);

        // Create an object of FileInputStream class to read excel file
        FileInputStream inputStream = new FileInputStream(file);
        Workbook workbook = null;

        // Find the file extension by splitting file name in substring and
        // getting only extension name
        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        // Check condition if the file is xlsx file
        if (fileExtensionName.equals(".xlsx")) {
            // If it is an xlsx file then create object of XSSFWorkbook class
            workbook = new XSSFWorkbook(inputStream);
        } else if (fileExtensionName.equals(".xls")) {
            // If it is an xls file then create object of XSSFWorkbook class
            workbook = new HSSFWorkbook(inputStream);
        }

        // Read sheet inside the workbook by its name
        Sheet sheet = workbook.getSheet(sheetName);

        // Find number of rows and cells in excel file
        int rowCount = sheet.getLastRowNum() + 1;
        int cellCount = sheet.getRow(0).getLastCellNum();
        // System.out.println("Row Count: " +rowCount+ " Cell Count: "
        // +cellCount);

        // Subtract 1 from row count to skip the header row.
        String[][] theArray = new String[rowCount - 1][cellCount];

        // Create a loop over all the rows of excel file to read it
        // Start from row 1 to skip the header row.
        for (int i = 1; i < rowCount; i++) {
            Row row = sheet.getRow(i);

            // Create a loop to print cell values in a row
            for (int j = 0; j < cellCount; j++) {
                // Print excel data in console
                // System.out.println("Setting [" +i+ "][" +j+ "] - " +
                // row.getCell(j).getStringCellValue());
                theArray[i - 1][j] = row.getCell(j).getStringCellValue();
            }
        }

        // System.out.println(theArray.toString());

        return theArray;
    }
}

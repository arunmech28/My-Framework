package Framework.Utilities;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.parser.ParseException;

import Framework.Handler.Parameters;

public class ExcelUtil {
	
	

	public static Object[][] getTestData(String fileName,String folderName) {
		Parameters params = Parameters.getInstance();
		ExcelReader excelread = null;
		try {
			excelread = new ExcelReader(params.getProperties().getProperty("TestDataPath")
					 +folderName+"\\"+fileName+".xlsx");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int numofrows = excelread.getNoOfRows();
		int numofcols = excelread.getNoOfCols();
		System.out.println("rows "+ numofrows);
		System.out.println("cols "+ numofcols);
		Object[][] testdata = new Object[numofrows-1][numofcols];
		
		for(int i=1;i<numofrows;i++) {
			for(int j=0;j<numofcols;j++) {
				testdata[i-1][j] = excelread.getCellData(i, j);
			}
		}
		
		
		return testdata;
		
	}
	
	public static List<String> getKeys(String fileName,String folderName) throws FileNotFoundException, IOException, ParseException{
		Parameters params = Parameters.getInstance();
		List<String> keyindex = new ArrayList<String>();
		ExcelReader excelread = new ExcelReader(params.getProperties().getProperty("TestDataPath")
				 +folderName+"\\"+fileName+".xlsx");
		
		int numofcols = excelread.getNoOfCols();
		
		
			for(int j=0;j<numofcols;j++) {
				keyindex.add(excelread.getCellData(0, j));
			}
		
		return keyindex;
		
	}

}

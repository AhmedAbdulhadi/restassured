package cucumber.rest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {
		FileInputStream fis = new FileInputStream("Book1.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int count = workbook.getNumberOfSheets();
		int column = 0;
		int iterator =0;
		for (int i = 0; i < count; i++) {
			
			if(workbook.getSheetName(i).equalsIgnoreCase("sheet1"));
			
			XSSFSheet sheet = workbook.getSheetAt(i);
			
			Iterator<Row> rows = sheet.iterator();
			Row objRow = rows.next();
			Iterator<Cell> cell = objRow.cellIterator();
			while(cell.hasNext()) {
				
				Cell value = cell.next();
				if(value.getStringCellValue().equalsIgnoreCase("testcase")) {
					
					column=iterator;
					
				}
				iterator++;
			}
			
			Row test = 
			
		}
		
		

	}
}

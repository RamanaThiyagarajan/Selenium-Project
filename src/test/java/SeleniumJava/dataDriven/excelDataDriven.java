package SeleniumJava.dataDriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelDataDriven {

	public ArrayList<String> getData(String testcasename) throws IOException {
		// TODO Auto-generated method stub

		ArrayList<String> a = new ArrayList<String>();
		FileInputStream fis = new FileInputStream("D:\\ExcelDemo.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		 int sheets = workbook.getNumberOfSheets();
		 
		 for(int i=0; i<sheets; i++) {
			  if(workbook.getSheetName(i).equalsIgnoreCase("testdata")) {
				  XSSFSheet sheet= workbook.getSheetAt(i);
				  Iterator<Row> rows = sheet.iterator();
				  Row firstRow = rows.next();
				  Iterator<Cell> ce = firstRow.cellIterator();
				  int k=0;
				  int column=0;
				  while(ce.hasNext()) {
					 Cell Value=  ce.next();
					 if(Value.getStringCellValue().equalsIgnoreCase("Testcase")) {
						 column=k;
					 }
					 k++;
				  }
				  System.out.println(column);
				  while(rows.hasNext()) {
					 Row r= rows.next();
					 if(r.getCell(column).getStringCellValue().equalsIgnoreCase(testcasename)) {
						Iterator<Cell> cv=  r.cellIterator();
						while(cv.hasNext()) {
							Cell cell=cv.next();
							if(cell.getCellType()==CellType.STRING) {
								a.add(cell.getStringCellValue());
							}else {
							a.add(NumberToTextConverter.toText(cell.getNumericCellValue()));
							}
						}
					 }
				  }
			  }
			  
		 }
		 return a;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

	}

}

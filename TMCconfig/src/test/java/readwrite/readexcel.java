package readwrite;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class readexcel

{

	public static void main(String[] args) throws

	EncryptedDocumentException, InvalidFormatException, IOException

	{

		FileInputStream fis = new FileInputStream("D:\\AutomationFramework\\TMCconfig\\ExportExcel.xlsx");

		Workbook wb = WorkbookFactory.create(fis);

		int rowCount = 0;
		System.out.println(
				wb.getSheet("Sheet2").getLastRowNum() + "===========" + wb.getSheet("Sheet2").getFirstRowNum());
		rowCount = wb.getSheet("Sheet2").getLastRowNum() - wb.getSheet("Sheet2").getFirstRowNum();

		for (int j = 0; j < wb.getSheet("Sheet2").getRow(j).getLastCellNum(); j++) {
			for (int ii = 0; ii < rowCount + 1; ii++) {
				Cell c = wb.getSheet("Sheet2").getRow(ii).getCell(j);
				System.out.println(c.toString());
			}

//Cell c = r.getCell(0); //String v = c.toString(); //System.out.println(v);

		}

	}
}
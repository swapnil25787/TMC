package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.io.FileHandler;

import downloadDocument.DownloadDoc;
import readwrite.ExcelWriting;

public class FetchFile {
	static Properties prop = new Properties();
	static String agendaFolder="";
	static String minutesFolder="";
	 public static void main(String[] args) throws IOException
	 {
		 
		 prop.load(new FileReader("Data.properties"));
		 String year=prop.getProperty("year");
		  agendaFolder=prop.getProperty("root")+ prop.getProperty("agenda.path")+ "\\" + year+"\\";
		  minutesFolder=prop.getProperty("root")+ prop.getProperty("minutes.path")+ "\\" + year+"\\";
		 FileHandler.createDir(new File(agendaFolder));
		 FileHandler.createDir(new File(minutesFolder));
		 System.out.println("Agenda Folder ---> " + agendaFolder);
		 System.out.println("Minutes Folder ---> " + minutesFolder);
		 readFromExcel(prop.getProperty("root")+prop.getProperty("data.file")); 
		 
		 
		 
	 }
	 
	 
	 public static void readFromExcel(String file) throws IOException
	 { 
	 	XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file)); 
	 	XSSFSheet sheet = myExcelBook.getSheet("Sheet1"); 
	 	int count=0;
	 	for (Row row:sheet)
	 	{
	 		//Agenda
	 		
	 		Cell cell = row.getCell(0);
	 		String agendaContent = cell.getStringCellValue();
	 		
	 		System.out.println("Agenda Content ----> " + agendaContent);
	 		
	 		if (! agendaContent.equalsIgnoreCase("blank") )
	 		{
	 			String[]  agendaArr = agendaContent.split("\\|\\|");
	 			System.out.println("Agenda Array Length ---> "+ agendaArr.length);
	 			System.out.println("Agenda array Content --->"+agendaArr[0]);
	 			
	 			for(int i=0; i <agendaArr.length; i++)
	 			{
	 				if(!agendaArr[i].equalsIgnoreCase("Blank") || ! agendaArr[i].equalsIgnoreCase("")) 
	 				{
	 					String[] agendaData = agendaArr[i].split(",");
	 					String agendaName = agendaData[0];
	 					
	 					System.out.println("Agenda Data ---> " + agendaData[0].toString());
	 					System.out.println("agendaName--->" + agendaName);
	 					String agendaDownloadUrl = agendaData[1];
	 					String downloadFileName =   agendaDownloadUrl.substring(agendaDownloadUrl.lastIndexOf("/")+1);
	 					downloadFileName=downloadFileName.replaceAll("%20", " ");
	 					String path = agendaFolder;
	 					//path = path + downloadFileName;
	 					DownloadDoc.DownloadPDFFromURL(agendaDownloadUrl, agendaFolder);
	 					ArrayList<String> dataList = new ArrayList<String>();
	 					dataList.add(agendaName + "," + "\""+ path + downloadFileName + "\"");
	 					ExcelWriting ex = new ExcelWriting();
	 					ex.excelWriting(prop.getProperty("root")+prop.getProperty("fetch.file"), count, dataList);
	 					
	 					
	 					
	 					String minutesContent = row.getCell(1).getStringCellValue();
	 					String[]  minutesArr = minutesContent.split("\\|\\|");
	 					if (! minutesContent.equalsIgnoreCase("blank") || ! minutesContent.equalsIgnoreCase(""))
	 					{
	 						String minutesConcat = "";
	 						for (int j=0; j <minutesArr.length;j++ ) 
		 					{
		 						if(!minutesArr[j].equalsIgnoreCase("Blank") )
			 					{
			 						
		 							String[] minutesData = minutesArr[j].split(",");
		 		 					String minutesName = minutesData[0];
		 		 					String minutesDownloadUrl = minutesData[1];
		 		 					String minutesdownloadFileName =   minutesDownloadUrl.substring(minutesDownloadUrl.lastIndexOf("/")+1);
		 		 					minutesdownloadFileName=minutesdownloadFileName.replaceAll("%20", " ");
		 		 					String minutespath = minutesFolder ;
		 		 					DownloadDoc.DownloadPDFFromURL(minutesDownloadUrl, minutesFolder);
		 		 					
		 		 					minutesConcat=minutesConcat+ minutesName+ "," + "\""+minutespath + minutesdownloadFileName +"\"" + "||";
		 		 					
		 		 					
			 					}
		 						
		 						
		 					}
	 						dataList.add(minutesConcat);
 		 					ex.excelWriting(prop.getProperty("root")+prop.getProperty("fetch.file"), count, dataList);
		 					
	 					}
	 					count++;
	 					
	 					
	 					
	 					
	 					
	 				}
	 				
	 				
	 				
	 				
	 			}
	 			
	 		}
	 		
	 		
	 		else
	 		{
	 			
	 			ArrayList<String> dataList = new ArrayList<String>();
	 			ExcelWriting ex = new ExcelWriting();
	 			dataList.add("Blank");
	 			ex.excelWriting(prop.getProperty("root")+prop.getProperty("fetch.file"), count, dataList);
	 			String minutesContent = row.getCell(1).getStringCellValue();
				String[]  minutesArr = minutesContent.split("\\|\\|");
				if (! minutesContent.equalsIgnoreCase("blank"))
				{
					{
	 					String minutesConcat = "";
	 					for (int j=0; j <minutesArr.length;j++ ) 
		 				{
		 					if(!minutesArr[j].equalsIgnoreCase("Blank") )
			 				{
			 						
		 						String[] minutesData = minutesArr[j].split(",");
		 		 				String minutesName = minutesData[0];
		 		 				String minutesDownloadUrl = minutesData[1];
		 		 				String minutesdownloadFileName =   minutesDownloadUrl.substring(minutesDownloadUrl.lastIndexOf("/")+1);
		 		 				minutesdownloadFileName=minutesdownloadFileName.replaceAll("%20", " ");
		 		 				String minutespath =minutesFolder;
		 		 				DownloadDoc.DownloadPDFFromURL(minutesDownloadUrl, minutesFolder);
		 		 					
		 		 				minutesConcat=minutesConcat+ minutesName+ "," + minutespath + minutesdownloadFileName + "||";
		 		 					
		 		 					
			 				}
		 						
		 						
		 				}
	 						dataList.add(minutesConcat);
 		 					ex.excelWriting(prop.getProperty("root")+prop.getProperty("fetch.file"), count, dataList);
		 					
	 				}
					count++;
				}
	 		}
	 		
	 		
	 		
	 	}
	 	
	 	
	 	
	 	
	 	myExcelBook.close(); 
	 }



	
	
	
	
	
	

}

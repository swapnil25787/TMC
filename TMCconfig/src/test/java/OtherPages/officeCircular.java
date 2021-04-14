package OtherPages;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import readwrite.ExcelWriting;

public class officeCircular {
	public static Map<String, ArrayList<String>> map1 = new LinkedHashMap<String, ArrayList<String>>();
	static ArrayList<String> valSetOne1 = null;
	public static void main(String[] args) {
		ExcelWriting ew=new ExcelWriting();
		String homePage = "https://thanecity.gov.in/office_circulars.php?pg=1";
		ArrayList<String> arrayList = new ArrayList<String>();
		System.setProperty("webdriver.chrome.driver","D:\\AutomationFramework\\ABMSmartScript\\functional\\config\\chromedriver36.exe");
		
		WebDriver driver = new ChromeDriver();
		int count=0;
		for (int j = 1; j <= 16; j++) {
			String url="https://thanecity.gov.in/office_circulars.php?pg="+j+"";
			driver.get(url);
			
			int size=driver.findElements(By.xpath("//*[@id=\"all\"]/div[3]/div/div[1]/div[2]/div/div[1]/div/table/tbody/tr[1]/td/table/tbody/tr")).size();
		
			
			
			
			for (int i = 1; i <= size; i++) {
				count++;	 
				String xpath="//*[@id='all']/div[3]/div/div[1]/div[2]/div/div[1]/div/table/tbody/tr[1]/td/table/tbody/tr"+"["+i+"]";
				String snno=xpath+"/td[1]";
				String CircularType=xpath+"/td[2]";
				String issuedate=xpath+"/td[3]";
				String serial=driver.findElement(By.xpath(snno)).getText();
				String circular=driver.findElement(By.xpath(CircularType)).getText();
				String issuedates=driver.findElement(By.xpath(issuedate)).getText();
				String href=xpath+"/td[4]";
				String da[]=issuedates.split("-");
				String modifiedissuedate="";
				//System.out.println("length"+da.length);
				  for (int k =da.length-1 ; k >=0 ; k--) {
					  
					  if (k>0) {
						  modifiedissuedate=modifiedissuedate+da[k]+"/";
					}else {
						 modifiedissuedate=modifiedissuedate+da[k];
					}
					  
					  
					  
		
					  }
				 
			
				  valSetOne1= new ArrayList<String>();
				if (j==1 &&i==1) {
				
					String hrefdata=driver.findElement(By.xpath(href)).getText();
					//System.err.println(serial+"|"+circular+"|"+issuedates+"|"+hrefdata);
					valSetOne1.add(serial);
					valSetOne1.add(circular);
					valSetOne1.add(issuedates);
					valSetOne1.add(hrefdata);
					ew.excelWriting("D:\\AutomationFramework\\TMCconfig\\ExportExcel.xlsx", count, valSetOne1);

					
				}
				if (i>1) {
										
					
					String hrefdata=driver.findElement(By.xpath(href+"/a")).getAttribute("href"	);
					//System.out.println(serial+"|"+circular+"|"+modifiedissuedate+"|"+hrefdata);
					valSetOne1.add(serial);
					valSetOne1.add(circular);
					valSetOne1.add(modifiedissuedate);
					valSetOne1.add(hrefdata);
					ew.excelWriting("D:\\AutomationFramework\\TMCconfig\\ExportExcel.xlsx", count, valSetOne1);
				}
				
		
				
				
				
				driver.findElement(By.xpath(xpath)).getText();
				
			}
	
		}
				System.out.println(count+"count"+valSetOne1);
		driver.quit();

	}

}

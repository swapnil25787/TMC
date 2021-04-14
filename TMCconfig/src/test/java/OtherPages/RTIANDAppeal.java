package OtherPages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import downloadDocument.DownloadDoc;
import readwrite.ExcelWriting;

public class RTIANDAppeal {
	public static Map<String, ArrayList<String>> map1 = new LinkedHashMap<String, ArrayList<String>>();
	static ArrayList<String> valSetOne1 = null;

	public static void main(String[] args) throws IOException {
		ExcelWriting ew = new ExcelWriting();
		String homePage = "https://thanecity.gov.in/office_circulars.php?pg=1";
		ArrayList<String> arrayList = new ArrayList<String>();
		System.setProperty("webdriver.chrome.driver",
				"D:\\AutomationFramework\\ABMSmartScript\\functional\\config\\chromedriver36.exe");

		WebDriver driver = new ChromeDriver();
		int count = 0;
		// FileWriter writer = new
		// FileWriter("C:\\Users\\swapnil.patil\\Desktop\\TSCL\\GeneralBodyMeetings\\MyFile.html",
		// true);
		for (int j = 0; j <= 30; j++) {
			String url ;
			if (j==0) {
				 url = "https://thanecity.gov.in/quotations.php";
			}else {
				url = "https://thanecity.gov.in/arch_quotations.php?pg=" + j + "";
			}
		
			driver.get(url);

			int size = driver
					.findElements(By.xpath("//*[@id=\"all\"]/div[3]/div/div[1]/div[2]/div/div[1]/div/table/tbody/tr"))
					.size();

			for (int i = 1; i <= size; i++) {
				try {
					count++;
					
					String xpath = "//*[@id=\"all\"]/div[3]/div/div[1]/div[2]/div/div[1]/div/table/tbody/tr" + "[" + i
							+ "]";
					String QuotationName = xpath + "/td[1]";
					String Department = xpath + "/td[2]";
					String IssueDate = xpath + "/td[3]";
					String LastDate = xpath + "/td[4]";
					String QuotationName1 = driver.findElement(By.xpath(QuotationName)).getText();
					String Department1 = driver.findElement(By.xpath(Department)).getText();
					String IssueDate1 = driver.findElement(By.xpath(IssueDate)).getText();
					
					String Lastdate=driver.findElement(By.xpath(LastDate)).getText();
					String href = xpath + "/td[5]";
					String da[] = IssueDate1.split("-");
					String modifiedissuedate = "";
					// System.out.println("length"+da.length);
					for (int k = da.length - 1; k >= 0; k--) {

						if (k > 0) {
							modifiedissuedate = modifiedissuedate + da[k] + "/";
						} else {
							modifiedissuedate = modifiedissuedate + da[k];
						}

					}
System.out.println(modifiedissuedate);
					if (j == 1 && i == 1) {
						valSetOne1=new ArrayList<String>();
						String hrefdata = driver.findElement(By.xpath(href)).getText();
						
						
						  System.out.println(QuotationName1+"|"+Department1+"|"+IssueDate1.replaceAll("-", "/")+"|"+Lastdate.replaceAll("-", "/")+ "|"+hrefdata+"|"); 
						  
						  valSetOne1.add(QuotationName1);
						  valSetOne1.add(Department1);
						  valSetOne1.add(IssueDate1.replaceAll("-", "/"));
						  valSetOne1.add(Lastdate.replaceAll("-", "/"));
						  valSetOne1.add(hrefdata);
						  // valSetOne1.add(hrefdata); //
						  ew.excelWriting("D:\\AutomationFramework\\TMCconfig\\ExportExcel.xlsx", count, valSetOne1);
						 

					}
					if (i > 1) {
						valSetOne1=new ArrayList<String>();
						String hrefdata = driver.findElement(By.xpath(href + "/a")).getAttribute("href");
						  valSetOne1.add(QuotationName1);
						  valSetOne1.add(Department1);
						  valSetOne1.add(IssueDate1.replaceAll("-", "/"));
						  valSetOne1.add(Lastdate.replaceAll("-", "/"));
						  valSetOne1.add(hrefdata);
						  System.out.println(QuotationName1+"|"+Department1+"|"+IssueDate1.replaceAll("-", "/")+"|"+Lastdate.replaceAll("-", "/")+ "|"+hrefdata+"|"); 
						 ew.excelWriting("D:\\AutomationFramework\\TMCconfig\\ExportExcel.xlsx", count, valSetOne1);
					}

					driver.findElement(By.xpath(xpath)).getText();
				} catch (Exception e) {

				}

			}

		}

		// writer.close();
		System.out.println(count + "count");
		driver.quit();

	}

}

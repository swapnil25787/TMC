package pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import readwrite.ExcelWriting;

public class fetchertext {

	public int standing(String Year, WebDriver d, int c) {
		int count = c;
		try {
			
			WebDriver driver = d;
			System.out.println("====================================" + Year + "==================================");
			ExcelWriting ew = new ExcelWriting();
			ArrayList<String> datatobewritten;

			// driver = new ChromeDriver();

			// driver.get("https://thanecity.gov.in/tmcGBYears2009_10.php");
			// driver.manage().window().maximize();

			List<WebElement> List = driver.findElements(By.className("rti_arrow"));

			List<WebElement> rti = List.get(0).findElements(By.tagName("a"));

			for (int i = 0; i < rti.size(); i++) {
				count++;
				datatobewritten = new ArrayList<String>();
				
				//System.out.println(Year + "|" + rti.get(i).getText() + "|" + rti.get(i).getAttribute("href"));
				
				datatobewritten.add("Agenda");
				datatobewritten.add(Year);
				String rti1[] = rti.get(i).getText().split("-");
				System.out.println( " rti1.length"+rti1.length);
				for (int j = 0; j < rti1.length; j++) {
				//	System.out.println("rti1[i].trim()"+rti1[j].trim());
					datatobewritten.add(rti1[j].trim());
				}
				datatobewritten.add(rti.get(i).getAttribute("href"));
System.out.println(datatobewritten);
				ew.excelWriting("D:\\AutomationFramework\\TMCconfig\\ExportExcel.xlsx", count, datatobewritten);

			}
			
			
			
			
			
			
			
			
			
			
			System.out.println("====================================Appeal==================================");
			count++;
			if (List.size() < 2) {
			} else {
				List<WebElement> ParentMinutes = List.get(1).findElements(By.tagName("li"));

				List<WebElement> Minutes = null;
				for (int l = 0; l < ParentMinutes.size(); l++) {

					List<WebElement> child = ParentMinutes.get(l).findElements(By.tagName("a"));

					for (int k = 0; k < child.size(); k++) {
						count++;
						datatobewritten = new ArrayList<String>();
						datatobewritten.add("Minutes");
						datatobewritten.add(Year);
						
						String rti1[] = child.get(k).getText().split("-");
						System.out.println( " rti1.length"+rti1.length);
						for (int j = 0; j < rti1.length; j++) {
						//	System.out.println("rti1[i].trim()"+rti1[j].trim());
							datatobewritten.add(rti1[j].trim());
							
						}
						datatobewritten.add(child.get(k).getAttribute("href"));
						//System.out.println(Year + "|" + child.get(k).getText() + "|" + child.get(k).getAttribute("href"));
						ew.excelWriting("D:\\AutomationFramework\\TMCconfig\\ExportExcel.xlsx", count, datatobewritten);
					}

				}

			}
			
			
//System.out.println("count"+count);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	
	
	}

	public static void main(String[] args) throws InterruptedException, IOException {
	}
}

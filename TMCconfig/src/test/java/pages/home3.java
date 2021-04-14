package pages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.formula.functions.Count;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import downloadDocument.DownloadDoc;
import readwrite.ExcelWriting;

public class home3 {

	public static int count = 0;

	public void MeetingsPage(String Year, WebDriver driver) {

		try {

			ArrayList<String> arrayList = new ArrayList<String>();
			ExcelWriting ew = new ExcelWriting();
			ArrayList<String> datatobewritten;

			String url = "";

			System.setProperty("webdriver.chrome.driver",
					"D:\\AutomationFramework\\ABMSmartScript\\functional\\config\\chromedriver36.exe");

			WebDriverWait wait = new WebDriverWait(driver, 20);

			List<WebElement> list = driver.findElements(By.className("rti_arrow"));

			if (list.size() < 2) {

				List<WebElement> agenda = list.get(0).findElements(By.tagName("a"));
				for (int kk = 0; kk < agenda.size(); kk++)
				{
					count++;

					List<WebElement> ag1 = null;
					datatobewritten = new ArrayList<String>();

					datatobewritten.add(Year);
					datatobewritten.add(agenda.get(kk).getText());
					datatobewritten.add(agenda.get(kk).getAttribute("href"));

					ew.excelWriting("D:\\AutomationFramework\\TMCconfig\\ExportExcel.xlsx", count, datatobewritten);

				}

			} else {

				List<WebElement> agenda = list.get(0).findElements(By.tagName("a"));
				List<WebElement> miniutes = list.get(1).findElements(By.tagName("li"));
System.err.println("asdddddddsasdas"+agenda.size());
				for (int i = 0; i < agenda.size(); i++) {

					try {
						List<WebElement> ag1 = null;
						datatobewritten = new ArrayList<String>();
						int miniutescount=0;
						try {
							
							ag1 = miniutes.get(i).findElements(By.tagName("a"));
							miniutescount=ag1.size();
							System.err.println(ag1.size());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							 miniutescount=0;
							
						}
						
						if (miniutescount== 0) {
							count++;
							datatobewritten = new ArrayList<String>();
							datatobewritten.add(Year);
							datatobewritten.add(agenda.get(i).getText());
							datatobewritten.add(agenda.get(i).getAttribute("href"));

							ew.excelWriting("D:\\AutomationFramework\\TMCconfig\\ExportExcel.xlsx", count, datatobewritten);

						} 
						else
						{
							for (int k = 0; k < ag1.size(); k++) {
								count++;
								datatobewritten = new ArrayList<String>();
								// System.out.println(agenda.get(i).getText() + "|" +
								// agenda.get(i).getAttribute("href") + "|" + ag1.get(k).getText() + "|" +
								// ag1.get(k).getAttribute("href"));
								datatobewritten.add(Year);
								datatobewritten.add(agenda.get(i).getText());
								datatobewritten.add(agenda.get(i).getAttribute("href"));
								datatobewritten.add(ag1.get(k).getText());
								datatobewritten.add(ag1.get(k).getAttribute("href"));
								ew.excelWriting("D:\\AutomationFramework\\TMCconfig\\ExportExcel.xlsx", count,datatobewritten);

							}
						}

						System.err.println(datatobewritten);
					} catch (Exception e) {
						
						e.printStackTrace();
					}
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		System.setProperty("webdriver.chrome.driver",
				"D:\\AutomationFramework\\ABMSmartScript\\functional\\config\\chromedriver36.exe");
		// ChromeOptions options = new ChromeOptions();

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);

		WebDriver driver = new ChromeDriver();
		home3 hm = new home3();
		driver.navigate().to("https://thanecity.gov.in/tmcGBYears2019_20.php");
		hm.MeetingsPage("2019", driver);
	}

}

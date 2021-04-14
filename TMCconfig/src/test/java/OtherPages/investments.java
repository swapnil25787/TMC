package OtherPages;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import readwrite.ExcelWriting;

public class investments {
	static ArrayList<String> valSetOne1 = null;
	public static void main(String[] args) throws InterruptedException {
		
		ExcelWriting ew=new ExcelWriting();
		String homePage = "https://thanecity.gov.in/account_investments.php?pg=9";
		ArrayList<String> arrayList = new ArrayList<String>();
		System.setProperty("webdriver.chrome.driver","D:\\AutomationFramework\\ABMSmartScript\\functional\\config\\chromedriver36.exe");
WebDriver driver = new ChromeDriver();
int count=0;
		for (int j = 1; j <= 51; j++) {
						String url="https://thanecity.gov.in/press_release.php?pg="+j+"";
			driver.get(url);
			//driver.manage().window().maximize();
			Thread.sleep(2000);
			WebElement inclass=driver.findElement(By.className("page_left"));
			List<WebElement> invest=inclass.findElements(By.tagName("a"));
			int size =driver.findElements(By.xpath("//*[@id=\"all\"]/div[3]/div/div[1]/div[2]/div/div[1]/div[1]/ul")).size();
			
			for (int i = 1; i <= size; i++) {
				//*[@id=\"all\"]/div[3]/div/div[1]/div[2]/div/div[1]/div[1]/ul
				//*[@id="all"]/div[3]/div/div[1]/div[2]/div/div[1]/div[1]/ul[1]/li/div/div/span[1]/a
				//*[@id="all"]/div[3]/div/div[1]/div[2]/div/div[1]/div[1]/ul[1]/li/div/div/span[2]
				
				String xpath="//*[@id=\"all\"]/div[3]/div/div[1]/div[2]/div/div[1]/div[1]/ul["+i+"]/";
				String ptesxt=xpath+"li/div/div/span[1]/a";
				String dates=xpath+"li/div/div/span[2]";
				
				
				valSetOne1=new ArrayList<String>();

			
				valSetOne1.add(driver.findElement(By.xpath(ptesxt)).getText());
				valSetOne1.add(driver.findElement(By.xpath(dates)).getText());
				valSetOne1.add(driver.findElement(By.xpath(ptesxt)).getAttribute("href"));
				
		
					count++;
					ew.excelWriting("D:\\AutomationFramework\\TMCconfig\\ExportExcel.xlsx", count, valSetOne1);
			
				 
			}
			System.out.println(count);
			}
	
		
				
		driver.quit();
	}}



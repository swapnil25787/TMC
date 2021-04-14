import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Throwables;
import com.ibm.icu.impl.ICUService.Key;


import pages.fetchertext;
import readwrite.ReadGuru99ExcelFile;

public class PortalProduct {
	
	public static void upload(String path) throws IOException, InterruptedException
	{
		Process proc=Runtime.getRuntime().exec("D:\\AutomationFramework\\TMCconfig\\src\\test\\resources\\upload.exe"+" "+path);
		
		System.err.println(proc.isAlive());

		for (int k = 0; k < 1;)  {
			if (proc.isAlive()) {
				System.err.println(" part1");
				Thread.sleep(3000);
			}else {
				System.err.println("else part");
				break;
			} 
		} 
		
	}

	public static void wait(WebDriver driver) {
		
		int i;
		for ( i = 0; i <1; ) {
			
	
		try {
			driver.findElement(By.xpath("//li[contains(text(),'1File uploaded')]")).isDisplayed();
			Thread.sleep(1000);
			break;
		} catch (Exception e) {
		System.out.println("Path not found");
		 i = 0;
		}
		}
		
	}
	public static void main(String[] args) throws InterruptedException, IOException {
		
		
		PortalProduct pr=new PortalProduct();
	
		String homePage = "http://103.209.145.36:8069/CitizenHome.html#";



		

		System.setProperty("webdriver.chrome.driver","D:\\AutomationFramework\\ABMSmartScript\\functional\\config\\chromedriver36.exe");
		
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS) ;
		// Thread.sleep(2000);//element =wait.until(ExpectedConditions.visibilityOfElementLocated
		
		driver.get(homePage);
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//span[8]//a[1]")).click();
		driver.findElement(By.xpath("//a[@class='internal'][contains(text(),'Login')]")).click();
		driver.findElement(By.xpath("//ul[@class='main-navigation']//ul//li//span[contains(text(),'Portal Admininstrator Login')]")).click();
		driver.findElement(By.xpath("//input[@id='emploginname']")).sendKeys("Rushiraj");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='adminEmployee.emppassword']")).sendKeys("Rushiraj@1993");
		driver.findElement(By.id("captchaSessionLoginValue")).sendKeys("1111");
		pr.waits(driver);
		
		
		
	
		
		
	
		driver.findElement(By.xpath("//input[@class='btn btn-success btn-block']")).click();
		
		///add code
		///code need to loop*****************************
		
		
		//WebElement Thread.sleep(2000);//element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Add Section Details')]")));
		pr.waits(driver);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Add Section Details')]")).click();
		
	
		pr.waits(driver);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Authenticated')]")).click();
		pr.waits(driver);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//td[@id='search_gridSectionDetailsApp']//span[@class='ui-icon ui-icon-search']")).click();
		Thread.sleep(1000);
		pr.waits(driver);
		Select drpCountry1 = new Select(driver.findElement(By.xpath("//td[@class='columns']//select")));
		drpCountry1.selectByVisibleText("Link Name (English)");
		
		drpCountry1= new Select(driver.findElement(By.xpath("//select[@class='selectopts']")));
		drpCountry1.selectByVisibleText("contains");
		
		Thread.sleep(1000);
		String LinkName="performance";
		
		driver.findElement(By.id("jqg2")).sendKeys(LinkName);
		pr.waits(driver);
		driver.findElement(By.xpath("//a[@id='fbox_gridSectionDetailsApp_search']")).click();
		Thread.sleep(1000);
		pr.waits(driver);
		 
		driver.findElement(By.xpath("//*[@id=\"gridSectionDetailsApp\"]/tbody/tr[2]/td[6]/form/a/img")).click();
		 pr.waits(driver);
		
		ReadGuru99ExcelFile objExcelFile = new ReadGuru99ExcelFile();
	    String filePath = "D:\\AutomationFramework\\TMCconfig\\";
	     objExcelFile.readExcel(filePath,"ExportExcel.xlsx","Sheet4");
	    System.err.println(objExcelFile.map1.get("Download"));
	    System.err.println(objExcelFile.map1.get("Download").size());
	   
		//for loop
		for (int i = 0; i < objExcelFile.map1.get("Download").size(); i++) {
	
			Thread.sleep(2000);
			 driver.findElement(By.xpath("//input[@id='AdminAdd']")).click();
			 pr.waits(driver);
			 
			 
			 
			 
			 
			 
			 
			 ///Qutation name 
			 driver.findElement(By.name("entity.txta_01_en")).sendKeys(objExcelFile.map1.get("type").get(i).toString());
			 driver.findElement(By.name("entity.txta_01_rg")).sendKeys(objExcelFile.map1.get("type").get(i).toString());
			 
			 
			 
			 
			 ///Dropdown
			 Select Dropdown = new Select(driver.findElement(By.id("entity.txt_01_en")));
			 Dropdown.selectByVisibleText("Account Department");
			 ///Dropdown
			 Select DropdownHindi = new Select(driver.findElement(By.id("entity.txt_01_rg")));
			 DropdownHindi.selectByVisibleText("लेखा विभाग");
			 
			 //Date
			 driver.findElement(By.name("entity.date_01")).sendKeys(objExcelFile.map1.get("Issue Date").get(i).toString());
			 driver.findElement(By.name("entity.date_02")).sendKeys(objExcelFile.map1.get("Last Date").get(i).toString());
			 
			 //issue Date
			 
			/*
			 * driver.findElement(By.id("entity.date_01")).sendKeys(objExcelFile.map1.get(
			 * "issuedate").get(i).toString());
			 * driver.findElement(By.id("entity.date_02")).sendKeys(objExcelFile.map1.get(
			 * "lastdate").get(i).toString());
			 */
			 
			 driver.findElement(By.id("entity.date_02")).sendKeys(Keys.TAB);
			 
			 
			 //upload
			 Thread.sleep(1000);
			 driver.findElement(By.xpath("//label[contains(text(),'Download')]")).click();
			
			 String Path1=objExcelFile.map1.get("Download").get(i).toString();
			 System.out.println("path+"+Path1);
			 String pdfname[]=Path1.split("/");
	         String Path="D:\\AutomationFramework\\TMCconfig\\qutations\\"+pdfname[pdfname.length-1].replace("%20", "");
	         Thread.sleep(1000);
				upload(Path);
				wait(driver);
			//	driver.findElement(By.id("entity.validityDate")).sendKeys("31/03/2020 06:00 am");
				
			 //driver.findElement(By.id("entity.subLinkFieldDtlOrd")).clear();
			 
			 
		//	 driver.findElement(By.id("entity.subLinkFieldDtlOrd")).sendKeys(objExcelFile.map1.get("SRNO").get(i).toString());
			 JavascriptExecutor js = (JavascriptExecutor)driver;
			 js.executeScript("document.getElementById('entity.chekkerflag1').disabled = false;");
			 
			   WebElement button =driver.findElement(By.id("entity.chekkerflag1"));
			   js.executeScript("arguments[0].click();", button);
			   pr.waits(driver);
			 driver.findElement(By.xpath("//input[@class='css_btn btn btn-success']")).click();
			 pr.waits(driver);
		}
		 
		
		
	
		
		
		 
		 
		 
		 
		 
		 
		 
		 
		
		
		
		
	}

	
	
	
	
	
	
	
	public void waits(WebDriver d)
	{
		
		FluentWait wait = new FluentWait(d); 
		//Specify the timout of the wait
		wait.withTimeout(5000, TimeUnit.MILLISECONDS);
		//Sepcify polling time
		wait.pollingEvery(350, TimeUnit.MILLISECONDS);
		//Specify what exceptions to ignore
		wait.ignoring(NoSuchElementException.class);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

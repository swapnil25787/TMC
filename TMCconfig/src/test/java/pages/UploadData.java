package pages;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.text.DecimalFormat;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;

public class UploadData {
	
	 static Properties prop = new Properties();
	//static FileHandler  f = new FileHandler ();
	private static DecimalFormat df = new DecimalFormat("##.#####");
	 
	 public static void main(String[] args) throws IOException, InterruptedException
	 {
		
		
		 prop.load(new FileReader("Data.properties"));
		 		
		 System.setProperty("webdriver.chrome.driver", prop.getProperty("chrome.driver")); 
		 WebDriver driver = new ChromeDriver();
		
		 doLoginSetup(driver);
		 readExcel(prop.getProperty("root")+prop.getProperty("fetch.file"),driver);
		 Thread.sleep(2000);
		 //tearDown();
		 
		 
		 
		 
	 }
	
	public static void readExcel(String file, WebDriver driver) throws FileNotFoundException, IOException, InterruptedException
	{
		XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(file)); 
	 	XSSFSheet sheet = myExcelBook.getSheet("Sheet1"); 
	 	String year=prop.getProperty("year");
	 	
	 	double counter =0.0001;
	 	for (Row row:sheet)
	 	{
	 				
	 		String agendaContent =row.getCell(0).getStringCellValue();
	 		String minutesContent =row.getCell(1).getStringCellValue();
	 		//Agenda Not Blank
	 		if(!agendaContent.equalsIgnoreCase("blank"))
	 		{
	 			String agendapath = "";
	 			String[] agendaData = agendaContent.split(",");
	 			String agendaname = agendaData[0];
	 			try {
	 			 agendapath = agendaData[1];
	 			 if(agendapath.contains("javascript:void(0)"))
	 			 {
	 				agendapath="";
	 			 }
	 			 
	 			}
	 			catch(Exception e) {}
	 			System.out.println("Agenda Path ----> " + agendapath);
	 			
	 			
	 			// Minutes = null, Add only agenda
	 			if(minutesContent.equalsIgnoreCase("")) {	 
	 				
	 				commonSetup(driver,year);
	 				agendaDetails(driver,agendaname,agendapath);
	 				Thread.sleep(2000);
	 				driver.findElement(By.id("entity.subLinkFieldDtlOrd")).clear();
	 				Thread.sleep(1000);
	 				driver.findElement(By.id("entity.subLinkFieldDtlOrd")).sendKeys(year.substring(0,4)+(df.format(counter)).substring(1));
	 				Thread.sleep(1000);
	 				driver.findElement(By.cssSelector("input[type='submit']")).click();
	 				Thread.sleep(1000);
	 				counter=counter+0.0001;
	 			}
	 			// Add Agenda & Minutes
	 			else {
	 				
	 				String[] minutesData=minutesContent.split("\\|\\|");
		 			for(int i=0; i < minutesData.length;i++)
		 			{
		 				
		 				String[] minutesSubData=minutesData[i].split(",");
		 				String minutespath="";
		 				String minutesName= minutesSubData[0];
		 				try {
		 				minutespath=minutesSubData[1]; 
		 				
		 				if(minutespath.contains("javascript:void(0)"))
			 			 {
		 					minutespath="";
			 			 }
		 				} catch(Exception e) {}
		 				System.out.println("Minutes Path --->" + minutespath);
		 				commonSetup(driver,year);
		 				Thread.sleep(2000);	
		 				agendaDetails(driver,agendaname,agendapath);
		 				Thread.sleep(2000);					
		 				minutesDetails(driver,minutesName,minutespath);
		 				driver.findElement(By.id("entity.subLinkFieldDtlOrd")).clear();
		 				Thread.sleep(1000);
		 				driver.findElement(By.id("entity.subLinkFieldDtlOrd")).sendKeys(year.substring(0,4)+(df.format(counter)).substring(1));
		 				Thread.sleep(1000);
		 				driver.findElement(By.cssSelector("input[type='submit']")).click();
		 				Thread.sleep(3000);
		 				counter=counter+0.0001;
		 			}
	 			}
	 			
	 		}
	 		// Agenda Blank, add only minutes
	 		else
	 		{
	 			if(!minutesContent.equalsIgnoreCase("")) {
	 				
	 				String[] minutesData=minutesContent.split("\\|\\|");
		 			
		 			for(int i=0; i < minutesData.length;i++)
		 			{
		 				
		 				String[] minutesSubData=minutesData[i].split(",");
		 				String minutespath="";
		 				String minutesName= minutesSubData[0];
		 						 				
		 				try {
			 				minutespath=minutesSubData[1]; 
			 				} catch(Exception e) {}
		 				
		 				System.out.println("Minutes Path --->" + minutespath);
		 				
		 				Thread.sleep(2000);	
		 				commonSetup(driver,year);
		 				Thread.sleep(2000);
						minutesDetails(driver,minutesName,minutespath);
		 				Thread.sleep(2000);
		 				driver.findElement(By.id("entity.subLinkFieldDtlOrd")).clear();
		 				Thread.sleep(1000);
		 				driver.findElement(By.id("entity.subLinkFieldDtlOrd")).sendKeys(year.substring(0,4)+(df.format(counter)).substring(1));
		 				Thread.sleep(1000);
		 				driver.findElement(By.cssSelector("input[type='submit']")).click();
		 				Thread.sleep(3000);
		 				counter=counter+0.0001;
		 				
		 			}
	 			}
	 		}
	 		
	 	}
		myExcelBook.close(); 
		
	}
	
	public static void doLoginSetup(WebDriver driver) throws InterruptedException
	{
		String homePage = prop.getProperty("url");

		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS) ;
		// Thread.sleep(2000);//element =wait.until(ExpectedConditions.visibilityOfElementLocated
		
		driver.get(homePage);
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//span[8]//a[1]")).click();
		driver.findElement(By.xpath("//a[@class='internal'][contains(text(),'Login')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//ul[@class='main-navigation']//ul//li//span[contains(text(),'Portal Admininstrator Login')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='emploginname']")).sendKeys(prop.getProperty("user"));
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='adminEmployee.emppassword']")).sendKeys(prop.getProperty("password"));
		Thread.sleep(1000);
		driver.findElement(By.id("captchaSessionLoginValue")).sendKeys("1111");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class='btn btn-success btn-block']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Add Section Details')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Authenticated')]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//td[@id='search_gridSectionDetailsApp']//span[@class='ui-icon ui-icon-search']")).click();
		Thread.sleep(2000);//element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='columns']//select")));
		Select drpCountry1 = new Select(driver.findElement(By.xpath("//td[@class='columns']//select")));
		drpCountry1.selectByVisibleText("Link Name (English)");
		
		drpCountry1= new Select(driver.findElement(By.xpath("//select[@class='selectopts']")));
		drpCountry1.selectByVisibleText("contains");
		
		
		String LinkName="general";
		
		driver.findElement(By.id("jqg2")).sendKeys(LinkName);
		driver.findElement(By.xpath("//a[@id='fbox_gridSectionDetailsApp_search']")).click();
		 Thread.sleep(2000);//element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[alt='Edit Details']")));
		driver.findElement(By.cssSelector("img[alt='Edit Details']")).click();
		 Thread.sleep(2000);//element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='AdminAdd']")));
		
		
	}
	
	
	
	public static void uploadFile(String path) throws IOException, InterruptedException
	{
		Process proc=Runtime.getRuntime().exec("H:\\Nishit_Gala\\Thane\\Web Portal\\upload.exe"+" "+path);
		
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
	
	
	public static void agendaDetails(WebDriver driver, String agendaname,String agendapath) throws InterruptedException, IOException
	{
		
		//211
		driver.findElement(By.id("txt_02_en")).sendKeys(agendaname);
		driver.findElement(By.id("txt_02_rg")).sendKeys(agendaname);
		
		//36
		//driver.findElement(By.id("_en")).sendKeys(agendaname);
		//driver.findElement(By.id("_rg")).sendKeys(agendaname);
		Thread.sleep(2000);
			driver.findElement(By.xpath("(.//span[@class='btn btn-darkblue-2 btn-file'])[1]")).click();
			Thread.sleep(1000);
			if(!agendapath.equalsIgnoreCase(""))
			{
				uploadFile(agendapath);
			}
			
			
			Thread.sleep(5000);	
			
			
	}
	
	public static void minutesDetails(WebDriver driver, String minutesName,String minutespath) throws InterruptedException, IOException
	{
		driver.findElement(By.xpath("(.//span[@class='btn btn-darkblue-2 btn-file'])[2]")).click();
			Thread.sleep(2000);
			if(!minutespath.equalsIgnoreCase(""))
			{
				uploadFile(minutespath);
			}
			//211
			driver.findElement(By.id("txt_03_en")).sendKeys(minutesName);
			driver.findElement(By.id("txt_03_rg")).sendKeys(minutesName);
			//36
			//driver.findElement(By.id("txt_02_en")).sendKeys(minutesName);
			//driver.findElement(By.id("txt_02_rg")).sendKeys(minutesName);
			Thread.sleep(5000);
	}
	
	public static void commonSetup(WebDriver driver, String year) throws InterruptedException
	{
			driver.findElement(By.xpath("//input[@id='AdminAdd']")).click();
			Thread.sleep(2000);	
			driver.findElement(By.id("txt_01_en")).sendKeys(year);
			driver.findElement(By.id("txt_01_rg")).sendKeys(year);
	}
	
	public static void tearDown() throws IOException
	{
		//Files.deleteIfExists(Paths.get(prop.getProperty("root")+prop.getProperty("fetch.file")));
		//Files.deleteIfExists(Paths.get(prop.getProperty("root")+prop.getProperty("data.file")));
	}

}

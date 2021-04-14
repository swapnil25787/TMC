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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Throwables;
import com.ibm.icu.impl.ICUService.Key;

import pages.fetchertext;
import readwrite.ReadGuru99ExcelFile;

public class PressRealease {
	
	
	
	
	
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
	
	
	
	
	
	
	
	public String month(String data) {

		String da[] = data.split(",");

		for (int i = 0; i < da.length; i++) {
			// System.out.println(da[i]);
		}
		String d = da[1];

		String dd[] = d.split(" ");

		for (int i = 0; i < dd.length; i++) {
			System.out.println("--" + dd[i]);
		}

		String num = "";

		String key = dd[2];
		switch (key) {
		case "Jan":
			num = "1";
			break;
		case "Feb":
			num = "2";
			break;
		case "Mar":
			num = "3";
			break;
		case "Apr":
			num = "4";
			break;

		case "May":
			num = "5";
			break;

		case "Jun":
			num = "6";
			break;
		case "Jul":
			num = "7";
			break;

		case "Aug":
			num = "8";
			break;

		case "Sep":
			num = "9";
			break;

		case "Oct":
			num = "10";
			break;

		case "Nov":
			num = "11";
			break;
		case "Dec":
			num = "12";
			break;

		}
		System.out.println(key);
		System.out.println(num);
		System.out.println(data.replaceAll(key, "/" + num + "/").replaceAll(" ", ""));
		return data.replaceAll(key, "/" + num + "/").replaceAll(" ", "");
	}

	public static void upload(String path) throws IOException, InterruptedException {
		Process proc = Runtime.getRuntime()
				.exec("D:\\AutomationFramework\\TMCconfig\\src\\test\\resources\\upload.exe" + " " + path);

		System.err.println(proc.isAlive());

		for (int k = 0; k < 1;) {
			if (proc.isAlive()) {
				System.err.println(" part1");
				Thread.sleep(2000);
			} else {
				System.err.println("else part");
				break;
			}
		}

	}

	public static void wait(WebDriver driver) {

		int i;
		for (i = 0; i < 1;) {

			try {
				driver.findElement(By.xpath("//li[contains(text(),'1File uploaded')]")).isDisplayed();
				break;
			} catch (Exception e) {
				System.out.println("Path not found");
				i = 0;
			}
		}

	}

	public static void main(String[] args) throws InterruptedException, IOException {
		PressRealease pr = new PressRealease();
		

		String homePage = "http://103.235.206.211/tscl/CitizenHome.html";

		System.setProperty("webdriver.chrome.driver",
				"D:\\AutomationFramework\\ABMSmartScript\\functional\\config\\chromedriver36.exe");

		WebDriver driver = new ChromeDriver();
 
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		// Thread.sleep(2000);//element
		// =wait.until(ExpectedConditions.visibilityOfElementLocated
		
		

		 WebElement element ;
	
	
		driver.get(homePage);
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//span[8]//a[1]")).click();
		driver.findElement(By.xpath("//a[@class='internal'][contains(text(),'Login')]")).click();
		driver.findElement(By
				.xpath("//ul[@class='main-navigation']//ul//li//span[contains(text(),'Portal Admininstrator Login')]"))
				.click();
		driver.findElement(By.xpath("//input[@id='emploginname']")).sendKeys("automation1");
		driver.findElement(By.xpath("//input[@id='adminEmployee.emppassword']")).sendKeys("Pass@123");
		driver.findElement(By.id("captchaSessionLoginValue")).sendKeys("1111");

	
		pr.waits(driver);
		//Thread.sleep(2000);;
		driver.findElement(By.xpath("//input[@class='btn btn-success btn-block']")).click();

		/// add code
		/// code need to loop*****************************

		// WebElement Thread.sleep(2000);//element														
		// =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Add
		// Section Details')]")));
		pr.waits(driver);

		driver.findElement(By.xpath("//a[contains(text(),'Add Recent Announcements')]")).click();

		pr.waits(driver);
		
	
		driver.findElement(By.xpath("//a[contains(text(),'Authenticated')]")).click();

		pr.waits(driver);

		ReadGuru99ExcelFile objExcelFile = new ReadGuru99ExcelFile();
		String filePath = "D:\\AutomationFramework\\TMCconfig\\";
		objExcelFile.readExcel(filePath, "PressRelease.xlsx", "Sheet2");
		System.err.println(objExcelFile.map1.get("Download"));
		System.err.println(objExcelFile.map1.get("Download").size());

		// for loop
		for (int i = objExcelFile.map1.get("Download").size() - 1; i >= 0; i--) {
			pr.waits(driver);
			
		
			driver.findElement(By.xpath("//a[@class='btn btn-success internal']")).click();
			pr.waits(driver);
		
			/// Details (English)/SEO Keywords
			driver.findElement(By.id("entity.announceDescEng"))
					.sendKeys(objExcelFile.map1.get("Order No.").get(i).toString());
			driver.findElement(By.id("entity.announceDescReg"))
					.sendKeys(objExcelFile.map1.get("Order No.").get(i).toString());

			
			System.out.println(pr.month(objExcelFile.map1.get("News Date").get(i).toString()));
			// newsDate
			driver.findElement(By.name("entity.newsDate"))
					.sendKeys(pr.month(objExcelFile.map1.get("News Date").get(i).toString()));
			driver.findElement(By.name("entity.validityDate")).sendKeys("15/02/2030");

			// highlightedDate
			driver.findElement(By.id("highlightedDate")).sendKeys("15/02/2020");

			// upload
			pr.waits(driver);
		
			driver.findElement(By.xpath("//label[contains(text(),'Upload Document')]")).click();

			String Path1 = objExcelFile.map1.get("Download").get(i).toString();
			System.out.println("path+" + Path1);
			String pdfname[] = Path1.split("/");
			String Path = "D:\\AutomationFramework\\TMCconfig\\PressRelease\\thanecity.gov.in\\uploadpdf\\"
					+ pdfname[pdfname.length - 1].replace("%20", "" + "");
			Thread.sleep(1000);
			upload(Path);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('entity.chekkerflag1').disabled = false;");

			WebElement button = driver.findElement(By.id("entity.chekkerflag1"));
			js.executeScript("arguments[0].click();", button);
			pr.waits(driver);
		
			driver.findElement(By.cssSelector("input[type='submit']")).click();
			pr.waits(driver);
			Thread.sleep(1000);
			driver.findElement(By.id("btnNo")).click();

		}

	}

}

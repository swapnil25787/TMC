import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Approval {

	public static void main(String[] args) throws InterruptedException {
	//////Cheker part
String homePage="http://103.235.206.211/tscl/CitizenHome.html";
		
	System.setProperty("webdriver.chrome.driver","D:\\AutomationFramework\\ABMSmartScript\\functional\\config\\chromedriver36.exe");
		
		WebDriver driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS) ;
		// Thread.sleep(2000);//element =wait.until(ExpectedConditions.visibilityOfElementLocated
		
		driver.get(homePage);
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//span[8]//a[1]")).click();
		driver.findElement(By.xpath("//a[@class='internal'][contains(text(),'Login')]")).click();
		driver.findElement(By.xpath("//ul[@class='main-navigation']//ul//li//span[contains(text(),'Portal Admininstrator Login')]")).click();
		driver.findElement(By.xpath("//input[@id='emploginname']")).sendKeys("automation");
		driver.findElement(By.xpath("//input[@id='adminEmployee.emppassword']")).sendKeys("Pass@123");
		driver.findElement(By.id("captchaSessionLoginValue")).sendKeys("1111");
		
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class='btn btn-success btn-block']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Approve Section Details')]")).click();
			
			 Thread.sleep(2000);//element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Authenticated')]")));
			driver.findElement(By.xpath("//a[contains(text(),'Authenticated')]")).click();
			 Thread.sleep(2000);//element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@id='search_gridSectionDetailsApp']//div[@class='ui-pg-div']")));
			driver.findElement(By.xpath("//td[@id='search_gridSectionDetailsApp']//div[@class='ui-pg-div']")).click();
			 Thread.sleep(2000);//element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='columns']//select")));
			Select drpCountry11 = new Select(driver.findElement(By.xpath("//td[@class='columns']//select")));
			drpCountry11.selectByVisibleText("Link Name (English)");
			
			drpCountry11= new Select(driver.findElement(By.xpath("//select[@class='selectopts']")));
			drpCountry11.selectByVisibleText("contains");
			Thread.sleep(3000);
			String LinkName="Quotations";
			driver.findElement(By.id("jqg2")).sendKeys(LinkName);
			driver.findElement(By.xpath("//a[@id='fbox_gridSectionDetailsApp_search']")).click();
			 Thread.sleep(2000);//element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[alt='Edit Details']")));
			driver.findElement(By.xpath("(.//img[@alt='Edit Details'])[2]")).click();
			 Thread.sleep(2000);//element =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Seniority List Of \"Fireman\"')]")));
			 
			 int size=driver.findElements(By.xpath(".//img[@alt='Edit']")).size();
			 System.out.println("size"+size);

			 for (int i = 1; i <=size; i++) {
				String xpath="(.//img[@alt='Edit'])"+"["+i+"]";
				Thread.sleep(2000);
				 driver.findElement(By.xpath(xpath)).click();
				 Thread.sleep(2000);
				 driver.findElement(By.id("entity.chekkerflag1")).click();
				 Thread.sleep(2000);
				 driver.findElement(By.id("entity.validityDate")).clear();
				 driver.findElement(By.id("entity.validityDate")).sendKeys("31/03/2030 12:00 am");
				 driver.findElement(By.cssSelector("input[type='submit']")).click();
			}
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 //////////////////////*****************************
		/*
		 * String ss="Seniority List Of Multi Purpose Worker"; String
		 * sectionname="//a[contains(text(),'"+ss+"')]";
		 * driver.findElement(By.xpath(sectionname)).click();
		 * Thread.sleep(2000);//element
		 * =wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(
		 * "entity.chekkerflag1")));
		 */
			
			
		
		

	}

}

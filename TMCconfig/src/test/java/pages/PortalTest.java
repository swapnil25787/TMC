package pages;

 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Properties;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;

 

import org.openqa.selenium.chrome.ChromeDriver;

 

 

import readwrite.ExcelWriting;

 

public class PortalTest {

      

       public static void main(String[] args) throws InterruptedException, IOException {
    	   String fileName="D:\\AutomationFramework\\TMCconfig\\Data.properties";
		FileInputStream fis = new FileInputStream(fileName);
           Properties prop = new Properties();
           prop.load(fis);

    		prop.load(new FileReader("Data.properties"));
    		String homePage = prop.getProperty("legacyUrl");
    		File f = new File(prop.getProperty("root")+"tmp.xlsx");
    		f.createNewFile();
    		
    		ArrayList<String> dataList;
    		
    		System.setProperty("webdriver.chrome.driver", prop.getProperty("chrome.driver"));
    		
    		WebDriver driver = new ChromeDriver();
    		
    		
    		//WebDriverWait wait = new WebDriverWait(driver, 20);


    		driver.get(homePage);
    		Thread.sleep(3000);
    		//driver.manage().window().maximize();	
    		
    		int minutesCount=driver.findElements(By.xpath(".//div[@class='fl pl5 width360']//li")).size();
    		
    		int agendaCount = driver.findElements(By.xpath(".//div[@class='fl width360']//li")).size();
    			
    		int count = agendaCount > minutesCount?agendaCount:minutesCount;
    		
    		System.out.println("Count = " + count);
    		
    		for (int i=0; i <count+1; i++)
    		{
    		
    			dataList = new ArrayList<String>();
    		String minuteselement=".//div[@class='fl pl5 width360']//li[" + i +"]";
    		String agendaelement=".//div[@class='fl width360']//li[" + i + "]";
    		
    		//div[@class='fl width360']
    		int agendainnerelement =0;
    		agendainnerelement=driver.findElements(By.xpath(agendaelement + "/a")).size();
    		String agendahref="";
    		String agendatext="";
    		
    		String agendaData="";
    		String minutesData="";
    		
    		if (agendainnerelement > 0)
    		{
    			for (int j=1;j <=agendainnerelement; j++ ) {
    				agendahref =driver.findElement(By.xpath(agendaelement+ "/a[" + j + "]")).getAttribute("href");
    				agendatext=driver.findElement(By.xpath(agendaelement+ "/a[" + j + "]")).getText();
    				
    				agendaData = agendaData + agendatext + "," + agendahref + "||";
    				
    				
    			}
    			
    			
    				dataList.add(agendaData);
    				
    		}
    		else
    		{
    			dataList.add("Blank");
    		}
    		
    		//finalData= finalData + "$$";
    		
    		
    		int innerelement =0;
    		innerelement=driver.findElements(By.xpath(minuteselement + "/a")).size();
    		String minuteshref="";
    		String minutestext="";
    		
    		if (innerelement > 0)
    		{
    			
    			
    			for (int j=1;j <=innerelement; j++ ) {
    				minuteshref =driver.findElement(By.xpath(minuteselement+ "/a[" + j + "]")).getAttribute("href");
    				minutestext=driver.findElement(By.xpath(minuteselement+ "/a["+ j + "]")).getText();
    				
    				minutesData = minutesData + minutestext + "," + minuteshref + "||";
    					
    				
    			}
    			
    			if(!minutesData.equalsIgnoreCase("||"))
    			{
    				dataList.add(minutesData);
    			}
    			else
    			{
    				dataList.add("Blank");
    			}
    			
    		}
    		else
    		{
    			dataList.add("Blank");
    		}
    		System.out.println(agendaData + "" + minutesData);
    		
    		ExcelWriting ex = new ExcelWriting();
    		ex.excelWriting(prop.getProperty("root")+ prop.getProperty("data.file"), i, dataList);
    		
    		
    		}

}

}

 
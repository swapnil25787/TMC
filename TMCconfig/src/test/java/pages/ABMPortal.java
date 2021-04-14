package pages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ABMPortal {
	static ArrayList<String> valSetOne1 = null;
	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		String homePage = "https://thanecity.gov.in/tmtCommitteeMeetings.php";
		ArrayList<String> arrayList = new ArrayList<String>();
	//	home2 home = new home2();
	//	home3 hm=new home3();
		fetchertext ft=new fetchertext();
		String url = "";

		System.setProperty("webdriver.chrome.driver",
				"D:\\AutomationFramework\\ABMSmartScript\\functional\\config\\chromedriver36.exe");
		// ChromeOptions options = new ChromeOptions();

		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);

		WebDriver driver = new ChromeDriver();

		WebDriverWait wait = new WebDriverWait(driver, 20);
		/// Thread.sleep(10000l);

		driver.get(homePage);
		driver.manage().window().maximize();

		WebElement list = driver.findElement(By.className("page_left"));

		List<WebElement> swap = list.findElements(By.tagName("a"));
		
		
		System.out.println(swap);

		for (int i = 0; i < swap.size(); i++) {

			System.out.println(swap.get(i).getText());
			arrayList.add(swap.get(i).getText());
					
			}
		int c=0;	
for (int i = 0; i < arrayList.size(); i++) {
	
	
	driver.findElement(By.linkText(arrayList.get(i))).click();
	Thread.sleep(2000);
	
	
		

c=ft.standing(arrayList.get(i), driver,c);
		driver.navigate().back();
}
			

	
	

	
		driver.quit();
	}
}

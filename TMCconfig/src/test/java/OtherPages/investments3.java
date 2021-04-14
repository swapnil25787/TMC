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

public class investments3 {
	static ArrayList<String> valSetOne1 = null;

	public static void main(String[] args) throws InterruptedException {

		ExcelWriting ew = new ExcelWriting();
		String homePage = "https://thanecity.gov.in/account_investments.php?pg=9";
		ArrayList<String> arrayList = new ArrayList<String>();
		System.setProperty("webdriver.chrome.driver",
				"D:\\AutomationFramework\\ABMSmartScript\\functional\\config\\chromedriver36.exe");
		WebDriver driver = new ChromeDriver();
		int count = 0;
		for (int j = 0; j <= 42; j++) {
			String url;
			if (j == 0) {
				url = "https://thanecity.gov.in/downloads.php";
			} else {
				url = "https://thanecity.gov.in/downloads.php?pg=" + j + "";
			}

			driver.get(url);
			// driver.manage().window().maximize();
			Thread.sleep(2000);

			List<WebElement> list = driver
					.findElements(By.xpath("//*[@id=\"all\"]/div[3]/div/div[1]/div[2]/div/div[1]/div[1]/ul/li"));
			int size = list.size();
			System.out.println(size);
			for (int i = 1; i <= size; i++) {
				//// *[@id="all"]/div[3]/div/div[1]/div[2]/div/div[1]/div[1]/ul/li["+4+"]/a"
				String data = driver
						.findElement(By.xpath(
								"//*[@id=\"all\"]/div[3]/div/div[1]/div[2]/div/div[1]/div[1]/ul/li[" + i + "]/a"))
						.getText();
				String hrefdata = driver
						.findElement(By.xpath(
								"//*[@id=\"all\"]/div[3]/div/div[1]/div[2]/div/div[1]/div[1]/ul/li[" + i + "]/a"))
						.getAttribute("href");

				valSetOne1 = new ArrayList<String>();

				// valSetOne1.add("लेखा विभाग मुदत ठेवी");
				valSetOne1.add(data);
				valSetOne1.add(hrefdata);
				System.out.println(data + "|" + hrefdata);

				count++;
				ew.excelWriting("D:\\AutomationFramework\\TMCconfig\\ExportExcel2.xlsx", count, valSetOne1);
			}

		}
		System.out.println(count);
	}

}

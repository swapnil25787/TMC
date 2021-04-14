package OtherPages;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.ibm.icu.text.DecimalFormat;

import readwrite.ExcelWriting;

public class transferorder {
	static String first1 = "";
	static ArrayList<String> valSetOne1 = null;

	public static void main(String[] args) {
		ExcelWriting ew = new ExcelWriting();
		String homePage = "https://thanecity.gov.in/office_circulars.php?pg=1";
		ArrayList<String> arrayList = new ArrayList<String>();
		System.setProperty("webdriver.chrome.driver",
				"D:\\AutomationFramework\\ABMSmartScript\\functional\\config\\chromedriver36.exe");
String yearss="";
		WebDriver driver = new ChromeDriver();
		int count = 0;

		String serialsNumber = "";
		DecimalFormat df2 = new DecimalFormat("#.#####");
		double ii = 0.0000;
		String nextyear="";
		for (int j = 1; j <= 119; j++) {

			String url = "https://thanecity.gov.in/app_trans_orders.php?pg=" + j + "";
			driver.get(url);
			// driver.manage().window().maximize();
			int size = driver.findElements(By.xpath(
					"//*[@id=\"all\"]/div[3]/div/div[1]/div[2]/div/div[1]/div/table/tbody/tr[1]/td/table/tbody/tr"))
					.size();
			// System.out.println("size"+size);

			for (int i = 1; i <= size; i++) {
				count++;
				String xpath = "//*[@id='all']/div[3]/div/div[1]/div[2]/div/div[1]/div/table/tbody/tr[1]/td/table/tbody/tr"
						+ "[" + i + "]";
				String snno = xpath + "/td[1]";
				String orderNo = xpath + "/td[2]";
				String Title = xpath + "/td[3]";
				String datetext = xpath + "/td[4]";
				String serial = driver.findElement(By.xpath(snno)).getText();
				String orderNotext = driver.findElement(By.xpath(orderNo)).getText();
				String Titletext = driver.findElement(By.xpath(Title)).getText();
				String date = driver.findElement(By.xpath(datetext)).getText();

				String href = xpath + "/td[5]";
				String da[] = date.split("-");
				String modifiedissuedate = "";
				// System.out.println("length" + da.length);

				for (int k = da.length - 1; k >= 0; k--) {

					if (k > 0) {
						modifiedissuedate = modifiedissuedate + da[k] + "/";
						
					} else {
						modifiedissuedate = modifiedissuedate + da[k];
						yearss=da[0];
					}

				}
			//	System.out.println("modifiedissuedate ---> " + modifiedissuedate);
				
				valSetOne1 = new ArrayList<String>();
				if (j == 1 && i == 1) {
					String hrefdata = driver.findElement(By.xpath(href)).getText();
					// System.err.println(serial + "|" + orderNotext + "|" + Titletext + "|" + modifiedissuedate + "|" + hrefdata);
					nextyear=yearss;
					System.err.println("sdfsdf"+nextyear);
					valSetOne1.add(serial);
					valSetOne1.add(orderNotext);
					valSetOne1.add(Titletext);
					valSetOne1.add(date);
					valSetOne1.add(hrefdata);
				ew.excelWriting("D:\\AutomationFramework\\TMCconfig\\AppointmentsTransfer Orders.xlsx", count, valSetOne1);

				}
				if (i > 1) {
					
					System.out.println(yearss+"=========================="+nextyear);
					if (yearss.equals(nextyear)) {
						ii=ii+0.0001;
						 System.out.println("firs tpart");
					}else {
						
						nextyear=yearss;
						ii=0.0001;
						System.out.println("second tpart");
					}
					
					
					String s=(df2.format(ii)).substring(0);
					
					String nums=yearss+s.substring(1);
				//System.out.println("years"+nums);
					String hrefdata = driver.findElement(By.xpath(href + "/a")).getAttribute("href");
					 //System.err.println(serial + "|" + orderNotext + "|" + Titletext + "|" + modifiedissuedate + "|" + hrefdata);
					valSetOne1.add(nums);
					valSetOne1.add(orderNotext);
					valSetOne1.add(Titletext);
					valSetOne1.add(modifiedissuedate);
					valSetOne1.add(hrefdata);
				ew.excelWriting("D:\\AutomationFramework\\TMCconfig\\AppointmentsTransfer Orders.xlsx", count, valSetOne1);
				}

			}

		}

		System.out.println(count + "count" + valSetOne1);
		driver.quit();
	}
}

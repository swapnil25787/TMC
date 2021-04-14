package downloadDocument;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class DownloadDoc {

	private static String FILE_URL = null;
	private static String FILE_NAME = null;








	public static void DownloadPDFFromURL(String link, String path) {
		try {

			URL url = new URL(link);
			InputStream in = url.openStream();
			String pdfname[] = link.split("/");

			//System.out.println("Pdfname" + pdfname[pdfname.length - 1].replace("%20", " "));
			String filename=pdfname[pdfname.length - 1].replace("%20", " ");
			FileOutputStream fos = new FileOutputStream(
					new File(path + filename));
			int length = -1;
			byte[] buffer = new byte[50000];
			while ((length = in.read(buffer)) > -1) {
				fos.write(buffer, 0, length);
			}
			fos.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public static String Download_Returnfilename(String link, String path) throws IOException {
		

		URL url = new URL(link);
		InputStream in = url.openStream();
		String pdfname[] = link.split("/");

		//System.out.println("Pdfname" + pdfname[pdfname.length - 1].replace("%20", " "));
		String filename=pdfname[pdfname.length - 1].replace("%20", " ");
		try {
		FileOutputStream fos = new FileOutputStream(
				new File(path + filename));
		int length = -1;
		byte[] buffer = new byte[50000];
		while ((length = in.read(buffer)) > -1) {
			fos.write(buffer, 0, length);
		}
		fos.close();
		in.close();
	} catch (Exception e) {
		e.printStackTrace();
	}
		return filename;

}

	
	
	
	
	
	

	public static void main(String[] args) throws IOException {
		
		


        String urlStr = "http://thanecity.gov.in/uploadpdf/Election%20Cand%20List1371556947.pdf";
		URL url = new URL(urlStr);
        BufferedInputStream bis = new BufferedInputStream(url.openStream());
        String file = "C:\\Users\\swapnil.patil\\Downloads\\Election%20Cand%20List1371556947.pdf";
		FileOutputStream fis = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int count=0;
        while((count = bis.read(buffer,0,1024)) != -1)
        {
            fis.write(buffer, 0, count);
        }
        fis.close();
        bis.close();
    }}
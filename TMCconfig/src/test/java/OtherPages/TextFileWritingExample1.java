package OtherPages;

import java.io.FileWriter;
import java.io.IOException;
 
/**
 * This program demonstrates how to write characters to a text file.
 * @author www.codejava.net
 *
 */
public class TextFileWritingExample1 {
 
    public static void main(String[] args) {
        try {
            FileWriter writer = new FileWriter("C:\\Users\\swapnil.patil\\Desktop\\TSCL\\GeneralBodyMeetings\\MyFile.html", true);
            for (int i = 0; i < 50; i++) {
            	  writer.write("Hello World");
                  writer.write("\r\n");   // write new line
			}
          
 
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
 
}

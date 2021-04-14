package OtherPages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class sss {

	public static void main(String[] args) {
		List<Long> list = new ArrayList<Long>();
		
		String str1="6789";
		
		System.out.println("Number = " + str1);
		int x = str1.length();
		Integer[] num = null;
		char five = '5';
		sss ss = new sss();
		for (int j = 0; j <= x; j++) {
			String number = ss.charecterinsert(str1, five, j);
			long result = Long.parseLong(number);

			list.add(result);

		}
		System.out.println(list);
		System.out.println("Maximum Possible element by inserting 5 : " + Collections.max(list));
	}

	public String charecterinsert(String str1, char string, int position) {
		int length = str1.length();
		char[] updatedArr = new char[length + 1];
		str1.getChars(0, position, updatedArr, 0);
		updatedArr[position] = string;
		str1.getChars(position, length, updatedArr, position + 1);
		return new String(updatedArr);
	}
}
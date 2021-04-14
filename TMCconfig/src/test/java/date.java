
public class date {

	public static void main(String[] args) {
		String dates = "Friday, 22 Nov 2019 ";
		String da[] = dates.split(",");

		for (int i = 0; i < da.length; i++) {
		//	System.out.println(da[i]);
		}
String d=da[1];

date swap=new date();
swap.month(d);

	}

	public String month(String data) {
		
		String da[] = data.split(",");

		for (int i = 0; i < da.length; i++) {
		//	System.out.println(da[i]);
		}
String d=da[1];
	
		
		
		
		String dd[]=d.split(" ");
		
		for (int i = 0; i < dd.length; i++) {
			System.out.println("--"+dd[i]);
		}
		
		
		String num="";
		
		String key = dd[2];
		switch (key) {
		case "Jan":
			num="1";
			break;
		case "Feb":
			num="2";
			break;
		case "Mar":
			num="3";
			break;
		case "Apr":
			num="4";
			break;

		case "May":
			num="5";
			break;

		case "Jun":
			num="6";
			break;
		case "Jul":
			num="7";
			break;

		case "Aug":
			num="8";
			break;

		case "Sep":
			num="9";
			break;

		case "Oct":
			num="10";
			break;

		case "Nov":
			num="11";
			break;
		case "Dec":
			num="12";
			break;

		}
		System.out.println(key);
		System.out.println(num);
		System.out.println(data.replaceAll(key, "/"+num+"/").replaceAll(" ", ""));
	return data.replaceAll(key, "/"+num+"/").replaceAll(" ", "");
	}

}

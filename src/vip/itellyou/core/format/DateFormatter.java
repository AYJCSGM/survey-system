package vip.itellyou.core.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatter {
	public static String toWholeString(Long date){
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return df.format(date);
	}
	
	public static Long toLong(String date) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		return df.parse(date).getTime();
	}
}

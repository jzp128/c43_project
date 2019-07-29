package Checkers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import java.util.*;
import java.text.*;
import static java.util.Calendar.*;


public class CheckersGeneric {
	
	public static boolean range(int from, int to, int number){
		
		return (number >= from && number <= to);
		
	}
	
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	
	
	public static int length(String str) { 
		int length = str.length( ); 
		return length;
	}

	public static int betweenDays(Date from, Date to){
//        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String inputString1 = "23 01 1997";
//        String inputString2 = "27 04 1997";
        try {
//        	java.util.Date date1 = myFormat.parse(inputString1);
//        	java.util.Date date2 = myFormat.parse(inputString2);
            long diff = to.getTime() - from.getTime();
            //System.out.println ("Days: " + TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            int between = Math.toIntExact(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
            return between;

        } catch (Exception e) {
            return 0;
        }
	}
	
	public static Date currentDate(){
	        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        Date date = new Date();
	        //System.out.println(dateFormat.format(date));
			return date;
	}
	
	//source
	//https://stackoverflow.com/questions/7906301/how-can-i-find-the-number-of-years-between-two-dates

	public static int getDiffYears(Date first, Date last) {
	    Calendar a = getCalendar(first);
	    Calendar b = getCalendar(last);
	    int diff = b.get(YEAR) - a.get(YEAR);
	    if (a.get(MONTH) > b.get(MONTH) || 
	        (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
	        diff--;
	    }
	    return diff;
	}

	public static Calendar getCalendar(Date date) {
	    Calendar cal = Calendar.getInstance(Locale.US);
	    cal.setTime(date);
	    return cal;
	}
	
}

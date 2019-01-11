package org.junior.java8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ConvertDate {
	
	
	 public static List<String> reformatDate(List<String> dates) {
		 List<String> resultDates = new ArrayList<String>();
		 dates.forEach((date) -> { 
			 String dateArr[] = date.split(" ");
			 String day = dateArr[0].replaceAll("\\D+","");
			 String month = dateArr[1];
			 String year = dateArr[2];
			 StringBuilder givenDate = new StringBuilder().append(day).append(" ").append(month).append(" ").append(year);
			 try {
				 Date theDate = new SimpleDateFormat("dd MMM yyyy").parse(givenDate.toString());
				 resultDates.add(new SimpleDateFormat("YYYY-MM-dd").format(theDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		 });
		 return resultDates;
	 }
	 public static void main(String[] args) { 
		 List<String> dates = Arrays.asList("20th Out 2052", "6th Jun 1933", "26th Mai 1960", "20th Set 1958", "16th Mar 2068");
		 List<String> resultDates = reformatDate(dates);
		 System.out.println("FINISHHHH");
		 resultDates.forEach( System.out::println );
	 }

}

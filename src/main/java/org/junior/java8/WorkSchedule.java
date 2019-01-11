package org.junior.java8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class WorkSchedule {

	/*public static List<String> findSchedules(int workHours, int dayHours, String pattern) {
		
		System.out.println("workHours: " + workHours);
		System.out.println("dayHours: " + dayHours);
		System.out.println("pattern: " + pattern);
		
		List<String> resultSchedules = new ArrayList<String>();
		
		int count = Math.toIntExact(pattern.toLowerCase().chars().filter(e -> e == '?').count());
		System.out.println("count: " + count);
		int sum = 0;
		for(char ch: pattern.toCharArray()){
			if (Character.isDigit(ch)) {
				 sum += Character.getNumericValue(ch); 
			}
		    
		}
		
		long hoursLeft = workHours - sum;
		long hoursPerDay =  hoursLeft / count;
		
		System.out.println("sum: " + sum);
		System.out.println("hoursLeft: " + hoursLeft);
		System.out.println("hoursPerDay: " + hoursPerDay);
		if (hoursPerDay == dayHours) {
			resultSchedules.add(pattern.replaceAll("\\?", Integer.toString(dayHours)));
		} else {
			for (int i = 0; i< (hoursLeft + count -1); i++) {
				String temp = pattern;
				for (int j = 0; j< count ; j++) {
					System.out.println("[I: " + i  + "][J: " + j  + "][(count -1) -i: " + ((count -1) -i) +"]");
					if (j != (count -1)  -i) {
						temp = temp.replaceFirst("\\?", Integer.toString(0));
						System.out.println("INSIDE temp: " + temp);
					} else {
						temp = temp.replaceFirst("\\?", Long.toString(hoursLeft));
						System.out.println("temp: " + temp);
					}
				}
				resultSchedules.add(temp);
			}
		}
		 return resultSchedules;
	}*/
	
	public static List<String> findSchedules(int workHours, int dayHours, String pattern) {
		
		System.out.println("workHours: " + workHours);
		System.out.println("dayHours: " + dayHours);
		System.out.println("pattern: " + pattern);
		List<String> resultSchedules = new ArrayList<String>();
		
		int count = Math.toIntExact(pattern.toLowerCase().chars().filter(e -> e == '?').count());
		System.out.println("count: " + count);
		int sum = 0;
		for(char ch: pattern.toCharArray()){
			if (Character.isDigit(ch)) {
				 sum += Character.getNumericValue(ch); 
			}
		}
		
		long hoursLeft = workHours - sum;
		long hoursPerDay =  hoursLeft / count;
		
		System.out.println("sum: " + sum);
		System.out.println("hoursLeft: " + hoursLeft);
		System.out.println("hoursPerDay: " + hoursPerDay);
		if (hoursPerDay == dayHours) {
			resultSchedules.add(pattern.replaceAll("\\?", Integer.toString(dayHours)));
		} else {
			
			
			long[] arrayValues = new long[count];
			Arrays.stream(arrayValues).forEach(x -> x = 0);
			
			long maxvalue = (hoursLeft > dayHours)? dayHours : hoursLeft; //
			int restOfDivision = Math.toIntExact(hoursLeft % dayHours);//
			int positionsToFill = (restOfDivision > 0) ? Math.toIntExact(hoursLeft / dayHours) + 1 : Math.toIntExact(hoursLeft / dayHours);
			System.out.println("maxvalue: " + maxvalue);
			System.out.println("restOfDivision: " + restOfDivision);
			System.out.println("positionsToFill: " + positionsToFill);
			for (int index = 1; index <= positionsToFill ; index ++ ) {   //
				arrayValues[(arrayValues.length - index)] =  maxvalue;
			}
			System.out.println("ARRAY: ");
			Arrays.stream(arrayValues).forEach(x -> System.out.println(x));
			String temp = pattern;
			int pointer = count -1;
			for (int maxIterations = 0; maxIterations< (hoursLeft + count -1); maxIterations++) {
				
				for(int cont=0 ; cont< arrayValues.length ; cont++){
					temp = temp.replaceFirst("\\?", Long.toString(arrayValues[cont]));
				}
				resultSchedules.add(temp);
				
				if (arrayValues[pointer] > 0) {
					arrayValues[pointer] --;
					if ((pointer > 0) &&  (arrayValues[pointer -1] < maxvalue)){
						arrayValues[pointer -1] ++;
					} else if (arrayValues[pointer -1] == maxvalue) {
						arrayValues[pointer -1] = 0;
						if (pointer > 1) {
							arrayValues[pointer -2] ++;
						}
					}
				} else {
					pointer --;
					arrayValues[pointer] --;
					if (pointer > 0) {
						arrayValues[pointer -1] ++;
					}
				}
				temp = pattern;
			}
		}
		 return resultSchedules;
	}
	
	
	public static void main(String[] args) { 
		 
		 //List<String> resultDates = findSchedules(56, 8, "???8???"); 24, 8, "08??840"  3, 2, "??2??00" 16, 8, "08???40"
		//List<String> resultDates = findSchedules(3, 2, "??2??00");
		//List<String> resultDates = findSchedules(24, 8, "08??840");
		List<String> resultDates = findSchedules(3, 1, "???????");
		 resultDates.forEach( System.out::println );
	 }

}

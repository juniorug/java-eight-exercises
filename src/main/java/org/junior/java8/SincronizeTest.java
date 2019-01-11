package org.junior.java8;

import java.util.logging.Logger;

public class SincronizeTest {

	private static Logger log = Logger.getLogger(SincronizeTest.class.getName());
	
	/*public synchronized static void methodA(int i, String msg){
	       System.out.println(Integer.toString(i));
	       System.out.println(msg);
	    }

	  
	    public static void methodB(int i, String msg){
	       synchronized(this){
	    	   System.out.println(Integer.toString(i));
	    	   System.out.println(msg);
	       }
	    }
	    */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SincronizeClass sc = new SincronizeClass(); 
		long startTime = System.nanoTime();
		sc.methodA(5, "TEST");
		long endTime = System.nanoTime();
		System.out.println("Took "+(endTime - startTime) + " ns"); 
		
		long startTime2 = System.nanoTime();
		sc.methodB(5, "TEST");
		long endTime2 = System.nanoTime();
		System.out.println("Took "+(endTime2 - startTime2) + " ns"); 
	}

}

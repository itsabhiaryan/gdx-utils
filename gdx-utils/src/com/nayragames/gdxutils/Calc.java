package com.nayragames.gdxutils;

/**
 * (c) 2016 Abhishek Aryan
 *
 * @author Abhishek Aryan
 * @since 24-07-2016
 *
 */

public class Calc {
		
	private double x;
	private double y;

	public Calc(double x, double y) {
		this.x=x;
		this.y=y;
	}

	public void print(double theta) {
		if( ((Math.toDegrees(theta) / 90) % 2) == 1) {
	            x = x*0;
	            y = y*Math.sin(theta);
		}
		else if( ((Math.toDegrees(theta) / 90) % 2) == 0) {
	            x = x*Math.cos(theta);
	            y = y*0; 
	        }
	        else
	        {
	           x = x*Math.cos(theta);
	           y = y*Math.sin(theta); 
	        }
			System.out.println("cos "+Math.toDegrees(theta)+ ": "+x);
	        System.out.println("sin "+Math.toDegrees(theta)+": "+y);
	}

	public static double getCosValue(double theta){
		double x=1;
	    	
		if( ((Math.toDegrees(theta) / 90) % 2) == 1) {
	            x = x*0; 
		}
		else if( ((Math.toDegrees(theta) / 90) % 2) == 0) {
	            x = x*Math.cos(theta);  
	        }
	        else {
	           x = x*Math.cos(theta);
	        }
	   
		return x;
	}
	    
	public static double getSinValue(double theta){
		double y=1;
	    	
		if( ((Math.toDegrees(theta) / 90) % 2) == 1) {
		           
			y = y*Math.sin(theta);
		}
		else if( ((Math.toDegrees(theta) / 90) % 2) == 0) {
		           
			y = y*0;
		}
		else {
		           
			y = y*Math.sin(theta);
		}
	   
		return y;
	}
}
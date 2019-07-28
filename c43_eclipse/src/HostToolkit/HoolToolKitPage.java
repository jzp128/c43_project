package HostToolkit;

import java.sql.Connection;
import java.util.ArrayList;

public class HoolToolKitPage {
	
	
	public HoolToolKitPage(Connection c, String from, String to, String city, String country, String type){
		Recommendation answer= new Recommendation();
		
		double zero = 0.00;
		System.out.println("====PRICING SUGGESTIONS!====");
		double globalpricingtemp = HostToolKitQueries.globalPricing(c, from, to);
		
		if (globalpricingtemp != zero){
			System.out.println("The Average Pricing for all listings on this site is:");
			System.out.println(globalpricingtemp);
			System.out.println("==============================");
			
			answer.highestPrice = globalpricingtemp;
			answer.lowestPrice = globalpricingtemp;
			answer.lastAverage = globalpricingtemp;
			

		}
		

		
		ArrayList<Recommendation> globalPricingbyTypetemp = HostToolKitQueries.globalPricingbyType(c);
		
		if (!globalPricingbyTypetemp.isEmpty()){
		
			System.out.println("The Average Pricing for all listings on this site categorized by type is the following:");
	        for ( Recommendation x : globalPricingbyTypetemp){
	        	System.out.print(x.type);
	        	System.out.println(" : " +x.price);
	        	if (greaterDouble(answer.highestPrice,x.price)) {
	        		answer.highestPrice = x.price;
	        	}
	        	if (smallerDouble(answer.lowestPrice,x.price)) {
	        		answer.lowestPrice = x.price;
	        	}
	        	if (x.type.equals(type)){
	        		answer.lastAverage = x.price;
	        	}
	        	
	        } 
			System.out.println("==============================");

		}
		
		
		
		ArrayList<Recommendation> globalPricingbyTypeAndRangetemp = HostToolKitQueries.globalPricingbyTypeAndRange(c, from, to);

		if (!globalPricingbyTypeAndRangetemp.isEmpty()){
			System.out.println("The Average Pricing for all listings on this site categorized by type");
			System.out.println("and within your time range "+from+ " - "+ to +" "+ "is the following:");
	
	        for ( Recommendation x : globalPricingbyTypeAndRangetemp){
	        	System.out.print(x.type);
	        	System.out.println(" : " +x.price);
	        	
	        	if (greaterDouble(answer.highestPrice,x.price)) {
	        		answer.highestPrice = x.price;
	        	}
	        	if (smallerDouble(answer.lowestPrice,x.price)) {
	        		answer.lowestPrice = x.price;
	        	}
	        	
	        	if (x.type.equals(type)){
	        		answer.lastAverage = x.price;
	        	}
	        	
	        	
	        } 
			System.out.println("==============================");

		}
		
		double localPricingtemp = HostToolKitQueries.localPricing (c, country,city);
		
		if (localPricingtemp != zero){
			System.out.println("The Average Pricing for all listings in " +city+","+country +" is:");
			System.out.println(localPricingtemp);
			System.out.println("==============================");
			answer.lastAverage = localPricingtemp;
        	if (greaterDouble(answer.highestPrice,localPricingtemp)) {
        		answer.highestPrice = localPricingtemp;
        	}
        	if (smallerDouble(answer.lowestPrice,localPricingtemp)) {
        		answer.lowestPrice = localPricingtemp;
        	}

		}
		
		
		
		
		double localPricingbyDatetemp = HostToolKitQueries.localPricingbyDate(c,country,city,from,to);
		
		if (localPricingbyDatetemp != zero){
			System.out.println("The Average Pricing for all listings in " +city+","+country +" is between the dates "+from+ " - "+ to +" :");
			System.out.println(localPricingbyDatetemp);
			System.out.println("==============================");
			answer.lastAverage = localPricingbyDatetemp;
        	if (greaterDouble(answer.highestPrice,localPricingbyDatetemp)) {
        		answer.highestPrice = localPricingbyDatetemp;
        	}
        	if (smallerDouble(answer.lowestPrice,localPricingbyDatetemp)) {
        		answer.lowestPrice = localPricingbyDatetemp;
        	}

		}
		
		
		ArrayList<Recommendation> localPricingbyTypeDatetemp = HostToolKitQueries.localPricingbyTypeDate( c,  from,  to,  city,  country);
		
		
		if (!localPricingbyTypeDatetemp.isEmpty()){
		
			System.out.println("The Average Pricing for all listings in " +city+","+country +" is");
			System.out.println("categorized by type within your time range of " +from+ " - "+ to);
	        for ( Recommendation x : localPricingbyTypeDatetemp){
	        	System.out.print(x.type);
	        	System.out.println(" : " +x.price);
	        	if (greaterDouble(answer.highestPrice,x.price)) {
	        		answer.highestPrice = x.price;
	        	}
	        	if (smallerDouble(answer.lowestPrice,x.price)) {
	        		answer.lowestPrice = x.price;
	        	}
	        	if (x.type.equals(type)){
	        		answer.lastAverage = x.price;
	        	}
	        }
		}
		
		
		
		if (answer.lastAverage== zero){ // case if this person is one of the first  host ever 
			System.out.println("You are the first to pick a listing like this!");
			System.out.println("The world is at your oyster!");
			System.out.println("The best listings that do well are usually priced around $150!");
		} else if (answer.highestPrice== zero) {// case if this person is one of the first  host ever
			System.out.println("You are the first to pick a listing like this!");
			System.out.println("The world is at your oyster!");
			System.out.println("The best listings that do well are usually priced around $150!");
		}
		else {
			System.out.println("==============================");
			System.out.println("Based on our findings......");
			System.out.println("We recommend you to pick a price at around: " + answer.lastAverage);
			System.out.println("Or at least a price between the range "+ answer.lowestPrice +" - " + answer.highestPrice);
			System.out.println("This is due to your location "+ city +"," +country +", type "+type +" and date range: " +from+ " - "+ to);
		}
		
	}
	
	private boolean greaterDouble(double a, double b){
		return (a < b);
	}
	private boolean smallerDouble(double a, double b){
		return (a > b);
	}
	
	

}

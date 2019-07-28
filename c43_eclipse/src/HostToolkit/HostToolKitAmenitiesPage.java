package HostToolkit;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import Listings.Amenity;

public class HostToolKitAmenitiesPage {
	
	ArrayList<Integer> whitelist;

	
	public HostToolKitAmenitiesPage(Connection c, List<Amenity> data){
		
		whitelist = new ArrayList<Integer>();
		whitelist.add(1);
		whitelist.add(2);
		whitelist.add(3);
		whitelist.add(5);
		whitelist.add(6);
		whitelist.add(7);
		whitelist.add(8);
		whitelist.add(10);
		whitelist.add(18);
		
		
		System.out.println("====AMENITY SUGGESTIONS!====");
		System.out.println("The more amenities you have the greater revenue you can expect to achieve.");
		System.out.println("For each amenitiy added expect to ask around 2% higher than without.");
		
		
		boolean missing = false;
		for (Amenity x: data){
			if (x.amenBool == false){
				missing = true;
			}
		}
		
		if (missing) {
			System.out.println("We have noticed that you did not pick the following amenities.");
			double accumulator = printAmenities(c, data);
			System.out.println("If the rest of the extra amenities were added, we anticipate an increase of " +round(accumulator,2)+"% extra revenue.");
		} else {
			System.out.println("We see that you have selected all amenities! That's great!");	
			double accumulator =  selectedAllprintAmenities(c,data);
			System.out.println("We anticipate an increase of " +round(accumulator,2)+"% extra revenue because you selected all amenities.");
				
		}
		

	}
	
	public double printAmenities(Connection c, List<Amenity> data){
		
		ArrayList<AmenityToolKit> list = HostToolKitQueries.groupAmenityBookingsNotCanceled(c);
		int denominator = HostToolKitQueries.countBookingsNotCanceled(c);
		
		double accumulator = 0;
		
		for (AmenityToolKit x: list){
			
			for (Amenity x2: data) {
				if ((x2.amenBool==false) && (x.id == x2.amenid)){
					System.out.println("===========================");
					
					if (whitelist.contains(x.id)){
						System.out.println("This amenity is determined as a very popular demand !!!");
						System.out.println("Our team definitely suggest this!!!!");
					}
					
					System.out.println("Amenity" + "[ "+x.id+ " ]"); // amneitiy name
					System.out.print(x.item); // amneitiy name
					System.out.print(" : "); // amneitiy name
					System.out.println(x.desc); // amneitiy description
					
					double fraction = (double)x.count/(double)denominator;
					double finalfraction = round((double)x.count/(double)denominator,2) + 2;
					
					accumulator += finalfraction;
					
					
					System.out.println("A 2% + " +round(fraction,2)+ "% =" + finalfraction +"% revenue increase is to be expected if added");
					System.out.println("===========================");

					break;
				}
			}
		}
		return accumulator;
	}
	
	
	
	public double selectedAllprintAmenities(Connection c, List<Amenity> data){
		
		ArrayList<AmenityToolKit> list = HostToolKitQueries.groupAmenityBookingsNotCanceled(c);
		int denominator = HostToolKitQueries.countBookingsNotCanceled(c);
		
		double accumulator = 0;
		
		for (AmenityToolKit x: list){
			
			for (Amenity x2: data) {
				if (x.id == x2.amenid){
					
					double fraction = (double)x.count/(double)denominator;
					double finalfraction = round(fraction,2) + 2;

					accumulator += round(finalfraction,2);			
					
					break;
				}
			}
		}
		return accumulator;
	}
	
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
	}

}

package Server;

import Listings.Listing;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

public class ListingQueries {
    public static ArrayList<Listing> searchRunner(Connection c){
        return null;
    }

    public static String searchByAddress(String address){
        String q = String.format("SELECT listingID FROM listing WHERE address LIKE %s", address);
        return q;
    }

    public static String searchByPostalCode(String postcode){
        String q = String.format("SELECT listingID FROM listing WHERE postal_code LIKE %s", postcode);
        return q;
    }

    public static String searchByLocation(Double latitude, Double longitude){
        //TODO CAN I EVEN DO THIS IN SQL??
        String q = String.format("SELECT listingID FROM listing WHERE postal_code LIKE %s");
        return q;
    }

    public static String getListingsByDateRange(Date from, Date to){
        // get the availabilites first
        String stringFrom = Helpers.utilDatetoString(from);
        String stringTo = Helpers.utilDatetoString(to);
        long range = Helpers.daysInBetween(from, to);
        String getInDateRange = String.format("SELECT listingID FROM available where availdate BETWEEN '%s' AND '%s' AND isBooked = 0 GROUP BY listingID HAVING COUNT(listingID) = %d", stringFrom, stringTo, range);
        return getInDateRange;
    }

    public static String filterByAmendities(int[] aList){
        int n = aList.length;
        String set = "";
        for (int a : aList){
            set = set + a + ",";
        }
        String q = "SELECT listingID FROM amenitiesList WHERE amentID IN ("+set+") GROUP BY listingID HAVING COUNT(listingID) = " + Integer.toString(n);
        return q;
    }

    public static String sortByPrice(){
        return "LMAO YEET";
    }

}

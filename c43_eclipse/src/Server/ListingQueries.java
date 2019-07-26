package Server;

import Listings.Listing;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;

public class ListingQueries {
    public static ArrayList<Listing> searchRunner(Connection c){
        return null;
    }

    public static String filterByAddress(String address){
        String q = String.format("(SELECT listingID FROM listing WHERE address LIKE %s) AS FADD", address);
        return q;
    }

    public static String filterByPostalCode(String postcode){
        String q = String.format("(SELECT listingID FROM listing WHERE postal_code LIKE %s) AS FPC", postcode);
        return q;
    }

    public static String searchByLocation(Double latitude, Double longitude){
        //TODO CAN I EVEN DO THIS IN SQL??
        //NOT WORKING
        String q = String.format("SELECT listingID FROM listing WHERE postal_code LIKE %s");
        return q;
    }

    public static String filterByDateRange(Date from, Date to){
        // get the availabilites first
        String stringFrom = Helpers.utilDatetoString(from);
        String stringTo = Helpers.utilDatetoString(to);
        long range = Helpers.daysInBetween(from, to);
        String getInDateRange = String.format("(SELECT listingID FROM available where availdate BETWEEN '%s' AND '%s' AND isBooked = 0 GROUP BY listingID HAVING COUNT(listingID) = %d) AS FDR", stringFrom, stringTo, range);
        return getInDateRange;
    }

    public static String filterByAmendities(int[] aList){
        int n = aList.length;
        String set = "";
        for (int a : aList){
            set = set + a + ",";
        }
        String q = "(SELECT listingID FROM amenitiesList WHERE amentID IN ("+set+") GROUP BY listingID HAVING COUNT(listingID) = " + Integer.toString(n)+ ") AS FA";
        return q;
    }

    public static String filterBypriceRange(double low, double high){
        String q = String.format("(SELECT listingID from listing WHERE avg_price BETWEEN %f AND %f) AS FPR", low, high);
        return q;
    }

    public static String applyFilters(String[] filters){
        String q = "(SELECT BASE.listingID FROM (SELECT listingID from listing) AS BASE";
        int i = 0;
        for(String f: filters){
            q = q + " INNER JOIN " + f + " USING(listingID)";
        }
        q += ")";
        return q;
    }

    public static String finalListingQuery(String[] filters, int priceSort){
        String filterString = applyFilters(filters);
        System.out.println(filterString);
        String q = "SELECT A.* FROM listing AS A INNER JOIN " + filterString + "AS b ON a.listingID = b.listingID";
        if (priceSort == 1){
            q = q + " ORDER BY a.avg_price ASC";
        }else if (priceSort == -1){
            q = q + " ORDER BY a.avg_price DESC";
        }
        return q;
    }
}

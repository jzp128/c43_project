package Server;

import Listings.Listing;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

public class ListingQueries {
    /**
     * HOW TO USE
     * 1. FIRST CREATE A String ARRAY String[]
     * 2. ASK THE USERS IF THEY WOULD LIKE TO FILTER BY AN OPTION
     * 3. IF THE USER SAYS YES ASK THEM FOR THE PARAMETERS
     * 4. get a string from one of these filter functions
     * 5. put string into list
     * 6. continue till we get to the last one, ask if they want to sort by (avg price)
     * 7. yes & ASC -> pricesort = 1 yes & DESC pricesort = -1 NO -> pricesort = 0
     * 8. CALL finalListingQuery(String[], pricesort), this will get your final string
     * 9. CALL runfilters, this will give us our ArrayList of listings
     * OPTIONAL: IF THE USER WANTED TO SORT BY THE LATITUDE LONGITUDE RANGE, AFTER STEP 9, put the Arraylist we got
     * to the filterByLocation function, remember to keep the pricesort value and put this in (we are assuming price
     * sorting overrules the distance sorting)
     */

    public static String filterByAddress(String address) {
        String q = String.format("(SELECT listingID FROM listing WHERE address LIKE %s) AS FADD", address);
        return q;
    }

    public static String filterByPostalCode(String postcode) {
        String q = String.format("(SELECT listingID FROM listing WHERE postal_code LIKE %s) AS FPC", postcode);
        return q;
    }

    public static ArrayList<Listing> searchByLocation(ArrayList<Listing> listings, Double latitude, Double longitude, Double limit, int priceSort) {
        //NOT WORKING
        ArrayList<Listing> result = new ArrayList<>();
        for (Listing l : listings) {
            Double lat2 = l.latitude;
            Double long2 = l.longitude;
            Double dist = Helpers.haversine(latitude, longitude, lat2, long2);
            if (dist <= limit) {
                result.add(l);
            }
        }
        if (priceSort == 0){
            Collections.sort(result, Helpers.getListingDistanceComparator(latitude, longitude));
        }
        return result;
    }

    public static String filterByDateRange(Date from, Date to) {
        // get the dates first first
        String stringFrom = Helpers.utilDatetoString(from);
        String stringTo = Helpers.utilDatetoString(to);
        long range = Helpers.daysInBetween(from, to);
        String getInDateRange = String.format("(SELECT listingID FROM available where availdate BETWEEN '%s' AND '%s' AND isBooked = 0 GROUP BY listingID HAVING COUNT(listingID) = %d) AS FDR", stringFrom, stringTo, range);
        return getInDateRange;
    }

    public static String filterByAmendities(int[] aList) {
        int n = aList.length;
        String set = "";
        for (int a : aList) {
            set = set + a + ",";
        }
        String q = "(SELECT listingID FROM amenitiesList WHERE amentID IN (" + set + ") GROUP BY listingID HAVING COUNT(listingID) = " + Integer.toString(n) + ") AS FA";
        return q;
    }

    public static String filterBypriceRange(double low, double high) {
        String q = String.format("(SELECT listingID from listing WHERE avg_price BETWEEN %f AND %f) AS FPR", low, high);
        return q;
    }

    public static String applyFilters(String[] filters) {
        String q = "(SELECT BASE.listingID FROM (SELECT listingID from listing) AS BASE";
        int i = 0;
        for (String f : filters) {
            q = q + " INNER JOIN " + f + " USING(listingID)";
        }
        q += ")";
        return q;
    }

//    public static String insertAvailDateRange(Date from, Date to, int ){
//        // get the availabilites first
//        String stringFrom = Helpers.utilDatetoString(from);
//        String stringTo = Helpers.utilDatetoString(to);
//        long range = Helpers.daysInBetween(from, to);
//        String getInDateRange = String.format("SELECT listingID FROM available where availdate BETWEEN '%s' AND '%s' AND isBooked = 0 GROUP BY listingID HAVING COUNT(listingID) = %d", stringFrom, stringTo, range);
//        return getInDateRange;
//    }

    public static String finalListingQuery(String[] filters, int priceSort) {
        String filterString = applyFilters(filters);
        System.out.println(filterString);
        String q = "SELECT A.* FROM listing AS A INNER JOIN " + filterString + "AS b ON a.listingID = b.listingID";
        if (priceSort == 1) {
            q = q + " ORDER BY a.avg_price ASC";
        } else if (priceSort == -1) {
            q = q + " ORDER BY a.avg_price DESC";
        }
        return q;
    }

    public static ArrayList<Listing> runFilters(Connection c, String q) {
        ArrayList<Listing> result = new ArrayList<>();
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("listingID");
                String city = rs.getString("city");
                String postal_code = rs.getString("postal_code");
                String address = rs.getString("address");
                String country = rs.getString("country");
                double longitude = rs.getDouble("longitude");
                double latitude = rs.getDouble("latitude");
                int hostID = rs.getInt("hosterID");
                String type = rs.getString("listingType");
                Listing l = new Listing(city, postal_code, country, address, latitude, longitude, hostID, type);
                l.id = id;
                l.price = rs.getDouble("avg_price");
                result.add(l);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

        }
        return result;
    }


}

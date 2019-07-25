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
        String q = String.format("SELECT * FROM listing WHERE address LIKE %s", address);
        return q;
    }

    public static String searchByPostalCode(String postcode){
        String q = String.format("SELECT * FROM listing WHERE postal_code LIKE %s", postcode);
        return q;
    }

    public static String searchByLocation(Double latitude, Double longitude){

        String q = String.format("SELECT * FROM listing WHERE postal_code LIKE %s", postcode);
        return q;
    }

    public static String getListingsByDateRange(Date from, Date to){
        // get the availabilites first
        String q = String.format("SELECT * FROM listing WHERE (DATE >= TO )");
    }

}

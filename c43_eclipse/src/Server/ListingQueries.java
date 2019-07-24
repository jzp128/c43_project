package Server;

import Listings.Listing;

import java.sql.Connection;
import java.util.ArrayList;

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

    public static String searchByLocation(String postcode){
        String q = String.format("SELECT * FROM listing WHERE postal_code LIKE %s", postcode);
        return q;
    }

}

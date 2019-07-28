package HostToolkit;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import Application.App;

public class HostToolKitQueries {
	
	/*
	 * average price in between the selected date ranges
	 * select avg(price) from available where availDate BETWEEN '2010-12-02' AND '2010-12-04 ';
	 * if null, then you know that no other price was avail! 
	 */
	
    public static double globalPricing(Connection c, String from, String to){
    	String q = "select avg(price) from available where availDate BETWEEN ? AND ?";
    	Double avg = 0.00;
    	try {
    		PreparedStatement ps = c.prepareStatement(q);
    		ps.setString(1, from);
    		ps.setString(2, to);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                avg = rs.getDouble(1);
            }
            rs.close();
    		ps.close();
    	} catch (SQLException e) {
    		// TODO: ADD ERROR MESSAGE
    		e.printStackTrace();
    	}
    	return avg;
    }
    
    
    
    
	/*
	 * average price per type (globally)
	 */
    public static ArrayList<Recommendation> globalPricingbyType(Connection c){
    	String q = "select avg(price), listingType from available a, listing l where a.listingID = l.listingID group by  listingType";
    	ArrayList<Recommendation> recom = new ArrayList<Recommendation>();
    	Double avg = 0.00;
    	String type = "";
    	try {
    		PreparedStatement ps = c.prepareStatement(q);
    		ps.execute();
    		ResultSet rs = ps.getResultSet();
    		while (rs.next()) {
    			avg = rs.getDouble(1);
    			type = rs.getString(2);
    			
    			Recommendation recomobj = new Recommendation();
    			recomobj.price = avg;
    			recomobj.type = type;
    			recom.add(recomobj);
    		}
    		rs.close();
    		ps.close();
    	} catch (SQLException e) {
    		// TODO: ADD ERROR MESSAGE
    		e.printStackTrace();
    	}
    	return recom;
    }
    
	/*
	*average price per type in between the ranges, globally
	*select avg(price), listingType from available a, listing l where availDate BETWEEN '2010-12-02' AND '2010-12-04' and a.listingID = l.listingID group by  listingType ;
	*/
    
    public static ArrayList<Recommendation> globalPricingbyTypeAndRange(Connection c, String from, String to){
    	String q = "select avg(price), listingType from available a, listing l where availDate BETWEEN ? AND ? and a.listingID = l.listingID group by listingType";
    	ArrayList<Recommendation> recom = new ArrayList<Recommendation>();
    	Double avg = 0.00;
    	String type = "";
    	try {
    		PreparedStatement ps = c.prepareStatement(q);
    		ps.setString(1, from);
    		ps.setString(2, to);
    		ps.execute();
    		ResultSet rs = ps.getResultSet();
    		while (rs.next()) {
    			avg = rs.getDouble(1);
    			type = rs.getString(2);
    			
    			Recommendation recomobj = new Recommendation();
    			recomobj.price = avg;
    			recomobj.type = type;
    			recom.add(recomobj);
    		}
    		rs.close();
    		ps.close();
    	} catch (SQLException e) {
    		// TODO: ADD ERROR MESSAGE
    		e.printStackTrace();
    	}
    	return recom;
    }
    
    /*
	average price in the city anytime
	*/
    public static double localPricing(Connection c, String country, String city){
    	String q = "select avg(price) from available a, listing l where a.listingID = l.listingID AND l.Country = ? AND l.city = ? ";
    	Double avg = 0.00;
    	try {
    		PreparedStatement ps = c.prepareStatement(q);
    		ps.setString(1, country);
    		ps.setString(2, city);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                avg = rs.getDouble(1);
            }
    		rs.close();
    		ps.close();
    	} catch (SQLException e) {
    		// TODO: ADD ERROR MESSAGE
    		e.printStackTrace();
    	}
    	return avg;
    }
    
    
    /*
     * average price in the same city between the selected date ranges
     * 
     */
    public static double localPricingbyDate(Connection c, String country, String city, String from , String to){
    	String q = "select avg(price) from available a, listing l where a.listingID = l.listingID AND l.Country = ? AND l.city= ? AND availDate BETWEEN ? AND ? ";
    	Double avg = 0.00;
    	try {
    		PreparedStatement ps = c.prepareStatement(q);
    		ps.setString(1, country);
    		ps.setString(2, city);
    		ps.setString(3, from);
    		ps.setString(4, to);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                avg = rs.getDouble(1);
            }
    		rs.close();
    		ps.close();
    	} catch (SQLException e) {
    		// TODO: ADD ERROR MESSAGE
    		e.printStackTrace();
    	}
    	return avg;
    }
    
    /*
     * average price in the same city between the selected date ranges and by listing
     * 
     */
    public static ArrayList<Recommendation> localPricingbyTypeDate(Connection c, String from, String to, String city, String country){
    	String q = 
    	"select avg(price), listingType from available a, listing l where availDate BETWEEN ? AND ? and a.listingID = l.listingID and l.city = ? and l.country = ? group by  listingType";
    	ArrayList<Recommendation> recom = new ArrayList<Recommendation>();
    	Double avg = 0.00;
    	String type = "";
    	try {
    		PreparedStatement ps = c.prepareStatement(q);
    		ps.setString(1, from);
    		ps.setString(2, to);
    		ps.setString(3, city);
    		ps.setString(4, country);
    		ps.execute();
    		ResultSet rs = ps.getResultSet();
    		while (rs.next()) {
    			avg = rs.getDouble(1);
    			type = rs.getString(2);
    			
    			Recommendation recomobj = new Recommendation();
    			recomobj.price = avg;
    			recomobj.type = type;
    			recom.add(recomobj);
    		}
    		rs.close();
    		ps.close();
    	} catch (SQLException e) {
    		// TODO: ADD ERROR MESSAGE
    		e.printStackTrace();
    	}
    	return recom;
    }
}

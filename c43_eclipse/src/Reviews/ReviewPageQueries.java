package Reviews;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import HostToolkit.Recommendation;

public class ReviewPageQueries {
	
	//============================FOR THE RENTERS PAGE============================
	
	//TODO must fix this later
	public static ArrayList<ListingReview> getReviewsAboutHostsMadeByARenter(Connection c, int renterID){
		String q = "select * from listingReviews where renterID = ? ";
		    	ArrayList<ListingReview> rev = new ArrayList<ListingReview>();
		    	try {
		    		PreparedStatement ps = c.prepareStatement(q);
		    		ps.setInt(1, renterID);
		    		ps.execute();
		    		ResultSet rs = ps.getResultSet();
		    		while (rs.next()) {

		    			ListingReview revobj = new ListingReview();
		    			
		    			revobj.ListingReviewid = rs.getInt("ListingReviewID");
		    			revobj.renterid = rs.getInt("renterID");
		    			revobj.bookingid = rs.getInt("bookingID");
		    			revobj.listingid = rs.getInt("listingID");
		    			revobj.hosterid = rs.getInt("hosterID");
		    			revobj.listingcontent = rs.getString("listingComment");
		    			revobj.listingrating = rs.getInt("listingRating");
		    			revobj.hostrating =  rs.getInt("hostRating");
		    			revobj.hostcomment = rs.getString("hosterComment");
		    			
		    			rev.add(revobj);
		    		}
		    		rs.close();
		    		ps.close();
		    	} catch (SQLException e) {
		    		// TODO: ADD ERROR MESSAGE
		    		e.printStackTrace();
		    	}
		    	return rev;
	}
	
	
	//TODO must fix this later
	public static ArrayList<RenterReview> getReviewsAboutRenters(Connection c, int renterID){
		String q = "select * from renterReviews where renterID = ? ";
		    	ArrayList<RenterReview> rev = new ArrayList<RenterReview>();
		    	try {
		    		PreparedStatement ps = c.prepareStatement(q);
		    		ps.setInt(1, renterID);
		    		ps.execute();
		    		ResultSet rs = ps.getResultSet();
		    		while (rs.next()) {

		    			RenterReview revobj = new RenterReview();
		    			revobj.RenterReviewid = rs.getInt("renterReviewID");
		    			revobj.bookingid = rs.getInt("renterID");
		    			revobj.renterid = rs.getInt("hosterID");
		    			revobj.hosterid = rs.getInt("bookingID");
		    			revobj.listingid = rs.getInt("listingID");
		    			revobj.rentercomment = rs.getString("renterComment");
		    			revobj.renterrating = rs.getInt("renterRating");
		    			
		    			rev.add(revobj);
		    		}
		    		rs.close();
		    		ps.close();
		    	} catch (SQLException e) {
		    		// TODO: ADD ERROR MESSAGE
		    		e.printStackTrace();
		    	}
		    	return rev;
	}
	
	//============================FOR THE HOST PAGE============================
	
	//TODO must fix this later
	public static ArrayList<RenterReview> getReviewsAboutRentersMadeByAHost(Connection c, int hostID){
		String q = "select * from renterReviews where hosterID = ?";
		    	ArrayList<RenterReview> rev = new ArrayList<RenterReview>();
		    	try {
		    		PreparedStatement ps = c.prepareStatement(q);
		    		ps.setInt(1, hostID);
		    		ps.execute();
		    		ResultSet rs = ps.getResultSet();
		    		while (rs.next()) {

		    			RenterReview revobj = new RenterReview();
		    			revobj.RenterReviewid = rs.getInt("renterReviewID");
		    			revobj.bookingid = rs.getInt("renterID");
		    			revobj.renterid = rs.getInt("hosterID");
		    			revobj.hosterid = rs.getInt("bookingID");
		    			revobj.listingid = rs.getInt("listingID");
		    			revobj.rentercomment = rs.getString("renterComment");
		    			revobj.renterrating = rs.getInt("renterRating");
		    			
		    			rev.add(revobj);
		    		}
		    		rs.close();
		    		ps.close();
		    	} catch (SQLException e) {
		    		// TODO: ADD ERROR MESSAGE
		    		e.printStackTrace();
		    	}
		    	return rev;
	}
    
	
	public static ArrayList<ListingReview> getReviewsAboutHosts(Connection c, int hostID){
		String q = "select * from listingReviews where hosterID = ?";
		    	ArrayList<ListingReview> rev = new ArrayList<ListingReview>();
		    	try {
		    		PreparedStatement ps = c.prepareStatement(q);
		    		ps.setInt(1, hostID);
		    		ps.execute();
		    		ResultSet rs = ps.getResultSet();
		    		while (rs.next()) {

		    			ListingReview revobj = new ListingReview();
		    			
		    			revobj.ListingReviewid = rs.getInt("ListingReviewID");
		    			revobj.renterid = rs.getInt("renterID");
		    			revobj.bookingid = rs.getInt("bookingID");
		    			revobj.listingid = rs.getInt("listingID");
		    			revobj.hosterid = rs.getInt("hosterID");
		    			revobj.listingcontent = rs.getString("listingComment");
		    			revobj.listingrating = rs.getInt("listingRating");
		    			revobj.hostrating =  rs.getInt("hostRating");
		    			revobj.hostcomment = rs.getString("hosterComment");
		    			
		    			rev.add(revobj);
		    		}
		    		rs.close();
		    		ps.close();
		    	} catch (SQLException e) {
		    		// TODO: ADD ERROR MESSAGE
		    		e.printStackTrace();
		    	}
		    	return rev;
	}
	
	
	
	
	
	//============================FOR THE LISTING PAGE============================

	
	
	public static ArrayList<ListingReview> getforListings(Connection c, int listingID){
		String q = "select * from listingReviews where listingID = ?";
		    	ArrayList<ListingReview> rev = new ArrayList<ListingReview>();
		    	try {
		    		PreparedStatement ps = c.prepareStatement(q);
		    		ps.setInt(1, listingID);
		    		ps.execute();
		    		ResultSet rs = ps.getResultSet();
		    		while (rs.next()) {

		    			ListingReview revobj = new ListingReview();
		    			
		    			revobj.ListingReviewid = rs.getInt("ListingReviewID");
		    			revobj.renterid = rs.getInt("renterID");
		    			revobj.bookingid = rs.getInt("bookingID");
		    			revobj.listingid = rs.getInt("listingID");
		    			revobj.hosterid = rs.getInt("hosterID");
		    			revobj.listingcontent = rs.getString("listingComment");
		    			revobj.listingrating = rs.getInt("listingRating");
		    			revobj.hostrating =  rs.getInt("hostRating");
		    			revobj.hostcomment = rs.getString("hosterComment");
		    			
		    			rev.add(revobj);
		    		}
		    		rs.close();
		    		ps.close();
		    	} catch (SQLException e) {
		    		// TODO: ADD ERROR MESSAGE
		    		e.printStackTrace();
		    	}
		    	return rev;
	}
	
	
	public static void printListingReviews(ArrayList<ListingReview> list){
		int iterator = 1;
		for (ListingReview x : list){
			System.out.println("=============="+iterator+"================");
			iterator++;
			System.out.println("Review ID: " + x.ListingReviewid);
			System.out.println("Listing ID: " + x.listingid);
			System.out.println("Host ID: " + x.hosterid);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

			System.out.println("Content: " + x.listingcontent);
			System.out.print("Rating: " + x.listingrating);
			System.out.println("Host Comment: " + x.hostcomment);
			System.out.print("Host Rating: " + x.hostrating);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Made by User: " + x.renterid);
			System.out.println("References booking: " + x.bookingid);

			
			System.out.println("==========================================");

			
			
		}
	}
	
	
	
	
	
	
	public static void printRenterReviews(ArrayList<RenterReview> list){
		int iterator = 1;
		for (RenterReview x : list){
			System.out.println("=============="+iterator+"================");
			iterator++;

			System.out.println("Review ID: " + x.RenterReviewid);
			System.out.println("Listing ID: " + x.listingid);
			System.out.println("Renter ID: " + x.renterid);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

			System.out.println("Renter Comment: " + x.rentercomment);
			System.out.print("Renter Rating: " + x.renterrating);
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
			System.out.println("Made by User: " + x.hosterid);
			System.out.println("References booking: " + x.bookingid);
			System.out.println("==========================================");
		}
	}

}

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
	public static ArrayList<Review> getReviewsAboutHostsMadeByARenter(Connection c, int renterID){
		String q = "select * from reviews where creatorID = ? AND reviewType = 'h'";
		    	ArrayList<Review> rev = new ArrayList<Review>();
		    	try {
		    		PreparedStatement ps = c.prepareStatement(q);
		    		ps.setInt(1, renterID);
		    		ps.execute();
		    		ResultSet rs = ps.getResultSet();
		    		while (rs.next()) {

		    			Review revobj = new Review();
		    			revobj.createrid = rs.getInt("creatorID");
		    			revobj.receiverid = rs.getInt("receiverID");
		    			revobj.listingid = rs.getInt("listingID");
		    			revobj.content = rs.getString("content");
		    			revobj.rating = rs.getInt("rating");
		    			revobj.reviewType = rs.getString("reviewType");
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
	public static ArrayList<Review> getReviewsAboutRenters(Connection c, int renterID){
		String q = "select * from reviews where receiverID = ? AND reviewType = 'r'";
		    	ArrayList<Review> rev = new ArrayList<Review>();
		    	try {
		    		PreparedStatement ps = c.prepareStatement(q);
		    		ps.setInt(1, renterID);
		    		ps.execute();
		    		ResultSet rs = ps.getResultSet();
		    		while (rs.next()) {

		    			Review revobj = new Review();
		    			revobj.createrid = rs.getInt("creatorID");
		    			revobj.receiverid = rs.getInt("receiverID");
		    			revobj.listingid = rs.getInt("listingID");
		    			revobj.content = rs.getString("content");
		    			revobj.rating = rs.getInt("rating");
		    			revobj.reviewType = rs.getString("reviewType");
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
	public static ArrayList<Review> getReviewsAboutRentersMadeByAHost(Connection c, int renterID){
		String q = "select * from reviews where creatorID = ? AND reviewType = 'r'";
		    	ArrayList<Review> rev = new ArrayList<Review>();
		    	try {
		    		PreparedStatement ps = c.prepareStatement(q);
		    		ps.setInt(1, renterID);
		    		ps.execute();
		    		ResultSet rs = ps.getResultSet();
		    		while (rs.next()) {

		    			Review revobj = new Review();
		    			revobj.createrid = rs.getInt("creatorID");
		    			revobj.receiverid = rs.getInt("receiverID");
		    			revobj.listingid = rs.getInt("listingID");
		    			revobj.content = rs.getString("content");
		    			revobj.rating = rs.getInt("rating");
		    			revobj.reviewType = rs.getString("reviewType");
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
	public static ArrayList<Review> getReviewsAboutHosts(Connection c, int hostid){
		String q = "select * from reviews where receiverID = ? and reviewType = 'h' ";
    	ArrayList<Review> rev = new ArrayList<Review>();
    	try {
    		PreparedStatement ps = c.prepareStatement(q);
    		ps.setInt(1, hostid);
    		ps.execute();
    		ResultSet rs = ps.getResultSet();
    		while (rs.next()) {

    			Review revobj = new Review();
    			revobj.createrid = rs.getInt("creatorID");
    			revobj.receiverid = rs.getInt("receiverID");
    			revobj.listingid = rs.getInt("listingID");
    			revobj.content = rs.getString("content");
    			revobj.rating = rs.getInt("rating");
    			revobj.reviewType = rs.getString("reviewType");
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

	
	public static ArrayList<Review> getReviewsAboutListings(Connection c, int listingid){
		String q = "select * from reviews where listingID = ? AND reviewType = 'l'";
		    	ArrayList<Review> rev = new ArrayList<Review>();
		    	try {
		    		PreparedStatement ps = c.prepareStatement(q);
		    		ps.setInt(1, listingid);
		    		ps.execute();
		    		ResultSet rs = ps.getResultSet();
		    		while (rs.next()) {

		    			Review revobj = new Review();
		    			revobj.createrid = rs.getInt("creatorID");
		    			revobj.receiverid = rs.getInt("receiverID");
		    			revobj.listingid = rs.getInt("listingID");
		    			revobj.content = rs.getString("content");
		    			revobj.rating = rs.getInt("rating");
		    			revobj.reviewType = rs.getString("reviewType");
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
	
	
	public static void printReviewListings(ArrayList<Review> list){
		int iterator = 1;
		for (Review x : list){
			System.out.println("=============="+iterator+"================");
			iterator++;
			System.out.println(x.content);
			System.out.print("Rating: ");
			System.out.println(x.rating);
			System.out.println("===============================");
		}
	}

}

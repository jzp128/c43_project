package Server;

import Listings.Amenity;
import Listings.Available;
import Listings.Listing;
import Users.User;

import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import Application.App;

public class Queries {

    //USER CREATION
    public static int create_user(Connection c, String sin, String userName, java.sql.Date dob,
                                  String occupation, String loginName, String pw,
                                  String postal_code, String country, String city, String address) {
        // adds user (no address) and then puts them into the table
        int id = -1;
        String query = "insert into users (sin, userName, dob, occupation, loginName, loginPW, address, country, city, postal_code) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            ps.setString(1, sin);
            ps.setString(2, userName);
            ps.setDate(3, dob);
            ps.setString(4, occupation);
            ps.setString(5, loginName);
            ps.setString(6, pw);
            ps.setString(7, address);
            ps.setString(8, country);
            ps.setString(9, city);
            ps.setString(10, postal_code);

            if (ps.executeUpdate() != 0) {
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()) {
                    id = rs.getInt(1);
                }
                rs.close();
            }
            ps.close();
        } catch (SQLException e) {
            // TODO: ADD ERROR MESSAGE
            e.printStackTrace();
        }

        return id;
    }

    public static String fetchPW(Connection c, String userName){
        String pw = null;
        String q = "SELECT loginPW FROM users WHERE loginName = ?";
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pw = rs.getString("loginPW");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            // TODO: ADD ERROR MESSAGE
            e.printStackTrace();
        }
        return pw;
    }

    public static boolean checkUserNameTaken(Connection c, String userName) {
        boolean contains = false;
        String q = "SELECT EXISTS(SELECT * FROM users WHERE loginName = '" + userName +"')";
        try {
            PreparedStatement ps = c.prepareStatement(q);
            //ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                contains = rs.getBoolean(1);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            // TODO: ADD ERROR MESSAGE
            e.printStackTrace();
        }
        return contains;
    }

    public static int checkAddress(Connection c, String city, String postal_code, String country, String street_name, String building_number, String unit_number) {
        int id = -1;
        String q = "SELECT userID FROM address WHERE city='?', postal_code='?', country='?', street_name='?', building_number='?', unit_number='?'";
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setString(1, city);
            ps.setString(2, postal_code);
            ps.setString(3, country);
            ps.setString(4, street_name);
            ps.setString(5, building_number);
            ps.setString(6, unit_number);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            // TODO: ADD ERROR MESSAGE
            e.printStackTrace();
        }
        return id;
    }

    public static boolean addRenter(Connection c, int uid, String ccnumber, String ccsec, String ccName) {
        boolean success = false;
        String q = "INSERT INTO renters (renterID, ccNumber, ccSecNum, ccCardName) VALUES(?,?,?,?)";
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setInt(1, uid);
            ps.setString(2, ccnumber);
            ps.setString(3, ccsec);
            ps.setString(4, ccName);
            success = ps.execute();
            ps.close();
        } catch (SQLException e) {
            // TODO: ADD ERROR MESSAGE
            e.printStackTrace();
        }
        return success;
    }

    public static boolean addHost(Connection c, int uid) {
        boolean success = false;
        String q = "UPDATE users SET isHoster = TRUE where userID = ?";
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setInt(1, uid);
            success = ps.execute();
            ps.close();
        } catch (SQLException e) {
            // TODO: ADD ERROR MESSAGE
            e.printStackTrace();
        }
        return success;
    }

    //LISTINGS
    public static int create_listing(Connection c, String type, double longitude, double latitude, int hostID, String address, String country, String city, String postal_code) {
        // adds user (no address) and then puts them into the table
        int id = -1;
        String query = "insert into listing (listingType, longitude, latitude, hosterID, address, country, city, postal_code) values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, type);
            ps.setDouble(2, longitude);
            ps.setDouble(3, latitude);
            ps.setInt(4, hostID);
            ps.setString(5, address);
            ps.setString(6, country);
            ps.setString(7, city);
            ps.setString(8, postal_code);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()){
                id = rs.getInt(1);
            }
            ps.close();
        } catch (SQLException e) {
            // TODO: ADD ERROR MESSAGE
            e.printStackTrace();
        }

        return id;
    }

    public static ArrayList<Listing> getListingsForUser(Connection c, int userID) {
        ArrayList<Listing> ret = new ArrayList<>();
        String q = "select * FROM listing WHERE hosterID = ?";
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setInt(1, userID);
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
                ret.add(l);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

        }
        return ret;
    }
    
    public static ArrayList<Listing> getAllListings(Connection c) {
        ArrayList<Listing> ret = new ArrayList<>();
        String q = "select * FROM listing";
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
                ret.add(l);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

        }
        return ret;
    }
    
    
    public static ArrayList<Available> getAvailListingsDates(Connection c, int listingID) {
        ArrayList<Available> ret = new ArrayList<>();
        String q = "SELECT listingID, availDate, price, isBooked from available where isBooked = 0 AND listingID = ? order by availDate";
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setInt(1, listingID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
            	int id = rs.getInt("listingID");
                int isbooked = rs.getInt("isBooked");
                BigDecimal price = rs.getBigDecimal("price");
                Date availdate = rs.getDate("availDate");

                Available a = new Available(availdate, price, id, isbooked );
                ret.add(a);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

        }
        return ret;
    }
    
    
    
    

    public static ArrayList<Listing> getListingsForGeoLocation(Connection c, double lat1, double long1) {
        ArrayList<Listing> ret = new ArrayList<>();
        String q = "select * FROM listing WHERE hosterID = ?";
        try {
            PreparedStatement ps = c.prepareStatement(q);
//            ps.setInt(1, userID);
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
                ret.add(l);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

        }
        return ret;
    }

    public static ArrayList<Listing> getListingsForPostalCode(Connection c, String pCode) {
        ArrayList<Listing> ret = new ArrayList<>();
        String q = "select * FROM listing WHERE postal_code = ?";
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setString(1, pCode);
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
                ret.add(l);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

        }
        return ret;
    }


    public static int addAmentities(Connection c, String name, String description){
        int id = -1;
        String q = "INSERT INTO amenities (amentName, amentDescription) values(?, ?)";
        try {
            PreparedStatement ps = c.prepareStatement(q, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            ps.setString(2, description);
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            while(rs.next()){
                id = rs.getInt(1);
            }
            rs.close();
            ps.close();
        }catch (SQLException e){

        }
        return id;
    }

    public static int searchAmentities(Connection c, String name, String description){
        int id = -1;
        String q = "SELECT amentID FROM amenities WHERE name = ? & description = ?";
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setString(1, name);
            ps.setString(2, description);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
        }catch (SQLException e){

        }
        return id;
    }

    public static int linkAmentities(Connection c, int listId, int amentId){
        int r = -1;
        String q = "INSERT INTO amenities (listingID, amentID) values(?, ?)";
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setInt(1, listId);
            ps.setInt(2, amentId);
            r = ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            rs.close();
            ps.close();
        }catch (SQLException e){

        }
        return r;
    }


	//TODO: check if this works ...... 
	public static boolean linkhostListing(Connection c, int listingID, int hosterID) {
		boolean success = false;
		String q = "UPDATE listing SET hosterID = ? where listingID = ?";
		try {
			PreparedStatement ps = c.prepareStatement(q);
			ps.setInt(1, hosterID);
			ps.setInt(2, listingID);
			int a = ps.executeUpdate();
			success = a >= 0;
			ps.close();
		} catch (SQLException e) {
			// TODO: ADD ERROR MESSAGE
			e.printStackTrace();
		}
		return success;
	}

	
	//LISTINGS TODO: error checks???
	public static int insertAment(Connection c, int listingID, int amentID) {
		// adds user (no address) and then puts them into the table
		int id = -1;
		String query = "insert into amenitiesList (listingID, amentID) values (?,?)";
		try {
			PreparedStatement ps = c.prepareStatement(query);
			ps.setInt(1, listingID);
			ps.setInt(2, amentID);

			// TODO: change this to execute query
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO: ADD ERROR MESSAGE
			e.printStackTrace();
		}
		
		return id;
	}
	
	
	public static List<Amenity> AvailAment(Connection c) { //TODO: check this function
		List<Amenity> amen = null;
		String query = "SELECT * FROM amenities";

		try {
			PreparedStatement ps = c.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			// TODO: change this to execute query
			
			amen = new ArrayList<>();
			
			while (rs.next()) {
				int amentid = rs.getInt("amentid");
				String amentName = rs.getString("amentName");
				String amentDescription = rs.getString("amentDescription");
				Amenity x = new Amenity(amentid,amentName,amentDescription);
				amen.add(x);
			}
			rs.close();
			ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO: ADD ERROR MESSAGE
			e.printStackTrace();
		}
		return amen;
		
	}
	

//	public Listing[] GetAmentities(Connection c, int userID){
//		Listing[] ret = {};
//		String q = "select * FROM listing WHERE hosterID = ?";
//		try {
//			PreparedStatement ps = c.prepareStatement(q);
//			ps.setInt(1, userID);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//
//				int id = rs.getInt("listingID");
//				String city = rs.getString("city");
//				String postal_code = rs.getString("");
////				Listing l = new Listing("", "", "", "", "", "", "");
//			}
//			rs.close();
//			ps.close();
//		}catch (SQLException e){
//
//		}
//		return ret;
//	}
	
    public static User getUser(Connection c, String loginname) {
        User ret = new User();
        String q = "select * FROM users WHERE loginName = ?";
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setString(1, loginname);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int userid = rs.getInt("userID");
                String sin = rs.getString("sin");
                String userName = rs.getString("userName");
                java.sql.Date dob = rs.getDate("dob");
                String job = rs.getString("occupation");
                String loginName = rs.getString("loginName");
                String loginPW = rs.getString("loginPW");
                String address = rs.getString("address");
                String country = rs.getString("country");
                String city = rs.getString("city");
                String postal_code = rs.getString("postal_code");
                int isHoster = rs.getInt("isHoster");
                ret = new User(loginName, loginPW, userName, dob, job, sin, address, postal_code, city, country, isHoster);
                //
                
                /*
                 * User(int idt, String usernamet, String passwordt,  String namet,  java.sql.Date dobt, 
			  String jobt,  String sint, String addresst, String postalcodet, String cityt, 
			  String countryt, String usertypet){
                 */
                //
                ret.id = userid;
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

        }
        return ret;
    }
    
    public static boolean deleteUser(Connection c, String loginName){
        boolean contains = false;        
        String q = "DELETE FROM users where loginName = ?";
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setString(1, loginName);
            int rs = ps.executeUpdate();
//            while (rs.next()) {
//                contains = rs.getBoolean(1);
//            }
            ps.close();
        } catch (SQLException e) {
            // TODO: ADD ERROR MESSAGE
            e.printStackTrace();
        }
        return contains;
    }
    
    public static boolean deleteRenter(Connection c, int renterID){
        boolean contains = false;        
        String q = "DELETE FROM renters where renterID = ?";
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setInt(1, renterID);
            int rs = ps.executeUpdate();
//            while (rs.next()) {
//                contains = rs.getBoolean(1);
//            }
            ps.close();
        } catch (SQLException e) {
            // TODO: ADD ERROR MESSAGE
            e.printStackTrace();
        }
        return contains;
    }

    public static int updateListingAvgCost(Connection c, int listingID){
        //TODO JACKY CAN YOU ADD THIS EVERY TIME WE ADD/REMOVE AVAILABILITIES
        // TODO IS THIS LEGAL LOL
        // UPDATE listing SET avg_price = (SELECT AVG(price) FROM available where listingID = 2) WHERE listingID = 2;
        // the above works too but it gives a warning
        double avg = getListingAvgCost(c, listingID);
        String q = "UPDATE listing SET avg_price = ? WHERE listingID = ?";
        int ra = 0;
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setInt(1, listingID);
            ra = ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            // TODO: ADD ERROR MESSAGE
            e.printStackTrace();
        }
        return ra;
    }

    public static double getListingAvgCost(Connection c, int listingID){
        double avg = 0;
        String q = "SELECT AVG(price) FROM available where listingID = ?";
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setInt(1, listingID);
            ps.execute();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                avg = rs.getDouble(1);
            }
            ps.close();
        } catch (SQLException e) {
            // TODO: ADD ERROR MESSAGE
            e.printStackTrace();
        }
        return avg;
    }
    public static void updateListingAvailibility(Connection c, int listingID, Date from, Date to){
    	String q = "UPDATE available SET isBooked = 1 WHERE listingID = ? AND availDate BETWEEN ? AND ?";
    	try {
    		PreparedStatement ps = c.prepareStatement(q);
    		ps.setInt(1, listingID);
    		ps.setDate(2, from);
    		ps.setDate(3, to);
    		ps.execute();
    		ps.close();
    	} catch (SQLException e) {
    		// TODO: ADD ERROR MESSAGE
    		e.printStackTrace();
    	}
    }
    public static void insertSingleBooking(Connection c, int hostID, int renterID, int listingID, Date from, Date to){
    	String q = "INSERT INTO bookings (hostID, renterID, listingID, isCanceled, isHistory, fromDate, toDate) VALUES (?,?,?,?,?,?,?)";
    	try { 
    		PreparedStatement ps = c.prepareStatement(q);
    		ps.setInt(1, hostID);
    		ps.setInt(2, renterID);
    		ps.setInt(3, listingID);
    		ps.setInt(4, 0);
    		ps.setInt(5, 0);
    		ps.setDate(6, from);
    		ps.setDate(7, to);
    		ps.execute();
    		ps.close();
    	} catch (SQLException e) {
    		// TODO: ADD ERROR MESSAGE and catch this
    		e.printStackTrace();
    	}
    }

	
    public static void main(String[] args) {
        App application = App.createAppInstance();

        application.connect();
        //maxAment(application.getconn());
        System.out.println(AvailAment(application.getconn()));
        //create_listing(application.getconn(),"jacqueline", 123, 123);
        //add_address(application.getconn(), "tdot", "l1c7y4", "canada", "mum","1", "12");
        //linkAddressListing(application.getconn(),1,123);
        application.disconnect();
        
        java.text.SimpleDateFormat sdf = 
        new java.text.SimpleDateFormat("yyyy-MM-dd");
        
        java.util.Date toDate;
		try {
			
			int t = 5;
			
			while (t !=0 ){
			toDate = sdf.parse("2000-11-30");
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(toDate);
	        cal.add(Calendar.DATE, 1);
	        toDate = cal.getTime();

	        System.out.println(toDate);
	        
	        cal.add(Calendar.DATE, 1);

	        toDate = cal.getTime();

	        System.out.println(toDate);
	        
	        java.util.Date dt = new java.util.Date();
	        
	        String dateTime = sdf.format(toDate);
	        
	        System.out.println(dateTime);
	        t =t -1;
			}
	        
	        
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        


        


        
        


    }
}

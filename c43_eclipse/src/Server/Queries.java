package Server;

import Listings.Listing;

import java.sql.*;

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

    public static boolean checkUserNameTaken(Connection c, String userName) {
        boolean contains = false;
        String q = "SELECT EXISTS(SELECT * FROM users WHERE loginName = '?')";
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setString(1, userName);
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

    public static Listing[] getListingsForUser(Connection c, int userID) {
        Listing[] ret = {};
        String q = "select * FROM listing WHERE hosterID = ?";
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setInt(1, userID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                int id = rs.getInt("listingID");
                String city = rs.getString("city");
                String postal_code = rs.getString("");
                double longitude = rs.getDouble("longitude");
                double latitude = rs.getDouble("latitude");
                int hostID = rs.getInt("hosterID");

//				Listing l = new Listing("", "", "", "", "", "", "");
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

        }
        return ret;
    }

    public static int addAmentities(Connection c, String name, String description){
        int id = -1;
        String q = "INSERT INTO amenities (amendName, amentDescription) values(?, ?)";
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


}

package Server;

import java.sql.*;

public class Queries {
	
	//USER CREATION
	public static int create_user(Connection c, String sin, String userName, java.sql.Date dob, String occupation, String loginName, String pw) {
		// adds user (no address) and then puts them into the table
		int id = -1;
		String query = "insert into users (sin, userName, dob, occupation, loginName, loginPW) values (?,?,?,?,?,?)";
		try {
			PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1,sin);
			ps.setString(2, userName);
			ps.setDate(3, dob);
			ps.setString(4, occupation);
			ps.setString(5, loginName);
			ps.setString(6, pw);

			if(ps.executeUpdate() != 0){
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()){
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

	public static int add_address(Connection c, String city, String postal_code, String country, String street_name, String building_number, String unit_number) {
		int addrID = -1;
		String query = "insert into address (city, postal_code, country, street_name, building_number, unit_number) values (?,?,?,?,?,?)";
		try {
			PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, city);
			ps.setString(2, postal_code);
			ps.setString(3, country);
			ps.setString(4, street_name);
			ps.setString(5, building_number);
			ps.setString(6, unit_number);

			if(ps.executeUpdate() != 0){
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()){
					addrID = rs.getInt(1);
				}
				rs.close();
			}

			ps.close();
		} catch (SQLException e) {
			// TODO: ADD ERROR MESSAGE
			e.printStackTrace();
		}
		return addrID;
	}

	public static boolean checkUserNameTaken(Connection c, String userName) {
		boolean contains = false;
		String q = "SELECT EXISTS(SELECT * FROM users WHERE loginName = '?')";
		try {
			PreparedStatement ps = c.prepareStatement(q);
			ps.setString(1, userName);
			ResultSet rs =  ps.executeQuery();
			while(rs.next()) {
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
			while(rs.next()) {
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
	
	public static boolean linkAddressUser(Connection c, int userID, int addrID) {
		boolean success = false;
		String q = "UPDATE users SET addrID = ? where userID = ?";
		try {
			PreparedStatement ps = c.prepareStatement(q);
			ps.setInt(1, userID);
			ps.setInt(2, addrID);
			success = ps.execute();
			ps.close();
		} catch (SQLException e) {
			// TODO: ADD ERROR MESSAGE
			e.printStackTrace();
		}
		return success;
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
}

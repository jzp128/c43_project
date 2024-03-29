package Application;

import java.sql.*;

public class Main {
	public static void main(String[] args) {
		System.out.println("Booting up...");
		App application = App.createAppInstance();
		application.connect();
		application.initListings();
		application.welcome();
		application.disconnect();

	}
	
	
	/*
	private static final String dbClassName = "com.mysql.cj.jdbc.Driver";
	private static final String CONNECTION = "jdbc:mysql://localhost:3306/airbnb";
	
	public static void main(String[] args) throws ClassNotFoundException {
		//Register JDBC driver
		Class.forName(dbClassName);
		//Database credentials
		final String USER = "root";
		final String PASS = "root";
		System.out.println("Connecting to database...");
		
		try {
			//Establish connection
			Connection conn = DriverManager.getConnection(CONNECTION,USER,PASS);
			System.out.println("Successfully connected to MySQL!");
			
			//Execute a query
			System.out.println("Preparing a statement...");
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM Sailors;";
			ResultSet rs = stmt.executeQuery(sql);
			
			//STEP 5: Extract data from result set
			while(rs.next()){
				//Retrieve by column name
				int sid  = rs.getInt("sid");
				String sname = rs.getString("sname");
				int rating = rs.getInt("rating");
				int age = rs.getInt("age");
			
				//Display values
				System.out.print("ID: " + sid);
				System.out.print(", Name: " + sname);
				System.out.print(", Rating: " + rating);
				System.out.println(", Age: " + age);
			}
			
			
			System.out.println("Closing connection...");
			rs.close();
			stmt.close();
			conn.close();
			System.out.println("Success!");
		} catch (SQLException e) {
			//System.err.println("Connection error occured!");
			System.out.println(e.toString());
		}
	} */
}

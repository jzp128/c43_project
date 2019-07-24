package Application;

import Server.Helpers;


import java.util.Scanner;

import Users.User;

import java.security.interfaces.RSAKey;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


public class App {
    /**
     * Runs the application
     *
     * @param args an array of String arguments to be parsed
     */
	
	//Applciation Instance
	public static App application;
	
	//Database credentials
	final String USER = "root";
	final String PASS = "chanja51";
	private static final String dbClassName = "com.mysql.cj.jdbc.Driver";
	//private static final String CONNECTION = "jdbc:mysql://localhost:3306/airbnb";
	private static final String CONNECTION = "jdbc:mysql://127.0.0.1/airbnb?serverTimezone=America/New_York";
	private Connection conn = null;
	private Statement st = null;

	private Scanner sc = null;
	
	User user = new User();
	
	
	public static App createAppInstance(){
		application = new App();
		return application;
	};
	
	public static App getAppInstance(){
		return application;
	};
    
	public void welcome(){
		user.setName(null);
		System.out.println("");
		System.out.println("**************************");
		System.out.println("*********WELCOME**********");
		System.out.println("**************************");
		System.out.println("");
		System.out.println("=========MENU=========");
		System.out.println("0. Sign Up.");
		System.out.println("1. Log In.");
		System.out.println("2. Exit.");
		System.out.print("Choose one of the previous options [0 - 2]: ");
		
	    Scanner keyboard = new Scanner (System.in);
		String option = keyboard.nextLine();

		int choice = -1;
		try {
			choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.signup();
				break;
			case 1:
				this.login();
				break;
			case 2:
				disconnect();
				System.out.println("Exiting...");
				System.out.println("Goodbye.");
				System.exit(0);
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	
	public void login(){

	    Scanner keyboard = new Scanner (System.in);

		System.out.println("");
		System.out.println("**********LOGIN***********");
		System.out.println("");
		System.out.println("=========MENU=========");
		System.out.println("Enter your username, then your password:");
		System.out.print("Username:");
		String inpUser = keyboard.nextLine();
		System.out.print("Password:");
		String inpPass = keyboard.nextLine();
		System.out.println("0. Submit.");
		System.out.println("1. Go Back.");
		System.out.print("Choose one of the previous options [0 - 1]: ");
		String option = keyboard.nextLine();
		int choice = -1;
		try {
			choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				//this.welcome(); //TODO:
				
				if (Server.Helpers.login(conn,inpUser,inpPass)){
					RenterPage renter = new RenterPage();
					renter.renterPageMenu();
				} else {
					System.out.println("Incorrect Login Credentials!");
					login();
				}
				break;
			case 1:
				HostPage host = new HostPage();
				host.hostPageMenu(user);  //TODO:
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	
	
	public void signup(){

	    Scanner keyboard = new Scanner (System.in);
	    User user = new User ();

		System.out.println("");
		System.out.println("**********SIGNUP***********");
		System.out.println("");
		System.out.println("=========MENU=========");
		System.out.println("Enter a username and a password:");
		System.out.print("Username:");
		String inpUser = keyboard.nextLine();
		user.username = inpUser;
		
		System.out.print("Password:");
		String inpPass = keyboard.nextLine();
		user.password = inpPass;
		
		System.out.println("Enter some information about you");
		System.out.print("First Name:");
		String firstname = keyboard.nextLine();
		user.name = firstname;
				
		System.out.print("Last Name:");
		String lastname = keyboard.nextLine();
		user.name = user.name + "_" + lastname;
		
		System.out.print("Date of Birth Format (yyyy-MM-dd):");
		String dob = keyboard.nextLine();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date date;
		
		
		try {
			date = sdf1.parse(dob);
			java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());  
			user.dob = sqlStartDate;
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		System.out.print("Occupation:");
		String job = keyboard.nextLine();
		user.job = job;
		
		System.out.print("SIN number:");
		String sin = keyboard.nextLine();
		user.sin = sin;
		
		System.out.print("Address:");
		user.address = keyboard.nextLine();
		
		System.out.print("Postal Code:");
		String postalcode = keyboard.nextLine();
		user.postalcode = postalcode;
		
		System.out.print("City:");
		String city = keyboard.nextLine();
		user.city = city;
		
		System.out.print("Country:");
		String country = keyboard.nextLine();
		user.country = country;
		
		System.out.println("Indicate if you want to be a [0] renter or [1] host.");
		String usertype = keyboard.nextLine();
		user.usertype = usertype;
		
		String cc = "";
		String ccname = "";
		String ccsecuritr = "";
		
		
		/*----------------renters payment---------------------*/
		
		int choice = -1;
		try {
			choice = Integer.parseInt(usertype);
			switch (choice) {
			case 0:
				System.out.println("Payment Information:");
				System.out.print("Credit Card Number:");
				cc = keyboard.nextLine();
				System.out.print("Name on the Credit Card:");
				ccname = keyboard.nextLine();
				System.out.print("Card Security Code");
				ccsecuritr = keyboard.nextLine();
				break;
			case 1:
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			choice = -1;
		}
		/*----------------renters payment---------------------*/

		System.out.println("By submitting I am declaring that I am at least 18 years old.");
		System.out.println("0. Submit.");
		System.out.println("1. Go Back.");
		System.out.print("Choose one of the previous options [0 - 1]: ");
		String option = keyboard.nextLine();
		int choice2 = -1;
		try {
			RenterPage renter = new RenterPage();
			HostPage host = new HostPage();
			choice2 = Integer.parseInt(option);
			switch (choice2) { //Activate the desired functionality
			case 0:
				try {
					int redirect = Integer.parseInt(usertype);
						switch (redirect) {
						case 0:
							user.makeUser(conn);
							//renter.renterPayment(); // figure out the order!
							renter.renterPageMenu();
							break;
						case 1:
							user.makeUser(conn);
							host.hostPageMenu(user);
							break;
						}
					} catch (NumberFormatException e) {
						usertype = "-1";
					}
				break;
			default:
				break;
			case 1:
				this.welcome();
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	
	
	public void run() {
    	sc = new Scanner(System.in);
    	String input = "";
    	input = sc.nextLine();
    	System.out.println(input);
    }
    
	
    public Connection connect() {
        try {
            conn = DriverManager.getConnection(CONNECTION,USER,PASS);
        	System.out.println("Database connected! " + conn.toString());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public Connection getconn() {
        return conn;
    }
    
	public void disconnect() {
    	System.out.println("Database disconnected! " + conn.toString());
		try {
			// st.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("Exception occured while disconnecting!");
			e.printStackTrace();
		} finally {
			//st = null;
			conn = null;
		}
	}
    
    
}

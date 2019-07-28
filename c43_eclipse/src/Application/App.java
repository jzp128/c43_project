package Application;

import Server.Helpers;
import Server.Queries;

import java.util.Scanner;

import Checkers.CheckersGeneric;
import Users.Renter;
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
	
	//Checker Instance
	CheckersGeneric checker = new CheckersGeneric();
	
	//queries
	Server.Queries queries = new Server.Queries();
	
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
		String option;
		int choice = -1;
		boolean optionb = false;
		while(!optionb){
			System.out.print("Choose one of the previous options [0 - 2]: ");
		    Scanner keyboard = new Scanner (System.in);
			option = keyboard.nextLine();
			try {
				choice = Integer.parseInt(option);
				optionb = CheckersGeneric.range(0,2,choice);
			} catch (Exception e) {
				System.out.println("Invalid Option!");
			}
		}
		try {
			switch (choice) { //Activate the desired functionality
			case 0:
				this.signup();
				break;
			case 1:
				this.login(conn);
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
	
	public void login(Connection c){

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
					// set up user variable
					// check to see whether renter or not
					user = queries.getUser(conn, inpUser);
					if (user.isHost == 0) {
						RenterPage renter = new RenterPage();
						renter.renterPageMenu(c,user);
					} else {
						HostPage host = new HostPage();
						host.hostPageMenu(conn,user);  //TODO:
					}
				} else {
					System.out.println("Incorrect Login Credentials!");
					login(conn);
				}
				break;
			case 1:
				this.welcome();
				break;
			default:
				this.welcome();
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	
	
	public void signup(){

	    Scanner keyboard = new Scanner (System.in);
	    User user = new User();

		System.out.println("");
		System.out.println("**********SIGNUP***********");
		System.out.println("");
		System.out.println("=========MENU=========");
		System.out.println("Enter a username and a password:");
		
		boolean incorrectuser = true;
		while (incorrectuser) {
			System.out.print("Username (20 chars max!):");
			String inpUser = keyboard.nextLine();
			user.loginname = inpUser;
			if (!user.checkusername(conn)){
				incorrectuser= false;
			} else {
				System.out.println("Please enter a different username!");
			}
		}
		
		System.out.print("Password (20 chars max!):");
		String inpPass = keyboard.nextLine();
		user.password = inpPass;
		
		System.out.println("Enter some information about you");
		System.out.print("First Name:");
		String firstname = keyboard.nextLine();
		user.name = firstname;
				
		System.out.print("Last Name:");
		String lastname = keyboard.nextLine();
		user.name = user.name + "_" + lastname;
		
		boolean dateCheckfalse = true;
		
		while (dateCheckfalse){
			try {
				System.out.print("Date of Birth Format (yyyy-MM-dd):");
				String dob = keyboard.nextLine();
				SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date date;
				date = sdf1.parse(dob);
				java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());  
				user.dob = sqlStartDate;
				dateCheckfalse = false;
			} catch (ParseException e1) {
				System.out.println("Incorrect birth day format. Please try again!");
				dateCheckfalse = true;
				//e1.printStackTrace();
			}
		}
		
		System.out.print("Occupation:");
		String job = keyboard.nextLine();
		user.job = job;
		

		
		boolean inccorectsin = true;
		while (inccorectsin) {
			System.out.print("SIN number (9 digits):");
			String sin = keyboard.nextLine();
			user.sin = sin;
			if (!Queries.checkSINTaken(conn, sin)){
				inccorectsin= false;
			} else {
				System.out.println("Please enter a different sin!");
				inccorectsin = true;
			}
		}
		
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
		
		String cc = "";
		String ccname = "";
		String ccsecuritr = "";
		
		
		/*----------------renters payment---------------------*/
		boolean invalidCreditInfo = true;
		while (invalidCreditInfo) {
			int choice = -1;
			try {
				choice = Integer.parseInt(usertype);
				user.isHost = choice;
				switch (choice) {
				case 0:
					System.out.println("Payment Information:");
					System.out.print("Credit Card Number (16 digits):");
					cc = keyboard.nextLine();
					user.ccnumber = cc;
					System.out.print("Name on the Credit Card:");
					ccname = keyboard.nextLine();
					user.ccName = ccname;
					System.out.print("Card Security Code (3 digits)");
					ccsecuritr = keyboard.nextLine();
					user.ccsec = ccsecuritr;
					
					if (CheckersGeneric.isNumeric(ccsecuritr) && CheckersGeneric.length(ccsecuritr)==3){
						if (CheckersGeneric.isNumeric(cc) && CheckersGeneric.length(cc)==16){
							invalidCreditInfo = false;
						}
					} else {
						invalidCreditInfo = true;
						System.out.println("Please try again~");
					}
					
					break;
				case 1:
					break;
				default:
					break;
				}
			} catch (Exception e) {
				invalidCreditInfo = true; 
				System.out.println("Please try again~");
				choice = -1;
			}
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
				user.makeUser(conn);
				try {
					boolean success;
					int redirect = Integer.parseInt(usertype);
						switch (redirect) {
						case 0:
							success = user.makeRenter(conn);
							renter.renterPageMenu(conn,user);
							break;
						case 1:
							success = user.makeHost(conn);
							host.hostPageMenu(conn,user);
							break;
						}
					} catch (Exception e) {
						usertype = "-1";
					}
				break;
			default:
				break;
			case 1:
				this.welcome();
				break;
			}
		} catch (Exception e) {
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

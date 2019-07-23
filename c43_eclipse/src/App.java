import java.util.Scanner;
import java.security.interfaces.RSAKey;
import java.sql.*;
import java.util.ArrayList;


public class App {
    /**
     * Runs the application
     *
     * @param args an array of String arguments to be parsed
     */
	private static final String dbClassName = "com.mysql.jdbc.Driver";
	private static final String CONNECTION = "jdbc:mysql://127.0.0.1/";
	private Connection conn = null;
	private Statement st = null;

	private Scanner sc = null;
	
    
	public void welcome(){
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
				System.out.println("Exiting...");
				System.out.println("Goodbye.");
				System.exit(0);
				break;
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
				this.welcome();
				break;
			case 1:
				this.welcome();
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

		System.out.println("");
		System.out.println("**********SIGNUP***********");
		System.out.println("");
		System.out.println("=========MENU=========");
		System.out.println("Enter a username and a password:");
		System.out.print("Username:");
		String inpUser = keyboard.nextLine();
		System.out.print("Password:");
		String inpPass = keyboard.nextLine();
		System.out.print("First Name:");
		String firstname = keyboard.nextLine();
		System.out.print("Last Name:");
		String lastname = keyboard.nextLine();
		System.out.print("Date of Birth Format (DDMMYYYY):");
		String dob = keyboard.nextLine();
		System.out.print("Occupation:");
		String job = keyboard.nextLine();
		System.out.print("SIN number:");
		String sin = keyboard.nextLine();
		System.out.print("Building Number:");
		String buildingno = keyboard.nextLine();
		System.out.print("Street Name:");
		String streetname = keyboard.nextLine();
		System.out.print("Unit Number (Optional):");
		String unitnumber = keyboard.nextLine();
		System.out.print("Postal Code:");
		String postalcode = keyboard.nextLine();
		System.out.print("City:");
		String city = keyboard.nextLine();
		System.out.print("Country:");
		String country = keyboard.nextLine();
		System.out.println("Indicate if you want to be a [0] renter or [1] host.");
		String usertype = keyboard.nextLine();
		System.out.println("By submitting I am declaring that I am at least 18 years old.");
		System.out.println("0. Submit.");
		System.out.println("1. Go Back.");
		System.out.print("Choose one of the previous options [0 - 1]: ");
		String option = keyboard.nextLine();
		int choice = -1;
		try {
			RenterPage renter = new RenterPage();
			HostPage host = new HostPage();
			choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				try {
					int redirect = Integer.parseInt(usertype);
						switch (redirect) {
						case 0:
							renter.renterPayment();
							renter.renterPageMenu();

							break;
						case 1:
							host.hostPageMenu();
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
    
	public boolean connect(String[] cred) throws ClassNotFoundException {
		Class.forName(dbClassName);
		boolean success = true;
		String user = cred[0];
		String pass = cred[1];
		String connection = CONNECTION + cred[2];
		try {
			conn = DriverManager.getConnection(connection, user, pass);
			st = conn.createStatement();
		} catch (SQLException e) {
			success = false;
			System.err.println("Connection could not be established!");
			e.printStackTrace();
		}
		return success;
	}
    
	public void disconnect() {
		try {
			st.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println("Exception occured while disconnecting!");
			e.printStackTrace();
		} finally {
			st = null;
			conn = null;
		}
	}
    
    
}

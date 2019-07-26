package Application;
import java.sql.Connection;
import java.util.Scanner;

import Users.User;

public class RenterPage extends UserPage{
	
	User user = new User();
	
	Scanner keyboard = new Scanner (System.in);
	//App application = new App();
	
//	public void renterPayment(){
//		System.out.println("Payment Information");
//		System.out.print("Credit Card Number:");
//		String cc = keyboard.nextLine();
//		System.out.print("Name on the Credit Card:");
//		String ccname = keyboard.nextLine();
//		System.out.print("Card Security Code");
//		String ccsecuritr = keyboard.nextLine();
//		System.out.println("==========================");
//		System.out.println("0  Submit.");
//		System.out.println("1. Cancel.");
//		String option = keyboard.nextLine();
//
//		try {
//			int choice = Integer.parseInt(option);
//			switch (choice) { //Activate the desired functionality
//			case 0:
//				this.renterPageMenu(); // TODO: FIX THIS
//				break;
//			case 1:
//				App.application.welcome(); // TODO: FIX THIS
//				break;
//			default:
//				break;
//			}
//		} catch (NumberFormatException e) {
//			option = "-1";
//		}
//	}
	
	
	
	public void renterPageMenu(Connection c, User u){	
		user = u;
		System.out.println("**************************");
		System.out.println("******ACCESS GRANTED******");
		System.out.println("**************************");
		System.out.println("");
		System.out.println("====RENTER'S HOME PAGE====");
		System.out.println("0  View my Bookings.");
		System.out.println("1. Search for a Booking.");
		System.out.println("2. Look at Availible Bookings.");
		System.out.println("3. Delete My Account.");
		System.out.println("4. Log Out");
		System.out.print("Choose one of the previous options [0 - 2]: ");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.renterbooking(c);
				break;
			case 1:
				this.renterbooking(c); //TODO
				break;
			case 2:
				super.listAvaillistings(c,u); //TODO
				break;
			case 3:
				super.deleteAccount(c,u);
				break;
			case 4:
				super.logout();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void historyBookingPage(Connection c){		

		System.out.println("====PREVIOUS BOOKINGS====");

		System.out.print("ENTER the booking number you want to view");
		String record = keyboard.nextLine();

		System.out.println("0  Submit.");
		System.out.println("1. Go Back.");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				//this.welcome();
				this.viewbooking(c);
				this.historyBookingPage(c);
				break;
			case 1:
				this.renterbooking(c);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void futureBookingPage(Connection c){		

		System.out.println("====FUTURE BOOKINGS====");

		System.out.print("ENTER the booking number you want to view");
		String record = keyboard.nextLine();

		System.out.println("0  Submit.");
		System.out.println("1. Go Back.");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.viewbooking(c);
				this.futureBookingPage(c);
				//this.welcome();
				break;
			case 1:
				this.renterbooking(c);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void canceledBookingPage(Connection c){		

		System.out.println("====CANCELED BOOKINGS====");

		System.out.print("ENTER the booking number you want to view");
		String record = keyboard.nextLine();

		System.out.println("0  Submit.");
		System.out.println("1. Go Back.");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.viewbooking(c);
				this.canceledBookingPage(c);
				//this.welcome();
				break;
			case 1:
				this.renterbooking(c);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void renterbooking(Connection c){		
		System.out.println("");
		System.out.println("=========BOOKINGS=========");
		System.out.println("0  View Future Bookings");
		System.out.println("1. View Previous Bookings.");
		System.out.println("2. View Canceled Bookings");
		System.out.println("3. Go Back");
		
		System.out.print("Choose one of the previous options [0 - 3]: ");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.futureBookingPage(c);
				break;
			case 1:
				this.historyBookingPage(c);
				break;
			case 2:
				this.canceledBookingPage(c);
				break;
			case 3:
				this.renterPageMenu(c,user);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void renterreviewhost(Connection c){
		System.out.println("");
		System.out.println("=========Review=========");
		System.out.println("Write a review of: (Maximum 2000 characters)"); // renters name
		String review = keyboard.nextLine();
		System.out.println("0. Submit");
		System.out.println("1. Go Back");
		String option = keyboard.nextLine();

		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				//try to insert into the db
				// check if they actually can
				//if so then
				System.out.println("Commented!");
				System.out.println("Going back now!");
				viewbooking(c);
			case 1:
				viewbooking(c);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	public void renterratehost(Connection c){
		System.out.println("");
		System.out.println("=========Rating=========");
		System.out.println("Provide a rating from [0-5]:"); // renters name
		String review = keyboard.nextLine();
		System.out.println("0. Submit");
		System.out.println("1. Go Back");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				//try to insert into the db
				// check if they actually can
				//if so then
				System.out.println("Rated!");
				System.out.println("Going back now!");
				viewbooking(c);
			case 1:
				viewbooking(c);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	public void renterreviewlisting(Connection c){
		System.out.println("");
		System.out.println("=========Review=========");
		System.out.println("Write a review of: (Maximum 2000 characters)"); // listing name
		String review = keyboard.nextLine();
		System.out.println("0. Submit");
		System.out.println("1. Go Back");
		String option = keyboard.nextLine();

		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				//try to insert into the db
				// check if they actually can
				//if so then
				System.out.println("Commented!");
				System.out.println("Going back now!");
				viewbooking(c);
			case 1:
				viewbooking(c);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	public void renterratelisting(Connection c){
		System.out.println("");
		System.out.println("=========Rating=========");
		System.out.println("Provide a rating from [0-5]:"); // renters name
		String review = keyboard.nextLine();
		System.out.println("0. Submit");
		System.out.println("1. Go Back");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				//try to insert into the db
				// check if they actually can
				//if so then
				System.out.println("Rated!");
				System.out.println("Going back now!");
				viewbooking(c);
			case 1:
				viewbooking(c);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	
	public void viewbooking(Connection c){		
		System.out.println("");
		System.out.println("=========BOOKING=========");
		System.out.println("0. Write a Review on the Host's Profile ");
		System.out.println("1. Write a Review on the Listing");
		System.out.println("2. Rate the Host's Profile! ");
		System.out.println("3. Rate the Listing! ");
		System.out.println("4. Go Back to the Main Booking Page");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality

			case 0:
				renterreviewhost(c);
				break;
			case 1:
				renterreviewlisting(c);
				break;
			case 2:
				renterratehost(c);
				break;
			case 3:
				renterratelisting(c);
				break;
			case 4:
				renterbooking(c);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
}

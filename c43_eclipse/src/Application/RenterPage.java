package Application;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Listings.Booking;
import Listings.Listing;
import Server.Queries;
import Users.User;

public class RenterPage extends UserPage{
	
	User user = new User();
	
	Scanner keyboard = new Scanner (System.in);
	
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
				this.renterbooking(c,u);
				break;
			case 1:
				this.renterbooking(c,u); //TODO
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
	
	public void historyBookingPage(Connection c, User u){		

		System.out.println("====PREVIOUS BOOKINGS====");
		
		Queries.updateHistoryBookingsforRenter(c,u.id);
		ArrayList<Booking> list = Queries.getHistoryBookingsforRenter(c,u.id);
		printBookings(list);

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
				this.viewbooking(c,u);
				this.historyBookingPage(c,u);
				break;
			case 1:
				this.renterbooking(c,u);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void futureBookingPage(Connection c, User u){		

		System.out.println("====FUTURE BOOKINGS====");

		Queries.updateHistoryBookingsforRenter(c,u.id);
		ArrayList<Booking> list = Queries.getCurrentBookingsforRenter(c,u.id);
		
		System.out.println(list);

		printBookings(list);

		System.out.print("ENTER the booking number you want to view");
		String record = keyboard.nextLine();

		System.out.println("0  Submit.");
		System.out.println("1. Go Back.");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.viewbooking(c, u);
				this.futureBookingPage(c,u);
				//this.welcome();
				break;
			case 1:
				this.renterbooking(c,u);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void canceledBookingPage(Connection c, User u){		

		System.out.println("====CANCELED BOOKINGS====");
		
		Queries.updateHistoryBookingsforRenter(c,u.id);
		ArrayList<Booking> list = Queries.getCanceledBookingsforRenter(c,u.id);
		printBookings(list);


		System.out.print("ENTER the booking number you want to view");
		String record = keyboard.nextLine();

		System.out.println("0  Submit.");
		System.out.println("1. Go Back.");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.viewbooking(c,u);
				this.canceledBookingPage(c,u);
				//this.welcome();
				break;
			case 1:
				this.renterbooking(c,u);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void renterbooking(Connection c, User u){		
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
				this.futureBookingPage(c,u);
				break;
			case 1:
				this.historyBookingPage(c,u);
				break;
			case 2:
				this.canceledBookingPage(c,u);
				break;
			case 3:
				this.renterPageMenu(c,u);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void renterreviewhost(Connection c, User u){
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
				viewbooking(c,u);
			case 1:
				viewbooking(c,u);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	public void renterratehost(Connection c, User u){
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
				viewbooking(c,u);
			case 1:
				viewbooking(c,u);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	public void renterreviewlisting(Connection c, User u){
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
				viewbooking(c,u);
			case 1:
				viewbooking(c,u);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	public void renterratelisting(Connection c, User u){
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
				viewbooking(c,u);
			case 1:
				viewbooking(c,u);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	
	public void viewbooking(Connection c, User u){		
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
				renterreviewhost(c,u);
				break;
			case 1:
				renterreviewlisting(c,u);
				break;
			case 2:
				renterratehost(c,u);
				break;
			case 3:
				renterratelisting(c,u);
				break;
			case 4:
				renterbooking(c,u);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	
	
	
	public void printBookings (List<Booking> list ) {
		int iterate = 1;
		for (Booking x: list) {
			System.out.println("=================================");
			System.out.println("Booking Choice ["+iterate+"]");
			System.out.println("Booking ID:" + x.bookingID +" " );
			System.out.println("Host ID:" + x.hostID +" " );
			System.out.println("Renter ID Type:" + x.renterID+ " ");
			System.out.println("Listing ID:" + x.listingID +" ");
			System.out.println("From Date:" + x.fromDate + " ");
			System.out.println("To Date:" + x.toDate + " ");
			System.out.println("=================================");	
			iterate ++;
		}
	}
	
	
}

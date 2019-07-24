package Application;
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
	
	
	
	public void renterPageMenu(User u){	
		user = u;
		System.out.println("**************************");
		System.out.println("******ACCESS GRANTED******");
		System.out.println("**************************");
		System.out.println("");
		System.out.println("====RENTER'S HOME PAGE====");
		System.out.println("0  View my Bookings.");
		System.out.println("1. Delete My Account.");
		System.out.println("2. Log Out");
		System.out.print("Choose one of the previous options [0 - 2]: ");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.renterbooking();
				break;
			case 1:
				super.deleteAccount();
				break;
			case 2:
				super.logout();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void historyBookingPage(){		

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
				this.viewbooking();
				this.historyBookingPage();
				break;
			case 1:
				this.renterbooking();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void futureBookingPage(){		

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
				this.viewbooking();
				this.futureBookingPage();
				//this.welcome();
				break;
			case 1:
				this.renterbooking();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void canceledBookingPage(){		

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
				this.viewbooking();
				this.canceledBookingPage();
				//this.welcome();
				break;
			case 1:
				this.renterbooking();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void renterbooking(){		
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
				this.futureBookingPage();
				break;
			case 1:
				this.historyBookingPage();
				break;
			case 2:
				this.canceledBookingPage();
				break;
			case 3:
				this.renterPageMenu(user);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void renterreviewhost(){
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
				viewbooking();
			case 1:
				viewbooking();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	public void renterratehost(){
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
				viewbooking();
			case 1:
				viewbooking();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	public void renterreviewlisting(){
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
				viewbooking();
			case 1:
				viewbooking();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	public void renterratelisting(){
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
				viewbooking();
			case 1:
				viewbooking();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	
	public void viewbooking(){		
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
				renterreviewhost();
				break;
			case 1:
				renterreviewlisting();
				break;
			case 2:
				renterratehost();
				break;
			case 3:
				renterratelisting();
				break;
			case 4:
				renterbooking();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
}

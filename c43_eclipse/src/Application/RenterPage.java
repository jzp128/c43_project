package Application;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Checkers.CheckersGeneric;
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
		System.out.println("Choose one of the previous options [0 - 2]: ");
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
			case 2://TODO
				super.listAvaillistings(c,u);
				renterPageMenu( c,  u);
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
		
		if (list.isEmpty()){
			System.out.println("You have no bookings here!");
			renterbooking(c,u);
			return; //TODO
		}
		
		//=============enter single booking page=======================
		
		System.out.println("ENTER the booking number you want to view.");
		System.out.println("(Or just press enter to continue.)");

		String record = keyboard.nextLine();

		System.out.println("0  Submit.");
		System.out.println("1. Go Back.");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			int choicerecord = Integer.parseInt(record);
			
			if (!CheckersGeneric.range(1,list.size(),choicerecord)){
				System.out.println("Your answer is invalid!");
				return; //TODO
			}
			
			switch (choice) { //Activate the desired functionality
			case 0:
				//this.welcome();
				this.viewbooking(c,u,list.get(choicerecord-1)); //feed the booking u want to view
				this.historyBookingPage(c,u);
				break;
			case 1:
				this.renterbooking(c,u);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			this.renterbooking(c,u);
		}
		//=============enter single booking page=======================

	}
	
	public void futureBookingPage(Connection c, User u){		

		System.out.println("====FUTURE BOOKINGS====");

		Queries.updateHistoryBookingsforRenter(c,u.id);
		ArrayList<Booking> list = Queries.getCurrentBookingsforRenter(c,u.id);
		
		if (list.isEmpty()){
			System.out.println("You have no bookings here!");
			renterbooking(c,u);
			return;
		}
		//System.out.println(list);

		printBookings(list);

		System.out.println("ENTER the booking number you want to view.");
		System.out.println("(Or just press enter to continue)");

		String record = keyboard.nextLine();

		System.out.println("0  Submit.");
		System.out.println("1. Go Back.");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			int choicerecord = Integer.parseInt(record);
			
			if (!CheckersGeneric.range(1,list.size(),choicerecord)){
				System.out.println("Your answer is invalid!");
				return; //TODO
			}
			
			switch (choice) { //Activate the desired functionality
			case 0:
				this.viewbooking(c, u, list.get(choicerecord-1));
				this.futureBookingPage(c,u);
				//this.welcome();
				break;
			case 1:
				this.renterbooking(c,u);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			this.renterbooking(c,u);
		}
		
	}
	
	public void canceledBookingPage(Connection c, User u){		

		System.out.println("====CANCELED BOOKINGS====");
		
		Queries.updateHistoryBookingsforRenter(c,u.id);
		ArrayList<Booking> list = Queries.getCanceledBookingsforRenter(c,u.id);
		if (list.isEmpty()){
			System.out.println("You have no bookings here!");
			renterbooking(c,u);
			return;
		}
		printBookings(list);

		System.out.println("ENTER the booking number you want to view.");
		System.out.println("(Or just press enter to continue)");
		String record = keyboard.nextLine();

		System.out.println("0  Submit.");
		System.out.println("1. Go Back.");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			int choicerecord = Integer.parseInt(record);
			
			if (!CheckersGeneric.range(1,list.size(),choicerecord)){
				System.out.println("Your answer is invalid!");
				return; //TODO
			}
			
			switch (choice) { //Activate the desired functionality
			case 0:
				this.viewbooking(c,u,list.get(choicerecord-1));
				this.canceledBookingPage(c,u);
				//this.welcome();
				break;
			case 1:
				this.renterbooking(c,u);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			this.renterbooking(c,u);
		}
		
	}
	
	public void renterbooking(Connection c, User u){		
		System.out.println("");
		System.out.println("=========BOOKINGS=========");
		System.out.println("0  View Future Bookings");
		System.out.println("1. View Previous Bookings.");
		System.out.println("2. View Canceled Bookings");
		System.out.println("3. Go Back");
		
		System.out.println("Choose one of the previous options [0 - 3]: ");
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
	
	public void renterreviewhost(Connection c, User u, Booking b){
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
				
				//=============RATING=====================
				
				int choicerating = 0;
				boolean ratingWrong = true;
				while(ratingWrong) {
				
				System.out.println("");
				System.out.println("=========Rating=========");
				System.out.println("Provide a rating from [0-5]:"); // renters name
				String rating = keyboard.nextLine();
				
				try {
					choicerating = Integer.parseInt(rating);
					
					if (CheckersGeneric.range(0,5,choicerating)){
						ratingWrong = false;
					} else {
						ratingWrong = true;
					}
 
				} catch (Exception e) {
					System.out.println("Please Try Again!");
				}
				
				}
				//=============RATING=====================

				//=============DB CALL=====================
				Queries.writeHostReview(c,u,b,review,choicerating,"h");

				
				System.out.println("Rated!");
				System.out.println("Commented!");
				System.out.println("Going back now!");
				
				//=============DB CALL=====================
				
				
				viewbooking(c,u,b);
			case 1:
				viewbooking(c,u,b);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	
	
	public void renterreviewlisting(Connection c, User u, Booking b){
		System.out.println("");
		System.out.println("=========Review=========");
		System.out.println("Write a review of: (Maximum 2000 characters)"); // listing name
		String review = keyboard.nextLine();
		System.out.println("0. Submit");
		System.out.println("1. Go Back");
		String option = keyboard.nextLine();

		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality TODO
			case 0:
				//try to insert into the db TODO
				// check if they actually can
				//if so then
				//=============RATING=====================
				
				int choicerating = 0;
				boolean ratingWrong = true;
				while(ratingWrong) {
				
				System.out.println("");
				System.out.println("=========Rating=========");
				System.out.println("Provide a rating from [0-5]:"); // renters name
				String rating = keyboard.nextLine();
				
				try {
					choicerating = Integer.parseInt(rating);
					
					if (CheckersGeneric.range(0,5,choicerating)){
						ratingWrong = false;
					} else {
						ratingWrong = true;
					}
 
				} catch (Exception e) {
					System.out.println("Please Try Again!");
				}
				
				}
				//=============RATING=====================

				//=============DB CALL=====================
				Queries.writeHostReview(c,u,b,review,choicerating,"l");

				
				System.out.println("Rated!");
				System.out.println("Commented!");
				System.out.println("Going back now!");
				
				//=============DB CALL=====================
				
				viewbooking(c,u,b);
			case 1:
				viewbooking(c,u,b);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	
	
	public void viewbooking(Connection c, User u, Booking b){		
		System.out.println("");
		System.out.println("=========BOOKING=========");
		System.out.println("0. Write a Review & Rating on the Host's Profile ");
		System.out.println("1. Write a Review & Rating on the Listing");
		System.out.println("2. Cancel this Booking (can only be done on future bookings)!");
		System.out.println("3. Go Back to the Main Booking Page");
		String option = keyboard.nextLine();
		
		
		// check if the booking was within 30 days
		
		java.util.Date today = CheckersGeneric.currentDate();
		
		int recent = CheckersGeneric.betweenDays(b.toDate,today);
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality

			case 0:
				if (b.isCanceled == 1){
					System.out.println("Sorry, our records show that this booking is canceled,");
					System.out.println("You cannot rate/review a canceled booking.");
					renterbooking(c,u);
					break;
				} else if (CheckersGeneric.range(0, 30, recent)){
					System.out.println("You can only rate/review a recent booking after 30 days.");
					renterbooking(c,u);
					break;
				}
				renterreviewhost(c,u,b);
				break;
			case 1:
				if (b.isCanceled == 1){
					System.out.println("Sorry, our records show that this booking is canceled,");
					System.out.println("You cannot rate/review a canceled booking.");
					renterbooking(c,u);
					break;
				} else if (CheckersGeneric.range(0, 30, recent)){
					System.out.println("You can only rate/review a recent booking after 30 days.");
					renterbooking(c,u);
					break;
				}
				renterreviewlisting(c,u,b);
				break;
			case 2:
				if ((b.isHistory == 1) && (b.isCanceled == 1)){
					System.out.println("Sorry, our records show that this booking is canceled/historic");
					System.out.println("You cannot cancel this booking....");
				} else {
					Queries.cancelBooking(c, b.bookingID);
					//update the availibility dates of the listing
					//Connection c, int listingID, Date from, Date to
					Queries.reupdateListingAvailibility(c,b.listingID,b.fromDate,b.toDate); //TODO check this!
					
					System.out.println("Canceled Booking!");
					
				}
				renterbooking(c,u);
				break;
			case 3:
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

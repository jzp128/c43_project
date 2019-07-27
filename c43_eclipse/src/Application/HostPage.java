
package Application;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import Checkers.CheckersGeneric;
import Listings.Amenity;
import Listings.Available;
import Listings.Listing;
import Server.Queries;
import Users.User;

public class HostPage extends UserPage{
	User user = new User();

	public void hostPageMenu(Connection c, User u){
		this.user = u;
		
		System.out.println("**************************");
		System.out.println("******ACCESS GRANTED******");
		System.out.println("**************************");
		System.out.println("");
		System.out.println("=====HOST'S HOME PAGE=====");
		System.out.println("0. Make a Listing.");
		System.out.println("1. View my Listings.");
		System.out.println("2. Delete My Account.");
		System.out.println("3. Log Out");
		System.out.print("Choose one of the previous options [0 - 2]: ");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.makeListingPage(c);
				break;
			case 1:
				this.hostlisting(c,u);
				break;
			case 2:
				super.deleteAccount(c,user);
				break;
			case 3:
				super.logout();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	
	public void makeListingPage(Connection c){	

		System.out.println("====MAKE A LISTING====");

		System.out.println("ENTER the necessary information");
		System.out.println("Listing type:");
		System.out.println("0. Full House:");
		System.out.println("1. Apartment:");
		System.out.println("2. Room:");
		
		String listingtype;
		int housingchoice = -1;
		boolean optionb = false;
		while(!optionb){
			System.out.println("Choose a listing type [0 - 2]");
			listingtype = keyboard.nextLine();
			try {
				housingchoice = Integer.parseInt(listingtype);
				optionb = CheckersGeneric.range(0,2,housingchoice);
			} catch (Exception e) {
				System.out.println("Invalid Option!");
			}
		}
		
		if (housingchoice == 0){
			listingtype = "Full House";
		} else if (housingchoice == 1){
			listingtype = "Apartment";
		} else {
			listingtype = "Room";
		}
		
		System.out.println("ENTER the longitude of the location:");
		String longitudestring = keyboard.nextLine();
		Double longitude = Double.parseDouble(longitudestring);
		
		System.out.println("ENTER the latitude of the location:");
		String latitudestring = keyboard.nextLine();
		Double latitude = Double.parseDouble(latitudestring);

		
		System.out.print("Address:");
		String address = keyboard.nextLine();

		
		System.out.print("Postal Code:");
		String postalcode = keyboard.nextLine();
		//user.postalcode = postalcode;
		
		System.out.print("City:");
		String city = keyboard.nextLine();
		//user.city = city;
		
		System.out.print("Country:");
		String country = keyboard.nextLine();
		//user.country = country;
		
		System.out.println("0  Submit.");
		System.out.println("1. Cancel.");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				App app = App.getAppInstance();
				Listing list = new Listing(city, postalcode, country, address,latitude, longitude, user.id, listingtype);
				int id = list.makelisting(app.getconn());
				amenitiesPage(id);
				System.out.println("Listing Added!");
				hostPageMenu(c,user);
//				this.previousListingPage();
				break;
			case 1:
				this.hostPageMenu(c, user);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void amenitiesPage(int listingid){
		App app = App.getAppInstance();
		System.out.println("Amenities!" + app.getconn());
		System.out.println(app.getconn());
		List<Amenity> amen = Queries.AvailAment(app.getconn());
		for (int i = 0; i < amen.size(); i++) {
			System.out.print(amen.get(i).amenid + ". ");
			System.out.println(amen.get(i).amenName);
			System.out.println("Description: " + amen.get(i).amenDescription);
			System.out.println("Enter [y/n] to confirm. Do you have this amenity?");
			String option = keyboard.nextLine();
			try {
				switch (option) { //Activate the desired functionality
				case "y":
					amen.get(i).amenBool = true;
					//confirm;
					//Queries.insertAmend(app.getconn(),listingid, amen.get(i).amendid);
					break;
				case "n":
					amen.get(i).amenBool = false;
					break;
				default:
					break;
				}
			} catch (NumberFormatException e) {
				option = "-1";
			}
		}
		
		confirmamenitiesPage(listingid, amen, app.getconn());
	}
	
	
	public void confirmamenitiesPage(int listingid, List<Amenity> amen, Connection conn){
		System.out.println("0.Confirm amenities");
		System.out.println("1.Restart");
		String option = keyboard.nextLine();
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				for (int i = 0; i < amen.size(); i++) {
					if (amen.get(i).amenBool) {
						Queries.insertAment(conn,listingid, amen.get(i).amenid);
					}
				}
				break;
			case 1:
				amenitiesPage (listingid);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}

	}
	

//	public void previousListingPage(Connection c, User u){		
//
//		System.out.println("====PREVIOUS LISTINGS====");
//
//		System.out.print("ENTER the listing number you want to view");
//		String record = keyboard.nextLine();
//
//		System.out.println("0  Submit.");
//		System.out.println("1. Go Back.");
//		String option = keyboard.nextLine();
//		
//		try {
//			int choice = Integer.parseInt(option);
//			switch (choice) { //Activate the desired functionality
//			case 0:
//				this.viewlisting(c, u, l);
//				this.previousListingPage(c,u);
//				break;
//			case 1:
//				this.hostlisting(c,u);
//				break;
//			default:
//				break;
//			}
//		} catch (NumberFormatException e) {
//			option = "-1";
//		}
//		
//	}
	
	public void currentListingPage(Connection c, User u){		

		System.out.println("====AVAILABILITY LISTINGS====");
		
		
		ArrayList<Listing> list = Queries.getListingsForUser(c, u.id);
		
		Listing chosenListing;
		
		int iterate = 1;
		for (Listing x: list) {
			System.out.println("=================================");
			System.out.println("Listing Choice ["+iterate+"]");
			System.out.println("Listing:" + x.id +" " );
			System.out.println("Listing Type:" + x.type+ " ");
			System.out.println("Longitude:" + x.longitude +" ");
			System.out.println("Latitude:" + x.latitude+ " ");
			System.out.println("Address:" + x.address+ " ");
			System.out.println("Country:" + x.country+ " ");
			System.out.println("City:" + x.city +" ");
			System.out.println("PostalCode:" + x.postal_code +" ");
			System.out.println("Host Profile ID:" + x.hostID +" ");
			System.out.println("=================================");	
			iterate ++;
		}
		
		String option;
		int choice = -1;
		boolean optionb = false;
		while(!optionb){
			if (list.isEmpty()){
				System.out.println("Sorry there are no available listings!");
				hostlisting(c,u);
				break;
			}
			
			
			System.out.println("Choose a listing by inputting the Listing Choice: [ 1 - " +list.size()+"]");
			System.out.println("Choose [ " +(list.size() + 1)+" ] to Cancel" );
			option = keyboard.nextLine();
			try {
				choice = Integer.parseInt(option);
				optionb = CheckersGeneric.range(1,list.size(),choice);
				if (choice == (list.size() + 1)) {
					hostlisting(c,u);
					break;
				}
			} catch (Exception e) {
				System.out.println("Invalid Option!");
			}
		}
		
		Listing chosenlisting =  list.get(choice-1);

		System.out.println("0  Submit.");
		System.out.println("1. Go Back.");
		String option2 = keyboard.nextLine();
		
		try {
			int choice2 = Integer.parseInt(option2);
			switch (choice2) { //Activate the desired functionality
			case 0:
				this.viewlisting(c, u, chosenlisting);
				this.currentListingPage(c,u);
				break;
			case 1:
				this.hostlisting(c,u);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
//	public void canceledListingPage(Connection c, User u){		
//
//		System.out.println("====CANCELED LISTING====");
//
//		System.out.print("ENTER the listing number you want to view");
//		String record = keyboard.nextLine();
//
//		System.out.println("0  Submit.");
//		System.out.println("1. Go Back.");
//		String option = keyboard.nextLine();
//		
//		try {
//			int choice = Integer.parseInt(option);
//			switch (choice) { //Activate the desired functionality
//			case 0:
//				this.viewlisting(c, u, l);
//				this.canceledListingPage(c,u);
//				break;
//			case 1:
//				this.hostlisting(c,u);
//				break;
//			default:
//				break;
//			}
//		} catch (NumberFormatException e) {
//			option = "-1";
//		}
//		
//	}
	
	public void hostlisting(Connection c, User u){		
		System.out.println("");
		System.out.println("=========LISTING=========");
		System.out.println("0  View Current Listings");
		System.out.println("1. View Previous Listings.");
		System.out.println("2. View Canceled Listings");
		System.out.println("3. Go Back");
		
		System.out.print("Choose one of the previous options [0 - 3]: ");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.currentListingPage(c,u);
				break;
			case 1:
				//this.previousListingPage(c,u, l);
				break;
			case 2:
				//this.canceledListingPage(c,u);
				break;
			case 3:
				this.hostPageMenu(c,user);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void viewlisting(Connection c, User u, Listing l){		
		System.out.println("");
		System.out.println("=========LISTING=========");
		System.out.println("0. View Rental History Bookings");
		System.out.println("1. Change the availible days");
		System.out.println("2. Go Back to the Main Listing Page");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				//this.bookingPage(c, u);
				break;
			case 1:
				availDays(c,u, l);
				break;
			case 2:
				hostlisting(c,u);
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	
	// TODO check this function
	
	public void availDays(Connection c, User u, Listing l){

		System.out.println("====AVAILIBILITY====");
		
		Available chosenavail =  new Available();

		ArrayList<Available> availlist = Queries.getAvailListingsDates(c,l.id);
			//System.out.println(availlist.toString());
		int availistingno = 1;
		for (Available x: availlist) {
			System.out.println("=================================");
			System.out.println("Listing Option ["+availistingno+"]");
			availistingno++;
			System.out.println("Corresponds to Listing:" + x.listingID +" " );
			System.out.println("Date:" + x.availDate+ " ");
			System.out.println("Price:" + x.price +" ");
			if (x.isBooked == 0) {
				System.out.println("Booking Status: Not booked yet!" );
			} else {
				System.out.println("Booking Status: Booked!" );
			}
			System.out.println("=================================");	
		}
			
		int optionint = 0;
			
		boolean optionb = false;
		while(!optionb){ 
			//IF THERE IS NO AVIL, THEN MAKE OPTIONB FALSE TODO
			
			if (availlist.isEmpty()){
				System.out.println("Sorry this Listing is fully booked! Nothing to be changed...");
				viewlisting(c,u,l);
				break;
			}
			
			System.out.println("Choose a listing date by inputting the listing option number: [ 1 - " +availlist.size()+"]");
			System.out.println("Choose [ " +(availlist.size() + 1)+"] to Cancel" ); //TODO
			String optionstring = keyboard.nextLine();
			try {
				optionint = Integer.parseInt(optionstring);
				if (optionint == (availlist.size() + 1)) {
					viewlisting(c,u,l);
					break;
				}
				optionb = CheckersGeneric.range(1,availlist.size(),optionint);
			} catch (Exception e) {
				//System.out.println("Invalid Option!");
			}
			
			if (optionb == false ){
				System.out.println("Invalid Option! Try Again.");
			} else {
				
				chosenavail = availlist.get(optionint-1);
			}

		}
		System.out.println("ENTER what you want to change!");
		System.out.println("0.  The Price.");
		System.out.println("1.  The Date.");
		System.out.println("2.  Delete this Availability.");
		System.out.println("3. Go Back to viewing listings.");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:			
				
				System.out.println("Enter the new price you want it to be.");
				String pricestring = keyboard.nextLine();
				double price;
				try {
					if (chosenavail.isBooked == 1){
						System.out.println("Sorry this day is unavailible, you cannot change the Price.");

					} else {
						price = Double.parseDouble(pricestring);
						Queries.updateAvailPrice(c, price, chosenavail.availDate,chosenavail.listingID);
						Queries.updateListingAvgCost(c, l.id);
						System.out.println("Price changed, going back now...");
					
					}
					this.viewlisting(c, u, l);
					break;
					
				} catch (Exception e) {
					System.out.println("Sorry invalid price, going back now...");
					this.viewlisting(c, u, l);
					break;
				}
			case 1:				
				
				boolean dateCheckfalse = true;
				
				while (dateCheckfalse){
					try {
						System.out.println("Enter the new Date you want it to be. (yyyy-MM-dd)");
						String datestring = keyboard.nextLine();
						SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
						java.util.Date date;
						date = sdf1.parse(datestring);
						java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
						
						if (chosenavail.isBooked == 1){
							System.out.println("Sorry this day is unavailible, you cannot change the date.");
						} else {
							Queries.updateAvailDate(c, chosenavail.availDate, chosenavail.listingID, sqlStartDate);
							System.out.println("Date updated!");
						}
						dateCheckfalse = false;
					} catch (Exception e1) {
						System.out.println("Incorrect day format. Please try again!");
						dateCheckfalse = true;
					}
				}
				
				this.viewlisting(c, u, l);
				break;
			case 2:
				System.out.println("Deleting this availibility.....");
				//String availstring = keyboard.nextLine();
				//int avail;
				try {
					if (chosenavail.isBooked == 1){
						System.out.println("Sorry this day is booked, you cannot change the availibility.");

					} else {
						//avail = Integer.parseInt(availstring);
						Queries.deleteAvailDate(c, chosenavail.availDate, chosenavail.listingID);
						System.out.println("Availibility changed, going back now...");
					
					}
					this.viewlisting(c, u, l);
					break;
					
				} catch (Exception e) {
					System.out.println("Sorry invalid price, going back now...");
					this.viewlisting(c, u, l);
					break;
				}
			case 3:
				this.viewlisting(c, u, l);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			this.viewlisting(c, u, l);
			return;
		}
	
	}
		

	
//	public void hostreview(Connection c, User u){
//		System.out.println("");
//		System.out.println("=========Review=========");
//		System.out.println("Write a review of: (Maximum 2000 characters)"); // renters name
//		String review = keyboard.nextLine();
//		System.out.println("0. Submit");
//		System.out.println("1. Go Back");
//		String option = keyboard.nextLine();
//
//		try {
//			int choice = Integer.parseInt(option);
//			switch (choice) { //Activate the desired functionality
//			case 0:
//				//try to insert into the db
//				// check if they actually can
//				//if so then
//				System.out.println("Commented!");
//				System.out.println("Going back now!");
//				viewbooking(c, u);
//			case 1:
//				viewbooking(c, u);
//				break;
//			default:
//				break;
//			}
//		} catch (NumberFormatException e) {
//			option = "-1";
//		}
//	}
//	public void hostrate(Connection c, User u){
//		System.out.println("");
//		System.out.println("=========Rating=========");
//		System.out.println("Provide a rating from [0-5]:"); // renters name
//		String review = keyboard.nextLine();
//		System.out.println("0. Submit");
//		System.out.println("1. Go Back");
//		String option = keyboard.nextLine();
//		
//		try {
//			int choice = Integer.parseInt(option);
//			switch (choice) { //Activate the desired functionality
//			case 0:
//				//try to insert into the db
//				// check if they actually can
//				//if so then
//				System.out.println("Rated!");
//				System.out.println("Going back now!");
//				viewbooking(c, u);
//			case 1:
//				viewbooking(c, u);
//				break;
//			default:
//				break;
//			}
//		} catch (NumberFormatException e) {
//			option = "-1";
//		}
//	}
	
//	public void viewbooking(Connection c, User u){
//		System.out.println("");
//		System.out.println("=========BOOKING=========");
//		System.out.println("0. Write a Review on the Renter's Profile");
//		System.out.println("1. Rate the Renter's Profile!");
//		System.out.println("2. Go Back");
//		System.out.println("3. Go Back to the Main Booking Page");
//		String option = keyboard.nextLine();
//		
//		try {
//			int choice = Integer.parseInt(option);
//			switch (choice) { //Activate the desired functionality
//			case 0:
//				// check if they had already reviewed the renter especially for this listing.
//				//check if they can write a review for this renter? check for whether the list is in the past and is recent!
//				hostreview(c, u);
//			case 1:
//				// check if they had already rated the renter especially for this listing.
//				//check if they can rate this renter? check for whether the list is in the past and is recent!
//				hostrate(c, u);
//			case 2:
//				//bookingPage(c, u);
//				break;
//			case 3:
//				viewlisting(c, u, l);
//				break;
//			default:
//				break;
//			}
//		} catch (NumberFormatException e) {
//			option = "-1";
//		}
//	}
//}
	
//	public void bookingPage(Connection c, User u, int listingID){		
//
//		System.out.println("====BOOKINGS====");
//		
//		//with the listingid get books
//		
//		
//		
//		try {
//			ArrayList<Available> availlist = Queries.getAvailListingsDates(c,listingID);
//			System.out.println(availlist.toString());
//			int availistingno = 1;
//			for (Available x: availlist) {
//				System.out.println("=================================");
//				System.out.println("["+availistingno+"]");
//				availistingno++;
//				System.out.println("Corresponds to Listing:" + x.listingID +" " );
//				System.out.println("Date:" + x.availDate+ " ");
//				System.out.println("Price:" + x.price +" ");
//				if (x.isBooked == 0) {
//					System.out.println("Booking Status: Not booked yet!" );
//				} else {
//					System.out.println("Booking Status: Booked!" );
//				}
//				System.out.println("=================================");	
//			}
//			
//			int optionstartchoice = 0;
//			int optionendchoice = 0;
//			Date startdate = new Date(0);
//			Date enddate = new Date(0);
//			
//			optionb = false;
//			while(!optionb){ 
//				//IF THERE IS NO AVIL, THEN MAKE OPTIONB FALSE TODO
//				
//				if (availlist.isEmpty()){
//					System.out.println("Sorry this Listing is fully booked!");
//					return false;
//				}
//				
//				System.out.println("Choose a listing date by inputting the Start Date and the End Date: [ 1 - " +availlist.size()+"]");
//				System.out.println("Choose [ " +(availlist.size() + 1)+"] to Cancel" ); //TODO
//				System.out.println("Start Date:");
//				String optionstart = keyboard.nextLine();
//				try {
//					optionstartchoice = Integer.parseInt(optionstart);
//					if (optionstartchoice == (availlist.size() + 1)) {
//						return false;
//					}
//					optionb = CheckersGeneric.range(1,availlist.size(),optionstartchoice);
//				} catch (Exception e) {
//					//System.out.println("Invalid Option!");
//				}
//				
//				System.out.println("End Date:");
//				String optionend = keyboard.nextLine();
//				try {
//					optionendchoice = Integer.parseInt(optionend);
//					optionb = CheckersGeneric.range(1,availlist.size(),optionendchoice);
//				} catch (Exception e) {
//					//System.out.println("Invalid Option!");
//				}
//				
//				if (optionb == false ){
//					System.out.println("Invalid Option! Try Again.");
//				}
//				else if (optionendchoice < optionstartchoice ){
//					System.out.println("Invalid Option! Try Again.");
//					optionb = false;
//				}
//				
//				startdate = availlist.get(optionstartchoice-1).availDate;
//				
//				enddate = availlist.get(optionendchoice-1).availDate;
//				
//
//			}
//		
//		
//		
//		
//		
//		
//		
//		
//		
//		System.out.print("ENTER the booking number you want to view");
//		String record = keyboard.nextLine();
//
//		System.out.println("0  Submit.");
//		System.out.println("1. Go Back.");
//		String option = keyboard.nextLine();
//		
//		try {
//			int choice = Integer.parseInt(option);
//			switch (choice) { //Activate the desired functionality
//			case 0:
//				this.viewbooking(c, u);
//				this.bookingPage(c, u);
//				//this.welcome();
//				break;
//			case 1:
//				this.viewlisting(c, u, l);
//				break;
//			default:
//				break;
//			}
//		} catch (NumberFormatException e) {
//			option = "-1";
//		}
//		
	}

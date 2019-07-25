package Application;

import java.sql.*;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import Checkers.CheckersGeneric;
import Listings.Amenity;
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
				this.hostlisting(c);
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
		
		if (housingchoice == 1){
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
					confirmamenitiesPage(listingid, amen, app.getconn());
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
	

	public void previousListingPage(Connection c){		

		System.out.println("====PREVIOUS LISTINGS====");

		System.out.print("ENTER the listing number you want to view");
		String record = keyboard.nextLine();

		System.out.println("0  Submit.");
		System.out.println("1. Go Back.");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.viewlisting(c);
				this.previousListingPage(c);
				break;
			case 1:
				this.hostlisting(c);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void currentListingPage(Connection c){		

		System.out.println("====CURRENT LISTINGS====");

		System.out.print("ENTER the listing number you want to view");
		String record = keyboard.nextLine();

		System.out.println("0  Submit.");
		System.out.println("1. Go Back.");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.viewlisting(c);
				this.currentListingPage(c);
				break;
			case 1:
				this.hostlisting(c);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void canceledListingPage(Connection c){		

		System.out.println("====CANCELED LISTING====");

		System.out.print("ENTER the listing number you want to view");
		String record = keyboard.nextLine();

		System.out.println("0  Submit.");
		System.out.println("1. Go Back.");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.viewlisting(c);
				this.canceledListingPage(c);
				break;
			case 1:
				this.hostlisting(c);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void hostlisting(Connection c){		
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
				this.currentListingPage(c);
				break;
			case 1:
				this.previousListingPage(c);
				break;
			case 2:
				this.canceledListingPage(c);
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
	
	public void viewlisting(Connection c){		
		System.out.println("");
		System.out.println("=========LISTING=========");
		System.out.println("0. View History");
		System.out.println("1. Go Back to the Main Listing Page");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.bookingPage(c);
				break;
			case 1:
				hostlisting(c);
			case 2:
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	
	public void bookingPage(Connection c){		

		System.out.println("====BOOKINGS====");

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
				this.bookingPage(c);
				//this.welcome();
				break;
			case 1:
				this.viewlisting(c);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void hostreview(Connection c){
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
	public void hostrate(Connection c){
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
		System.out.println("0. Write a Review on the Renter's Profile");
		System.out.println("1. Rate the Renter's Profile!");
		System.out.println("2. Go Back");
		System.out.println("3. Go Back to the Main Booking Page");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				// check if they had already reviewed the renter especially for this listing.
				//check if they can write a review for this renter? check for whether the list is in the past and is recent!
				hostreview(c);
			case 1:
				// check if they had already rated the renter especially for this listing.
				//check if they can rate this renter? check for whether the list is in the past and is recent!
				hostrate(c);
			case 2:
				bookingPage(c);
				break;
			case 3:
				viewlisting(c);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
}

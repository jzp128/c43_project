
package Application;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import Checkers.CheckersGeneric;
import HostToolkit.AmenityToolKit;
import HostToolkit.HostToolKitAmenitiesPage;
import HostToolkit.HostToolKitPage;
import Listings.Amenity;
import Listings.Available;
import Listings.Booking;
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
		System.out.println("3. View all the Renter Reviews I Made");
		System.out.println("4. View all the Reviews Renters Made about me");
		System.out.println("5. Log Out");
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
				//super.deleteAccount(c,user);
				break;
			case 4:
				//super.deleteAccount(c,user);
				break;
			case 5:
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
				amenitiesPage(c, id);
				
				availibilityAndPrice(c, list);

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
	
	public List<Amenity> amenitiesPage(Connection c, int listingid){
		App app = App.getAppInstance();
		System.out.println("Amenities!");
		//System.out.println(app.getconn());
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
					amen.get(i).amenBool = false;
					break;
				}
			} catch (NumberFormatException e) {
				option = "-1";
			}
		}
		
		System.out.println("We can provide suggestions for additional amenities to add! Type [YES] for suggestions by our HostToolKit!");
		System.out.println("Press anyother key to continue!");
		String tooloption = keyboard.nextLine();
		if (tooloption.equalsIgnoreCase("yes")){
			HostToolKitAmenitiesPage temphostpage = new HostToolKitAmenitiesPage(c,amen);
		}
		
		confirmamenitiesPage(c, listingid, amen, app.getconn());
		return amen;
	}
	
	
	public void confirmamenitiesPage(Connection c, int listingid, List<Amenity> amen, Connection conn){
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
				amenitiesPage (c,listingid);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}

	}
	
	
	public void currentListingPage(Connection c, User u){		

		System.out.println("====AVAILABILITY LISTINGS====");
		
		
		ArrayList<Listing> list = Queries.getListingsForUser(c, u.id);
		
		Listing chosenlisting;
		
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
		
		chosenlisting =  list.get(choice-1);

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
	
	//TODO
	public void hostlisting(Connection c, User u){		
		System.out.println("");
		System.out.println("=========LISTING=========");
		System.out.println("0  View Listings");
		System.out.println("1. Go Back");
		
		System.out.print("Choose one of the previous options [0 - 1]: ");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.currentListingPage(c,u);
				break;
			case 1:
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
		System.out.println("2. Delete this listing");
		System.out.println("3. Go Back to the Main Listing Page");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.bookingPage(c, u, l);
				break;
			case 1:
				availDays(c,u, l);
				break;
			case 2:
				deletelisting(c,l);
				hostlisting(c,u);
				break;
			case 3:
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
				
				System.out.println("We can provide suggestions for the new price! Type YES for suggestions by our HostToolKit!");
				System.out.println("Press anyother key to continue!");
				String tooloption = keyboard.nextLine();
				if (tooloption.equalsIgnoreCase("yes")){
					HostToolKitPage host = new HostToolKitPage(c,chosenavail.availDate.toString(), chosenavail.availDate.toString(),l.city,l.country,l.type);
				}
				
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
	

	
	public void bookingPage(Connection c, User u, Listing l){		

		System.out.println("====ALL PAST AND CURRENT BOOKINGS====");
		
		Queries.updateHistoryBookingsforHost(c,u.id);
		ArrayList<Booking> list = Queries.getHistoryandCurrentBookingsforHost(c,u.id);
		
		if (list.isEmpty()){
			System.out.println("You have no bookings here!");
			viewlisting(c,u,l);
			return;
		}

		printBookings(list);

		System.out.println("ENTER the booking number you want to change.");
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
				this.bookingOption(c, u, list.get(choicerecord-1), l); //TODO
				this.viewlisting(c,u, l);//TODO
				//this.welcome();
				break;
			case 1:
				this.viewlisting(c,u,l);//TODO
				break;//TODO
			default:
				break;
			}
		} catch (Exception e) {//TODO
			this.viewlisting(c,u, l);//TODO
		}
		
		
	}
	
	public void bookingOption(Connection c, User u, Booking b, Listing l){		

		System.out.println("====BOOKING====");
		System.out.println("ENTER what you want to change!");
		System.out.println("0.  CANCEL this Booking.");
		System.out.println("1.  RATE the Renter.");
		System.out.println("2. Go Back to viewing listings.");
		
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:		
				System.out.println("Confirm to cancel booking:");
				System.out.println("0.  Confirm.");
				System.out.println("1.  Go Back.");
				String cancelchoicestring = keyboard.nextLine();
				int cancelchoice;
				try {
					cancelchoice = Integer.parseInt(cancelchoicestring);
					switch(cancelchoice){
					case 0:
						Queries.cancelBooking(c, b.bookingID);
						Queries.reupdateListingAvailibility(c,b.listingID,b.fromDate,b.toDate); //TODO check this!
						Queries.updateListingAvgCost(c, b.listingID);

						System.out.println("Canceled~");
						System.out.println("Note: if this listing was already canceled, it will stay canceled.");
						break;
					case 1:
						break;
					}
					bookingOption(c,u,b,l); 
					break;
					
				} catch (Exception e) {
					bookingOption(c,u,b,l); 
					break;
				}
			case 1:
				
				java.util.Date today = CheckersGeneric.currentDate();
				int recent = CheckersGeneric.betweenDays(b.toDate,today);
				
				if (b.isCanceled == 1){
					System.out.println("Sorry, our records show that this booking is canceled,");
					System.out.println("You cannot rate/review a canceled booking.");
					this.viewlisting(c, u, l);
					break;
				} else if (CheckersGeneric.range(0, 30, recent)){
					System.out.println("You can only rate/review a recent booking after 30 days.");
					this.viewlisting(c, u, l);
					break;
				}
				
				hostreviewrentert(c,u,b,l);
				this.viewlisting(c, u, l);
				break;
				
				
			case 2:				
				this.viewlisting(c, u, l);
				break;
			default:
				this.viewlisting(c, u, l);
				break;
			}
		} catch (Exception e) {
			this.viewlisting(c, u, l);
			return;
		}
		
		
	}
	
	
	public void hostreviewrentert(Connection c, User u, Booking b, Listing l){
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
				Queries.writeRenterReview(c,u,b,review,choicerating,"r");
				System.out.println("Rated!");
				System.out.println("Commented!");
				System.out.println("Going back now!");
				
				//=============DB CALL=====================
				
				
				bookingOption(c,u,b,l);
			case 1:
				bookingOption(c,u,b,l);
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	
	public void availibilityAndPrice(Connection c, Listing l){
		
		int optionstartchoice = 0;
		int optionendchoice = 0;
		
		SimpleDateFormat sdf = new SimpleDateFormat();
		java.util.Date startdate;
		java.util.Date enddate;
		int dayCounter = 0;
		double price = 0;
		
		String optionstart = "";
		String optionend = ""; 
		
					
			boolean dateCheckfalse = true;
			
			while (dateCheckfalse){
				try {
					
					
					System.out.println("Choose a date range of availibility by inputting the Start Date and the End Date:");
					System.out.println("Start Date (yyyy-MM-dd):");
					optionstart = keyboard.nextLine();
					
					System.out.println("End Date (yyyy-MM-dd):");
					optionend = keyboard.nextLine();
					
					sdf = new SimpleDateFormat("yyyy-MM-dd");

					startdate = sdf.parse(optionstart);
					enddate = sdf.parse(optionend);
					
					dayCounter = CheckersGeneric.betweenDays(startdate, enddate);
					
					if (dayCounter < 0 ){
						System.out.println("Incorrect day format or order. Please try again!");
						dateCheckfalse = true;

					} else {
					
//					java.sql.Date sqlStartDate = new java.sql.Date(startdate.getTime());  
//					java.sql.Date sqlendDate = new java.sql.Date(enddate.getTime());  
						
						
						System.out.println("We can provide suggestions for the price! Type YES for suggestions by our HostToolKit!");
						System.out.println("Press anyother key to continue!");
						String tooloption = keyboard.nextLine();
						if (tooloption.equalsIgnoreCase("yes")){
							HostToolKitPage host = new HostToolKitPage(c,optionstart, optionend,l.city,l.country,l.type);
						}
						
						
						boolean priceFalse = true;
						
						while (priceFalse) {
							System.out.println("Please note the the price you enter will apply to all the selected dates.");
							System.out.println("You can individually adjust the price per day after the listing is made. ");
							System.out.println("Enter a price:");
							String pricestring = keyboard.nextLine();
							
							if (CheckersGeneric.isNumeric(pricestring)){
								price = Double.parseDouble(pricestring);
								priceFalse = false;
							} else {
								System.out.println("Try Again!");
								priceFalse = true;
							}

						}
					
						dateCheckfalse = false;
					}
				} catch (ParseException e1) {
					System.out.println("Incorrect day format or order. Please try again!");
					dateCheckfalse = true;
					//e1.printStackTrace();
				}
			}
			
			Calendar cal = Calendar.getInstance();
			String dt;
			java.util.Date newday;
			
			String datIterator=optionstart;
			
			for (int i=0; i < dayCounter+1;i++){
				try {
					// (Connection c, Date date, double price, int listingid, int isbooked) 
					Queries.insertSingleAvailability(c,datIterator,price,l.id,0);
					
					cal.setTime(sdf.parse(datIterator));
					cal.add(Calendar.DATE, 1);  // number of days to add
					dt = sdf.format(cal.getTime());  // dt is now the new date

					datIterator = dt;
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	
	public void deletelisting(Connection c,Listing l){
		System.out.println("Note: this action cannot be undone!");
		System.out.println("Deletion of a listing can only happen if there are no existing bookings or reviews");
		
		if (Queries.checkReviewsExistForHost(c, l.id) > 0 ){
			System.out.println("Looks like there are reviews for this listing! Delete cannot be done!");
		} else if (Queries.checkBookingsExistForHost(c, l.id) > 0){
			System.out.println("Looks like there are bookings for this listing! Delete cannot be done!");
		} else if (Queries.checkAvailBookingsExistForHost(c, l.id) > 0) {
			System.out.println("Looks like there are bookings for this listing! Delete cannot be done!");
		} else {
			System.out.print("=========" + l.id);
			Queries.deleteAmenitiesListExistForListing(c,l.id);// delete all amenitiies from list
			Queries.deleteAvailibleExistForListing(c,l.id);//delete days avail
			Queries.deleteListingExistForListing(c,l.id);//delete listing

		}
		
	}

		
	}


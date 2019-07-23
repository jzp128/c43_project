
public class HostPage extends UserPage{
	public void hostPageMenu(){
		
		System.out.println("**************************");
		System.out.println("******ACCESS GRANTED******");
		System.out.println("**************************");
		System.out.println("");
		System.out.println("=====HOST'S HOME PAGE=====");
		System.out.println("0. View my Listings.");
		System.out.println("1. Delete My Account.");
		System.out.println("2. Log Out");
		System.out.print("Choose one of the previous options [0 - 2]: ");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.hostlisting();
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

	public void previousListingPage(){		

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
				this.viewlisting();
				this.previousListingPage();
				break;
			case 1:
				this.hostlisting();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void currentListingPage(){		

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
				this.viewlisting();
				this.currentListingPage();
				break;
			case 1:
				this.hostlisting();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void canceledListingPage(){		

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
				this.viewlisting();
				this.canceledListingPage();
				break;
			case 1:
				this.hostlisting();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void hostlisting(){		
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
				this.currentListingPage();
				break;
			case 1:
				this.previousListingPage();
				break;
			case 2:
				this.canceledListingPage();
				break;
			case 3:
				this.hostPageMenu();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void viewlisting(){		
		System.out.println("");
		System.out.println("=========LISTING=========");
		System.out.println("0. View History");
		System.out.println("1. Go Back to the Main Listing Page");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.bookingPage();
				break;
			case 1:
				hostlisting();
			case 2:
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	
	public void bookingPage(){		

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
				this.viewbooking();
				this.bookingPage();
				//this.welcome();
				break;
			case 1:
				this.viewlisting();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void hostreview(){
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
			case 2:
				viewlisting();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	public void hostrate(){
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
				hostreview();
			case 1:
				// check if they had already rated the renter especially for this listing.
				//check if they can rate this renter? check for whether the list is in the past and is recent!
				hostrate();
			case 2:
				bookingPage();
				break;
			case 3:
				viewlisting();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
}

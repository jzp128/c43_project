
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

		System.out.println("====FUTURE LISTINGS====");

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
		System.out.println("1. View History");
		System.out.println("1. Go Back");
		System.out.println("2. Go Back to the Main Listing Page");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				break;
			case 1:
				this.viewbooking();
				break;
			case 2:
				hostlisting();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
	}
	
	public void BookingPage(){		

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
				this.BookingPage();
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
	
	public void viewbooking(){		
		System.out.println("");
		System.out.println("=========BOOKING=========");
		System.out.println("0. Write a Review");
		System.out.println("1. Go Back");
		System.out.println("2. Go Back to the Main Booking Page");
		String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				
			case 1:
				//this.futureBookingPage();
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
}

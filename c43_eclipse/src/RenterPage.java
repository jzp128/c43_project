import java.util.Scanner;

public class RenterPage extends UserPage{
	
	Scanner keyboard = new Scanner (System.in);
	
	public void renterPayment(){
		System.out.println("Payment Information");
		System.out.print("Credit Card Number:");
		String cc = keyboard.nextLine();
		System.out.print("Name on the Credit Card:");
		String ccname = keyboard.nextLine();
		System.out.print("Card Security Code");
		String ccsecuritr = keyboard.nextLine();
	}
	
	
	
	public void renterPageMenu(){		
		System.out.println("**************************");
		System.out.println("******ACCESS GRANTED******");
		System.out.println("**************************");
		System.out.println("");
		System.out.println("====RENTER'S HOME PAGE====");
		System.out.println("0  View my Listings.");
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
		System.out.println("=========HISTORY=========");
		System.out.println("0  View Future Bookings");
		System.out.println("1. View Past Bookings.");
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
				this.renterbooking();
				break;
			case 3:
				this.canceledBookingPage();
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
		System.out.println("0. Go Back");
				String option = keyboard.nextLine();
		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.futureBookingPage();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}

}

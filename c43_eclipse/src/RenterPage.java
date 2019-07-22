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
		System.out.println("0. Rent Home Page.");
		System.out.println("1. Host Home Page.");
		System.out.println("2. Log Out");
		System.out.print("Choose one of the previous options [0 - 2]: ");
		String option = keyboard.nextLine();
	}

}

import java.util.Scanner;

public class UserPage {
	
	Scanner keyboard = new Scanner (System.in);
	App application = new App();

	
	public void logout(){
		System.out.println("LOGOUT SUCCESSFUL");
		application.welcome();
	}
	
	
	public void deleteAccount(){
		System.out.println("ARE YOU SURE YOU WANT TO DELETE YOUR ACCOUNT?");
		System.out.println("0  NO.");
		System.out.println("1. YES ");
		String option = keyboard.nextLine();

		
		try {
			int choice = Integer.parseInt(option);
			switch (choice) { //Activate the desired functionality
			case 0:
				this.gotoUserHome();
				break;
			case 1:
				System.out.println("ACCOUNT DELETED.");
				application.welcome();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}


	public void gotoUserHome() {
		//go the user's home page
		//query to check if user is renter or host
		//if user is renter, go to renter page
		// else go to host page
	}
}

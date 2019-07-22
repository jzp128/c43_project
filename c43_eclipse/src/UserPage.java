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
		System.out.println("ACCOUNT DELETED.");
		application.welcome();
		
	}
}

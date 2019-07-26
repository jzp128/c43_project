package Application;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import Checkers.CheckersGeneric;
import Listings.Listing;
import Listings.Available;
import Server.Queries;
import Users.User;

public class UserPage {
	
	Scanner keyboard = new Scanner (System.in);
	Queries queries = new Queries ();

	
	public void logout(){
		System.out.println("LOGOUT SUCCESSFUL");
		App.application.welcome();
	}
	
	
	public void deleteAccount(Connection c, User u){
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
				
				queries.deleteUser(c, u.loginname);
				queries.deleteRenter(c, u.id );
				
				App.application.welcome();
				break;
			default:
				break;
			}
		} catch (NumberFormatException e) {
			option = "-1";
		}
		
	}
	
	public void listAvaillistings (Connection c) {
		ArrayList<Listing> list = queries.getAllListings(c);
		
		for (Listing x: list) {
			System.out.println("=================================");

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
		}
		
		String option;
		int choice = -1;
		boolean optionb = false;
		while(!optionb){
			System.out.println("Choose a listing by inputting the ID: [ 1 - " +list.size()+"]");
			option = keyboard.nextLine();
			try {
				choice = Integer.parseInt(option);
				optionb = CheckersGeneric.range(0,list.size(),choice);
			} catch (Exception e) {
				System.out.println("Invalid Option!");
			}
		}
		
		try {
			System.out.println("JACQUELINE");
			System.out.println(choice);
			ArrayList<Available> availlist = queries.getAvailListingsDates(c,choice);
			System.out.println(availlist.toString());
			int availistingno = 1;
			for (Available x: availlist) {
				System.out.println("=================================");
				System.out.println("["+availistingno+"]");
				availistingno++;
				System.out.println("Corresponds to Listing:" + x.listingID +" " );
				System.out.println("Date:" + x.availDate+ " ");
				System.out.println("Price:" + x.price +" ");
				System.out.println("=================================");	
			}
			
			int optionstartchoice = 0;
			int optionendchoice = 0;
			
			optionb = false;
			while(!optionb){
				System.out.println("Choose a listing by inputting the Start Date and the End Date: [ 1 - " +availlist.size()+"]");
				System.out.println("Start Date:");
				String optionstart = keyboard.nextLine();
				try {
					optionstartchoice = Integer.parseInt(optionstart);
					optionb = CheckersGeneric.range(0,availlist.size(),optionstartchoice);
				} catch (Exception e) {
					//System.out.println("Invalid Option!");
				}
				
				System.out.println("End Date:");
				String optionend = keyboard.nextLine();
				try {
					optionendchoice = Integer.parseInt(optionend);
					optionb = CheckersGeneric.range(0,availlist.size(),optionendchoice);
				} catch (Exception e) {
					//System.out.println("Invalid Option!");
				}
				
				if (optionb == false ){
					System.out.println("Invalid Option! Try Again.");
				}
				else if (optionendchoice < optionstartchoice ){
					System.out.println("Invalid Option! Try Again.");
					optionb = false;
				}
			}

			} catch (Exception e) {
			System.out.println("Try Again!");
			listAvaillistings(c);
		}
		
		
		
	}
	
	
	


	public void gotoUserHome() {
		//go the user's home page
		//query to check if user is renter or host
		//if user is renter, go to renter page
		// else go to host page
	}
}

package Application;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

import Checkers.CheckersGeneric;
import Listings.Listing;
import Listings.Available;
import Server.ListingQueries;
import Server.Queries;
import Users.User;

public class UserPage {

    Scanner keyboard = new Scanner(System.in);
    Queries queries = new Queries();


    public void logout() {
        System.out.println("LOGOUT SUCCESSFUL");
        App.application.welcome();
    }


    public void deleteAccount(Connection c, User u) {
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

                    Queries.deleteUser(c, u.loginname);
                    Queries.deleteRenter(c, u.id);

                    App.application.welcome();
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            option = "-1";
        }

    }

    public void listAvaillistings(Connection c, User u) {
        ArrayList<Listing> list = Queries.getAllListings(c);
        Listing chosenListing;

        int iterate = 1;
        for (Listing x : list) {
            System.out.println("=================================");
            System.out.println("Listing Choice [" + iterate + "]");
            System.out.println("Listing:" + x.id + " ");
            System.out.println("Listing Type:" + x.type + " ");
            System.out.println("Longitude:" + x.longitude + " ");
            System.out.println("Latitude:" + x.latitude + " ");
            System.out.println("Address:" + x.address + " ");
            System.out.println("Country:" + x.country + " ");
            System.out.println("City:" + x.city + " ");
            System.out.println("PostalCode:" + x.postal_code + " ");
            System.out.println("Host Profile ID:" + x.hostID + " ");
            System.out.println("=================================");
            iterate++;
        }

        String option;
        int choice = -1;
        boolean optionb = false;
        while (!optionb) {
            System.out.println("Choose a listing by inputting the Listing Choice: [ 1 - " + list.size() + "]");
            option = keyboard.nextLine();
            try {
                choice = Integer.parseInt(option);
                optionb = CheckersGeneric.range(1, list.size(), choice);
            } catch (Exception e) {
                System.out.println("Invalid Option!");
            }
        }

        try {
            chosenListing = list.get(choice - 1);
            int choiceindex = chosenListing.id;
            ArrayList<Available> availlist = Queries.getAvailListingsDates(c, choiceindex);
            System.out.println(availlist.toString());
            int availistingno = 1;
            for (Available x : availlist) {
                System.out.println("=================================");
                System.out.println("[" + availistingno + "]");
                availistingno++;
                System.out.println("Corresponds to Listing:" + x.listingID + " ");
                System.out.println("Date:" + x.availDate + " ");
                System.out.println("Price:" + x.price + " ");
                System.out.println("=================================");
            }

            int optionstartchoice = 0;
            int optionendchoice = 0;
            Date startdate = new Date(0);
            Date enddate = new Date(0);

            optionb = false;
            while (!optionb) {
                System.out.println("Choose a listing date by inputting the Start Date and the End Date: [ 1 - " + availlist.size() + "]");
                System.out.println("Start Date:");
                String optionstart = keyboard.nextLine();
                try {
                    optionstartchoice = Integer.parseInt(optionstart);
                    optionb = CheckersGeneric.range(0, availlist.size(), optionstartchoice);
                } catch (Exception e) {
                    //System.out.println("Invalid Option!");
                }

                System.out.println("End Date:");
                String optionend = keyboard.nextLine();
                try {
                    optionendchoice = Integer.parseInt(optionend);
                    optionb = CheckersGeneric.range(0, availlist.size(), optionendchoice);
                } catch (Exception e) {
                    //System.out.println("Invalid Option!");
                }

                if (optionb == false) {
                    System.out.println("Invalid Option! Try Again.");
                } else if (optionendchoice < optionstartchoice) {
                    System.out.println("Invalid Option! Try Again.");
                    optionb = false;

                    startdate = availlist.get(optionstartchoice - 1).availDate;
                    enddate = availlist.get(optionendchoice - 1).availDate;
                }
            }

            renterBooking(c, chosenListing, u, startdate, enddate);


        } catch (Exception e) {
            System.out.println("Try Again!");
            listAvaillistings(c, u);
        }


    }


    public void renterBooking(Connection c, Listing l, User u, Date from, Date to) {

        Queries.updateListingAvailibility(c, l.id, from, to);

        Queries.insertSingleBooking(c, l.hostID, u.id, l.id, from, to);

    }

    public void searchBookings(Connection c, User u) {
        String choice = "";
        ArrayList<String> filters = new ArrayList<>();

        boolean locationFilter = false;
        Double longitude = 0.00;
        Double latitude = 0.00;
        Double range = 0.00;

        System.out.print("Search by LOCATION? (Y/N):");
        choice = keyboard.nextLine();
        String in = "";
        if (choice.toLowerCase().equals("y")) {
            locationFilter = true;
            System.out.print("Enter Latitude:");
            in = keyboard.nextLine();
            // ADD CHECKING HERE
            latitude = Double.parseDouble(in);
            System.out.print("Enter Longitude:");
            in = keyboard.nextLine();
            longitude = Double.parseDouble(in);
            System.out.print("Enter range (will default to 5KM if nothing is entered):");
            in = keyboard.nextLine();
            range = 0.00;
            if (in == "") {
                range = 5.00;
            } else {
                range = Double.parseDouble(in);
            }
        }

        System.out.print("Filter by ADDRESS? (Y/N):");
        choice = keyboard.nextLine();
        if (choice.toLowerCase().equals("y")) {
            System.out.print("Enter Address:");
            in = keyboard.nextLine();
            String address = in;
            filters.add(ListingQueries.filterByAddress(address));
        }

        System.out.print("Filter by POSTAL CODE? (Y/N):");
        choice = keyboard.nextLine();
        if (choice.toLowerCase().equals("y")) {
            System.out.print("Enter Postal Code:");
            in = keyboard.nextLine();
            String postCode = in;
            filters.add(ListingQueries.filterByPostalCode(postCode));
        }

        System.out.print("Filter by Date Range? (Y/N):");
        choice = keyboard.nextLine();
        if (choice.toLowerCase().equals("y")) {
            System.out.print("Enter Starting Date (yyyy-MM-dd):");
            in = keyboard.nextLine();
            String from = in;
            System.out.print("Enter Ending Date (yyyy-MM-dd):");
            in = keyboard.nextLine();
            String to = in;
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                java.util.Date fromDate = sf.parse(from);
                java.util.Date toDate = sf.parse(to);
                filters.add(ListingQueries.filterByDateRange(fromDate, toDate));
            } catch (java.text.ParseException e) {

            }
        }

        System.out.print("Filter by Price Range? (Y/N):");
        choice = keyboard.nextLine();
        if (choice.toLowerCase().equals("y")) {
            System.out.print("Enter Minimum price:");
            in = keyboard.nextLine();
            Double low = Double.parseDouble(in);
            System.out.print("Enter Maximum price:");
            in = keyboard.nextLine();
            Double high = Double.parseDouble(in);

            filters.add(ListingQueries.filterBypriceRange(low, high));
        }

		System.out.print("Filter by Amenities? (Y/N):");
        //TODO FIX THIS
		choice = keyboard.nextLine();
		if (choice.toLowerCase().equals("y")) {
			System.out.print("Enter Minimum price:");
			in = keyboard.nextLine();
			Double low = Double.parseDouble(in);
			System.out.print("Enter Maximum price:");
			in = keyboard.nextLine();
			Double high = Double.parseDouble(in);

			filters.add(ListingQueries.filterBypriceRange(low, high));
		}
		int priceSort = 0;
		System.out.print("Would you like to sort by average price? (Y/N):");
		//TODO FIX THIS
		choice = keyboard.nextLine();
		if (choice.toLowerCase().equals("y")) {
			System.out.print("Enter 0 for DESCENDING, 1 for ASCENDING:");
			in = keyboard.nextLine();
			Double low = Double.parseDouble(in);
			System.out.print("Enter Maximum price:");
			in = keyboard.nextLine();
			Double high = Double.parseDouble(in);

			filters.add(ListingQueries.filterBypriceRange(low, high));
		}

		String finalQ = ListingQueries.finalListingQuery(filters, priceSort);

		ArrayList<Listing> result = ListingQueries.runFilters(c, finalQ);

		if(locationFilter){
			result = ListingQueries.searchByLocation(result, latitude, longitude, range, priceSort);
		}

		//TODO HOW DO I PRINT THIS??



    }


    public void gotoUserHome() {
        //go the user's home page
        //query to check if user is renter or host
        //if user is renter, go to renter page
        // else go to host page
    }
}

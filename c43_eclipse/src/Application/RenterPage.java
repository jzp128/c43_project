package Application;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import Checkers.CheckersGeneric;
import Listings.Booking;
import Listings.Listing;
import Reviews.ListingReview;
import Reviews.RenterReview;
import Reviews.ReviewPageQueries;
import Server.Queries;
import Users.User;

public class RenterPage extends UserPage {

    User user = new User();

    Scanner keyboard = new Scanner(System.in);

    public void renterPageMenu(Connection c, User u) {
        user = u;
        System.out.println("**************************");
        System.out.println("******ACCESS GRANTED******");
        System.out.println("**************************");
        System.out.println("");
        System.out.println("====RENTER'S HOME PAGE====");
        System.out.println("0  View my Bookings.");
        System.out.println("1. Search for a Listing.");
        System.out.println("2. Look at Availible Listings.");
        System.out.println("3. Delete My Account.");
        System.out.println("4. View all the Reviews I Made About Hosts & Listings.");
        System.out.println("5. View all the Reviews about Me");
        System.out.println("6. Log Out");
        System.out.println("Choose one of the previous options [0 - 2]: ");
        String option = keyboard.nextLine();

        try {
            int choice = Integer.parseInt(option);
            switch (choice) { //Activate the desired functionality
                case 0:
                    this.renterbooking(c, u);
                    break;
                case 1:
                    this.searchBookings(c, u); //TODO
                    renterPageMenu(c, u);
                    break;
                case 2://TODO
                    ArrayList<Listing> list = Queries.getAllListings(c);
                    super.listAvaillistings(c, u, list);
                    renterPageMenu(c, u);
                    break;
                case 3:
                    super.deleteAccount(c, u);
                    break;
                case 4:
    				ArrayList<ListingReview> reviewlist = ReviewPageQueries.getReviewsAboutHostsMadeByARenter(c, u.id);
    				if (reviewlist.isEmpty()){
    					System.out.println("There are no reviews about you yet!");
    				} else {
    					ReviewPageQueries.printListingReviews(reviewlist);
    				}
    				renterPageMenu(c,u);
                	break;
                case 5:
    				ArrayList<RenterReview> review = ReviewPageQueries.getReviewsAboutRenters(c, u.id);
    				if (review.isEmpty()){
    					System.out.println("There are no reviews about you yet!");
    				} else {
    					ReviewPageQueries.printRenterReviews(review);
    				}
    				renterPageMenu(c,u);
    				break;
                case 6:
                    super.logout();
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            option = "-1";
        }

    }

    public void historyBookingPage(Connection c, User u) {

        System.out.println("====PREVIOUS BOOKINGS====");

        Queries.updateHistoryBookingsforRenter(c, u.id);
        ArrayList<Booking> list = Queries.getHistoryBookingsforRenter(c, u.id);
        printBookings(list);

        if (list.isEmpty()) {
            System.out.println("You have no bookings here!");
            renterbooking(c, u);
            return; //TODO
        }

        //=============enter single booking page=======================

        System.out.println("ENTER the booking number you want to view.");
        System.out.println("(Or just press enter to continue.)");

        String record = keyboard.nextLine();

        System.out.println("0  Submit.");
        System.out.println("1. Go Back.");
        String option = keyboard.nextLine();

        try {
            int choice = Integer.parseInt(option);
            int choicerecord = Integer.parseInt(record);

            if (!CheckersGeneric.range(1, list.size(), choicerecord)) {
                System.out.println("Your answer is invalid!");
                return; //TODO
            }

            switch (choice) { //Activate the desired functionality
                case 0:
                    //this.welcome();
                    this.viewbooking(c, u, list.get(choicerecord - 1)); //feed the booking u want to view
                    this.historyBookingPage(c, u);
                    break;
                case 1:
                    this.renterbooking(c, u);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            this.renterbooking(c, u);
        }
        //=============enter single booking page=======================

    }

    public void futureBookingPage(Connection c, User u) {

        System.out.println("====FUTURE BOOKINGS====");

        Queries.updateHistoryBookingsforRenter(c, u.id);
        ArrayList<Booking> list = Queries.getCurrentBookingsforRenter(c, u.id);

        if (list.isEmpty()) {
            System.out.println("You have no bookings here!");
            renterbooking(c, u);
            return;
        }
        //System.out.println(list);

        printBookings(list);

        System.out.println("ENTER the booking number you want to view.");
        System.out.println("(Or just press enter to continue)");

        String record = keyboard.nextLine();

        System.out.println("0  Submit.");
        System.out.println("1. Go Back.");
        String option = keyboard.nextLine();

        try {
            int choice = Integer.parseInt(option);
            int choicerecord = Integer.parseInt(record);

            if (!CheckersGeneric.range(1, list.size(), choicerecord)) {
                System.out.println("Your answer is invalid!");
                return; //TODO
            }

            switch (choice) { //Activate the desired functionality
                case 0:
                    this.viewbooking(c, u, list.get(choicerecord - 1));
                    this.futureBookingPage(c, u);
                    //this.welcome();
                    break;
                case 1:
                    this.renterbooking(c, u);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            this.renterbooking(c, u);
        }

    }

    public void canceledBookingPage(Connection c, User u) {

        System.out.println("====CANCELED BOOKINGS====");

        Queries.updateHistoryBookingsforRenter(c, u.id);
        ArrayList<Booking> list = Queries.getCanceledBookingsforRenter(c, u.id);
        if (list.isEmpty()) {
            System.out.println("You have no bookings here!");
            renterbooking(c, u);
            return;
        }
        printBookings(list);

        System.out.println("ENTER the booking number you want to view.");
        System.out.println("(Or just press enter to continue)");
        String record = keyboard.nextLine();

        System.out.println("0  Submit.");
        System.out.println("1. Go Back.");
        String option = keyboard.nextLine();

        try {
            int choice = Integer.parseInt(option);
            int choicerecord = Integer.parseInt(record);

            if (!CheckersGeneric.range(1, list.size(), choicerecord)) {
                System.out.println("Your answer is invalid!");
                return; //TODO
            }

            switch (choice) { //Activate the desired functionality
                case 0:
                    this.viewbooking(c, u, list.get(choicerecord - 1));
                    this.canceledBookingPage(c, u);
                    //this.welcome();
                    break;
                case 1:
                    this.renterbooking(c, u);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            this.renterbooking(c, u);
        }

    }

    public void renterbooking(Connection c, User u) {
        System.out.println("");
        System.out.println("=========BOOKINGS=========");
        System.out.println("0  View Future Bookings");
        System.out.println("1. View Previous Bookings.");
        System.out.println("2. View Canceled Bookings");
        System.out.println("3. Go Back");

        System.out.println("Choose one of the previous options [0 - 3]: ");
        String option = keyboard.nextLine();

        try {
            int choice = Integer.parseInt(option);
            switch (choice) { //Activate the desired functionality
                case 0:
                    this.futureBookingPage(c, u);
                    break;
                case 1:
                    this.historyBookingPage(c, u);
                    break;
                case 2:
                    this.canceledBookingPage(c, u);
                    break;
                case 3:
                    this.renterPageMenu(c, u);
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            option = "-1";
        }

    }


	public void renterreviewlisting(Connection c, User u, Booking b) {
        System.out.println("");
        System.out.println("=========Review=========");
        System.out.println("Write a review of the listing: (Maximum 2000 characters)"); // listing name
        String listingReview = keyboard.nextLine();
		System.out.println("Write a review of the host: (Maximum 2000 characters)"); // listing name
		String hostReview = keyboard.nextLine();
        System.out.println("0. Submit");
        System.out.println("1. Go Back");
        String option = keyboard.nextLine();

        try {
            int choice = Integer.parseInt(option);
            switch (choice) { //Activate the desired functionality TODO
                case 0:
                    //try to insert into the db TODO
                    // check if they actually can
                    //if so then
                    //=============RATING=====================

                    int listingRatingI = 0;
					int hostRatingI = 0;
                    boolean ratingWrong = true;
                    while (ratingWrong) {

                        System.out.println("");
                        System.out.println("=========Rating=========");
                        System.out.println("Provide a rating from [0-5] for the listing:");
                        String listingRating = keyboard.nextLine();

                        try {
                            listingRatingI = Integer.parseInt(listingRating);

                            if (CheckersGeneric.range(0, 5, listingRatingI)) {
                                ratingWrong = false;
                            } else {
                                ratingWrong = true;
                            }

                        } catch (Exception e) {
                            System.out.println("Please Try Again!");
                            ratingWrong = true;
                        }

						System.out.println("Provide a rating from [0-5] for the host:");
						String hostRating = keyboard.nextLine();

						try {
							hostRatingI = Integer.parseInt(hostRating);

							if (CheckersGeneric.range(0, 5, hostRatingI)) {
								ratingWrong = false;
							} else {
								ratingWrong = true;
							}

						} catch (Exception e) {
							System.out.println("Please Try Again!");
							ratingWrong = true;
						}

                    }
                    //=============RATING=====================

                    //=============DB CALL=====================
                    Queries.writeListingReview(c, u, b, listingReview, listingRatingI, hostReview, hostRatingI);
                    System.out.println("Rated!");
                    System.out.println("Commented!");
                    System.out.println("Going back now!");

                    //=============DB CALL=====================

                    viewbooking(c, u, b);
                case 1:
                    viewbooking(c, u, b);
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            option = "-1";
        }
    }


    public void viewbooking(Connection c, User u, Booking b) {
        System.out.println("");
        System.out.println("=========BOOKING=========");
        System.out.println("0. Write a Review & Rating on the Listing & Host");
        System.out.println("1. Cancel this Booking (can only be done on future bookings)!");
        System.out.println("2. Go Back to the Main Booking Page");
        String option = keyboard.nextLine();


        // check if the booking was within 30 days

        java.util.Date today = CheckersGeneric.currentDate();

        int recent = CheckersGeneric.betweenDays(b.toDate, today);

        try {
            int choice = Integer.parseInt(option);
            switch (choice) { //Activate the desired functionality
                case 0:
                    if (b.isCanceled == 1) {
                        System.out.println("Sorry, our records show that this booking is canceled,");
                        System.out.println("You cannot rate/review a canceled booking.");
                        renterbooking(c, u);
                        break;
                    } else if (CheckersGeneric.range(0, 30, recent)) {
                        System.out.println("You can only rate/review a recent booking after 30 days.");
                        renterbooking(c, u);
                        break;
                    }
                    renterreviewlisting(c, u, b);
                    break;
                case 1:
                    if ((b.isHistory == 1) && (b.isCanceled == 1)) {
                        System.out.println("Sorry, our records show that this booking is canceled/historic");
                        System.out.println("You cannot cancel this booking....");
                    } else {
                        Queries.cancelBooking(c, b.bookingID);
                        //update the availibility dates of the listing
                        //Connection c, int listingID, Date from, Date to
                        Queries.reupdateListingAvailibility(c, b.listingID, b.fromDate, b.toDate); //TODO check this!
                        Queries.updateListingAvgCost(c, b.listingID);

                        System.out.println("Canceled Booking!");

                    }
                    renterbooking(c, u);
                    break;
                case 2:
                    renterbooking(c, u);
                    break;
                default:
                    break;
            }
        } catch (NumberFormatException e) {
            option = "-1";
        }
    }


}

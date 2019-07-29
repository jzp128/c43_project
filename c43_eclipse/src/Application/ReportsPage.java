package Application;

import Server.Helpers;
import Server.ReportQueries;
import TableGen.CommandLineTable;

import java.sql.Connection;
import java.util.Date;
import java.util.Scanner;

public class ReportsPage {

    Scanner keyboard = new Scanner (System.in);
    // TODO IDK IF THIS IS NEEDED
    public void logout(){
        System.out.println("EXITING");
        App.application.welcome();
    }

    public void control(Connection c){

        String choice = "";
        int choiceInt = 0;
        boolean stop = false;
        while(!stop) {
            System.out.println("----------OPTIONS----------");
            System.out.println("1. get number of bookings by city");
            System.out.println("2. get number of bookings by city & postal code");
            System.out.println("3. get number of listings per country");
            System.out.println("4. get number of listings per city & country");
            System.out.println("5. get number of listings per city & country & postal code");
            System.out.println("6. rank host by number of listings they own");
            System.out.println("7. rank host by number of listings they own per city");
            System.out.println("8. find commercial hosts");
            System.out.println("9. rank renters by number of bookings");
            System.out.println("10. rank renters by number of bookings per city");
            System.out.println("11. find the host with the maximum amount of cancels this year");
            System.out.println("12. find the renter with the maximum amount of cancels this year");
            System.out.println("13. get Report for the top 5 used words in each listing review");
            System.out.println("-1 To exit");
            System.out.print("Choose your option [0-13]:");
            choice = keyboard.nextLine();
            if (choice != null || choice.isEmpty()) {
                choiceInt = Integer.parseInt(choice);
                switch (choiceInt) {
                    case 1:
                        getNumBookingsDateRangeCity(c);
                        break;
                    case 2:
                        getNumBookingsDateRangeCityPostCode(c);
                        break;
                    case 3:
                        ReportQueries.getNumberListingsPerCountry(c).print();
                        break;
                    case 4:
                        ReportQueries.getNumberListingsPerCountryCity(c).print();
                        break;
                    case 5:
                        ReportQueries.getNumberListingsPerCountryCityPostCode(c).print();
                        break;
                    case 6:
                        ReportQueries.getRankedHostByNumListings(c).print();
                        break;
                    case 7:
                        ReportQueries.getRankedHostByNumListingsCity(c).print();
                        break;
                    case 8:
                        ReportQueries.getFindOverLimitHosts(c).print();
                        break;
                    case 9:
                        rankRentersA(c);
                        break;
                    case 10:
                        rankRentersB(c);
                        break;
                    case 11:
                        ReportQueries.reportMaxCancelHost(c).print();
                        break;
                    case 12:
                        ReportQueries.reportMaxCancelRenter(c).print();
                        break;
                    case 13:
                        ReportQueries.reviewKeyword(c).print();
                        break;
                    case -1:
                        stop = true;
                        this.logout();
                        break;
                    default:
                        System.out.println("wrong input!");
                }
                System.out.print("Enter Anything to continue");
                keyboard.nextLine();
            }
        }
    }

    public void getNumBookingsDateRangeCity(Connection c){
        System.out.print("Enter from date:");
        String from = keyboard.nextLine();
        System.out.print("Enter to Date:");
        String to = keyboard.nextLine();
        Date dto = Helpers.stringToUtilDate(to);
        Date dfrom = Helpers.stringToUtilDate(from);
        CommandLineTable t = ReportQueries.getNumBookingsInDateRangeByCity(c, dfrom, dto);
        t.print();
    }

    public void getNumBookingsDateRangeCityPostCode(Connection c){
        System.out.print("Enter from date:");
        String from = keyboard.nextLine();
        System.out.print("Enter to Date:");
        String to = keyboard.nextLine();
        Date dto = Helpers.stringToUtilDate(to);
        Date dfrom = Helpers.stringToUtilDate(from);
        CommandLineTable t = ReportQueries.getNumberBookingsByPostalCode(c, dfrom, dto);
        t.print();
    }

    public void rankRentersA(Connection c){
        System.out.print("Enter from date:");
        String from = keyboard.nextLine();
        System.out.print("Enter to Date:");
        String to = keyboard.nextLine();
        Date dto = Helpers.stringToUtilDate(to);
        Date dfrom = Helpers.stringToUtilDate(from);
        CommandLineTable t = ReportQueries.rankRenters(c, dfrom, dto);
        t.print();
    }

    public void rankRentersB(Connection c){
        System.out.print("Enter from date:");
        String from = keyboard.nextLine();
        System.out.print("Enter to Date:");
        String to = keyboard.nextLine();
        Date dto = Helpers.stringToUtilDate(to);
        Date dfrom = Helpers.stringToUtilDate(from);
        CommandLineTable t = ReportQueries.rankRentersCity(c, dfrom, dto);
        t.print();
    }

}

package Server;

import java.sql.Connection;
import java.util.Date;

public class ReportQueries {
    public static int getNumBookingsInDateRangeByCity(Connection c, Date from, Date to) {
        String toString = Helpers.utilDatetoString(to);
        String fromString = Helpers.utilDatetoString(from);
        String q = "SELECT city, COUNT(bookingID) FROM bookings INNER JOIN listing USING(listingID) as A WHERE fromDate <= ? AND toDate >= ? GROUP BY city";
        return -1;
    }

    public static int getNumberBookingsByPostalCode(Connection c, Date from, Date to) {
        String toString = Helpers.utilDatetoString(to);
        String fromString = Helpers.utilDatetoString(from);
        String q = "SELECT city, postal_code ,COUNT(city) FROM bookings INNER JOIN listing USING(listingID) as A WHERE fromDate <= ? AND toDate >= ? GROUP BY city, postal_code";
        return -1;
    }

    public static int getNumberListingsPerCountry(Connection c){
        String q = "SELECT country, COUNT(listingID) FROM listing GROUP BY country";
        return -1;
    }

    public static int getNumberListingsPerCountryCity(Connection c){
        String q = "SELECT country, city, COUNT(listingID) FROM listing GROUP BY country, city";
        return -1;
    }

    public static int getNumberListingsPerCountryCityPostCode(Connection c){
        String q = "SELECT country, city,postal_code, COUNT(listingID) FROM listing GROUP BY country, city, postal_code";
        return -1;
    }

    public static int getRankedHostByNumListings(Connection c){
        String q = "SELECT hosterID, COUNT(listingID) FROM listing GROUP BY hosterID ORDER BY COUNT(listingID) DESC";
        return -1;
    }

    public static int getRankedHostByNumListingsCity(Connection c){
        String q = "SELECT hosterID, city, COUNT(listingID) FROM listing GROUP BY hosterID, city ORDER BY COUNT(listingID) DESC";
        return -1;
    }

    public static int getFindOverLimitHosts(Connection c){
        String q = "SELECT hosterID FROM listing GROUP BY hosterID HAVING COUNT(listingID) * 10 > (SELECT COUNT(listingID) FROM listing)";
        return -1;
    }

    public static int rankRenters(Connection c, Date from, Date to) {
        String toString = Helpers.utilDatetoString(to);
        String fromString = Helpers.utilDatetoString(from);
        String q = "SELECT renterID, COUNT(bookingID) FROM bookings WHERE fromDate <= ? AND toDate >= ? GROUP BY renterID ORDER BY COUNT(bookingID) DESC";
        return -1;
    }

    public static int rankRentersCity(Connection c, Date from, Date to) {
        String toString = Helpers.utilDatetoString(to);
        String fromString = Helpers.utilDatetoString(from);
        String q = "SELECT renterID, COUNT(bookingID) FROM bookings WHERE fromDate <= ? AND toDate >= ? GROUP BY renterID (HAVING COUNT(bookingID) >= 2) ORDER BY COUNT(bookingID) DESC";
        return -1;
    }

    public static int reportMaxCancelHoster(Connection c){
        Date currDate = new Date();
        String cDateString = Helpers.utilDatetoString(currDate);
        String q = "SELECT hosterID, COUNT(bookingID) FROM SELECT bookings WHERE fromDate <= ? AND toDate >= ? GROUP BY renterID ORDER BY DESC";
        return -1;
    }
}

package Server;

import TableGen.CommandLineTable;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ReportQueries {
    public static CommandLineTable getNumBookingsInDateRangeByCity(Connection c, Date from, Date to) {
        CommandLineTable info = new CommandLineTable();
        info.setHeaders("City", "Bookings");
        info.setShowVerticalLines(true);
        String toString = Helpers.utilDatetoString(to);
        String fromString = Helpers.utilDatetoString(from);
        String q = "SELECT city, COUNT(bookingID) FROM bookings INNER JOIN listing USING(listingID) WHERE fromDate >= ? AND toDate <= ? GROUP BY city ORDER BY COUNT(bookingID) DESC";
        try {
            PreparedStatement ps = c.prepareStatement(q);

            ps.setString(1, fromString);
            ps.setString(2, toString);
//            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String city = rs.getString("city");
                int count = rs.getInt(2);
                info.addRow(city, Integer.toString(count));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

        }
        return info;
    }

    public static CommandLineTable getNumberBookingsByPostalCode(Connection c, Date from, Date to) {
        CommandLineTable info = new CommandLineTable();
        info.setHeaders("City", "Postal Code", "Bookings");
        info.setShowVerticalLines(true);
        String toString = Helpers.utilDatetoString(to);
        String fromString = Helpers.utilDatetoString(from);
        String q = "SELECT city, postal_code ,COUNT(city) FROM bookings INNER JOIN listing USING(listingID) WHERE fromDate >= ? AND toDate <= ? GROUP BY city, postal_code ORDER BY COUNT(bookingID) DESC";
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setString(1, fromString);
            ps.setString(2, toString);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String city = rs.getString("city");
                String pCode = rs.getString("postal_code");
                int count = rs.getInt(3);
                info.addRow(city, pCode, Integer.toString(count));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

        }
        return info;
    }

    public static CommandLineTable getNumberListingsPerCountry(Connection c) {
        String q = "SELECT country, COUNT(listingID) FROM listing GROUP BY country";
        CommandLineTable info = new CommandLineTable();
        info.setHeaders("Country", "# of Listings");
        info.setShowVerticalLines(true);
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String country = rs.getString("country");
                int count = rs.getInt(2);
                info.addRow(country, Integer.toString(count));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

        }
        return info;
    }

    public static CommandLineTable getNumberListingsPerCountryCity(Connection c) {
        CommandLineTable info = new CommandLineTable();
        info.setHeaders("Country", "City", "# of Listings");
        String q = "SELECT country, city, COUNT(listingID) FROM listing GROUP BY country, city";
        info.setShowVerticalLines(true);
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String country = rs.getString("country");
                String city = rs.getString("city");
                int count = rs.getInt(3);
                info.addRow(country, city, Integer.toString(count));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

        }
        return info;
    }

    public static CommandLineTable getNumberListingsPerCountryCityPostCode(Connection c) {
        CommandLineTable info = new CommandLineTable();
        info.setHeaders("Country", "City", "Postal Code", "# of Listings");
        info.setShowVerticalLines(true);
        String q = "SELECT country, city, postal_code, COUNT(listingID) FROM listing GROUP BY country, city, postal_code";
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String country = rs.getString("country");
                String city = rs.getString("city");
                String pCode = rs.getString("postal_code");
                int count = rs.getInt(4);
                info.addRow(country, city, pCode, Integer.toString(count));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Something wrong with query!");
        }
        return info;
    }

    public static CommandLineTable getRankedHostByNumListings(Connection c) {
        CommandLineTable info = new CommandLineTable();
        info.setHeaders("hosterID", "# of Listings");
        info.setShowVerticalLines(true);
        String q = "SELECT hosterID, COUNT(listingID) FROM listing GROUP BY hosterID ORDER BY COUNT(listingID) DESC";
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int hosterID = rs.getInt("hosterID");
                int count = rs.getInt(2);
                info.addRow(Integer.toString(hosterID), Integer.toString(count));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

        }
        return info;
    }

    public static CommandLineTable getRankedHostByNumListingsCity(Connection c) {
        String q = "SELECT hosterID, city, COUNT(listingID) FROM listing GROUP BY hosterID, city ORDER BY COUNT(listingID) DESC";
        CommandLineTable info = new CommandLineTable();
        info.setShowVerticalLines(true);
        info.setHeaders("hosterID", "City", "# of Listings");
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int hosterID = rs.getInt("hosterID");
                String city = rs.getString("city");
                int count = rs.getInt(3);
                info.addRow(Integer.toString(hosterID), city, Integer.toString(count));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

        }
        return info;
    }

    public static CommandLineTable getFindOverLimitHosts(Connection c) {
        String q = "SELECT hosterID FROM listing GROUP BY hosterID HAVING COUNT(listingID) * 10 > (SELECT COUNT(listingID) FROM listing)";
        CommandLineTable info = new CommandLineTable();
        info.setShowVerticalLines(true);
        info.setHeaders("hosterID");
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int hosterID = rs.getInt("hosterID");
                info.addRow(Integer.toString(hosterID));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

        }
        return info;
    }

    public static CommandLineTable rankRenters(Connection c, Date from, Date to) {
        String toString = Helpers.utilDatetoString(to);
        String fromString = Helpers.utilDatetoString(from);
        String q = "SELECT renterID, COUNT(bookingID) FROM bookings WHERE fromDate >= ? AND toDate <= ? GROUP BY renterID ORDER BY COUNT(bookingID) DESC";
        CommandLineTable info = new CommandLineTable();
        info.setHeaders("renterID", "# of Bookings");
        info.setShowVerticalLines(true);
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setString(1, fromString);
            ps.setString(2, toString);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int renterID = rs.getInt("renterID");
                int count = rs.getInt(2);
                info.addRow(Integer.toString(renterID), Integer.toString(count));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("oh no");
        }
        return info;
    }

    public static CommandLineTable rankRentersCity(Connection c, Date from, Date to) {
        String toString = Helpers.utilDatetoString(to);
        String fromString = Helpers.utilDatetoString(from);
        String q = "SELECT renterID, COUNT(bookingID) FROM bookings WHERE fromDate >= ? AND toDate <= ? GROUP BY renterID (HAVING COUNT(bookingID) >= 2) ORDER BY COUNT(bookingID) DESC";
        CommandLineTable info = new CommandLineTable();
        info.setHeaders("renterID", "# of Bookings");
        info.setShowVerticalLines(true);
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setString(1, fromString);
            ps.setString(2, toString);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int renterID = rs.getInt("renterID");
                int count = rs.getInt(2);
                info.addRow(Integer.toString(renterID), Integer.toString(count));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

        }
        return info;
    }

    public static CommandLineTable reportMaxCancelRenter(Connection c) {
        Date currDate = new Date();
        String cDateString = Helpers.utilDatetoString(currDate);
        String q = "SELECT renterID, COUNT(bookingID) FROM bookings WHERE year(fromDate) = year(?) OR year(toDate) = year(?) AND isCanceled = 1 GROUP BY renterID ORDER BY COUNT(bookingID) DESC";
        CommandLineTable info = new CommandLineTable();
        info.setHeaders("renterID", "# of Cancellations");
        info.setShowVerticalLines(true);
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setString(1, cDateString);
            ps.setString(2, cDateString);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int renterID = rs.getInt("renterID");
                int count = rs.getInt(2);
                info.addRow(Integer.toString(renterID), Integer.toString(count));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return info;
    }

    public static CommandLineTable reportMaxCancelHost(Connection c) {
        Date currDate = new Date();
        String cDateString = Helpers.utilDatetoString(currDate);
        String q = "SELECT hosterID, COUNT(bookingID) FROM bookings WHERE year(fromDate) = year(?) OR year(toDate) = year(?) AND isCanceled = 1 GROUP BY hosterID ORDER BY count(bookingID) DESC";
        CommandLineTable info = new CommandLineTable();
        info.setHeaders("hostID", "# of Bookings");
        info.setShowVerticalLines(true);
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ps.setString(1, cDateString);
            ps.setString(2, cDateString);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int renterID = rs.getInt("hosterID");
                int count = rs.getInt(2);
                info.addRow(Integer.toString(renterID), Integer.toString(count));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

        }
        return info;
    }

    public static CommandLineTable reviewKeyword(Connection c){
        CommandLineTable info = new CommandLineTable();
        info.setHeaders("listingID", "Most used words");
        info.setShowVerticalLines(true);
        // GET ALL THE LISTINGSIDS THAT HAVE REVIEWS
        String q = "SELECT DISTINCT listingID FROM listingReviews";
        ArrayList<Integer> listingIDs = new ArrayList<>();
        try {
            PreparedStatement ps = c.prepareStatement(q);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int lid = rs.getInt("listingID");
                listingIDs.add(lid);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {

        }
        RestrictedWords wordChecker = new RestrictedWords();
        for (Integer a : listingIDs) {
            ArrayList<String> comments = new ArrayList<>();
            q = String.format("SELECT listingComment FROM listingReviews where listingID = %d", a);
            try {
                PreparedStatement ps = c.prepareStatement(q);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String comms = rs.getString("listingComment");
                    comments.add(comms);
                }
                rs.close();
                ps.close();
            } catch (SQLException e) {

            }
            HashMap<String, Integer> wordCount = new HashMap<>();
            for (String comment : comments){
                String[] split = Helpers.stringSplitter(comment);
                for (String s : split){
                    if(!wordChecker.restricted(s)){
                        String l = s.toLowerCase();
                        wordCount.putIfAbsent(l, 0);
                        wordCount.put(l, wordCount.get(l) + 1);
                    }
                }
            }
            HashMap<String, Integer> sortedWordCount = Helpers.sortHashMapByValue(wordCount);
            // get the top 5 most used words
            ArrayList<String> top5 = new ArrayList<>();
            int i = 0;
            for (Map.Entry<String, Integer> en : sortedWordCount.entrySet()){
                top5.add(en.getKey());
                i ++;
                if(i == 5){
                    break;
                }
            }
            String top = "";
            // put it in the mf commandline table
            for (String s : top5){
                top = top + s + ", ";
            }
            String ss = top.substring(0, top.length() - 2);
            info.addRow(Integer.toString(a), ss);
        }


        return info;
    }


}

package Server;
import Listings.Listing;
import TableGen.CommandLineTable;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class TestQueries {
    public static Connection createConn() {
        Connection c = null;
        String dbClassName = "com.mysql.cj.jdbc.Driver";
        String CONNECTION = "jdbc:mysql://localhost:3306/airbnb";
        try {
            Class.forName(dbClassName);
        }catch (ClassNotFoundException e){
            System.out.println("xd");
        }
        //Database credentials
        final String USER = "root";
        final String PASS = "root";
//        System.out.println("Connecting to database...");

        try {
            //Establish connection
            c = DriverManager.getConnection(CONNECTION, USER, PASS);
//            System.out.println("Successfully connected to MySQL!");
        } catch (SQLException e) {
            System.out.println("connection failure");
        }
        return c;
    }

    public static void test1(Connection c){
        int id = -1;
        String query = "insert into users (sin, userName, dob, occupation, loginName, loginPW) values (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, "00000000");
            ps.setString(2, "Bob Zhang");
            ps.setString(3, "1998-12-08");
            ps.setString(4, "Dabber");
            ps.setString(5, "c");
            ps.setString(6, "aaa");
            if (ps.executeUpdate() != 0){
                ResultSet rs = ps.getGeneratedKeys();
                while (rs.next()){
                    System.out.println(rs.getInt(1));
                }
                rs.close();
            }
            ps.close();
        } catch (SQLException e) {
            // TODO: ADD ERROR MESSAGE
            e.printStackTrace();
        }
    }

    public static void main(String args[]){
        Connection c = createConn();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            java.util.Date from = sf.parse("2010-12-01");
            java.util.Date to = sf.parse("2010-12-10");
            String a = ListingQueries.filterByDateRange(from, to);
            String b = ListingQueries.filterBypriceRange(0, 100.00);
            ArrayList<String> x = new ArrayList<>();
            x.add(a);
            x.add(b);
            String q = ListingQueries.finalListingQuery(x, 0);
            System.out.println(q);
            ArrayList<Listing> pen = ListingQueries.runFilters(c, q);
            for(Listing p : pen){
                System.out.println(p.id);
            }
            CommandLineTable t = ReportQueries.getNumberListingsPerCountryCity(c);
            t.setShowVerticalLines(true);
            t.print();
            t = ReportQueries.getFindOverLimitHosts(c);
            t.print();
            String testSplitter = "WHAT THE FUCK, IS THIS CAN'T WAKE UP! what is happening";
            String[] aaa = Helpers.stringSplitter(testSplitter);
            for (String i : aaa){
                System.out.println(i);
            }

        }catch (Exception e){

        }

//        try {
////            c.close();
//        } catch(SQLException e){
//            System.out.println("whoops");
//        }
    }
}

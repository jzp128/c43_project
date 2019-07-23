package Server;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.logging.SimpleFormatter;

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
        System.out.println("Connecting to database...");

        try {
            //Establish connection
            c = DriverManager.getConnection(CONNECTION, USER, PASS);
            System.out.println("Successfully connected to MySQL!");
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
        int i = Queries.add_address(c, "Toronto", "M1T1K4", "Canada", "Lowcrest Blvd", "39", "");
        System.out.println(i);
        try {
            c.close();
        } catch(SQLException e){
            System.out.println("whoops");
        }
    }
}

package Server;

import Listings.Listing;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Helpers {
    public static final double R = 6372.8; // In kilometers
    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        // HARVERSINE FORMULA TAKEN FROM http://rosettacode.org/wiki/Haversine_formula
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        double a = Math.pow(Math.sin(dLat / 2),2) + Math.pow(Math.sin(dLon / 2),2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.asin(Math.sqrt(a));
        return R * c;
    }

    public static boolean login(Connection c, String loginName, String pw){
        String dbPW = Queries.fetchPW(c,loginName); // this should be loginName right? TODO:
        return pw.equals(dbPW);
    }

    public static String utilDatetoString(Date d){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strdate = dateFormat.format(d);
        return strdate;
    }

    public static long daysInBetween(Date from, Date to){
        long diff = to.getTime() - from.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + 1;
    }

    public static Comparator<Listing> getListingDistanceComparator(Double latitude, Double longitude){
        Comparator<Listing> distComparator = new Comparator<Listing>() {

            public int compare(Listing s1, Listing s2) {
                Double dist1 = haversine(latitude, longitude, s1.latitude, s1.longitude);
                Double dist2 = haversine(latitude, longitude, s2.latitude, s2.longitude);
                //ascending order
                return dist2.compareTo(dist1);

                //descending order
                //return StudentName2.compareTo(StudentName1);
            }};
        return distComparator;
    }
}

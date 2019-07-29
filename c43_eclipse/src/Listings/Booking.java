package Listings;

import java.sql.*;

public class Booking {
	public int bookingID;
	public int hostID;
	public int renterID;
	public int listingID;
	public int isCanceled;
	public int isHistory;
	public Date fromDate;
	public Date toDate;
	
	
	public Booking(int hostIDt,int renterIDt,int listingIDt,int isCanceledt,int isHistoryt,Date fromDatet,Date toDatet){
		 this.hostID = hostIDt;
		 this.renterID = renterIDt;
		 this.listingID = listingIDt;
		 this.isCanceled = isCanceledt;
		 this.isHistory = isHistoryt;
		 this.fromDate = fromDatet;
		this.toDate = toDatet;
		
	}
}

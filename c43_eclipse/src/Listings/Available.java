package Listings;

import java.math.BigDecimal;
import java.sql.Date;

public class Available {
    public Date availDate;
    public BigDecimal price;
    public int listingID;
    public int isBooked;
    public Available(Date availDate,BigDecimal pricet,int listingid, int isbooked){
    	this.availDate = availDate;
    	this.price = pricet;
    	this.listingID = listingid;
    	this.isBooked = isbooked;
	  }
}

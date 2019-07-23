package Listings;

import java.sql.Connection;

public class Listing {
    public int id;
    public String city;
    public String postal_code;
    public String country;
    public String street_name;
    public String building_number;
    public String unit_number;
    public Double latitude;
    public Double longitude;
    public int hostID;
    public double price;

    public Listing(int id, String city, String postal_code, String country, String street_name, String building_number, String unit_number){
        this.id = id;
        this.city = city;
        this.postal_code = postal_code;
        this.country = country;
        this.street_name = street_name;
        this.building_number = building_number;
        this.unit_number = unit_number;
    }
    
	  public int makelisting(Connection c) {
		  Server.Queries queries = new Server.Queries();
		  int id = queries.create_listing(c,id,city, postal_code, country, street_name, building_number, unit_number);
		  return id;
	  }
    
    
}


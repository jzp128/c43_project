package Listings;

import Server.Helpers;

import java.sql.Connection;

public class Listing {
    public int id;
    public String city;
    public String postal_code;
    public String country;
    public String address;
    public Double latitude;
    public Double longitude;
    public int hostID;
    public double price;
    public String type;

    public Listing(String city, String postal_code, String country, String address, double latitude, double longitude, int hostid, String type) {
        this.city = city;
        this.postal_code = postal_code;
        this.country = country;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hostID = hostid;
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int makelisting(Connection c) {
        Server.Queries queries = new Server.Queries();
        this.id = queries.create_listing(c, this.type, this.longitude, this.latitude, this.hostID, this.address, this.country, this.city, this.postal_code);
        return this.id;
    }

    public Double getDist(Double lat, Double longit){
        return Helpers.haversine(this.latitude, this.longitude, lat, longit);
    }
}


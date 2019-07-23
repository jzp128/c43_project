package Listings;


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

    public Listing(int id, String city, String postal_code, String country, String address, double latitude, double longitude, int hostid, String type){
        this.id = id;
        this.city = city;
        this.postal_code = postal_code;
        this.country = country;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.hostID = hostid;
        this.type = type;
    }

    public void setPrice(double price){
        this.price = price;
    }
}


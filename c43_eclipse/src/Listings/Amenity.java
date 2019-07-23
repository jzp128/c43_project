package Listings;

public class Amenity {
    public int amendid;
    public String amendName;
    public String amendDescription;
    public boolean amendBool;
    
    public Amenity(int amendidP, String amendNameP,String amendDescriptionP){
         amendid = amendidP;
         amendName = amendNameP;
         amendDescription = amendDescriptionP;    	
    }
    
    public void setamendBool(boolean a){
        amendBool = a;
    }
}



package Listings;

public class Amenity {
    public int amenid;
    public String amenName;
    public String amenDescription;
    public boolean amenBool;
    
    public Amenity(int amenidP, String amenNameP,String amenDescriptionP){
         amenid = amenidP;
         amenName = amenNameP;
         amenDescription = amenDescriptionP;    	
    }
    
    public void setamendBool(boolean a){
        amenBool = a;
    }
}



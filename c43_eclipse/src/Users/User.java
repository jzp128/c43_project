package Users;

import java.sql.Connection;
import Server.Queries;

public class User {
	
	  public int id; 
	  public String username; 
	  public String password; 
	  public String name; 
	  public java.sql.Date dob; 
	  public String job; 
	  public String sin; 
	  public String address;
	  public String postalcode; 
	  public String city; 
	  public String country; 
	  public String usertype; 
	  
	  public String ccnumber;
	  public String ccsec;
	  public String ccName;

	  public String getName() {
	    return username;
	  }

	  public void setName(String newName) {
	    this.username = newName;
	  }
	  
	  public int makeUser(Connection c) {
	      Server.Queries queries = new Server.Queries();
		  id = Queries.create_user(c,sin,name,dob,job, username,password, postalcode, country, city, address);
		  return id;
		  //Queries.create_user(c,sin,name,dob,job, username,password, postalcode, country, city, address);
	  }
	  
      public boolean makeRenter(Connection c){
	      Server.Queries queries = new Server.Queries();
	      boolean success = Queries.addRenter( c, id, ccnumber, ccsec, ccName);
		  return success;
	
      }
      
      public boolean makeHost(Connection c){
	      Server.Queries queries = new Server.Queries();
	      boolean success = Queries.addHost( c, id);
		  return success;
	
		}
      
      public boolean checkusername(Connection c){
    	  Server.Queries queries = new Server.Queries();
    	  boolean success = Queries.checkUserNameTaken( c, username);
    	  return success;
      }
      
      
      
      
      
      
      
      
      
      
      
      

}

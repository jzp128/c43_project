package Users;

import java.sql.Connection;
import Server.Queries;

public class User {
	
	  public int id; 
	  public String loginname; 
	  public String password; 
	  public String name; 
	  public java.sql.Date dob; 
	  public String job; 
	  public String sin; 
	  public String address;
	  public String postalcode; 
	  public String city; 
	  public String country; 
	  public int isHost; 
	  
	  public String ccnumber;
	  public String ccsec;
	  public String ccName;
	  
	  
	  public User(String loginnamet, String passwordt,  String namet,  java.sql.Date dobt, 
			  String jobt,  String sint, String addresst, String postalcodet, String cityt, 
			  String countryt, int isHost){
		  this.loginname = loginnamet;
		  this.password = passwordt;
		  this.name = namet;
		  this.dob = dobt;
		  this.job = jobt;
		  this.sin = sint;
		  this.address = addresst;
		  this.postalcode = postalcodet;
		  this.city = cityt;
		  this.country = countryt;
		  this.isHost = isHost;
	  }
	  
	  public User(){
		  
	  }

	  public String getName() {
	    return loginname;
	  }

	  public void setName(String newName) {
	    this.loginname = newName;
	  }
	  
	  public int makeUser(Connection c) {
	      Server.Queries queries = new Server.Queries();
		  id = Queries.create_user(c,sin,name,dob,job, loginname,password, postalcode, country, city, address);
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
    	  boolean success = Queries.checkUserNameTaken( c, loginname);
    	  return success;
      }
      
      
      
      
      
      
      
      
      
      
      
      
      
      

}

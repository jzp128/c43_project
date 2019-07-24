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

	  public String getName() {
	    return username;
	  }

	  public void setName(String newName) {
	    this.username = newName;
	  }
	  
	  public void makeUser(Connection c) {
	      Server.Queries queries = new Server.Queries();
		  id = Queries.create_user(c,sin,name,dob,job, username,password, postalcode, country, city, address);
	  }

}

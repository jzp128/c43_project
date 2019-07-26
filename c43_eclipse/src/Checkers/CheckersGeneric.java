package Checkers;

public class CheckersGeneric {
	
	public static boolean range(int from, int to, int number){
		
		return (number >= from && number <= to);
		
	}
	
	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
	
	
	public static int length(String str) { 
		int length = str.length( ); 
		return length;
	}

}

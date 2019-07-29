package HostToolkit;

public class Recommendation {
	public String type;
	public double price;
	
	public double lowestPrice;
	public double highestPrice;
	public double lastAverage;
	
	public Recommendation(){
		lowestPrice = 0.00;
		highestPrice = 0.00;
		lastAverage = 0.00;
	}

}

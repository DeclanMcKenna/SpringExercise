package core;

import java.math.RoundingMode;
import java.text.DecimalFormat;

//@author: Declan McKenna
//@version: 1.0.0
//@description: This is an abstract class that provides its child classes with a set of base attributes and operations
//common to both. 
public class Instrument
{
	//Attributes
	private String instrumentType;
	private String name;
	private int quantity;
	private float profit;
	
	//Empty Constructor
	public Instrument()
	{
		instrumentType = "";
		name = "";
		quantity = 0;
		profit = 0.00f;
	}//end Instrument()
	
	//Fully-Overloaded Constructor
	public Instrument(String typeIn, String nameIn, int quantityIn)
	{
		instrumentType = typeIn;
		name = nameIn;
		quantity = quantityIn;
	}//end Instrument(String, String, int, double)
	
	//Getters
	public String getInstrumentType()
	{
		return instrumentType;
	}//end getInstrumentType()
	
	public String getName()
	{
		return name;
	}//end getName()
	
	public int getQuantity()
	{
		return quantity;
	}//end getQuantity()
	
	public float getProfit()
	{
		return profit;
	}//end getProfit()
	
	//Setters
	public void setProfit(float profitIn)
	{
		DecimalFormat df = new DecimalFormat("####.##");
		df.setRoundingMode(RoundingMode.HALF_DOWN);
		profit = Float.parseFloat(df.format(profitIn));
	}//end setProfit(float)
	
}//end class Instrument

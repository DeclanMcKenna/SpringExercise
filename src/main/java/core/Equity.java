package core;

//@author: Declan McKenna
//@version: 1.0.0
//@description: This is a child class of Instrument which implements the attributes and operations specific to an Equity.
public class Equity extends Instrument 
{
	//Attributes
	private float buyPrice;
	private float sellPrice;
	
	//Empty Constructor
	public Equity()
	{
		super();
		buyPrice = 0.00f;
		sellPrice = 0.00f;
	}//end Equity
	
	//Fully-Overloaded Constructor
	public Equity(String nameIn, int quantityIn, float buyPriceIn, float sellPriceIn)
	{
		super("Equity", nameIn, quantityIn);
		buyPrice = buyPriceIn;
		sellPrice = sellPriceIn;
		calculateProfit(sellPriceIn, buyPriceIn);
	}//end Equity(String, int, float, float)
	
	//This method is used to calculate the profit amount of the equity
	//@param: sellPriceIn - This is the number of shares within the equity
	//@param: buyPriceIn - This is the amount the equity is worth
	//@return: The value returned is the total amount the equity is worth
	private void calculateProfit(float sellPriceIn, float buyPriceIn)
	{
		super.setProfit((sellPriceIn - buyPriceIn) * super.getQuantity());
	}//end calculateProfit(float, float)
	
	//Getters
	public float getBuyPrice()
	{
		return buyPrice;
	}//end getBuyPrice()
	
	public float getSellPrice()
	{
		return sellPrice;
	}//end getSellPrice()
	
}//end class Equity

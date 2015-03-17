package core;

//@author: Declan McKenna
//@version: 1.0.0
//@description: This is a child class of Instrument which implements the attributes and operations specific to a Bond.
public class Bond extends Instrument 
{
	//Attributes
	private float coupon;
	
	//Empty Constructor
	public Bond()
	{
		super();
		coupon = 0.00f;
	}//end Bond()
	
	//Fully-Overloaded Constructor
	public Bond(String nameIn, int quantityIn, float couponIn)
	{
		super("Bond", nameIn, quantityIn);
		coupon = couponIn;
		calculateProfit(quantityIn, couponIn);
	}//end Bond()
	
	//This method is used to calculate the profit amount of the bond
	//@param: quantityIn - This is the number of shares within the bond
	//@param: couponIn - This is the amount the bond is worth
	//@return: The value returned is the total amount the bond is worth
	private void calculateProfit(int quantityIn, float couponIn) 
	{
		super.setProfit(quantityIn * couponIn);
	}//end calculateProfit(int, float)
	
	//Getters
	public float getCoupon()
	{
		return coupon;
	}//end getCoupon()
	
}//end class Bond

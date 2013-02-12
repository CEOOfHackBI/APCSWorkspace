/**
 * 
 */

/**
 * @author jack
 *
 */
public class TradeOrder {

	/**
	 * @param args
	 */
	
	private String stockSymbol;
	private int numShares;
	private boolean buy;
	private boolean market;
	private double price;
	private Trader myTrader;
	
	
	public TradeOrder(Trader myTrader,String stockSymbol,  boolean buy,boolean market, int numShares,double price)
	{
		this.stockSymbol=stockSymbol;
		this.numShares=numShares;
		this.buy=buy;
		this.market=market;
		this.price=price;
		this.myTrader=myTrader;
	}
	
	//Returns the stock symbol for this trade order.
	public String getSymbol()
	{
		return stockSymbol;
	}
	
	//  Returns the trader for this trade order.
	public Trader getTrader()
	{
		return myTrader;
	}
	
	//Returns the number of shares to be traded in this trade order.
	public int getShares()
	{
		return numShares;
	}
	
	//  Returns true if this is a buy order; otherwise returns false.
	public boolean isBuy()
	{
		return buy;
	}
	
	//   Returns true if this is a limit order; otherwise returns false.
	public boolean isLimit()
	{
		return !market;
	}
	
	//Returns true if this is a sell order; otherwise returns false.
	public boolean isSell()
	{
		return !buy;
	}
	
	  //Returns true if this is a market order; otherwise returns false.
	public boolean isMarket()
	{
		return market;
	}
	
	//Returns the price per share for this trade order
	public double getPrice()
	{
		return price;
	}
	
	//Subtracts a given number of shares from the total number of shares in this trade order.
	public void subtractShares(int shares)
	{
		numShares-=shares;
	}
	
	

}

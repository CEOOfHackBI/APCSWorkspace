import java.util.PriorityQueue;

/**
 * 
 */

/**
 * @author Jack,Noah,Benji
 *
 *General Description: stock class that represents and individual stock
 *also has the job of executing orders and providing quotes
 *
 */
public class Stock {

	
	private String stockSymbol;		//symbol for reference
	private String companyName;		//company name for reference
	private double lowSellPrice;	//lowest price for the day
	private double highSellPrice;	//highest price for the day
	private double lastPrice;		//last order's price
	private double volume;			//total amount sold
	private StockExchange myStockExchange;
	
	//Priority queues because they most closely resemble how the orders will be processed
	private PriorityQueue<TradeOrder> buyOrders;
	private PriorityQueue<TradeOrder> sellOrders;
	
	//constructor that sets all of the values to the individual stock specs
	
	public Stock(String stockName, String companyName, double price)
	{
		this.stockSymbol=stockName;
		this.companyName=companyName;
		lowSellPrice=price;
		highSellPrice=price;
		lastPrice=price;
		volume=0;
		PriceComparator ascending=new PriceComparator();
		PriceComparator descending =new PriceComparator(false);
		buyOrders=new PriorityQueue<TradeOrder>(10,ascending);
		sellOrders=new PriorityQueue<TradeOrder>(10, descending);
	}
	
	
	//placeOrder adds new orders to the buy/sellOrders collections 
	//it also sends a message to the trader saying what the order was for exactly
	//trader will instruct its trader window to display this message
	
	public void placeOrder(TradeOrder order)
	{
		String msg="";
		msg+="New Order:  ";
		
		if(order.isBuy())
		{
			buyOrders.add(order);
			msg+="Buy ";
		}
		else
		{
			sellOrders.add(order);
			msg+="Sell ";
		}
		
		msg+=stockSymbol+" ("+companyName+")"+'\n'+order.getShares()+" shares at $"+order.getPrice();
		
		order.getTrader().recieveMessage(msg);
		
		execute();
	}
	
	
	//grab most important buy(largest price) and sell(smallest price)
	//if both exist - attempt to resolve
	//if both market, then price is last price then adjust units and eliminate orders and send messages to Traders
	//if both limit orders (and sell<=buy) then 
	//if either one is market, operate at limit price
	
	public void execute()
	{
		TradeOrder SELL = sellOrders.peek(); //gets the most important sell
		TradeOrder BUY = buyOrders.peek(); //gets the most important buy
		int numSharesSold = 0;
		double priceSoldAt = 0;
		if(SELL!=null && BUY!=null) //makes sure that both are existing orders
		{
			if(BUY.isMarket() && SELL.isMarket()) //if both are market
			{
				if(SELL.getShares() > BUY.getShares()) //adjusts for more sell shares than buy shares
				{
					SELL.subtractShares(BUY.getShares()); //adjust the num of sell shares left after the trade
					numSharesSold = BUY.getShares(); //sets the num of shares sold
					priceSoldAt = lastPrice; //adjusts lastPrice
					buyOrders.remove(); //removes the resolved order
				}
				else if(SELL.getShares() < BUY.getShares()) //adjusts for more buy shares than sell shares
				{
					BUY.subtractShares(SELL.getShares());
					numSharesSold = SELL.getShares();
					priceSoldAt = lastPrice;
					sellOrders.remove();
				}
				else if(SELL.getShares() == BUY.getShares()) //adjusts for the same amount of each
				{
					numSharesSold = BUY.getShares();
					priceSoldAt = lastPrice;
					sellOrders.remove();
					buyOrders.remove();
				}
			}
			else if(!BUY.isMarket() && SELL.isMarket()) //if buy is limit and sell is market
			{
				if(SELL.getShares() > BUY.getShares())
				{
					SELL.subtractShares(BUY.getShares());
					numSharesSold = BUY.getShares();
					priceSoldAt = BUY.getPrice();
					buyOrders.remove();
				}
				else if(SELL.getShares() < BUY.getShares())
				{
					BUY.subtractShares(SELL.getShares());
					numSharesSold = SELL.getShares();
					priceSoldAt = BUY.getPrice();
					sellOrders.remove();
				}
				else if(SELL.getShares() == BUY.getShares())
				{
					numSharesSold = BUY.getShares();
					priceSoldAt = BUY.getPrice();
					sellOrders.remove();
					buyOrders.remove();
				}
			}
			else if(BUY.isMarket() && !SELL.isMarket()) //if buy is market and sell is limit
			{
				if(SELL.getShares() > BUY.getShares())
				{
					SELL.subtractShares(BUY.getShares());
					numSharesSold = BUY.getShares();
					priceSoldAt = SELL.getPrice();
					buyOrders.remove();
				}
				else if(SELL.getShares() < BUY.getShares())
				{
					BUY.subtractShares(SELL.getShares());
					numSharesSold = SELL.getShares();
					priceSoldAt = SELL.getPrice();
					sellOrders.remove();
				}
				else if(SELL.getShares() == BUY.getShares())
				{
					numSharesSold = BUY.getShares();
					priceSoldAt = SELL.getPrice();
					sellOrders.remove();
					buyOrders.remove();
				}
			}
			else if(!BUY.isMarket() && !SELL.isMarket()) //if both are limit
			{
				if(SELL.getPrice() <= BUY.getPrice()) //checks to see if its selling for <= to what they are willing to pay
				{
					if(SELL.getShares() > BUY.getShares())
					{
						SELL.subtractShares(BUY.getShares());
						numSharesSold = BUY.getShares();
						priceSoldAt = SELL.getPrice();
						buyOrders.remove();
					}
					else if(SELL.getShares() < BUY.getShares())
					{
						BUY.subtractShares(SELL.getShares());
						
						numSharesSold = SELL.getShares();
						priceSoldAt = SELL.getPrice();
						sellOrders.remove();
					}
					else if(SELL.getShares() == BUY.getShares())
					{
						numSharesSold = BUY.getShares();
						priceSoldAt = SELL.getPrice();
						sellOrders.remove();
						buyOrders.remove();
					}
				}
			}
			BUY.getTrader().recieveMessage("You just bought " + numSharesSold + " share(s) of " + stockSymbol + " at $" + priceSoldAt);
			SELL.getTrader().recieveMessage("You just sold " + numSharesSold + " share(s) of " + stockSymbol + " at $" + priceSoldAt);
		}
		
		//updates the high/low sell price if need be
		
		if(priceSoldAt >= highSellPrice)
		{
		  highSellPrice = priceSoldAt;
		}
		else
		{
		  lowSellPrice = priceSoldAt;
		}
		
		//updates the volume of the shares sold
		volume += numSharesSold;
		
		//updates last sell price
		if(priceSoldAt!=0)
			lastPrice=priceSoldAt;
		
		if(numSharesSold!=0)
			execute();
	}
	
	//this returns the value of the stock based on whether or buy/sell orders is empty
	//if its is empty, we use the last price it was sold and
	//and the same for the last buy price
	public String getQuote()
	{
		String result=""+stockSymbol+" selling at: $";
		
		if(!buyOrders.isEmpty())
			result+= buyOrders.peek().getPrice();
		else
			result+=lastPrice;
		
		result+=" and buying at: $";
		
		if(!sellOrders.isEmpty())
			result+=sellOrders.peek().getPrice();
		else
			result+=lastPrice;
		
		return result;
	}
}

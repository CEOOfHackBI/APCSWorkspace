import java.util.PriorityQueue;

/**
 * 
 */

/**
 * @author jack
 *
 */
public class Stock {

	/**
	 * @param args
	 */
	
	private String stockSymbol;
	private String companyName;
	private double lowSellPrice;
	private double highSellPrice;
	private double lastPrice;
	private double volume;
	private StockExchange myStockExchange;
	private PriorityQueue<TradeOrder> buyOrders;
	private PriorityQueue<TradeOrder> sellOrders;
	
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
		TradeOrder SELL = sellOrders.peek();
		TradeOrder BUY = buyOrders.peek();
		int numSharesSold = 0;
		double priceSoldAt = 0;
		if(!SELL.equals(null) && !BUY.equals(null))
		{
			if(BUY.isMarket() && SELL.isMarket())
			{
				if(SELL.getShares() > BUY.getShares())
				{
					SELL.subtractShares(BUY.getShares());
					numSharesSold = BUY.getShares();
					priceSoldAt = lastPrice;
					buyOrders.remove();
				}
				if(SELL.getShares() < BUY.getShares())
				{
					BUY.subtractShares(SELL.getShares());
					numSharesSold = SELL.getShares();
					priceSoldAt = lastPrice;
					sellOrders.remove();
				}
				if(SELL.getShares() == BUY.getShares())
				{
					numSharesSold = BUY.getShares();
					priceSoldAt = lastPrice;
					sellOrders.remove();
					buyOrders.remove();
				}
			}
			if(!BUY.isMarket() && SELL.isMarket())
			{
				if(SELL.getShares() > BUY.getShares())
				{
					SELL.subtractShares(BUY.getShares());
					numSharesSold = BUY.getShares();
					priceSoldAt = BUY.getPrice();
					buyOrders.remove();
				}
				if(SELL.getShares() < BUY.getShares())
				{
					BUY.subtractShares(SELL.getShares());
					numSharesSold = SELL.getShares();
					priceSoldAt = BUY.getPrice();
					sellOrders.remove();
				}
				if(SELL.getShares() == BUY.getShares())
				{
					numSharesSold = BUY.getShares();
					priceSoldAt = BUY.getPrice();
					sellOrders.remove();
					buyOrders.remove();
				}
			}
			if(BUY.isMarket() && !SELL.isMarket())
			{
				if(SELL.getShares() > BUY.getShares())
				{
					SELL.subtractShares(BUY.getShares());
					numSharesSold = BUY.getShares();
					priceSoldAt = SELL.getPrice();
					buyOrders.remove();
				}
				if(SELL.getShares() < BUY.getShares())
				{
					BUY.subtractShares(SELL.getShares());
					numSharesSold = SELL.getShares();
					priceSoldAt = SELL.getPrice();
					sellOrders.remove();
				}
				if(SELL.getShares() == BUY.getShares())
				{
					numSharesSold = BUY.getShares();
					priceSoldAt = SELL.getPrice();
					sellOrders.remove();
					buyOrders.remove();
				}
			}
			if(!BUY.isMarket() && !SELL.isMarket())
			{
				if(SELL.getPrice() <= BUY.getPrice())
				{
					if(SELL.getShares() > BUY.getShares())
					{
						SELL.subtractShares(BUY.getShares());
						numSharesSold = BUY.getShares();
						priceSoldAt = SELL.getPrice();
						buyOrders.remove();
					}
					if(SELL.getShares() < BUY.getShares())
					{
						BUY.subtractShares(SELL.getShares());
						numSharesSold = SELL.getShares();
						priceSoldAt = SELL.getPrice();
						sellOrders.remove();
					}
					if(SELL.getShares() == BUY.getShares())
					{
						numSharesSold = BUY.getShares();
						priceSoldAt = SELL.getPrice();
						sellOrders.remove();
						buyOrders.remove();
					}
				}
			}
			BUY.getTrader().recieveMessage("You just bought " + numSharesSold + " share(s) of " + stockSymbol + " at " + priceSoldAt);
			SELL.getTrader().recieveMessage("You just sold " + numSharesSold + " share(s) of " + stockSymbol + " at " + priceSoldAt);
		}
		
		if(numSharesSold!=0)
			execute();
	}
	
	public double returnQuote()
	{
		return lowSellPrice;
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

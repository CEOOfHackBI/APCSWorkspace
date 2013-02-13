import java.util.Comparator;

/**
 * 
 */

/**
 * @author jack
 *
 */
public class PriceComparator implements Comparator<TradeOrder>{

	/**
	 * @param args
	 */
	
	private boolean ascending;
	
	public PriceComparator()
	{
		ascending=true;
	}
	
	public PriceComparator(boolean asc)
	{
		ascending=asc;
	}
	
	//returns positive if 0 if both are market, 1 if t1 is the only limit order, -1 if t2 is the only limit order
	//returns difference in prices if both are limit and also accounts for ascending or descending
	public int compare(TradeOrder t1,TradeOrder t2)
	{
		if(t1.isMarket()&&t2.isMarket())
		{
			return 0;
		}
		if(t1.isMarket()&&t2.isLimit())
		{
			return -1;
		}
		if(t2.isMarket()&&t1.isLimit())
		{
			return 1;
		}
		
		if(ascending)
			return (int)(t1.getPrice()-t2.getPrice()*100);
		
		return (int)(-t1.getPrice()+t2.getPrice()*100);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	

}
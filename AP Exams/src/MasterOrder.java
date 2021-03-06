import java.util.ArrayList;
import java.util.List;

/**
 * 
 */

/**
 * @author jack
 *
 */
public class MasterOrder {

	/**
	 * @param args
	 */
	
	private List<CookieOrder> orders;
	
	public MasterOrder()
	{
		orders=new ArrayList<CookieOrder>();
	}
	
	public void addOrder(CookieOrder theOrder)
	{
		orders.add(theOrder);
	}
	
	public int getTotalBoxes()
	{
		int result=0;
		for(int i=0;i<orders.size();i++)
		{
			result+=orders.get(i).getNumBoxes();
		}
		return result;
	}
	
	public int removeVariety(String cookieVar)
	{
		int result=0;
		for(int i=0;i<orders.size();i++)
		{
			if(orders.get(i).getVariety().equals(cookieVar))
			{
				result+=orders.get(i).getNumBoxes();
				orders.remove(i);
			}
		}
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MasterOrder goodies=new MasterOrder();
		goodies.addOrder(new CookieOrder("Chocolate Chip",1));
		goodies.addOrder(new CookieOrder("Shortbread",5));
		goodies.addOrder(new CookieOrder("Macaroon",2));
		goodies.addOrder(new CookieOrder("Chocolate Chip",3));
		System.out.println(goodies.removeVariety("Shortbread"));
		
	}

}

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * 
 */

/**
 * @author Jack,Noah,Benji
 * 		-Primary Editor: Benji
 */
public class Brokerage implements Login {

	/**
	 * @param args
	 */
	private TreeMap<String,String> myTraders= new TreeMap<String,String>();
	private TreeSet<String> loggedInTraders = new TreeSet<String>();
	private HashMap<String,Trader> existingTraders=new HashMap<String,Trader>();
	private StockExchange myStockExchange;
	
	public Brokerage(StockExchange stockExchange) //Constructs new brokerage affiliated with a given stock exchange.
	{
		myStockExchange=stockExchange;
	}
	public int addUser(String username, String password) //Tries to register a new trader with a given screen name and password.
	{
		
		String lowerName = username.toLowerCase();
		
		if(lowerName.length() < 4 || lowerName.length() >= 11)  //name must be within 4-10 chars
			return -1;
		else if(password.length() < 2 || password.length() >= 11) //pass must me within 2 - 10 chars
			return -2;
		else if(myTraders.containsKey(lowerName))  //if the username is already taken
			return -3;
		else
		{
			Trader t=new Trader(this,username,password);
			existingTraders.put(username,t);
			myTraders.put(username,password); //add the new user to myTrader
			return 0;
		}
	}
	public void getQuote(String stockSymbol, Trader trader) // Requests a quote for a given stock from the stock exchange and 
															//passes it along to the trader by calling trader's receiveMessage method.
	{
		 trader.recieveMessage(myStockExchange.getQuote(stockSymbol)); //tell the trader to receive a message containing the quote
	}
	public int login(String username, String password) // Tries to login a trader with a given screen name and password.
	{
		if(!myTraders.containsKey(username)) //myTraders doesn't contain the username
			return -1;  //screen name not found 
		else if(!myTraders.get(username).equals( password)) //the password doesnt match the password in the database
			return -2;  //invalid password
		else if(loggedInTraders.contains(username)) //if the trader is already logged in
			return -3;  // user is already logged in.
		else
		{
			loggedInTraders.add(username);  //add the user to the loggedInTraders
			existingTraders.get(username).openWindow();
			return 0;
		}
	}
	public void logout(Trader trader) //Removes a specified trader from the set of logged-in traders.
	{
		loggedInTraders.remove(trader.getName()); //take this trader offline
	}
	public void placeOrder(TradeOrder to) //to = TradeOrder
	{
		myStockExchange.placeOrder(to);  
	}
	
	

}
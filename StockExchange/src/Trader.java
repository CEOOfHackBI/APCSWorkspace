import java.util.LinkedList;
import java.util.Queue;


/**
 * 
 * @author Jack,Noah,Benji
 *
 *
 *	General Description: trader class that places trade orders and communicates
 *to the user with the traderwindow class
 */


public class Trader implements Comparable<Trader>
{
	private String traderName;			//name of trader
	private String traderPassword;		//trader password
	
	//list of messages, should be a queue because it most closely represents the way messages are recieved and read in the project
	private Queue<String> mailbox;	
	private Brokerage myBrokerage;		
	private TraderWindow myTraderWindow;
	
	
	//this constructor sets the trader's user name and password 
	//its also initiates the mailbox that accepts messages from other classes
	public Trader(Brokerage myBrokerage,String username, String password)
	{
		this.myBrokerage=myBrokerage;
		this.traderName = username;
		this.traderPassword = password;
		mailbox = new LinkedList<String>();
		myBrokerage.login(traderName,traderPassword);
	}
	
	//returns screen name
	public String getName()
	{
		return traderName;
	}
	
	//Requests quote from brokerage
	public void getQuote(String symbol)
	{
		myBrokerage.getQuote(symbol, this);
	}
	
	
	//true if mailbox isn't empty
	public boolean hasMessages()
	{
		return mailbox.size()!=0;
	}
	
	//opens new window and prints out messages and deletes them
	public void openWindow()
	{
		myTraderWindow=new TraderWindow(this);
		while(mailbox.size()!=0)	//makes sure that there are actually messages
		{
			myTraderWindow.showMessage(mailbox.remove());
		}
	}
	
	//adds a message to mailbox and displays all messages if logged in
	public void recieveMessage(String msg)
	{
		mailbox.add(msg);		//adds message to the mailbox
		if(myTraderWindow!=null)
		{
			while(mailbox.size()!=0)
			{
				myTraderWindow.showMessage(mailbox.remove());	//tells the traderwindow to display these messages
			}
		}
	}
	
	//returns password
	public String getPassword()
	{
		return traderPassword;
	}
	
	//places order by calling brokerage's placeOrder
	public void placeOrder(TradeOrder tradeorder)
	{
		myBrokerage.placeOrder(tradeorder);
	}
	
	//this method logs the trader out of the brokerage
	//it also deletes its traderWindow
	public void quit()
	{
		myBrokerage.logout(this);
		myTraderWindow=null;
	}
	
	
	//equal if screen names are the same
	public boolean equals(Object other)
	{
		if(other instanceof Trader)
		{
			if(((Trader)other).traderName.equals(traderName))
				return true;
		}
		return false;
	}
	
	
	//compares based on screen name
	public int compareTo(Trader trader2) 
	{
		System.out.println("this far");
		return traderName.compareTo(trader2.traderName);
	}
}

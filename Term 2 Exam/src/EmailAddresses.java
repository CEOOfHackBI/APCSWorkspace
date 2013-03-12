import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * 
 */

/**
 * @author croftj
 *Problem 3
 *
 */
public class EmailAddresses {

	/**
	 * @param args
	 */
	
	private Map<String,Set<String>> addressBook;
	
	public EmailAddresses(Map<String,Set<String>> m)
	{
		addressBook=m;
	}
	
	private void appendSetToQueue(Set items,Queue q)
	{
		Iterator i=items.iterator();
		
		while(i.hasNext())
		{
			q.add(i.next());
		}
	}
	
	public Set expandAlias(String alias)
	{
		Set<String> result=new HashSet<String>();
		
		Set keys=addressBook.keySet();
		Iterator i=keys.iterator();
		Queue holding=new LinkedList();
		
		
		while(i.hasNext())
		{
			appendSetToQueue(addressBook.get(i.next()),holding);
			while(!holding.isEmpty())
			{
				if(!addressBook.containsKey(holding.peek()))
					result.add((String)holding.remove());
				else
				{
					appendSetToQueue(addressBook.get(holding.remove()),holding);
				}
			}
				
		}
		
		return result;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
	}

}

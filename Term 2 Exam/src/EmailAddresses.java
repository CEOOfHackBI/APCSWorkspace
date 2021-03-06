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
	
			holding.add(alias);
			while(!holding.isEmpty())
			{
				if(!addressBook.containsKey(holding.peek()))
					result.add((String)holding.remove());
				else
				{
					appendSetToQueue(addressBook.get(holding.remove()),holding);
				}
			}
		
		return result;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		Map<String,Set<String>> m=new HashMap<String,Set<String>>();
		Set<String> t=new HashSet<String>();
		t.add("pat@ez.edu");
		t.add("chis@ez.edu");
		m.put("techstaff",t);
		Set<String> f=new HashSet<String>();
		f.add("bobby");
		f.add("ana");
		f.add("sam@ez.edu");
		m.put("faculty",f);
		Set<String> b=new HashSet<String>();
		b.add("bob@cs.org");
		m.put("bobby",b);
		Set<String> a=new HashSet<String>();
		a.add("ana@ez.edu");
		m.put("ana", a);
		Set<String> all=new HashSet<String>();
		all.add("phil@ez.edu");
		all.add("faculty");
		all.add("techstaff");
		m.put("all", all);
		
		
		EmailAddresses ea=new EmailAddresses(m);
		Set<String> s=	ea.expandAlias("bobby");
		System.out.println(s);
		
	}

}

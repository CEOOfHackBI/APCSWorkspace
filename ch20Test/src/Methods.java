import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author jack
 *
 */
public class Methods {

	//returns a Set of integers containing the intersection of Collections c1 and c2
	public Set<Integer> intersect(Collection<Integer> c1, Collection<Integer> c2)
	{
		Set<Integer> result=new TreeSet<Integer>();
		Iterator<Integer> i1=c1.iterator();
		
		while(i1.hasNext())
		{
			Integer one=i1.next();
			Iterator<Integer> i2=c2.iterator();
			while(i2.hasNext())
			{
				Integer two=i2.next();
				if(one.equals(two))
					result.add(one);
			}
		}
		return result;
	}
	
	//Prints out the elements of the list in reverse order
	public void reverse(List<Object> o)
	{
		ListIterator<Object> i=o.listIterator(o.size());
		while(i.hasPrevious())
		{
			System.out.println(i.previous());
		}
	}
	
	//removes all but one of any group of consecutive elements in a given list
	public void removeConsecDuplicates(List<String> list)
	{
		ListIterator<String> i=list.listIterator();
		
		while(i.hasNext())
		{
			String str=i.next();
			if(i.hasNext())
			{
				String str2=i.next();
			
				if(str.equals(str2))
					i.remove();
				
				i.previous();
			}
		}
	}
	
	//returns a Queue of Integers with every group of consecutive increasing elements in the original Queue in reverse order
	public Queue<Integer> reverseChunks(Queue<Integer> q)
	{
		Queue<Integer> result=new LinkedList<Integer>();
		Iterator<Integer> i=q.iterator();
		Stack<Integer> s=new Stack<Integer>();
		
		while(i.hasNext())
		{
			Integer next=i.next();
			
			if(s.isEmpty()||next>s.peek())
			{
				s.push(next);
			}
			else
			{
				while(!s.isEmpty())
				{
					result.add(s.pop());
				}
				s.push(next);
			}
		}
		while(!s.isEmpty())
			result.add(s.pop());
		
		return result;
	}
		
	//Declaration&initialization
	private Queue<PriorityQueue<Message>> messages=new LinkedList<PriorityQueue<Message>>();
	
	
	//returns next Message to be processed in messages or null if there are no messages
	public Message getNextMessage()
	{
		if(messages.isEmpty())
			return null;
		
		return messages.peek().remove();
	}
	
	//checks to see if a Student is assigned to a HomeRoom in the given Map, if not, the Student is assigned to the next HomeRoom
	//prints out each combination of Student and HomeRoom as well
	public void assign(Set<Student> students,Set<HomeRoom> rooms, Map<Student,HomeRoom> assignments)
	{
		Iterator<Student> i=students.iterator();
		Iterator<HomeRoom> j=rooms.iterator();
		
		while(i.hasNext())
		{
			Student next=i.next();
			if(assignments.containsKey(next))
			{
				System.out.println(next+": "+assignments.get(next));
			}
			else
			{
				if(j.hasNext())
				{
					HomeRoom h=j.next();
					assignments.put(next,h);
					System.out.println(next+": "+h);
				}
				else
				{
					j=rooms.iterator();
					HomeRoom h=j.next();
					assignments.put(next,h);
					System.out.println(next+": "+h);
				}
			}
		}
	}
}

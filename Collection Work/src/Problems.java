import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


public class Problems {

	/**
	 * @param args
	 */
	
	/*
	 * Problem #1
	 * 
	 * (a) will not compile
	 * (b) will compile
	 * (c) will compile
	 * (d) will not compile
	 * (e) will compile
	 * (f) will not compile
	 * (g) will not compile
	 * (h) will not compile
	 * 
	 */
	
	
	//Problem #2
	public <E> void append(List<E> list1, List<E> list2)
	{
		for(int i=0;i<list2.size();i++)
		{
			list1.add(list2.get(i));
		}
	}
	
	/*
	 * Problem #3
	 * 
	 * Iterators use the most efficient algorithms for going through lists, which is important when you could be going through either an ArrayList or LinkedList
	 * 
	 */
	
	
	//Problem #4(a)
	
	public <E> void appendA(List<E> list1, List<E> list2)
	{
		Iterator<E> i=list2.iterator();
		
		while(i.hasNext())
		{
			list1.add(i.next());
		}
	}
	
	//Problem #4(b)
	
	public <E> void appendB(List<E> list1, List<E> list2)
	{
		for(E e:list2)
		{
			list1.add(e);
		}
	}
	
	//Problem #5
	
	public LinkedList<String> mix(List<String> list1, List<String> list2)
	{
		Iterator<String> i=list1.iterator();
		Iterator<String> j=list2.iterator();
		LinkedList<String> result=new LinkedList<String>();
		
		while(i.hasNext()&&j.hasNext())
		{
			result.add(i.next());
			result.add(j.next());
		}
		return result;
	}
	
	//Problem #6
	
	public double sum2(List<Double> list)
	{
		double sum=0;
		
		ListIterator<Double> i=list.listIterator();
		
		while(i.hasNext())
		{
			ListIterator<Double> j= list.listIterator(i.nextIndex());
			
			while(j.hasNext())
			{
				sum+=i.next()*j.next();
			}
		}
		return sum;
	}
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
	}

}

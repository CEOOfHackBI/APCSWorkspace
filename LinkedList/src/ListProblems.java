/**
 * 
 */

/**
 * @author jack
 *
 */
public class ListProblems {

	/**
	 * @param args
	 */
	
	//Problem 2
	
	public boolean hasTwo(ListNode head)
	{
		return head.getNext()!=null;
	}
	
	//Problem 3a
	
	public int sizeFor(ListNode head)
	{
		int counter=0;
		for(ListNode node=head;node!=null;node=node.getNext())
		{
			counter++;
		}
		return counter;
	}
	
	//Problem 3b
	
	public int sizeRecu(ListNode head)
	{
		if(head.getNext()!=null)
			return sizeRecu(head.getNext())+1;
		else
			return 1;
	}
	
	
	public ListNode reverseList(ListNode head)
	{
		ListNode newHead=null;
		
		for(ListNode node=head;node!=null;node=node.getNext())
		{
			
		}
		
		return newHead;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ListProblems lp=new ListProblems();
		SinglyLinkedList s= new SinglyLinkedList();
		ListNode one=null;
		ListNode two=null;
		ListNode three=null;
		s.add("cool");
		s.add("neat");
		s.add("sweet");
		three=new ListNode("sweet",null);
		two=new ListNode("neat",three);
		one=new ListNode("cool",two);
		System.out.println(lp.sizeRecu(one));
		
		
	}

}

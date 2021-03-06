/**
 * 
 */

/**
 * @author jack
 *
 */
public class LinkedListWithTail {

	/**
	 * @param args
	 */
	
	private ListNode head;
	private ListNode tail;
	
	
	public LinkedListWithTail()
	{
		head=null;
		tail=null;
	}
	
	public boolean isEmpty()
	{
		return head==null;
	}
	
	public Object peek()
	{
		if(isEmpty())
			return null;
		return head.getValue();
	}
	
	public Object remove()
	{
		if(isEmpty())
			return null;
		
		Object result=head.getValue();
		head=head.getNext();
		return result;
	}
	
	public boolean add(Object obj)
	{
		if(isEmpty())
		{
			head=new ListNode(obj,tail);
		}
		else
		{
			tail.setNext(new ListNode(obj,null));
		}
	
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

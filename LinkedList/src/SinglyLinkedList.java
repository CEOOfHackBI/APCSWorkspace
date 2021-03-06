/**
 * @author croftj
 * Date of Completion: 4/5/13
 * Attribution:none
 * All work here is honestly obtained and is my own
 */


// Implements a singly-linked list.

import java.util.Iterator;

public class SinglyLinkedList implements Iterable<Object>
{
  private ListNode head;
  private int nodeCount;

  // Constructor: creates an empty list
  public SinglyLinkedList()
  {
    head = null;
    nodeCount = 0;
  }

  // Constructor: creates a list that contains
  // all elements from the array values, in the same order
  public SinglyLinkedList(Object[] values)
  {
    ListNode tail = null;
    for (Object value : values) // for each value to insert
    {
      ListNode node = new ListNode(value, null);
      if (head == null)
        head = node;
      else
        tail.setNext(node);
      tail = node;    // update tail
    }

    nodeCount = values.length;
  }
  
  private boolean inBounds(int i)
  {
	  if(nodeCount==0)
		  return i==0;
	  
	  return i<nodeCount&&i>=0;
  }

  // Returns true if this list is empty; otherwise returns false.
  public boolean isEmpty()
  {
    return nodeCount==0;
  }

  // Returns the number of elements in this list.
  public int size()
  {
    return nodeCount;
  }

  // Returns true if this list contains an element equal to obj;
  // otherwise returns false.
  public boolean contains(Object obj)
  {
	  return indexOf(obj)>=0;
  }

  // Returns the index of the first element in equal to obj;
  // if not found, returns -1.
  public int indexOf(Object obj)
  {
	  	int counter=0;
	  	for(ListNode node=head;node!=null;node=node.getNext(),counter++)
	  	{
	  		if(obj.equals(node.getValue()))
	  			return counter;
	  	}
		 
	  	return -1;
  }

  // Adds obj to this collection.  Returns true if successful;
  // otherwise returns false.
  public boolean add(Object obj)
  {
	  add(nodeCount,obj);
	  
	  return true;
  }

  // Removes the first element that is equal to obj, if any.
  // Returns true if successful; otherwise returns false.
  public Object remove(Object obj)
  {
	  Object result=null;
	  ListNode before=head;
	  
	  for(ListNode node=head;node!=null;node=node.getNext())
	  {
		  if(node.getValue().equals(obj))
		  {
			  if(node==head)
			  {
				  result=head.getValue();
				  head=head.getNext();
			  }
			  else
			  {
				  result=node.getValue();
			  	  before.setNext(node.getNext());
			  }
			  nodeCount--;
		  }
		  else
			  before=node;
	  }
	  
	  return result;
  }

  // Returns the i-th element.
  public Object get(int i)
  {
	  if(!inBounds(i))
		  return null;
	  
	  int counter=0;
	  Object result=null;
	  
	  for(ListNode node=head;node!=null;node=node.getNext(),counter++)
	  {
		  if(counter==i)
			  result=node.getValue();
	  }
	  
	  return result;
  }

  // Replaces the i-th element with obj and returns the old value.
  public Object set(int i, Object obj)
  {
	  
	  if(!inBounds(i))
		  return null;
	  
	  int counter=0;
	  Object result=null;
	  
	  for(ListNode node=head;node!=null;node=node.getNext(),counter++)
	  {
		  if(counter==i)
		  {
			  result=node.getValue();
			  node.setValue(obj);
		  }
	  }
	  
	  return result;
  }

  // Inserts obj to become the i-th element. Increments the size
  // of the list by one.
  public void add(int i, Object obj)
  {
	  if(nodeCount==0&&i!=0)
		  return;
	  if(!(i<=nodeCount&&i>=0))
		  return;
	  
	  int counter=0;
	  ListNode before=head;
	  
	  if(head==null)
	  {
		  head=new ListNode(obj,null);
		  nodeCount++;
		  return;
	  }
	 
	  for(ListNode node=head;counter<=nodeCount;node=node.getNext(),counter++)
	  {
		  if(counter==i)
		  {  
			  if(i==nodeCount)
			  {
				  before.setNext(new ListNode(obj, null));
			  }
			  else if(i==0)
			  {
				  head=new ListNode(obj,head);
			  }
			  else
			  {
				  before.setNext(new ListNode(obj,node));
			  }
			  nodeCount++;
			  return;
		  }
		  else
		  {
			  before=node;
		  }
	  }
  }

  // Removes the i-th element and returns its value.
  // Decrements the size of the list by one.
  public Object remove(int i)
  {
	  if(!inBounds(i))
		  return null;
	  
	  int counter=0;
	  Object result=null;
	  ListNode before=head;
	  
	  for(ListNode node=head;node!=null;node=node.getNext(),counter++)
	  {
		  if(counter==i)
		  {
			  if(counter==0)
			  {
				  result=head.getValue();
				  head=head.getNext();
			  }
			  else
			  {
				  result=node.getValue();
				  before.setNext(node.getNext());
			  }
			  nodeCount--;
		  }
		  else
		  {
			  before=node;
		  }
	  }
	  
	  return result;
  }

  // Returns a string representation of this list.
  public String toString()
  {
	  String result="[";
	  
	  for(ListNode node=head;node!=null;node=node.getNext())
	  {
		  result+=node.getValue().toString();
		  
		  if(node.getNext()!=null)
			  result+=", ";
		  
	  }
	  result+="]";
	  
	  
	  return result;
  }

  // Returns an iterator for this collection.
  public Iterator<Object> iterator()
  {
    return new SinglyLinkedListIterator(head);
  }

  //comprehensive testing all output and copied into test file
  public static void main(String[] args)
  {
	  SinglyLinkedList list=new SinglyLinkedList();
	  System.out.println(list.isEmpty()); //should be true
	  
	  list.add("cool");
	  list.add("beans");
	  list.add("thanks");
	  list.add("man");
	  
	  System.out.println(list); //should be [cool, beans, thanks, man]
	  System.out.println(list.size());	//should be 4
	  System.out.println(list.contains("cool")); //should be true
	  System.out.println(list.contains("fdkj")); //should be false
	  System.out.println(list.indexOf("thanks")); //should be 2
	  System.out.println(list.indexOf("hadskj")); //should be -1
	  
	  list.remove("cool");
	  System.out.println(list); //should be [beans, thanks, man]
	  
	  list.add(0,"cool");
	  System.out.println(list); //should be [cool, beans, thanks, man]
	  System.out.println(list.get(1)); //should be beans
	  
	  list.set(3, "maybe");
	  System.out.println(list); //should be [cool, beans, thanks, maybe]
	  
	  list.remove(3);
	  System.out.println(list); //should be [cool, beans, thanks]
  }
}
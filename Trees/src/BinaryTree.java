/**
 * 
 */

/**
 * @author jack
 *
 */
public class BinaryTree {

	/**
	 * @param args
	 */
	
	private TreeNode root;
	
	
	public BinaryTree()
	{
		root=null;
	}
	
	//get the number of nodes
	public int nodeCount()
	{
		return nodeCount(root);
	}
	
	//recursive helper counter method
	private int nodeCount(TreeNode r)
	{
		if(r==null)
			return 0;
		
		return nodeCount(r.getLeft())+nodeCount(r.getRight())+1;
	}
	
	//Traversals=prefix-node,left,right postfix-left,right,node infix-right,left,node
	
	//prints out node, then left, then right
	public void prefix()
	{
		recPrefix(root);
	}
	
	private void recPrefix(TreeNode r)
	{
		if(r==null)
			return;
		
		System.out.println(r.getValue());
		recPrefix(r.getLeft());
		recPrefix(r.getRight());
	}
	
	public void infix()
	{
		recInfix(root);
	}
	
	private void recInfix(TreeNode r)
	{
		if(r==null)
			return;
		
		recPrefix(r.getRight());
		recPrefix(r.getLeft());
		System.out.println(r.getValue());
	}
	
	public void postfix()
	{
		recPostfix(root);
	}
	
	private void recPostfix(TreeNode r)
	{
		if(r==null)
			return;
		
		recPrefix(r.getLeft());
		recPrefix(r.getRight());
		System.out.println(r.getValue());
	}
	
	//count number of leaves in Tree
	public int numLeaves()
	{
		return numLeaves(root);
	}
	
	private int numLeaves(TreeNode node)
	{
		if(node==null)
			return 0;
		
		if(node.getLeft()==null&&node.getRight()==null)	//leaf
			return 1;
		
		return numLeaves(node.getLeft())+numLeaves(node.getRight());
	}
	
	//adds an object to the tree
	public boolean add(Object value)
	{
		root=add(root,value);
		return true;
	}
	
	private TreeNode add(TreeNode node,Object value)
	{
		if (node==null)
		      node=new TreeNode(value);
		else
		{
		   int  diff = ((Comparable<Object>)value).compareTo(node.getValue());
		   if (diff < 0)
		     node.setLeft(add(node.getLeft(), value));
		   else 
		     node.setRight(add(node.getRight(), value));
		}
		return node;
	}
	
	//returns true if object is in the tree, false otherwise
	public boolean contains(Object value)
	{
		return get(value)!=null;
	}
	
	//returns a reference to the node of a specific object, returns null if not there
	public TreeNode get(Object value)
	{
		if(root==null)
			return null;
		
		return get(root,value);
	}
	
	private TreeNode get(TreeNode node, Object value)
	{
		if(node==null)
			return null;
		
		int diff=((Comparable<Object>)value).compareTo(node.getValue());
		
		if(diff==0)
			return node;
		if(diff>0)
			return get(node.getLeft(),value);
		else
			return get(node.getRight(),value);
	}
	
	//returns a sum of all the nodes, assuming they are integers
	public int sum()
	{
		return sum(root,0);
	}
	
	private int sum(TreeNode node,int sum)
	{
		if(node==null)
			return sum;
		
		return sum+=sum(node.getRight(),sum)+sum(node.getLeft(),sum)+(Integer)node.getValue();
	}
	
	public int depth()
	{
		return depth(root,0);
	}
	
	private int depth(TreeNode node,int depth)
	{
		if(node==null)
			return depth;
		
		return Math.max(depth(node.getLeft(),depth+1), depth(node.getRight(),depth+1));
	}
	
	//finds the biggest value in the tree
	public Object max()
	{
		if(root==null)
			return null;
		
		return max(root,root.getValue());
	}
	
	private Object max(TreeNode node,Object max)
	{
		if(node.getRight()==null)
			return node.getValue();
		
		return max(node.getRight(),max);
	}
	
	
	//finds the smallest value in the tree
	public Object min()
	{
		if(root==null)
			return null;
		
		return min(root,root.getValue());
	}
	
	private Object min(TreeNode node,Object min)
	{
		if(node.getLeft()==null)
			return node.getValue();
		
		return min(node.getLeft(),min);
	}
	
	//returns a string containing a all the values on a particular level
	public String printLevel(int level)
	{
		if(root==null)
			return "";
		
		String str= printLevel(root,level,0,"");
		return str.substring(0,str.length()-2);
				
	}
	
	private String printLevel(TreeNode node, int level, int current,String str)
	{
		if(node==null)
			return "";
		
		if(current==level)
			return str+=node.getValue().toString()+", ";
	
		
		return printLevel(node.getRight(),level,current+1,str)+printLevel(node.getLeft(),level,current+1,str);
	}
	//adds an object to the tree in an iterative fashion
	public boolean addIterative(Object value)
	{
		boolean finished=false;
		TreeNode node=root;
		TreeNode newNode=new TreeNode(value);
		
		if(root==null)
			root=newNode;
		
		else
		{
			while(!finished)
			{
				if(((Comparable<Object>) value).compareTo(node.getValue())<0)
				{
					if(node.getLeft()==null)
					{
						node.setLeft(newNode);
						finished=true;
					}
					else
						node=node.getLeft();
				}
				else
				{
					if(node.getRight()==null)
					{
						finished=true;
						node.setRight(newNode);
					}
					else
						node=node.getRight();
				}
			}
		}
		return true;	
	}
	//returns true if the tree is fully balanced, with the right and left subtrees being equally big all the way down, false otherwise
	public boolean fullyBalanced()
	{
		int depth=depth();
		
		int counter=0;
		
		for(int i=0;i<depth;i++)
		{
			counter+=Math.pow(2, i);
		}
		return counter==nodeCount();
	}
	//returns the greatest value in an unsorted tree
	public Object maxUnsorted()
	{
		return maxUnsorted(root);
	}
	
	private Object maxUnsorted(TreeNode node)
	{
		if(node==null)
			return null;
		
		if(node.getLeft()==null&&node.getRight()==null)
			return node.getValue();
		
		Integer diffLeft=null;
		Integer diffRight=null;
		Object maxLeft=maxUnsorted(node.getLeft());
		Object maxRight=maxUnsorted(node.getRight());
		
		if(node.getLeft()!=null)
			diffLeft=((Comparable<Object>) node.getValue()).compareTo(maxLeft);
		
		if(node.getRight()!=null)
			diffRight=((Comparable<Object>) node.getValue()).compareTo(maxRight);
		
		if(diffLeft==null)
		{
			if(diffRight>0)
				return node.getValue();
			
			return maxRight;
		}
		
		if(diffRight==null)
		{
			if(diffLeft>0)
				return node.getValue();
			
			return maxLeft;
		}
		
		int diffBetween=((Comparable<Object>) maxRight).compareTo(maxLeft);
		
		if(diffBetween>0)
			return maxRight;
		
		return maxLeft;
	}
	//returns the smallest value in an unsorted tree
	public Object minUnsorted()
	{
		return minUnsorted(root);
	}
	
	private Object minUnsorted(TreeNode node)
	{
		if(node==null)
			return null;
		
		if(node.getLeft()==null&&node.getRight()==null)
			return node.getValue();
		
		Integer diffLeft=null;
		Integer diffRight=null;
		Object minLeft=minUnsorted(node.getLeft());
		Object minRight=minUnsorted(node.getRight());
		
		if(node.getLeft()!=null)
			diffLeft=((Comparable<Object>) node.getValue()).compareTo(minLeft);
		
		if(node.getRight()!=null)
			diffRight=((Comparable<Object>) node.getValue()).compareTo(minRight);
		
		if(diffLeft==null)
		{
			if(diffRight<0)
				return node.getValue();
			
			return minRight;
		}
		
		if(diffRight==null)
		{
			if(diffLeft<0)
				return node.getValue();
			
			return minLeft;
		}
		
		int diffBetween=((Comparable<Object>) minRight).compareTo(minLeft);
		
		if(diffBetween<0)
			return minRight;
		
		return minLeft;
	}
	//returns true if in order, false otherwise
	public boolean ordered()
	{
		return ordered(root);
	}
	
	private boolean ordered(TreeNode node)
	{
		if(node==null)
			return true;
		
		if(node.getRight()==null&&node.getLeft()==null)
			return true;
		
		if(node.getRight()==null)
		{
			if(((Comparable<Object>) node.getLeft().getValue()).compareTo(node.getValue())<0)
				return ordered(node.getLeft());
			else
				return false;
		}
		
		if(node.getLeft()==null)
		{
			if(((Comparable<Object>) node.getRight().getValue()).compareTo(node.getValue())>0)
				return ordered(node.getRight());
			else
				return false;
		}
		
		if((((Comparable<Object>) node.getRight().getValue()).compareTo(node.getValue())>0)&&(((Comparable<Object>) node.getLeft().getValue()).compareTo(node.getValue())<0))
			return ordered(node.getRight())&&ordered(node.getLeft());
		else
			return false;
	}
	
	//returns a string of all the values in the tree
	public String toString()
	{
		String str=toString(root);
		if(str.startsWith(" ,"));
		str=str.substring(2);
		str="["+str+"]";
		return str;
	}
	
	public String toString(TreeNode node)
	{
		if(node==null)
			return"";
			
		return toString(node.getLeft())+", "+node.getValue().toString()+toString(node.getRight());
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTree bt=new BinaryTree();
		BinaryTree bt2=new BinaryTree();
		bt.add("cool");
		bt.add("beans");
		bt.add("thanks");
		bt.add("man");
		bt2.addIterative(5);
		bt2.addIterative(6);
		bt2.addIterative(3);
		System.out.println(bt);	//should be [beans, cool, man, thanks]
		System.out.println(bt2); //should be[3, 5, 6]
		System.out.println(bt.contains("cool")); //should be true
		System.out.println(bt.contains("sweet"));	//should be false
		System.out.println(bt.get("cool"));	//should be an object reference
		System.out.println(bt.get("sweet"));	//should be null
		System.out.println(bt2.sum());	//should be 14
		System.out.println(bt.depth());	//should be 3
		System.out.println(bt.nodeCount());	//should be 4
		System.out.println(bt.max());    //should be thanks
		System.out.println(bt.min());	//should be beans
		System.out.println(bt.printLevel(0));	//should be cool
		System.out.println(bt.printLevel(1));   //should be thanks, beans
		System.out.println(bt.numLeaves());   //should be 2
		System.out.println(bt.fullyBalanced());  //should be false
		System.out.println(bt2.fullyBalanced());  //should be true
		System.out.println(bt.maxUnsorted());	//should be thanks
		System.out.println(bt.minUnsorted());   //should be beans
		System.out.println(bt.ordered());   //should be true
	}
}
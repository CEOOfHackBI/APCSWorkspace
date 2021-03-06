// 

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;


/**
 *  In this program Cookie Monster finds the optimal path from 
 *  the upper left corner (0,0) to the lower right corner
 *  (SIZE-1,SIZE-1) in a cookie array.  The elements of
 *  the array contain cookies (a non-negative number) or barrels
 *  (-1).  On each step Cookie Monster can only go down or
 *  to the right.  He is not allowed to step on barrels. 
 *  The optimal path contains the largest number of cookies.
 *   
 *  The program prompts the user for a file name,
 *  reads the cookie array from the file, and reports the
 *  number of cookies on the optimal path. Assumed size of the
 *  grid of values i 12 x 12, stored in row-major order.
 *  
 *  Bonus:  Adapt the program to read 2 ints from the file first
 *  representing numRows and numCols, and then read all values into
 *  a 2-d array.  (Consult FloodFill project for an example.)
 *  
 *  The program also reports the actual optimal path, location
 *  by location.
 *  
 *  Finally, the program will output every successful path found,
 *  along with the total cookies along that path.
 */


public class CookieMonsterStarter
{
  private final int SIZE = 12; //Can be altered for different files. 
  private int[][] cookies = new int[SIZE][SIZE];
  
  
  //Location class to represent a single point in the grid
  public class Location
  {
	  private int row;	
	  private int col;	
	  
	  public Location(int row,int col)	//regular constructor
	  {
		  this.row=row;
		  this.col=col;
	  }
	  
	  public Location(Location l)		//copy constructor
	  {
		  this.row=l.row;
		  this.col=l.col;
	  }
	  
	  public int getRow()	//returns row of location
	  {
		  return row;
	  }
	  
	  public int getCol()	//returns col of location
	  {
		  return col;
	  }
	  
	  public int cookiesAtLocation()	//returns number of cookies at the specific point
	  {
		  return cookies[row][col];
	  }
	  
	  public String toString()		//prints out location in form (x, y)
	  {
		  return "("+row+ ", "+col+")";
	  }
  }
  
  
  
  //Path class to represent a series of locations to the end
  public class Path
  {
	  private Queue<Location> locations=new LinkedList<Location>();	//series of locations
	  private int pathTotal=0;	//current path total
	  private Location current;	//final location of path
	  
	  public Path()	//default constructor
	  {
		  
	  }
	  
	  public Path(Path p)	//copy constructor
	  {
		  this.locations=new LinkedList<Location>(p.locations);
		  this.pathTotal=p.pathTotal;
		  this.current=new Location(p.current);
	  }
	  
	  public int getPathTotal()	//returns total number of cookies up to current spot
	  {
		  return pathTotal;
	  }
	  
	  public Location getLocation()	//returns current spot of path
	  {
		  return current;
	  }
	  
	  public void add(Location l)	//adds a new location to queue, updates number of cookies, and updates current location
	  {
		  locations.add(l);
		  pathTotal+=cookies[l.getRow()][ l.getCol()];
		  current=l;
	  }
	  
	  public Queue<Location> getPath()		//returns entire path
	  {
		  return locations;
	  }
	  
	  public String toString()	//prints out locations of path then number of cookies such as (x, y), (x,y+1), cookies=11
	  {
		  String s="";
		  
		  for(int i=0;i<locations.size();i++)
		  {
			  Location l=locations.remove();
			  s+=l.toString();
			  s+=", ";
			  locations.add(l);
		  }
		  s+="cookies="+pathTotal;
		  return s;
	  }
  }
    
  /**
   *  Reads cookies from file
   */
  private void loadCookies(Scanner input)
  {  
    for (int row = 0;   row < SIZE;   row++)  
      for (int col = 0;   col < SIZE;   col++)  
        cookies[row][col] = input.nextInt();  
  }  

  /**
   *  Returns true if (row, col) is within the array and that position is
   *  not a barrel (-1); false otherwise
   *  
   */
  private boolean goodPoint(int row, int col)  
  {  
	  return row<SIZE&&col<SIZE&&cookies[row][col]>=0;  
  }
  private boolean goodPoint(Location l)
  {
	  return l.getRow()<SIZE&&l.getCol()<SIZE&&cookies[l.getRow()][l.getCol()]>=0;  
  }

  /**
   *  Returns the largest number of cookies collected by Monster
   *  on a path from (0,0) to (SIZE-1, SIZE-1)
   */
  
  private int optimalPath()  
  {  
	  // LOTS OF STUFF FOR HERE! Plan first!
	  //go through, check down then proceed if possible and add right location and path to stack, if not move right and repeat
	  //print out paths as you go
	  //keep track of cookies on each path also
	  
	  
	  Stack<Path> pathStack=new Stack<Path>();				//stack of paths to be looked at
	  Location currentLoc=new Location(0,0);				//current location of current path
	  Path currentPath=new Path();							//current path being looked at
	  ArrayList<Path> allPaths=new ArrayList<Path>();		//list of all possible paths
	  Path bestPath=new Path();								//best path
	  
	  if(!goodPoint(0,0))	//don't do anything if original isn't good
		  return 0;
	  
	  allPaths.add(new Path());					//original path with starting point
	  allPaths.get(0).add(new Location(0,0));

	  pathStack.push(allPaths.get(0));
	  
	  while(!pathStack.isEmpty())		//stop condition is empty stack
	  {
		  currentPath=pathStack.pop();				//update current path and location
		  currentLoc=currentPath.getLocation();
		  
		  if(goodPoint(currentLoc))		//checking if good point
		  {
			  if(currentLoc.getRow()==SIZE-1&&currentLoc.getCol()==SIZE-1)	//checking if end of path
			  {
				  if(currentPath.getPathTotal()>bestPath.getPathTotal())	//check for more cookies on current path
					  bestPath=currentPath;
				  
				  System.out.println(currentPath);
			  }
			  else
			  {
				  boolean down=goodPoint(currentLoc.getRow()+1,currentLoc.getCol());		//checking both down and right locations
				  boolean right=goodPoint(currentLoc.getRow(),currentLoc.getCol()+1);
				  
				  if(down&&right)		//if both, create new path, add points to both current and new and push them onto stack
				  {
					  Path p=new Path(currentPath);
					  p.add(new Location(currentLoc.getRow(),currentLoc.getCol()+1));
					  allPaths.add(p);
					  
					  currentPath.add(new Location(currentLoc.getRow()+1,currentLoc.getCol()));
					  
					  pathStack.push(p);
					  pathStack.push(currentPath);
				  }
				  
				  else if(down)		//if down, update path and push back onto stack
				  {
					  currentPath.add(new Location(currentLoc.getRow()+1,currentLoc.getCol()));
					  pathStack.push(currentPath);
				  }
				  else if(right)	//if right, update path and push back onto stack
				  {
					  currentPath.add(new Location(currentLoc.getRow(),currentLoc.getCol()+1));
					  pathStack.push(currentPath);
				  }
			  }
			  
		  }
	  }
	  System.out.println("Best Path: "+bestPath.getPath());
	  return bestPath.getPathTotal();
  }
  
  
  
  /**  The following is something we coded together in Ch20 work:
  *		E  is an Element Type
  * 	It is a Static method:  to activate it...
  *      in another class:  someotherq= CoookieMonster.copy(someq);
  *      in this class:   		 newq = copy(q);
  *      */
  public static <E>  Queue<E>   copy(Queue<E> q){
	  
	  Queue<E> q2 = new LinkedList<E>();
	  
	  if (!q.isEmpty()){
		  
		   E obj = q.remove();
		   E first = obj;
		   q2.add(obj);
		   q.add(obj);
		   
		   while (q.peek() != first) {
			   obj = q.remove();
			   q.add(obj);
			   q2.add(obj);
		   }  
	  }
	  
	  return q2;
  }
  
  

  public static void main(String args[])
  {  // Adapt this as you see fit.
    String fileName;

    if (args.length >= 1)
    {
      fileName = args[0];
    }
    else
    {
      Scanner kboard = new Scanner(System.in);
      System.out.print("Enter the cookies file name: ");
      fileName = kboard.nextLine();
     }

    File file = new File(fileName);
    Scanner input = null;
    try
    {
      input = new Scanner(file);
    }
    catch (FileNotFoundException ex)
    {
      System.out.println("Cannot open " + fileName);
      System.exit(1);
    }

    CookieMonsterStarter monster = new CookieMonsterStarter();
    monster.loadCookies(input);
    System.out.println("Optimal path has " +
                                  monster.optimalPath() + " cookies.\n");
  }
}
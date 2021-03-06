
/**
 * Write a description of class LifeGrid here.
 * 
 * @author croftj
 * Date of Completion: 12/10/12
 * Assignment: Life Grid class for Game of Life project
 * Attribution: none
 * All work here is honestly obtained and is my own.
 * Provides a 2D array for the Game of Life
 * 
 */

public class LifeGrid
{
	//2D array and ALIVE AND DEAD variables to replace 1 and 0
   private int[][] grid;
   private final int ALIVE=1;
   private final int DEAD=0;
   
   
   //@param rows-number of rows
   //@param col-number of cols
   //basic constructor
   public LifeGrid(int rows, int cols)
   {
	   grid=new int[rows][cols];
   }
	
   
 //@return-number of rows
   public int getNumRows()
   {
	   return grid.length;
   }
   
   //@return-number of cols
   public int getNumCols()
   {
	   return grid[0].length;
   }
   
 //@param row-specfic row
 //@param col-specific col
   //@return-value at(row,col)
   public int getCell(int row, int col)
   {
	   return grid[row][col];
   }
   
   //@param row-specfic row
   //@param col-specific col
   //@param value-value to replace old value
   public void setCell(int row, int col, int value)
   {
	   grid[row][col]=value;
   }
   
   //moves the grid one step forward in its progression
   public void evolve()
   {
	  int[][] temp=new int[grid.length][grid[0].length];		//temp array to prevent changes from happening too early
	   
	  for(int i=0;i<grid.length;i++)		//for loops to go through each spot
	  {
		  for(int j=0;j<grid[0].length;j++)
		  {
			  int neighbors=countNeighbors(i,j);
			  
			  if(grid[i][j]==ALIVE)		//if alive check if continues to live
			  {
				  if(!(neighbors==2||neighbors==3))
				  {
					  temp[i][j]=DEAD;
				  }
				  else
					  temp[i][j]=ALIVE;
			  }
			  else		//if dead, check to see if it lives
			  {
				  if(neighbors==3)
					  temp[i][j]=ALIVE;
				  else
					  temp[i][j]=DEAD;
			  }
		  }
	  }
	  grid=temp;		//set grid to temp
   }
   
   //@param row-specific row
   //@param col-specific col
   //@return-number of living neighbors around a position
   private int countNeighbors(int row, int col)
   {
	   int counter=0;
	   int i=0,j=0;
	   int iEnd=3, jEnd=3;
	   int gridLength=grid.length;
	   int gridHeight=grid[0].length;
	   
	   
	   if(row==0)		//check to see if at top
		   i=1;
	   if(row==gridLength-1)		//check for bottom
	   		iEnd=2;
	   	
	   for(;i<iEnd;i++)
	   {
		   if(col==0)		//check for left
			   j=1;
		   else
			   j=0;
		   if(col==gridHeight-1)		//check for right
		   		jEnd=2;
		   else
			   jEnd=3;
		   for(;j<jEnd;j++)
		   {
			   if(grid[row-1+i][col-1+j]==ALIVE&&!(i==1&&j==1))		//check if alive and not at itself
				   counter++;
		   }
	   }
	   
	  return counter; 
	   
   }
}


/**
 * Write a description of class LifeGrid here.
 * 
 * @author croftj
 * @version (a version number or a date)
 * Lets try changing this and see what happens!!
 */

public class LifeGrid
{
   private int[][] grid;
   private final int ALIVE=1;
   private final int DEAD=0;
   
   
   public LifeGrid(int rows, int cols)
   {
	   grid=new int[rows][cols];
   }
	
   public int getNumRows()
   {
	   return grid.length;
   }
   
   public int getNumCols()
   {
	   return grid[0].length;
   }
   
   public int getCell(int row, int col)
   {
	   return grid[row][col];
   }
   
   public void setCell(int row, int col, int value)
   {
	   grid[row][col]=value;
   }
   
   public void evolve()
   {
	  int[][] temp=new int[grid.length][grid[0].length];
	   
	  for(int i=0;i<grid.length;i++)
	  {
		  for(int j=0;j<grid[0].length;j++)
		  {
			  int neighbors=countNeighbors(i,j);
			  
			  if(grid[i][j]==1)
			  {
				  if(!(neighbors==2||neighbors==3))
				  {
					  temp[i][j]=0;
				  }
				  else
					  temp[i][j]=1;
			  }
			  else
			  {
				  if(neighbors==3)
					  temp[i][j]=1;
				  else
					  temp[i][j]=0;
			  }
		  }
	  }
	  grid=temp;
   }
   
   private int countNeighbors(int row, int col)
   {
	   int counter=0;
	   int i=0,j=0;
	   int iEnd=3, jEnd=3;
	   int gridLength=grid.length;
	   int gridHeight=grid[0].length;
	   
	   
	   if(row==0)
		   i=1;
	   if(row==gridLength-1)
	   		iEnd=2;
	   	
	   for(;i<iEnd;i++)
	   {
		   if(col==0)
			   j=1;
		   else
			   j=0;
		   if(col==gridHeight-1)
		   		jEnd=2;
		   else
			   jEnd=3;
		   for(;j<jEnd;j++)
		   {
			   if(grid[row-1+i][col-1+j]==1&&!(i==1&&j==1))
				   counter++;
		   }
	   }
	   
	  return counter; 
	   
   }
}

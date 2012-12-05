
/**
 * Write a description of class LifeGrid here.
 * 
 * @author croftj
 * @version (a version number or a date)
 * Lets try changing this
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
					  System.out.println(neighbors);
					  temp[i][j]=0;
				  }
			  }
			  else
			  {
				  if(neighbors==3)
					  temp[i][j]=1;
			  }
		  }
	  }
	  grid=temp;
   }
   
   private int countNeighbors(int row, int col)
   {
//	   int counter=0;
//	   
//	   if(row==0||col==0)
//		   return countBorderNeighbors(row, col);
//	   
//	   for(int i=0;i<3;i++)
//	   {
//		   for(int j=0;j<3;j++)
//		   {
//			   if(grid[row-1+i][col-1+i]==1)
//				   counter++;
//		   }
//	   }
//	   
//	   return counter;
	   
	   int counter=0;
	   int i=0,j=0;
	   int iEnd=3, jEnd=3;
	   
	   
	   if(row==0)
		   i=1;
	   if(col==0)
		   j=1;
	   if(row==grid.length-1);
	   		iEnd=2;
	   	if(col==grid[0].length-1)
	   		jEnd=2;
	   	
	   for(;i<iEnd;i++)
	   {
		   for(;j<jEnd;j++)
		   {
			   if(grid[row-1+i][col-1+j]==1&&i!=row&&j!=col)
				   counter++;
		   }
	   }
	   
	  return counter; 
	   
   }
}

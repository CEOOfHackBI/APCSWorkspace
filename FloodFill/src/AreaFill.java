

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AreaFill extends JPanel
    implements MouseListener
{

  private final char WHITE = '.';
  private final char BLACK = 'x';
  private char[][] board;	 
  private Color[][] plane;
  
  public AreaFill(String fileName)
  {
    EasyReader file = new EasyReader(fileName);
    if (file.bad())
    {
      System.out.println("Cannot open " + fileName);
      System.exit(1);
    }
  
    int nRows = file.readInt();
    int nCols = file.readInt();
    file.readLine();
   
    board = new char[nRows][nCols];
    int row, col;
    for (row = 0; row < nRows; row++)
    {
      for (col = 0; col < nCols; col++)
        board[row][col] = file.readChar();
      file.readLine();
    }

    file.close();

    plane = new Color[nRows][nCols];
    for (int r = 0; r < board.length; r++)
    {
      for (int c = 0; c < board[0].length; c++)
      {
        if (board[r][c] != '.')
          plane[r][c] = Color.WHITE;
        else
          plane[r][c] = Color.BLACK;
      }
    }

    setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    addMouseListener(this);
  }

  // A helper method for fillPool
  private boolean isValidLocation(Color[][] plane, int row, int col)
  {
    return row >= 0 && row < plane.length && 
           col >= 0 && col < plane[0].length;
  }

  // Fills with a given color a contiguous pool of cells that is
  // surrounded by a contour of WHITE cells.  row, col is a
  // location inside the pool.
  private void fillPool(Color[][] plane, int row, int col, Color color)
  {
	  if(isValidLocation(plane,row,col))
	  {	  
		  Color clickedColor=plane[row][col];
		  
		  if(plane[row][col].equals(clickedColor))
		  
		  plane[row][col]=color;
		  
		  if(isValidLocation(plane,row-1,col)&&plane[row-1][col].equals(clickedColor))
			  fillPool(plane,row-1,col,color);
		  
		  if(isValidLocation(plane,row,col+1)&&plane[row][col+1].equals(clickedColor))
			  fillPool(plane,row,col+1,color);
			  
		  if(isValidLocation(plane,row,col-1)&&plane[row][col-1].equals(clickedColor))
			  fillPool(plane,row,col-1,color);
			  
		  if(isValidLocation(plane,row+1,col)&&plane[row+1][col].equals(clickedColor))
			  fillPool(plane,row+1,col,color);
	  }
  }
  

  // Fills pool with Color.BLUE color.
  private void fillPool(Color[][] plane, int row, int col)
  {
    fillPool(plane, row, col, Color.BLUE);
  }

/**
   *  Prints board to System console or Terminal window.
   */
  private void toConsole()
  {
      int r,c;
      for (r = 0; r < board.length; r++)
      {
        for (c = 0; c < board[0].length; c++)
            System.out.print(board[r][c]);
        System.out.println();
      }
            
      System.out.println();
      
  }


  public void paintComponent(Graphics g)
  {
    int rows = plane.length;
    int cols = plane[0].length;
    int w = getWidth() / cols;
    int h = getHeight() / rows;
    for (int r = 0; r < rows; r++)
    {
      for (int c = 0; c < cols; c++)
      {
        int x = w * c;
        int y = h * r;
        g.setColor(plane[r][c]);
        g.fillRect(x, y, w-2, h-2);
      }
    }
  }

  // MouseListener implementation:
  public void mousePressed(MouseEvent e)
  {
    int x = e.getX();
    int y = e.getY();
    int rows = plane.length;
    int cols = plane[0].length;
    int w = getWidth() / cols;
    int h = getHeight() / rows;
    int row = y / h;
    int col = x / w;
    if (row >= rows || col >= cols)
      return;

    Color fillColor = Color.BLUE;
    if (fillColor.equals(plane[row][col]))
      fillColor = Color.BLACK;

    fillPool(plane, row, col, fillColor);
    repaint();
  }

  // Not used:
  public void mouseClicked(MouseEvent e) { }
  public void mouseReleased(MouseEvent e) { }
  public void mouseEntered(MouseEvent e) { }
  public void mouseExited(MouseEvent e) { }

  public static void main(String[] args)
  {
    JFrame window = new JFrame("Area fill");
    window.setBounds(100, 100, 320, 300);
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.getContentPane().add(new AreaFill("boardflood.dat"));
    window.setVisible(true);
  }
}

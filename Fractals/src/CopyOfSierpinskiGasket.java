/**
 * 
 */

/**
 * @author jack
 *
 */

import edu.gatech.mediaprogramming.*;

public class CopyOfSierpinskiGasket extends Turtle{

	/**
	 * @param args
	 */
	
	public void drawGasket(int depth, double length)
	{
		if(depth==1)
		{
			int angle=30;
			turn(angle);
			drawTree(2,length);
			//forward(length);
			turn(angle*4);
		    //forward(length);
			drawTree(2,length);
			turn(angle*4);
			//forward(length);
			drawTree(2,length);
			turn(angle*4);
			
			try{Thread.sleep(0);}
			catch(InterruptedException e){}
		}
		else
		{
			int angle=30;
			turn(angle);
			forward(length/2);
			turn(3*angle);
			drawGasket(depth-1, length/2);
			turn(-5*angle);
			drawGasket(depth-1,length/2);
			turn(4*angle);
			forward(length/2);
			turn(-5*angle);
			drawGasket(depth-1,length/2);
			turn(-4*angle);
			forward(length/2);
			turn(4*angle);
			
		}
	}
	
	public void drawKochCurve(int depth, double length)
	{
		if(depth==1)
		{
			forward(length);
			
			try
			{
				Thread.sleep(0);
			}
			catch(InterruptedException e)
			{
				
			}
		}
		else
		{
			drawKochCurve(depth-1,length/3);
			turn(-60);
			drawKochCurve(depth-1,length/3);
			turn(120);
			drawKochCurve(depth-1,length/3);
			turn(-60);
			drawKochCurve(depth-1,length/3);
			
		}
	}
	
	
	
	
	public void drawTree(int depth, double distance)
	{
		if(depth==0)
		{
			forward(distance);
			backward(distance);
			try{Thread.sleep(0);}
			catch(InterruptedException e){}
		}
		else
		{
			int angle=60;
			double half=distance/2;
			forward(distance);
			turn(-angle);
			drawTree(depth-1,half);
			turn(2*angle);
			drawTree(depth-1,half);
			turn(-angle);
			drawTree(depth-1,half);
			backward(distance);
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CopyOfSierpinskiGasket sg=new CopyOfSierpinskiGasket();
		sg.drawGasket(5,250);
		sg.turn(90);
		sg.drawGasket(5,250);
		sg.turn(90);
		sg.drawGasket(5,250);
		sg.turn(30);
		sg.drawGasket(5,250);
		sg.turn(90);
		sg.drawGasket(5,250);
		sg.turn(90);
		sg.drawGasket(5,250);
		sg.hide();
		

	}

}

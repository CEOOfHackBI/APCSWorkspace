/**
 * 
 */

/**
 * @author jack
 *
 */

import edu.gatech.mediaprogramming.*;

public class SierpinskiGasket extends Turtle{

	/**
	 * @param args
	 */
	
	public void drawGasket(int depth, double length)
	{
		if(depth==1)
		{
			turn(30);
			forward(length);
			turn(120);
			forward(length);
			turn(120);
			forward(length);
			turn(120);
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
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		SierpinskiGasket sg=new SierpinskiGasket();
		sg.drawGasket(4,250);
		

	}

}
/**
 * 
 */
import edu.gatech.mediaprogramming.*;
/**
 * @author jack
 *
 */
public class CopyOfTreeTurtle extends Turtle{

	/**
	 * @param args
	 */
	public void drawTree(int depth, double distance)
	{
		if(depth==0)
		{
			drawKochCurve(3,distance);
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
	
	
	public void drawGasket(int depth, double length)
	{
		if(depth==1)
		{
			int angle=30;
			turn(angle);
			forward(length);
			
			turn(angle*4);
			forward(length);
			
			turn(angle*4);
			forward(length);
			
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
		//	drawTree(2,length);
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
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CopyOfTreeTurtle t=new CopyOfTreeTurtle();
		t.moveTo(300,600);
		t.drawTree(4,200);
		t.hide();

	}

}

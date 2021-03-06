/**
 * 
 */
import edu.gatech.mediaprogramming.*;
/**
 * @author jack
 *
 */
public class CopyOfKochCurve extends Turtle{

	/**
	 * @param args
	 */
	
	public void drawKochCurve(int depth, double length)
	{
		if(depth==1)
		{
			forward(length);
		//	drawTree(2,length*2);
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
			int angle=40;
			drawKochCurve(depth-1,length/3);
			turn(-angle);
			drawKochCurve(depth-1,length/3);
			turn(2*angle);
			drawKochCurve(depth-1,length/3);
			turn(-angle);
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
		
		
		CopyOfKochCurve t=new CopyOfKochCurve();
		t.turn(90); //face east
		t.penUp();
		t.moveTo(0,200);
		t.penDown();
		t.drawKochCurve(5, 350);
		t.turn(180);
		t.drawKochCurve(5,350);
		t.hide();
		

	}

}

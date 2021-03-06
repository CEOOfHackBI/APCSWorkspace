/**
 * 
 */
import edu.gatech.mediaprogramming.*;
/**
 * @author jack
 *
 */
public class KochCurve extends Turtle{

	/**
	 * @param args
	 */
	
	public void drawKochCurve(int depth, double length)
	{
		if(depth==1)
		{
			forward(length);
			//drawTree(2,length);
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
		
		
		KochCurve t=new KochCurve();
		t.turn(90); //face east
		t.penUp();
		t.moveTo(200,200);
		t.penDown();
		t.drawKochCurve(5, 400);
		t.hide();
		

	}

}

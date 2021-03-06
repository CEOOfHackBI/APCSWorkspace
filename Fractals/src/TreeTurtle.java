/**
 * 
 */
import edu.gatech.mediaprogramming.*;
/**
 * @author jack
 *
 */
public class TreeTurtle extends Turtle{

	/**
	 * @param args
	 */
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
		
		TreeTurtle t=new TreeTurtle();
		t.drawTree(7,175);
		t.hide();

	}

}

import edu.gatech.mediaprogramming.Turtle;

/**
 * 
 */

/**
 * @author jack
 *
 */
public class TurtleCurve extends Turtle{

	/**
	 * @param args
	 */
	
	public void makeCoastline(int depth,double length)
	{
		if(depth==0)
		{
			forward(length);
		}
		else
		{
			int angle=60;
			makeCoastline(depth-1,length/2);
			turn(-angle);
			makeCoastline(depth-1,length/3);
			turn(2*angle);
			makeCoastline(depth-1,length/3);
			turn(-angle);
			makeCoastline(depth-1,length/2);
		}
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TurtleCurve turtle=new TurtleCurve();
		turtle.penUp();
		turtle.moveTo(100,400);
		turtle.turn(90);
		turtle.penDown();
		turtle.makeCoastline(2,400);
		turtle.hide();
		
	}

}

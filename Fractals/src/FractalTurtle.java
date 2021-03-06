import edu.gatech.mediaprogramming.Turtle;

/**
 * 
 */

/**
 * @author jack
 *Problem 2
 */
public class FractalTurtle extends Turtle{

	/**
	 * @param args
	 */
	
	
	public void drawTree(int depth,double distance)
	{
		if(depth==0)
		{
			forward(distance);
			backward(distance);
		}
		else
		{
			forward(distance);
			turn(-60);
			drawTree(depth-1,distance/2);
			turn(120);
			drawTree(depth-1,distance/2);
			turn(-60);
			drawTree(depth-1,distance/2);
			backward(distance);
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Hello JavaWIDE!");
		FractalTurtle t=new FractalTurtle();
		t.penUp();
		t.moveTo(350, 750);
		t.penDown();
		t.drawTree(2,200);
		
	}

}

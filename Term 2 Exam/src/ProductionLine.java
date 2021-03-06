import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 */

/**
 * @author croftj
 *Problem 4
 *
 */
public class ProductionLine {

	/**
	 * @param args
	 */
	
	private Queue<Disk> assemblyLineIn;
	private Queue<Tower> assemblyLineOut;
	private Tower robotArm;
	
	public ProductionLine(int nDisks,int maxRadius)
	{
		assemblyLineIn=new LinkedList<Disk>();
		
		for(int i=0;i<nDisks;i++)
		{
			assemblyLineIn.add(new Disk((int)(Math.random()*maxRadius)+1));
		}
		robotArm=new Tower();
		assemblyLineOut=new LinkedList<Tower>();
	}
	
	public void unloadRobot()
	{
		Tower t=new Tower();
		
		while(!robotArm.isEmpty())
			t.push(robotArm.pop());
		
		assemblyLineOut.add(t);
	}
	
	public void process()
	{
		while(!assemblyLineIn.isEmpty())
		{
			if(robotArm.isEmpty()||assemblyLineIn.peek().compareTo(robotArm.peek())>0)
				robotArm.push(assemblyLineIn.remove());
			else
				unloadRobot();
		}
		if(!robotArm.isEmpty())
			unloadRobot();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

/**
 * 
 */

/**
 * @author croftj
 *Problem 6
 */
public class ArithmeticNumberStream implements IncreasingNumberStream{

	/**
	 * @param args
	 * 
	 */
	
	private int start;
	private int increment;
	private int last;
	
	public ArithmeticNumberStream(int start, int i)
	{
		this.start=start;
		increment=i;
		last=start-increment;
	}
	
	public int nextTerm() 
	{
		if(last+increment>=Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		
		last+=increment;
		return last;
	}

	
	public void restart() 
	{
		last=start-increment;
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IncreasingNumberStream stream=new ArithmeticNumberStream(12,5);
		System.out.print(stream.nextTerm()+" ");
		System.out.print(stream.nextTerm()+" ");
		System.out.print(stream.nextTerm()+" ");
		stream.restart();
		System.out.print(stream.nextTerm()+" ");
		System.out.print(stream.nextTerm()+" ");

	}
}

package problems1;
/**
 * 
 */

/**
 * @author jack
 *
 */
public class Diploma {

	/**
	 * @param args
	 */
	private String name;
	private String course;
	
	public Diploma(String name, String course)
	{
		this.name=name;
		this.course=course;
	}
	
	public String toString()
	{
		String str;
		str="This certifies that " + name;
		str+='\n'+ "has completed a course in "+course;
		return str;
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}

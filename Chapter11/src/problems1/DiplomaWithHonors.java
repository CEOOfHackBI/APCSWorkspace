package problems1;
/**
 * 
 */

/**
 * @author jack
 *
 */
public class DiplomaWithHonors extends Diploma{

	
	private String name;
	private String course;
	
	public DiplomaWithHonors(String name, String course) 
	{
		super(name, course);
		this.name=name;
		this.course=course;
	}
	
	public String toString()
	{
		String str;
		str=super.toString();
		str+='\n'+"*** with honors ***";
		return str;
	}

	/**
	 * @param args
	 */
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}

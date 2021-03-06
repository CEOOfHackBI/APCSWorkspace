package problems1;
/**
 * @author jack
 *
 */
public abstract class Poem {

	/**
	 * @param args
	 */
	private int lines;
	
	public abstract int numLines();
	
	public abstract int getSyllables(int k);
	
	public void printRhythm() 
	{
		String str = "";
		for(int i=0;i<numLines();i++)
		{
			for(int j=0;j<getSyllables(i);j++)
			{
				if(j==getSyllables(i)-1)
					str+="ta";
				else
					str+="ta-";
			}
			str+='\n';
		}
		System.out.println(str);
	}
}
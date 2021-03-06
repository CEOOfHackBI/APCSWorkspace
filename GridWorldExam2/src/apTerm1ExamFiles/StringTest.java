/**
 * 
 */
package apTerm1ExamFiles;

/**
 * Jack Croft
 * Problem 6
 *
 */
public class StringTest {

	/**
	 * @param args
	 */
	
	public String scroll(String str)
	{
		if(str.length()>2)
		{
			String temp=str.substring(str.length()-3);
			return temp+str.substring(0,str.length()-3);
		}
		else
		{
			return str;
		}
	}
	
	public boolean wierdMatch(String s1, String s2)
	{
		if(s1!=null&&s2!=null)
		{
			if(s1.length()==s2.length())
			{
				if(s1.length()%2==1)
				{
					if(Character.compare(s1.charAt(s1.length()/2), s2.charAt(s2.length()/2))==0)
						return true;
				}
				else
				{
					if(Character.compare(s1.charAt(s1.length()/2), s2.charAt(s2.length()/2))==0)
					{
						if(Character.compare(s1.charAt(s1.length()/2-1), s2.charAt(s2.length()/2-1))==0)
							return true;
					}
				}
			}
		}
		return false;
	}
	
	public String removeParens(String str)
	{
		if(str.indexOf('(')>-1)
		{
			if(str.indexOf(')')>-1&&str.indexOf('(')<str.lastIndexOf(')'))
				return str.substring(str.indexOf('(')+1, str.indexOf(')'));
		}
		return str;
	}
	
	public static String[] mySplit(String s, String delim)
	{
		String[] result=new String[s.length()];
		
		
		for(int i=0,j=0;;i++,j++)
		{
			if(s.indexOf(delim)==-1)
			{
				result[j]=s;
				break;
			}
			else
			{
				result[j]=s.substring(0,s.indexOf(delim));
				i=s.indexOf(delim)+delim.length();
				s=s.substring(s.indexOf(delim)+1);
			}
		}
		
		int counter=0;
		for(int i=0;i<result.length;i++)
		{
			if(result[i]!=null)
				counter++;
		}
		
		String[] temp= new String[counter];
		
		for(int i=0,j=0;i<result.length;i++)
		{
			if(result[i]!=null)
			{
				temp[j]=result[i];
				j++;
			}
		}
		
		String[] temp2=new String[temp.length-2];
		for(int i=1;i<temp.length-1;i++)
		{
			temp2[i-1]=temp[i];
		}
		
		
		return temp2;
	}
}

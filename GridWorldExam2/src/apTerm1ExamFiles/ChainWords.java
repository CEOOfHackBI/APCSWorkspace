package apTerm1ExamFiles;
import java.util.ArrayList;
import java.util.List;


public class ChainWords {

	/**
	 * @param args
	 */
	
	public static int linkStrength(String word1, String word2)
	{
		for(int i=0;i<word2.length();i++)
		{
			if(word1.charAt(i)==word2.charAt(0))
			{
				if(word1.substring(i).equals(word2.substring(0,word1.length()-i)))
					return word1.length()-i;
			}
		}
		return 0;
	}
	
	public static void keepFirstChain(List<String> words)
	{
		int spot=0;
		for(int i=0;i<words.size()-1;i++)
		{
			if(linkStrength(words.get(i),words.get(i+1))==0)
			{            
				spot=i;
				break;
			}
		}
	}
}
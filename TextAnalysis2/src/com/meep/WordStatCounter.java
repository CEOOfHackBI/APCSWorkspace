/**
 * 
 */
package com.meep;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author jack
 *
 */
public class WordStatCounter implements Runnable {

	/**
	 * @param args
	 */
	private String[] words;
	
	public WordStatCounter(String[] words)
	{
		this.words=words;
	}
	
	public void wordStats()
	{
		String shortestWord="akjfkfdafdskjldfh";
		String longestWord=words[0];
		String mostFrequentWord=words[0];
		String leastFrequentWord=words[0];
		int leastFrequency=10;
		int mostFrequency=1;
		Map<String,Integer> frequencies=new TreeMap<String,Integer>();
		
		
		for(int i=0;i<words.length;i++)
		{
			if(frequencies.containsKey(words[i]))
			{
				Integer value=new Integer((int)frequencies.get(words[i])+1);
				frequencies.put(words[i],value);
			}
			else
			{
				frequencies.put(words[i],1);
			}
		}
		
		for(int i=0;i<words.length;i++)
		{
			if(shortestWord.length()>words[i].length())
				shortestWord=words[i];
			
			if(longestWord.length()<words[i].length())
				longestWord=words[i];
		}
		
		Set s=frequencies.keySet();
		Iterator i=s.iterator();
		
		while(i.hasNext())
		{
			String str=(String) i.next();
			
			if(frequencies.get(str)<leastFrequency)
			{
				leastFrequency=frequencies.get(str);
				leastFrequentWord=str;
			}
			if(frequencies.get(str)>mostFrequency)
			{
				mostFrequency=frequencies.get(str);
				mostFrequentWord=str;
			}
		}
		TextAnalyzer.DISTINCTWORDCOUNT=frequencies.size();
		TextAnalyzer.MOSTFREQUENCY=mostFrequency;
		TextAnalyzer.LEASTFREQUENCY=leastFrequency;
		TextAnalyzer.MOSTFREQUENTWORD=mostFrequentWord;
		TextAnalyzer.LEASTFREQUENTWORD=leastFrequentWord;
		TextAnalyzer.LONGESTWORD=longestWord;
		TextAnalyzer.SHORTESTWORD=shortestWord;
		
//		System.out.println("Distinct Words: "+ TextAnalyzer.DISTINCTWORDCOUNT);
//		System.out.println("Shortest Word: "+shortestWord);
//		System.out.println("Longest Word: "+longestWord);
//		System.out.println("Highest Frequency: "+mostFrequency);
//		System.out.println("Lowest Frequency: "+leastFrequency);
//		System.out.println("Most Frequent Word: "+mostFrequentWord);
//		System.out.println("Least Frequent Word: "+leastFrequentWord);
	}
	
	
	@Override
	public void run() 
	{
		wordStats();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}

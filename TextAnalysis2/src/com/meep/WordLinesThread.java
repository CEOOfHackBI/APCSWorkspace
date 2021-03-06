/**
 * 
 */
package com.meep;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author jack
 *
 */
public class WordLinesThread implements Runnable {

	/**
	 * @param args
	 */
	String[] lines;
	
	
	
	public WordLinesThread(String[] data)
	{
		lines=data;
	}
	
	public void matchWordLines()
	{
		
		
		Map<String, ArrayList<Integer>> lineNums = new TreeMap<String, ArrayList<Integer>>();
		
		for(int i=0;i<lines.length;i++)
		{
			String regex="[\\s,!.;:{}\"]+";
			String[] lineWords=lines[i].replaceFirst("^"+regex, "").split(regex);
			
			for(int j=0;j<lineWords.length;j++)
			{
				String word=lineWords[j];
				if(lineNums.containsKey(word))
				{
					ArrayList<Integer> temp=lineNums.get(word);
					temp.add(i);
					lineNums.put(word, temp);
				}
				else
				{
					ArrayList<Integer> temp=new ArrayList<Integer>();
					temp.add(i);
					lineNums.put(word,temp);
				}
			}
		}
		TextAnalyzer.WORDLINES=(TreeMap) lineNums;
		
		
	}
	
	public void run()
	{
		matchWordLines();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

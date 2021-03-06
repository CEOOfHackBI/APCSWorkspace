/**

 * 
 */
package com.meep;


/**
 * At the start of the output file, the filename should be displayed prominently, immediately
followed by some statistical information about the text. At the very least: number of
distinct words in the file, shortest word, longest word, least frequent word and most
frequent word. In addition, you must output the total computer processing time that it
took from the moment you enter the destination output filename to the completion of
generation of the final output file. All the words that occur in the text should be listed in
the created index output file in upper case and in alphabetical order. Each word should be
followed by a number representing its frequency in the text and a list of all of the line
numbers for lines that contain that word.
 */


import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

/**
 * @author jack
 *
 */
public class TextAnalyzer {

	/**
	 * @param args
	 */
	public static int WORDCOUNT=0;
	public static int LINECOUNT=0;
	public static int DISTINCTWORDCOUNT=0;
	public static int MOSTFREQUENCY=0;
	public static int LEASTFREQUENCY=0;
	public static String MOSTFREQUENTWORD="";
	public static String LEASTFREQUENTWORD="";
	public static String LONGESTWORD="";
	public static String SHORTESTWORD="";
	public static String OUTPUT="";
	public static String[] WORDS=null;
	public static String[] LINES=null;
	public static TreeMap WORDLINES=null;
	public static long TIME=0;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		long startTime=System.nanoTime();
		
		
		Options options = new Options();
		options.addOption("v", false, "Display version and exit");
		options.addOption("i", true, "Input file");
		
		
		try 
		{
			CommandLineParser parser = new PosixParser();
			CommandLine cmd = parser.parse(options, args);
		
			if(cmd.hasOption("v"))
			{
				System.out.println("Version 0.1");
			}
			if(cmd.hasOption("i"))
			{
				String fileName=cmd.getOptionValue("i");
				try
				{
					String data = FileLoader.load(fileName);
				    
				    WordCountThread wct=new WordCountThread(data);
				    LineCountThread lct=new LineCountThread(data);
				    Thread t1= new Thread(wct);
				    Thread t2=new Thread(lct);
				    
				    t1.start();
				    t2.start();
				
				    try
				    {
				    	t1.join();
				    	t2.join();
				    }
				    catch(InterruptedException ie)
				    {
				    	 System.err.println(ie.getMessage());
				    }
				    
				    WordStatCounter wsc = new WordStatCounter(WORDS);
				    WordLinesThread wlt=new WordLinesThread(LINES);
				    
				    Thread t3=new Thread(wsc);
				    Thread t4=new Thread(wlt);
				    
				    t3.start();
				    t4.start();
				    try
				    {
				    	t3.join();
				    	t4.join();
				    }
				    catch(InterruptedException ie)
				    {
				    	 System.err.println(ie.getMessage());
				    }
				    
				    
				    
				}
				catch(IOException ioe)
				{
					System.err.println(ioe.getMessage());
				}
			}
			
		}
		catch(ParseException pe) 
		{
			System.out.println("Error on command line.");
		}
		
		OUTPUT+="Time: "+TIME/1000000000.+" seconds"+'\n';
		OUTPUT+="Distinct Words: "+DISTINCTWORDCOUNT+'\n';
		OUTPUT+="Shortest Word: "+SHORTESTWORD+'\n';
		OUTPUT+="Longest Word: "+LONGESTWORD+'\n';
		
		Set words=WORDLINES.keySet();
		Iterator i=words.iterator();
		
		long startTime2=System.nanoTime();
		while(i.hasNext())
		{
			String str=(String)i.next();
			
			OUTPUT+=str+": ";
			String temp= WORDLINES.get(str).toString();
			temp=temp.substring(1,temp.length()-2);
			OUTPUT+=temp;
			OUTPUT+='\n';
		}
		
		long endTime2=System.nanoTime();
	    long time2 = endTime2-startTime2;
	    System.out.println(time2);
		try 
		{
			File output= new File("output.txt");
			
			if(!output.exists())
			{
				output.createNewFile();
			}
			
			FileWriter fw = new FileWriter(output.getAbsoluteFile());
			BufferedWriter bw=new BufferedWriter(fw);
			bw.write(OUTPUT);
			bw.close();
			
		} 
		catch (FileNotFoundException e) 
		{
			System.err.println(e.getMessage());
		}
		catch(IOException ioe)
		{
			System.err.println(ioe.getMessage());
		}
		
		long endTime=System.nanoTime();
	    long time = endTime-startTime;
	    TIME=time;
	    System.out.println("Total Time: "+time+" nanoseconds");
	    System.out.println("Total Time: "+time/1000000000.+" seconds");
		
	}
	
	
}
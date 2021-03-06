

import java.util.*;
import java.io.*;

/**
 * @author Bill O'Donoghue
 * Date of Completion: 3/21/13
 * All work here is honestly obtained and is my own
 * Attribution: Jack Croft, Griffin Medendorp, IndexMaker and CookieMonster projects
 * Errata: none
 * 
 */
public class MovieIndexMaker
{
    private PriorityQueue<Movie> titleSorted; //movies sorted by title
    private PriorityQueue<Movie> directorSorted; //movies are sorted by the director's name
    private Set<Person> allActors; //all the actors

    public MovieIndexMaker() {
        titleSorted = new PriorityQueue<Movie>(10, new MovieComparator(0));
        directorSorted = new PriorityQueue<Movie>(10, new MovieComparator(1));
        allActors=new TreeSet<Person>();
    }
    
    /**
     * A method that sorts the movie file
     * It checks to see if the specified sorting method is valid
     * and then creates the input and output files and calls the appropriate method to sort the
     * input file
     * @sortMethod- a number that determines what how to sort the information in the movie file
     * 0-sort by movie's name; 1-sort by director's name; 2-Display all of the actors in alphabetical order
     */
    public void sort(int sortMethod) {
    	/* Verify valid sortMethod int */
    	if (sortMethod >= 0 && sortMethod <= 2) { //if sortMethod is a valid number
    		
    		/* Get Input and OutputFiles */
    		Scanner keyBoard = new Scanner(System.in); //to read the response from the user
    		BufferedReader inputFile = getInputFile(keyBoard);
    		PrintWriter outputFile = getOutputFile(keyBoard, sortMethod);
    		keyBoard.close(); //the user no longer needs to provide any input
    		
    		/* Choose correct methods to sort the input file */
    		if (sortMethod == 0)
    			sortByMovie(inputFile, outputFile, titleSorted);
    		else if (sortMethod == 1)
    			sortByMovie(inputFile, outputFile, directorSorted);
    		else //if (sortMethod == 2)
    			sortAllActors(inputFile, outputFile);
    		
    		/* Close the files */
            try {
                inputFile.close();
                outputFile.close();
            }
            catch (IOException e) {
                System.out.print("Could not close the files");
            }    	    		
    	}
        else
            System.out.println("Invalid sorting option!!");
    }
    
    
    /**
     * Returns a suitable inputFile to read information from
     * using a file name provided by the user
     * If a file name is invalid, requests the user provide another one
     * @param keyBoard
     * @return
     */
    private BufferedReader getInputFile(Scanner keyBoard) {
    	BufferedReader inputFile = null;
        boolean haveGoodFileName = false; //will be set to true when the file is correctly opened
        
        while (!haveGoodFileName) {
            try { 
                //get input file name from user
                
                System.out.println("Enter the input file name: ");
                String fileName = keyBoard.nextLine().trim();
                
                //try using it to open a file
                inputFile = new BufferedReader(new FileReader(fileName), 1024);
                haveGoodFileName = true; //get out of the loop if an exception is not thrown
            }
            catch (FileNotFoundException ex) {
                System.out.println("Cannot open the given file");
                haveGoodFileName = false; //the fileName failed, so try again
            }
        }
        
        return inputFile;
    }
    
    
    
    /**
     * Returns a suitable output file to write information to
     * using a file name provided by the user
     * If a file name is invalid, requests the user provide another one
     * @param keyBoard
     * @return
     */
    private PrintWriter getOutputFile(Scanner keyBoard, int sortMethod) {
    	PrintWriter outputFile = null;
    	boolean haveGoodFileName = false; //will be set to true when the file is correctly opened
    	
        while (!haveGoodFileName) {
            try { 
                //get a output file name from user
                System.out.println("Enter the output file name: ");
                String fileName = keyBoard.nextLine().trim();
            
                //try using it to make a new file   
                outputFile = new PrintWriter(new FileWriter(sortMethod + "." + fileName));
                haveGoodFileName = true; //get out of the loop if an exception is not thrown
            }
            catch (IOException e) {
                System.out.println("Problem creating an output file");
                haveGoodFileName = false; //the fileName failed, so try again
            }
        }
        return outputFile;
    }
    
    
    
    /**
     * Sorts the inputFile by creating movie objects and adding them to the given 
     * PriorityQueue which automatically arranges them in the manner specified by 
     * the int sortMethod from the sort method
     * @param inputFile
     * @param outputFile
     * @param queue
     */
    private void sortByMovie(BufferedReader inputFile, PrintWriter outputFile, PriorityQueue<Movie> queue) {
    	/* Fill the Queue with all of the Movies*/
        String line;
        try {
     	   while ((line = inputFile.readLine()) != null) {
     		   queue.add(getMovieFromLine(line));
             }
         }
         catch (IOException e) {
             System.out.println("Could not read the file");
         }
         
         /* Output Queue to new File */
         while (!queue.isEmpty()) {
             outputFile.println(queue.remove());
         }
    }
    
    /**
     * Creating a file with the all the actors from the Movie file in alphabetical order
     * without repeats requires a different approach than sorting the movies by their directors or 
     * by their names. All of the actors are placed in a TreeSet so that they are in order
     * and their are no duplicates because of the nature of the TreeSet and Set collections respectively
     * Then an iterator returns all of them in order to be printed out to a new file
     * @param inputFile
     * @param outputFile
     */
    private void sortAllActors(BufferedReader inputFile, PrintWriter outputFile) {
    	/* Fill allActors */
    	String line;
        try {
     	   while ((line = inputFile.readLine()) != null) {
     		   for (Person p : getMovieFromLine(line).getActors()) //for all of the actors in that movie
     			   allActors.add(p); //add them to allActors
             }
         }
         catch (IOException e) {
             System.out.println("Could not read the file");
         }
        
        /* Output allActors to a new file */
        Iterator<Person> iter = allActors.iterator();
        while (iter.hasNext()) 
        	outputFile.println(iter.next());
    }
    
    /**
     * A method that takes a long string
     * and breaks it up according to a pre-determined format to extract specific information from it
     * the format is
     * Year:  Movie name  Actor first name actor last name, (multiple times)  Dir: Director first name director last name (possibly an additionaly director
     */
    private Movie getMovieFromLine(String line) {
    	
        /* Get Movie Name */
        String movieName = line.substring(4, line.indexOf("  ")); //the string from just past the year to the first tab
        line = line.substring(line.indexOf("  ")+1); //remove that section from beginning to (and including) the first tab
        //since the movie name was retrieved, don't need it in line
        
        
        /* Get Actors' Name */
        String actorNames = line.substring(0, line.indexOf("Dir:")-1); //the string from the new beginning to the director section
        line = line.substring(line.indexOf("Dir: ") + 5); //chop off all the actor names and get rid of "Dir: " because it is no longer neccessary
        ArrayList<Person> actors = new ArrayList<Person>(); 
        
        System.out.println();
        while (actorNames.indexOf(",") >= 0) { //while there are still instances of ","        	
			String fullName = actorNames.substring(0,actorNames.indexOf(",")).trim(); //represents the entire name of a person
			actorNames = actorNames.substring(actorNames.indexOf(",") + 2).trim(); //actorNames, just without the first actor's name
			//System.out.println(actorNames + "::::::" + fullName);
			actors.add(getPersonsName(fullName));
        }
		//actorNames should still have one more name in it
        actors.add(getPersonsName(actorNames));

        
        /* Get Director's Name */
        Person directorName = null;
        if (line.indexOf(",") >= 0) {//if line contains a "," then there are two directors for the movie
        	//and the director whose name comes first in the alphabet will be set as the movie's director
        	Person director1 = getPersonsName(line.substring(0,line.indexOf(",")));
        	Person director2 = getPersonsName(line.substring(line.indexOf(",") + 2)); //plus two to exclude the ", "
        	if (director1.compareTo(director2) < 0) //if director1's name is earlier in the alphabet than director2's name
        		directorName = director1;
        	else //else director2's name is earlier in the alphabet
        		directorName = director2;
        }
        else
        	directorName = getPersonsName(line); //line has no "," so the only thing left is a director's name 

        /* Return the Movie */
        return new Movie(movieName, actors, directorName);          
    }    
    
    /**
     * Properly creates a new Person from a String
     * The biggest problem is dealing with middle names: this was solved by having the first name field for 
     * Person hold both the first and middle names (or from the beginning of full name to the last space)
     * and the last name hold the last name (or from the spot after the last space to the end)
     * @param fullName- a Person's full name
     * @return
     */
    private Person getPersonsName(String fullName) {
    	int firstSpaceIndex = fullName.lastIndexOf(" ");
    	return new Person(fullName.substring(0,firstSpaceIndex), fullName.substring(firstSpaceIndex));
    }
    
    
    public static void main(String[] args)
    {
    	MovieIndexMaker m=new MovieIndexMaker();
    	m.sort(0);
    	//m.sort(1);
    	//m.sort(2);
    }

}

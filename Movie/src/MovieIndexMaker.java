
import java.util.ArrayList;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.io.*;

/**
 * @author Bill O'Donoghue
 * Date of Completion: 3/21/13
 * All work here is honestly obtained and is my own
 * Attribution: Jack Croft, Griffin Medendorp
 * Errata: Some problems with extracting all of the information from movies.txt such as the year or have more than
 * just one first and last name for the director
 * Extra Errata: OutOfBoundsError:Line 141
 */
public class MovieIndexMaker
{
    private PriorityQueue<Movie> titleSorted; //movies sorted by title
    private PriorityQueue<Movie> directorSorted; //movies are sorted by the director's name
    private PriorityQueue<Movie> numActorSorted; //movies are sorted by the number of actors
    private String fileName;

    public MovieIndexMaker() {
        titleSorted = new PriorityQueue<Movie>(10, new MovieComparator(0));
        directorSorted = new PriorityQueue<Movie>(10, new MovieComparator(1));
        numActorSorted = new PriorityQueue<Movie>(10, new MovieComparator(2));
    }
    
    /**
     * A method that sorts the movie file
     * It prompts the user for the input file name
     * and then the name of the file the user wants the newly sorted to be stored in
     * After using the correct PriorityQueue, the method extracts the data from the file into that PriorityQueue
     * and finally outputs it to a new file
     * @sortMethod- a number that determines what PriorityQueue needs to be used
     * 0-sort by movie's name; 1-sort by director's name; 2-sort by number of actors
     */
    public void sort(int sortMethod) {
        /* Get Access to the input file */
        BufferedReader inputFile = null;
        boolean haveGoodFileName = false; //will be set to true when the file is correctly opened
        
        //tries to open the file with the fileName from setFileName()
        //if that fails, prompts the user to type in another name
        while (!haveGoodFileName) {
            try { 
                //get input file name from user
                Scanner keyBoard = new Scanner(System.in);
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
        
        /* Create output file */
        PrintWriter outputFile = null;
        while (!haveGoodFileName) {
            try { 
                //get a output file name from user
                Scanner keyBoard = new Scanner(System.in);
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

        /* Grab correct PriorityQueue */
        PriorityQueue<Movie> queue = null; //the later code will used this name for one of the three PriorityQueue option
        if (sortMethod == 0) //sort by movie title
            queue = titleSorted;
        else if (sortMethod == 1) //sort by director's name
            queue = directorSorted;
        else if (sortMethod == 2) //sort by number of actors
            queue = numActorSorted;
        else {
            System.out.println("Invalid sorting option!!");
            return;
        }
        
        /* Update the Queue */
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
        while (!queue.isEmpty())
            outputFile.println(queue.remove());
        //!WARNING! the previous line requires that the Movie class has a suitable toString() method

        /* Close the files */
        try {
            inputFile.close();
            outputFile.close();
        }
        catch (IOException e) {
            System.out.print("Could not close the files");
        }
    }
    
    /**
     * A method that takes a long string
     * and breaks it up according to a pre-determined format to extract specific information from it
     * the format is
     * Movie name   Actor first name actor last name (multiple times)   Director: Director first name director last name
     */
    private Movie getMovieFromLine(String line) {
    	
    	/**
    	
        /* Get Movie Name */
    	
    	//changed "/t" to "  "
        String movieName = line.substring(4, line.indexOf("  ")); //the string from just past the year to the first tab
        line = line.substring(line.indexOf("  ")+1); //remove that section from beginning to and including the first tab
        //since the movie name was retrieved, don't need it in line
        
        
        
        
        /* Get Actors' Name */
        
        //change "/t" to "Dir:"-1
        String actorNames = line.substring(0, line.indexOf("Dir:")-1); //the string from the new beginning to the first tab
        line = line.substring(line.indexOf("Dir:")); //chop off all the actor names
        ArrayList<Person> actorFullNames = new ArrayList<Person>(); //for every two names in nameArray, there will be one actor's name
        
        
        
        while (actorNames.indexOf(",") >= 0) { //while there are still instances of 
		String name = actorNames .substring(0,line.indexOf(",")); //represents the entire name of a person
		actorNames = actorNames.substring(line.indexOf(",") + 2); //actorNames, just without the first name
		
		actorFullNames.add(new Person(name.substring(0,name.indexOf(" ")), name.substring(name.lastIndexOf(" "))));//cuts out any middle names
	}
	//actorNames should still have one more name in it
	actorFullNames.add(new Person(actorNames.substring(0,actorNames.indexOf(" ")), actorNames.substring(actorNames.lastIndexOf(" "))));//cuts out any middle names

        /* Get Director's Name */
        line = line.substring(line.indexOf("Dir: ") + 5); //cut off the "director: " part
        Person directorName = new Person(line.substring(0, line.indexOf(" ")), line.substring(line.indexOf(" ") + 1));
	//!Warning! this code does not support directors with middle names or multiple directors

	/* Return the Movie */
        //!WARNING! This may not be Movie's actual constructor
        //I assumed it would be along the lines of Movie(String name, Person[] actorNames, Person director)
        return new Movie(movieName, actorFullNames, directorName);          
    }
    public static void main(String[] args)
    {
    	MovieIndexMaker m=new MovieIndexMaker();
    	m.sort(0);
    }

}

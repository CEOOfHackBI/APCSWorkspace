import java.util.Comparator;

/**
 * 
 */

/**
 * @author jack
 *
 */
public class MovieComparator implements Comparator<Movie>{

	/**
	 * @param args
	 */
	
	//0-movie title
	//1-director
	//2-num of actors
	private int compareValue=-1;
	
	public MovieComparator(int type)
	{
		compareValue=type;
	}
	
	public int compare(Movie m1, Movie m2) 
	{
		if(type==0)
			return m1.getTitle();
		return 0;
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}

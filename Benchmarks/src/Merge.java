/**
 * 
 */

/**
 * @author jack
 *
 */
public class Merge {

	/**
	 * @param args
	 */
	
private static double[] temp;
	
	public static void sort(double[] a)
	{
		temp=new double[a.length];
		recursiveSort(a, 0, a.length-1);
	}
	
	private static void recursiveSort(double[] a, int from, int to)
	{
		if(to-from<2)
		{
			if(to>from&&a[from]>a[to])
			{
				double temp=a[from];
				a[from]=a[to];
				a[to]=temp;
			}
		}
		else
		{
			int middle=(to+from)/2;
			recursiveSort(a,from,middle);
			recursiveSort(a,middle+1,to);
			merge(a,from,middle,to);
		}
	}
	
	private static void merge(double[] a, int from, int middle, int to)
	{
		int i=from;
		int j=middle+1;
		int k=from;
		
		while(i<=middle&&j<=to)
		{
			if(a[i]<a[j])
			{
				temp[k]=a[i++];
			}
			else
			{
				temp[k]=a[j++];
			}
			k++;
		}
		
		while(i<=middle)
		{
			temp[k++]=a[i++];
		}
		while(j<=to)
		{
			temp[k++]=a[j++];
		}
		for(k=from;k<=to;k++)
			a[k]=temp[k];
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

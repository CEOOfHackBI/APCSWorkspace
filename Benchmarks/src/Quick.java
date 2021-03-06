/**
 * 
 */

/**
 * @author jack
 *
 */
public class Quick {

	/**
	 * @param args
	 */
	
	public static void sort(double[] a)
	{
		recursiveSort(a,0,a.length-1);
	}
	
	public static void recursiveSort(double[] a, int from, int to)
	{
		if(from>=to)
			return;
		
		int i=from;
		int j=to;
		int p=(from+to)/2;
		
		while(i<=j)
		{
			if(a[i]<=a[p])
				i++;
			else if(a[j]>=a[p])
				j--;
			else
			{
				swap(a,i,j);
				i++;
				j--;
			}
		}
		if(p<j)
		{
			swap(a,p,j);
			p=j;
		}
		else if(p>i)
		{
			swap(a,p,i);
			p=i;
		}
		
		recursiveSort(a,from,p-1);
		recursiveSort(a,p+1,to);
	}
	
	public static void swap(double[] a, int i,int j)
	{
		double temp=a[j];
		a[j]=a[i];
		a[i]=temp;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

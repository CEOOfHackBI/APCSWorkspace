
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;


public class Radix {

	/**
	 * @param args
	 */
	
	private int[] list;
	
	public Radix(int[] list)
	{
		this.list=list;
	}
	
	
	public void sort()
	{
		Queue<Integer>[] buckets = (LinkedList<Integer>[]) new LinkedList[10];
		
		for(int i=0;i<buckets.length;i++)
		{
			buckets[i]=new LinkedList<Integer>();
		}
		
		
		int maxNum=list[0];
		for(int i=1;i<list.length;i++)
		{
			if(maxNum<list[i])
				maxNum=list[i];
		}
		maxNum=(int)Math.log10(maxNum)+1;
		
		
		for(int i=0;i<maxNum;i++)
		{
			for(int j=0;j<list.length;j++)
			{
				buckets[getDigitAt(10^i,list[j])].add(list[j]);
			}
			int counter=0;
			for(int j=0;j<buckets.length;j++)
			{
				for(int k=0;k<buckets[j].size();k++)
				{
					list[counter]=buckets[j].remove();
					counter++;
				}
			}
		}
	}
	
	public int getDigitAt(int place, int num)
	{
		num/=place;
		return num%=10;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] cool=new int[5];
		cool[0]=785;
		cool[1]=4654;
		cool[2]=561;
		cool[3]=78889;
		cool[4]=4444;
		Radix r=new Radix(cool);
		r.sort();
		for(int i=0;i<cool.length;i++)
		{
			System.out.println(cool[i]);
		}
		
	}

}

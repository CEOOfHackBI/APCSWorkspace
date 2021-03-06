/**
 * 
 */

/**
 * @author jack
 *
 */
public class Trail {

	/**
	 * @param args
	 */
	
	private int[] markers;
	
	public void setMarkers(int[] m)
	{
		markers=m;
	}
	
	public boolean isLevelTrailSegment(int start, int end)
	{
		int max=markers[start];
		int min=markers[start];
		
		for(int i=start;i<=end;i++)
		{
			if(markers[i]>max)
				max=markers[i];
			if(markers[i]<min)
				min=markers[i];
		}
		
		return Math.abs(max-min)<=10;
	}
	
	public boolean isDifficult()
	{
		int count=0;
		
		for(int i=0;i<markers.length-1;i++)
		{
			if(Math.abs(markers[i]-markers[i+1])>=30)
				count++;
		}
		
		return count>=3;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Trail t=new Trail();
		int[] cool={100,150,105,120,90,80,50,75,75,70,80,90,100};
		t.setMarkers(cool);
		System.out.println(t.isDifficult());
	}

}

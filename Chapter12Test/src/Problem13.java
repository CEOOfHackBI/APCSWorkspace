public class Problem13 {

	public int[][] makeMagicSquare(int n)
	{
		int[][] magicSquare=new int[n][n];
		int i=0;
	//	int j=1;
		int j=n/2;
		
		
		for(int c=0;c<n*n;c++)
		{
			magicSquare[i][j]=c+1;
			
			int checkI=i;
			int checkJ=j;
			
			if(checkJ==n-1)
				checkJ=0;
			else
				checkJ++;
			if(checkI==0)
				checkI=n-1;
			else
				checkI--;
			
			if(magicSquare[checkI][checkJ]==0)
			{
				i=checkI;
				j=checkJ;
			}
//			else
//			{
//				i++;
//			}
			else
			{
				if(i!=n-1)
					i++;
				else
					i=0;
			}
		}
		
		return magicSquare;
	}
	
	public static void main(String[] args)
	{
		Problem13 p13=new Problem13();
		int[][] cool=p13.makeMagicSquare(5);
		for(int i=0;i<cool.length;i++)
		{
			for(int j=0;j<cool[i].length;j++)
			{
				System.out.println(cool[i][j]);
			}
		}
	}
}

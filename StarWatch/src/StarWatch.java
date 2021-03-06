import java.util.ArrayList;

/**
 * 
 */

/**
 * @author jack
 *
 */
public class StarWatch {

	/**
	 * @param args
	 */
	
	private ArrayList<DataPacket> dataPackets;
	
	public StarWatch()
	{
		dataPackets=new ArrayList<DataPacket>();
	}
	
	public DataPacket getBestPacket(double minIntelFactor)
	{
		if(dataPackets.size()==0)
			return null;
		
		DataPacket greatest=null;
		for(int i=0;i<dataPackets.size();i++)
		{
			if(dataPackets.get(i).getIntelFactor()>=minIntelFactor)
			{
				if(greatest==null||dataPackets.get(i).getIntelFactor()>greatest.getIntelFactor())
					greatest=dataPackets.get(i);
			}
		}
		
		return greatest;
	}
	
	public int filterList(int minStrength, int minDuration)
	{
		int deleted=0;
		
		for(int i=0;i<dataPackets.size();i++)
		{
			if(dataPackets.get(i).getDuration()<minDuration||dataPackets.get(i).getStrength()<minStrength)
			{
				dataPackets.remove(i);
				i--;
				deleted++;
			}
		}
		
		return deleted;
	}
	
	public ArrayList<DataPacket> alienMessage(int minStrength, int minDuration, double minIntelFactor)
	{
		filterList(minStrength,minDuration);
		DataPacket best=getBestPacket(minIntelFactor);
		ArrayList<DataPacket> result=new ArrayList<DataPacket>();
		
		if(best!=null)
		{
			String source=best.getSource();
			int quad=best.getQuadrant();
			
			for(int i=0;i<dataPackets.size();i++)
			{
				if(dataPackets.get(i).getSource().equals(source)&&dataPackets.get(i).getQuadrant()==quad)
					result.add(dataPackets.get(i));
			}
		}
		
		return result;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

/**
 * 
 */
package projects.critters;

import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

/**
 * @author jack
 *
 */
public class StockpileCritter extends Critter{

	/**
	 * @param args
	 */
	
	private int energy;
	
	
	public void processActors(ArrayList<Actor> actors)
	{
		energy+=actors.size();
		for(int i=0;i<actors.size();i++)
		{
			actors.get(i).removeSelfFromGrid();
		}
	}
	
	public void makeMove(Location loc)
	{
		if(energy!=0)
		{
			energy--;
			super.makeMove(loc);
		}
		else
		{
			removeSelfFromGrid();
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

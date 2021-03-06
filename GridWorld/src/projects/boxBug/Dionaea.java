/**
 * 
 */
package projects.boxBug;

import java.awt.Color;
import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Location;

/**
 * @author jack
 *
 */
public class Dionaea extends Flower{

	/**
	 * @param args
	 */
	
	public Dionaea()
	{
		super(null);
	}
	
	public Dionaea(Color color)
	{
		super(color);
	}
	
	public void turn()
	{
		setDirection(getDirection() + Location.HALF_RIGHT);
	}
	
	public void eat()
	{
		getGrid().get(getLocation().getAdjacentLocation(getDirection())).removeSelfFromGrid();
	}
	
	public boolean canEat()
	{
		Location next=getLocation().getAdjacentLocation(getDirection());
		if(getGrid().isValid(next))
		{
			if(getGrid().get(next)!=null)
			{
				if(getGrid().get(next) instanceof Bug)
					return true;
			}
		}
		return false;
	}
	
	public void act()
	{
		if(canEat())
			eat();
		else
			turn();
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		 ActorWorld world = new ActorWorld();
		 world.add(new Dionaea());
		 world.add(new Dionaea());
		 world.add(new Dionaea());
		 world.add(new Bug());
		 world.add(new Bug());
		 world.add(new Bug());
		 world.show();
		
	}

}

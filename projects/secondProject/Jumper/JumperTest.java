import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.Color;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;

import info.gridworld.grid.Location;

public class JumperTest{

    // Used to give row location of jumper
    static final int LOCATION_ROW = 4;
        
    // Used to give column location of jumper
    static final int LOCATION_COL = 4;

    // Used to give delta jump distance of jumper
    static final int DELTA = 2;

    // Used to set barrier
    static final int HALF_DELTA = 1;

    @Test
	public void testDefaultColor(){
        Jumper jumper = new Jumper();
		assertTrue(jumper.getColor() == Color.RED);
    }

    @Test
	public void testColor(){
        Jumper jumper = new Jumper(Color.PINK);
		assertTrue(jumper.getColor() == Color.PINK);
    }

	@Test
	public void testJump(){
        ActorWorld world = new ActorWorld();
        Jumper jumper = new Jumper();
        jumper.setDirection(Location.RIGHT);
        world.add(new Location(LOCATION_ROW, LOCATION_COL), jumper);
        world.step();
		assertTrue(jumper.getLocation().equals(new Location(LOCATION_ROW,LOCATION_ROW + DELTA)));
    }
    
    @Test
	public void testCanJumpAmongRock(){
        ActorWorld world = new ActorWorld();
        Jumper jumper = new Jumper();
        jumper.setDirection(Location.RIGHT);
        world.add(new Location(LOCATION_ROW, LOCATION_COL), jumper);
        Rock rock = new Rock();
        world.add(new Location(LOCATION_ROW, LOCATION_COL + HALF_DELTA), rock);
        world.step();
		assertTrue(jumper.getLocation().equals(new Location(LOCATION_ROW,LOCATION_ROW + DELTA)));
    }

    @Test
    public void testCanJumpAmongFlower(){
        ActorWorld world = new ActorWorld();
        Jumper jumper = new Jumper();
        jumper.setDirection(Location.RIGHT);
        world.add(new Location(LOCATION_ROW, LOCATION_COL), jumper);
        Flower flower = new Flower();
        world.add(new Location(LOCATION_ROW, LOCATION_COL + HALF_DELTA), flower);
        world.step();
		assertTrue(jumper.getLocation().equals(new Location(LOCATION_ROW,LOCATION_ROW + DELTA)));
    }

    @Test
    public void testCanJumpAmongBug(){
        ActorWorld world = new ActorWorld();
        Jumper jumper = new Jumper();
        jumper.setDirection(Location.RIGHT);
        world.add(new Location(LOCATION_ROW, LOCATION_COL), jumper);
        Bug bug = new Bug();
        world.add(new Location(LOCATION_ROW, LOCATION_COL + HALF_DELTA), bug);
        world.step();
		assertTrue(jumper.getLocation().equals(new Location(LOCATION_ROW,LOCATION_ROW)));
    }

}

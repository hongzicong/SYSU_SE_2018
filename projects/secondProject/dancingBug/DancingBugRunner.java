/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class DancingBugRunner 
{
    // Used to give row location of dancingBug
    static final int LOCATION_ROW = 5;
    
    // Used to give column location of dancingBug
    static final int LOCATION_COL = 5;

    // Used to give parameter of DancingBug
    static final int[] TURNARR = {2,5,3,7};
    
    private DancingBugRunner(){}
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        DancingBug alice = new DancingBug(turnArr);
        alice.setColor(Color.ORANGE);
        world.add(new Location(LOCATION_ROW, LOCATION_COL), alice);
        world.show();
    }
}

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
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A <code>KingCrab</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class KingCrab extends CrabCritter {
    
    /*
     * A KingCrab causes each actor that it processes to move one location further away from the KingCrab.
     * If the actor cannot move away, the KingCrab removes it from the grid.
     */
    public void processActors(List<Actor> actors) {
        for (Actor a : actors) {
            Location loc = a.getLocation();
            int direction = getLocation().getDirectionToward(loc);

            ArrayList<Location> possLocs = new ArrayList<>();
            possLocs.add(loc.getAdjacentLocation(direction));
            possLocs.add(loc.getAdjacentLocation(direction + Location.HALF_LEFT));
            possLocs.add(loc.getAdjacentLocation(direction + Location.HALF_RIGHT));

            for(Location tempLoc:possLocs){
                if(!getGrid().isValid(tempLoc)){
                    possLocs.remove(tempLoc);
                }
            }

            Location nextLoc = selectMoveLocation(possLocs);

            if(!nextLoc.equals(loc)){
                a.moveTo(nextLoc);
            } else{
                a.removeSelfFromGrid();
            }
        }
    }

}

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
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * A <code>BlusterCritter</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter
{
    
    /**
     * @return list of empty locations immediately to the right and to the left
     */
    public List<Location> getMoveLocations(){
        ArrayList<Location> locs = new ArrayList<Location>();
        
        Location loc = getLocation();

        Location right = loc.getAdjacentLocation(getDirection() + Location.RIGHT);
        Location left = loc.getAdjacentLocation(getDirection() + Location.LEFT);
        
        getRightLoc(locs, right);

        getLeftLoc(locs, left);

        
        return locs;
    }

    private void getRightLoc(List<Location> locs, Location right){
        Grid gr = getGrid();
        if (gr.isValid(right)){
            Location rightNext = right.getAdjacentLocation(getDirection() + Location.RIGHT);
            if (gr.isValid(rightNext) && gr.get(right) == null && gr.get(rightNext) == null){
                locs.add(rightNext);
            }
            else if(gr.isValid(rightNext) && gr.get(right) == null){
                locs.add(right);
            }
        }
    }

    private void getLeftLoc(List<Location> locs, Location left){
        Grid gr = getGrid();
        if (gr.isValid(left)){
            Location leftNext = left.getAdjacentLocation(getDirection() + Location.LEFT);
            if (gr.isValid(leftNext) && gr.get(left) == null && gr.get(leftNext) == null){
                locs.add(leftNext);
            }
            else if(gr.isValid(leftNext) && gr.get(left) == null){
                locs.add(left);
            }
        }
    }

    /**
     * If the crab critter doesn't move, it randomly turns left or right.
     */
    public void makeMove(Location loc)
    {
        if (loc.equals(getLocation()))
        {
            double r = Math.random();
            int angle;
            if (r < 0.5){
                angle = Location.LEFT;
            }
            else{
                angle = Location.RIGHT;
            }
            setDirection(getDirection() + angle);
        }
        else{
            super.makeMove(loc);
        }
    }

}

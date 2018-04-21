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

/**
 * A <code>BlusterCritter</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter
{

    private static final double DARKENING_FACTOR = 0.05;
    private static final int[] DELTA = {-2, -1, 0, 1, 2};

    private int mCourage;
    
    public BlusterCritter(int courage){
        super();
        mCourage = courage;
    }

    /**
     * Gets the actors for processing. Implemented to return the actors that
     * occupy neighboring grid locations. Override this method in subclasses to
     * look elsewhere for actors to process.<br />
     * Postcondition: The state of all actors is unchanged.
     * @return a list of actors that this critter wishes to process.
     */
    public ArrayList<Actor> getActors(){
        List<Actor> actors = new ArrayList<Actor>();
        Grid grid = getGrid();
        Location loc = getLocation();

        ArrayList<Location> locs = new ArrayList<Location>();

        int row = loc.getRow();
        int col = loc.getCol();

        for(int i = 0; i < 5; ++i){
            for(int j = 0; j < 5; ++j){
                int tempRow = row + DELTA[i];
                int tempCol = col + DELTA[j];
                Location tempLoc = new Location(tempRow, tempCol);
                if (isValid(tempLoc) && i != 2 && j != 2){
                    locs.add(tempLoc);
                }
            }
        }

        for (Location neighborLoc : locs){
            if (get(neighborLoc) != null && get(neighborLoc) instanceof Critter){
                actors.add(get(neighborLoc));
            }
        }

        return actors;
    }

    /**
     * Processes the elements of <code>actors</code>. New actors may be added
     * to empty locations. Implemented to "eat" (i.e. remove) selected actors
     * that are not critters. Override this method in subclasses to
     * process actors in a different way. <br />
     * Postcondition: (1) The state of all actors in the grid other than this
     * critter and the elements of <code>actors</code> is unchanged. (2) The
     * location of this critter is unchanged.
     * @param actors the actors to be processed
     */
    public void processActors(ArrayList<Actor> actors)
    {
        Color c = getColor();
        if(actors.size() < mCourage){
            int red = (int) (c.getRed() * (1 + DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 + DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 + DARKENING_FACTOR));
        } else{
            int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
            int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
            int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
        }
        setColor(new Color(red, green, blue));
    }

}

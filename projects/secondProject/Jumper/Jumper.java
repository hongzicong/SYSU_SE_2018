
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

import java.awt.Color;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

import info.gridworld.grid.Location;
import info.gridworld.grid.Grid;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Jumper extends Actor {

    /**
     * Constructs a red Jumper.
     */
    public Jumper() {
        setColor(Color.RED);
    }

    /**
     * Constructs a bug of a given color.
     * @param bugColor the color for this jumper
     */
    public Jumper(Color bugColor) {
        setColor(bugColor);
    }

    /**
     * Jumps if it can jump, turns otherwise.
     */
    public void act() {
        if (canJump()) {
            jump();
        } else {
            turn();
        }
    }

    /**
     * Turns the bug 45 degrees to the right without changing its location.
     */
    public void turn() {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /**
     * Jumps the bug forward
     */
    public void jump() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection()).getAdjacentLocation(getDirection());
        if (gr.isValid(next)) {
            moveTo(next);
        } else {
            removeSelfFromGrid();
        }
    }

    /**
     * Tests whether this jumper can move forward into a location that is empty
     * @return true if this jumper can jump.
     */
    public boolean canJump() {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) {
            return false;
        }

        Actor neighbor = gr.get(next);
        if (!((neighbor == null) || (neighbor instanceof Flower) || (neighbor instanceof Rock))) {
            return false;
        }

        next = next.getAdjacentLocation(getDirection());
        if (!gr.isValid(next)) {
            return false;
        }
        neighbor = gr.get(next);
        return (neighbor == null) || (neighbor instanceof Flower) ;
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
    }

}

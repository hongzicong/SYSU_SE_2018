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

import info.gridworld.actor.ActorWorld;

/**
 * This class runs a world that contains jumper bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public final class JumperRunner 
{
    
    private JumperRunner(){}

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        
        // In order to distinguish them
        world.add(new Jumper(Color.GREEN));
        world.add(new Jumper(Color.BLUE));

        world.add(new Rock());

        world.add(new Bug(Color.ORANGE));

        world.add(new Flower());

        world.show();
    }
}

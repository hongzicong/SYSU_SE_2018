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

import info.gridworld.actor.Bug;

/**
 * A <code>BoxBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class DancingBug extends Bug
{
    private int[] mTurnArr;
    private int count;

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public DancingBug(int[] turnArr)
    {
        count = 0;
        if(turnArr!=null){
            mTurnArr = new int[turnArr.length];
            System.arraycopy(turnArr, 0, mTurnArr, 0, turnArr.length);
        } else{
            mTurnArr = new int[1];
        }
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        if (canMove() && count % 2 == 0) {
            move();
        } else {
            for(int i = 0; i < mTurnArr[count / 2]; ++i){
                turn();
            }
        }
        count ++;
        count %= (mTurnArr.length * 2);
    }
}

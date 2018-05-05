package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	
	private Location next;
	
	private boolean isEnd = false;
	
	private Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();

	private Integer stepCount = 0;
	
	// final message has been shown
	private boolean hasShown = false; 

	// East, South, West, North
	private int[] directProb = {1, 1, 1, 1};

	/**
	 * denotes to the position of each direction in directProb
	 * used to solve magic number problem
	 */
	private static final int EAST_PROB = 0;
	private static final int SOUTH_PROB = 1;
	private static final int WEST_PROB = 2;
	private static final int NORTH_PROB = 3;

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {

		// init
		if(stepCount == 0){  
			Location local = getLocation();  

			// push first road into the crossLocation record
			ArrayList<Location> firstList = new ArrayList<Location>();  
			firstList.add(local);
			crossLocation.push(firstList);
		}

		// every 30 steps should update the probability of each direction
		if(stepCount % 30 == 0){
			guessDirectProb();
		}

		// check if there has road to go
		boolean willMove = canMove();

		if (isEnd) {
		//to show step count when reach the goal
			if (!hasShown) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			// can move to next position
			move();
			//increase step count when move 
			stepCount++;
		} else {
			backTrack();
		}
	}

	/**
	 * Find all positions that can be move to.
	 * The position has constraint that only four directions
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null){
			return null;
		}
		ArrayList<Location> valid = new ArrayList<Location>();
		Location[] allLoc = {loc.getAdjacentLocation(Location.EAST), loc.getAdjacentLocation(Location.WEST),
							 loc.getAdjacentLocation(Location.SOUTH), loc.getAdjacentLocation(Location.NORTH)};
		
		for(Location tempLoc:allLoc){
			if(gr.isValid(tempLoc)){
				Actor actor = gr.get(tempLoc);
				if(actor instanceof Rock && actor.getColor().equals(Color.RED)){
					next = tempLoc;
					ArrayList<Location> tempArr = new ArrayList<Location>();
					tempArr.add(tempLoc);
					return tempArr;
				} else if(actor == null){
					valid.add(tempLoc);
				}
			}
		}

		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {

		// check if the next location can move
		if(getValid(getLocation()).size() == 0){
			return false;
		}

		// the next location is a Rock which is not red
		return true;
	}

	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 * Meanwhile choose a new next position
	 */
	public void move() {

		Grid<Actor> gr = getGrid();

		if (gr == null){
			return;
		}
 
		Location loc = getLocation();

		// change next location and last location
		ArrayList<Location> canMovePosList = getValid(loc);

		next = getNextPos(canMovePosList);

		if (gr.isValid(next)) {  
            Actor actor = gr.get(next);  
			  
			// if reach to the goal rock
            if(actor instanceof Rock && actor.getColor().equals(Color.RED) ){  
                isEnd = true; 
			}  
			
			// It may be end if it reaches to red rock instead of only facing the red rock
			moveTo(next);
			
			int direct = loc.getDirectionToward(next);
			setDirection(direct);
				
			// update the probability
			directProb[(direct / 90 + 3) % 4] += 1;
			
            ArrayList<Location> temp = crossLocation.pop();  
            temp.add(next);  
            crossLocation.push(temp);  
              
            ArrayList<Location> newest = new ArrayList<Location>();  
            newest.add(next);  
            crossLocation.push(newest);  
        } else {
            removeSelfFromGrid();  
        }

		leaveFlower(loc);
	}

	/**
	 * used to guess the radom probability of each direction
	 */
	public void guessDirectProb(){  

		Grid<Actor> gr = getGrid();  
		ArrayList<Location> locations = gr.getOccupiedLocations();  
			
		for(Location loc : locations){  
			Actor goal = gr.get(loc);  
			if (goal instanceof Rock && goal.getColor().equals(Color.RED)){  
				// bug in north of goal, so it needs to go south
				if(getLocation().getRow() < loc.getRow()){  
					directProb[SOUTH_PROB] += 5;  
				// bug in south of goal, so it needs to go north
				} else { 
					directProb[NORTH_PROB] += 5;  
				} 
				// bug in west of goal, so it needs to go east
				if(getLocation().getCol() < loc.getCol()){  
					directProb[EAST_PROB] += 5;
				// bug in east of goal, so it needs to go west
				}else {  
					directProb[WEST_PROB] += 5;  
				}  
				return;  
			} 
		}  
	}


	/**
	 * If there is no road, the bug will return to the last cross point
	 */
	public void backTrack(){
		if(crossLocation.size() != 0){

			crossLocation.pop();

			if(crossLocation.size() != 0){
				Grid gr = getGrid();
				
				ArrayList<Location> backRoad = crossLocation.peek();  
				Location oldLoc = backRoad.get(0);
				
				Location loc = getLocation();

				int direct = loc.getDirectionToward(oldLoc);

				if (gr.isValid(oldLoc)) {  
                    setDirection(direct);  
                    moveTo(oldLoc);  
                    stepCount ++;
                } else {  
                    removeSelfFromGrid();  
				}
				
				directProb[(direct / 90 + 5) % 4] -= 1;

				leaveFlower(loc);
			}
		}
	}

	// leave flower after the bug
	private void leaveFlower(Location loc){
		Flower flower = new Flower(getColor());  
		flower.putSelfInGrid(getGrid(), loc);
	}

	private Location getNextPos(ArrayList<Location> validLoc){
		
		Location loc = getLocation();
		ArrayList<Integer> tempDirect = new ArrayList<Integer>();

		for(Location tempLoc : validLoc){
			tempDirect.add((loc.getDirectionToward(tempLoc) / 90 + 3) % 4);
		}
		
		int sum = 0;
		for(Integer i : tempDirect){
			sum += directProb[i];
		}
		int randNum = new Random().nextInt(sum);
		
		int i = 0;
		int low = 0;
		int high = directProb[tempDirect.get(0)];
		for(; i < tempDirect.size(); low += directProb[tempDirect.get(i)], high += directProb[tempDirect.get(i + 1)], ++i){
			if(low <= randNum && randNum < high){
				break;
			}
		}

		return validLoc.get(i);
	}

}

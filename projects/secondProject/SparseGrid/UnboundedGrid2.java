import info.gridworld.grid.*;
import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;

public class UnboundedGrid2<E> extends AbstractGrid<E>{

    // an array to store
    private Object[][] occupantArr;
    
    private int side;

    /**
     * Constructs an empty unbounded grid.
     */
    public UnboundedGrid2()
    {
        side = 16;
        occupantArr = new Object[side][side];
    }

    public int getNumRows()
    {
        return side;
    }

    public int getNumCols()
    {
        return side;
    }

    public boolean isValid(Location loc)
    {
        return loc.getRow() >= 0 && loc.getCol() >= 0;
    }

    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            for (int c = 0; c < getNumCols(); c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (loc.getRow() >= side || loc.getCol() >= side){
            return null;
        }
        return (E) occupantArr[loc.getRow()][loc.getCol()]; // unavoidable warning
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        if (loc.getRow() >= side || loc.getCol() >= side){
            biggerGrid();
        }

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArr[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    private void biggerGrid(){
        
        // double the new side
        int newSide = side * 2;

        // a new occupant map
        Object[][] newOccupantArr = new Object[newSide][newSide];

        for(int i = 0; i < side; ++i){
            for(int j = 0; j < side; ++j){
                newOccupantArr[i][j] = occupantArr[i][j];
            }
        }

        occupantArr = newOccupantArr;
        side = newSide;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        
        if (loc.getRow() >= side || loc.getCol() >= side){
            return null;
        }

        // Remove the object from the grid.
        E r = get(loc);
        occupantArr[loc.getRow()][loc.getCol()] = null;
        return r;
    }

}

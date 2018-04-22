import info.gridworld.grid.*;
import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;

public class UnboundedGrid2<E> extends AbstractGrid<E>{

    private Object[][] occupantMap;
    
    private int side;

    /**
     * Constructs an empty unbounded grid.
     */
    public UnboundedGrid2()
    {
        int side = 16;
        occupantMap = new Object[side][side];
    }

    public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
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
        return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
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
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    private void biggerGrid(){
        
        // double the new size
        int newSide = size * 2;

        // a new occupant map
        Object[][] newOccupantMap = new Object[newSide][newSide];

        for(int i = 0; i < side; ++i){
            for(int j = 0; j < side; ++j){
                newOccupantMap[i][j] = occupantMap[i][j];
            }
        }

        occupantMap = newOccupantMap;
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
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }

}

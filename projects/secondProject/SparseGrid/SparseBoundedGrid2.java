
import info.gridworld.grid.*;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;

public class SparseBoundedGrid2<E> extends AbstractGrid<E>{

    private HashMap<Location, E> occupantMap;

    private int mRows;
    private int mCols;

    /**
     * Constructs an empty sparse grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in SparseBoundedGrid
     * @param cols number of columns in SparseBoundedGrid
     */
    public SparseBoundedGrid2(int rows, int cols)
    {
        if (rows <= 0){
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0){
            throw new IllegalArgumentException("cols <= 0");
        }
        mRows = rows;
        mCols = cols;
        occupantMap = new HashMap();
    }

    public int getNumRows()
    {
        return mRows;
    }

    public int getNumCols()
    {
        return mCols;
    }

    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    // get the occupied locations
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (Location loc : occupantMap.keySet()){
            theLocations.add(loc);
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }

        return occupantMap.get(loc); 
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (obj == null){
            throw new NullPointerException("obj == null");
        }
        
        return occupantMap.put(loc, obj); 
    }

    public E remove(Location loc)
    {
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        
        return occupantMap.remove(loc); 

    }

}
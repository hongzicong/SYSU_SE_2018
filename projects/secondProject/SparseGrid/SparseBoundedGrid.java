
import info.gridworld.grid.*;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;

public class SparseBoundedGrid<E> extends AbstractGrid<E>{

    private ArrayList<LinkedList> occupantInColArr;

    private int mRows;
    private int mCols;

    /**
     * Constructs an empty sparse grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in SparseBoundedGrid
     * @param cols number of columns in SparseBoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols)
    {
        if (rows <= 0){
            throw new IllegalArgumentException("rows <= 0");
        }
        if (cols <= 0){
            throw new IllegalArgumentException("cols <= 0");
        }
        mRows = rows;
        mCols = cols;
        occupantInColArr = new ArrayList<>();
        for(int i = 0; i < rows; ++i){
            occupantInColArr.add(new LinkedList<OccupantInCol>());
        }
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
    public List<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++)
        {
            LinkedList<OccupantInCol> row = occupantInColArr.get(r);
            if (row != null){
                for (OccupantInCol tempOccupantInCol:row){
                    Location loc = new Location(r, tempOccupantInCol.getCol());
                    theLocations.add(loc);
                }
            }
        }

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }

        LinkedList<OccupantInCol> row = occupantInColArr.get(loc.getRow());
        
        if(row != null){
            for(OccupantInCol tempOccupantInCol: row){
                if(loc.getCol() == tempOccupantInCol.getCol()){
                    return (E)tempOccupantInCol.getOccupant(); //must cast to E
                }
            }
        }
        return null;
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (obj == null){
            throw new NullPointerException("obj == null");
        }
        
        // Add the object to the grid.
        E oldOccupant = remove(loc);

        occupantInColArr.get(loc.getRow()).add(new OccupantInCol(obj, loc.getCol()));
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        
        // Remove the object from the grid.
        E obj = get(loc);

        // It is not found
        if (obj == null) return null;
        
        LinkedList<OccupantInCol> row = occupantInColArr.get(loc.getRow());
        
        if(row != null){
            for(OccupantInCol tempOccupantInCol: row){
                if(loc.getCol() == tempOccupantInCol.getCol()){
                    row.remove(tempOccupantInCol);
                }
            }
        }

        return obj;

    }

}
import info.gridworld.grid.*;
import java.util.ArrayList;

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
        for (int r = 0; r < side; r++)
        {
            for (int c = 0; c < side; c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null){
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
        if (loc.getRow() >= side || loc.getCol() >= side){
            return null;
        }
        return (E) occupantArr[loc.getRow()][loc.getCol()]; // unavoidable warning
    }

    public E put(Location loc, E obj)
    {
        if (loc == null){
            throw new NullPointerException("loc == null");
        }
        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
        if (obj == null){
            throw new NullPointerException("obj == null");
        }

        // Add the object to the grid.
        E oldOccupant = get(loc);

        if (loc.getRow() >= side || loc.getCol() >= side){
            Object[][] tmp = occupantArr;
            int ant = side;
            while(loc.getRow() >= ant || loc.getCol() >= ant){
                ant *= 2;
            }
            occupantArr = new Object[ant][ant];
            for (int r = 0; r < side; r++) {
                for (int c = 0; c < side; c++)
                    occupantArr[r][c] = tmp[r][c];
            }
            side = ant;
        }

        occupantArr[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    public E remove(Location loc)
    {
        if (loc == null){
            throw new NullPointerException("loc == null");
        }

        if (!isValid(loc)){
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }
 
        E r = get(loc);
        if (loc.getRow() < occupantArr.length && loc.getCol() < occupantArr.length)
        occupantArr[loc.getRow()][loc.getCol()] = null;
        return r;
    }

}

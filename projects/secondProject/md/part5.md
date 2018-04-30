1. Specified in the `Grid` Interface and `BoundedGrid` and `UnboundedGrid` class provide an implementation of this method.

```
// @file: info/gridworld/grid/Grid.java
// @line: 50
boolean isValid(Location loc);

// @file: info/gridworld/grid/BoundedGrid.java
// @line: 60
public boolean isValid(Location loc)

// @file: info/gridworld/grid/UnboundedGrid.java
// @line: 53
public boolean isValid(Location loc)
```

2. `getValidAdjacentLocations` calls. `getNeighbors`, `getEmptyAdjacentLocations` and `getOccupiedAdjacentLocations` do not directly call but they use other method to call `isValid` indirectly.

```
// @file: info/gridworld/grid/AbstractGrid.java
// @line: 44~45
if (isValid(neighborLoc))
    locs.add(neighborLoc);

// @file: info/gridworld/grid/AbstractGrid.java
// @line: 65
for (Location neighborLoc : getValidAdjacentLocations(loc))

// @file: info/gridworld/grid/AbstractGrid.java
// @line: 54
for (Location neighborLoc : getValidAdjacentLocations(loc))

// @file: info/gridworld/grid/AbstractGrid.java
// @line: 31
for (Location neighborLoc : getOccupiedAdjacentLocations(loc))
```


3. `get` and `getOccupiedAdjacentLocations`. The latter is implemented in `AbstractGrid` and the former is implemented in `BoundedGrid` and `UnboundedGrid` class.

```
// @file: info/gridworld/grid/AbstractGrid.java
// @line: 62
public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)

// @file: info/gridworld/grid/BoundedGrid.java
// @line: 85
public E get(Location loc)

// @file: info/gridworld/grid/UnboundedGrid.java
// @line: 66
public E get(Location loc)
```

4. `get` returns a reference to the object in the given location or null. `getEmptyAdjacentLocations` tests if the location is empty and adds empty position to the list.

```
// @file: info/gridworld/grid/AbstractGrid.java
// @line: 51~60
public ArrayList<Location> getEmptyAdjacentLocations(Location loc)
{
    ArrayList<Location> locs = new ArrayList<Location>();
    for (Location neighborLoc : getValidAdjacentLocations(loc))
    {
        if (get(neighborLoc) == null)
            locs.add(neighborLoc);
    }
    return locs;
}
```

5. The number of possible result changes from 8 to 4.

```
// @file: info/gridworld/grid/AbstractGrid.java
// @line: 40~47
int d = Location.NORTH;
for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
{
    Location neighborLoc = loc.getAdjacentLocation(d);
    if (isValid(neighborLoc))
        locs.add(neighborLoc);
    d = d + Location.HALF_RIGHT;
}
```

1. The constructor of `BoundedGrid` class will ensure there must be one location in the grid otherwise it will throw an exception.

```
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 39~46
public BoundedGrid(int rows, int cols)
{
    if (rows <= 0)
        throw new IllegalArgumentException("rows <= 0");
    if (cols <= 0)
        throw new IllegalArgumentException("cols <= 0");
    occupantArray = new Object[rows][cols];
}
```

2. `occupantArray[0].length`. The constructor of `BoundedGrid` class will make sure.

```
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 53~58
public int getNumCols()
{
    // Note: according to the constructor precondition, numRows() > 0, so
    // theGrid[0] is non-null.
    return occupantArray[0].length;
}
```


3. Row(Column) must be less than the maximum number of rows(columns) and greater than or equal to 0.

```
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 60~64
public boolean isValid(Location loc)
{
    return 0 <= loc.getRow() && loc.getRow() < getNumRows()
            && 0 <= loc.getCol() && loc.getCol() < getNumCols();
}
```

4. `ArrayList<Location>` and the time complexity is `O(r*c)` where `r` is the number of rows and `c` is the number of columns.

```
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 71~80
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
```


5. `E`, `Location` and `O(1)`.

```
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 85~91
public E get(Location loc)
{
    if (!isValid(loc))
        throw new IllegalArgumentException("Location " + loc
                + " is not valid");
    return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
}
```

6. The location is not a valid location or the object sent to the method is null and the time complexity is `O(1)`.

```
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 95~99
if (!isValid(loc))
    throw new IllegalArgumentException("Location " + loc
            + " is not valid");
if (obj == null)
    throw new NullPointerException("obj == null");
```

7. The returned type is `E` and the `null` will be returned when making an attempt to remove an item from empty location. The time complexity is `O(1)`.

```
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 107~117
public E remove(Location loc)
{
    if (!isValid(loc))
        throw new IllegalArgumentException("Location " + loc
                + " is not valid");
    
    // Remove the object from the grid.
    E r = get(loc);
    occupantArray[loc.getRow()][loc.getCol()] = null;
    return r;
}
```

8. Yes, most of operations are `O(1)` and only one method `getOccupiedLocations` needs `O(r*c)`. But we can use linked list or hash map to improve performance.

```
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 31
private Object[][] occupantArray; // the array storing the grid elements

// @file: info/gridworld/grid/BoundedGrid.java
// @line: 85
public E get(Location loc)

// @file: info/gridworld/grid/BoundedGrid.java
// @line: 107
public E remove(Location loc)
```


1. `hashCode` and `equals` method. The `Location` class implements `Comparable` interface which means it needs to implement `compareTo` method. Yes.

```
// @file: info/gridworld/grid/Location.java
// @line: 205
public boolean equals(Object other)

// @file: info/gridworld/grid/Location.java
// @line: 218
public int hashCode()

// @file: info/gridworld/grid/Location.java
// @line: 28
public class Location implements Comparable
```


2. `HashMap` is the data structure of `UnboundedGrid`. Method `isValid` always
returns true and it does not check for null locations. In `HashMap`, null is legal value.
But in `UnboundedGrid`, null is not a legal value. Hence `get`, `put` and `remove` will check the location parameter and throw a `NullPointerException` when the parameter is null.


In `BoundedGrid`, `isValid` will be called before every access to the element in the grid. If the element is out of the bound, it will cause a `IllegalArgumentException`. If the element is null, attempting to access will also cause a `NullPointerException` to be thrown. 

```
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 95~99
if (!isValid(loc))
    throw new IllegalArgumentException("Location " + loc
            + " is not valid");
if (obj == null)
    throw new NullPointerException("obj == null");
```
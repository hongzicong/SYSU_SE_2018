1. loc1.getRow();

```
// @file: info/gridworld/grid/Location.java
// @line: 110~113
public int getRow()
{
    return row;
}
```

2. false

```
// @file: info/gridworld/grid/Location.java
// @line: 205~212
public boolean equals(Object other)
{
    if (!(other instanceof Location))
        return false;

    Location otherLoc = (Location) other;
    return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
}
```

3. (4, 4)

```
// @file: info/gridworld/grid/Location.java
// @line: 133~139
int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
if (adjustedDirection < 0)
    adjustedDirection += FULL_CIRCLE;

adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
int dc = 0;
int dr = 0;
```



4. 135

```
// @file: info/gridworld/grid/Location.java
// @line: 180~187
int dx = target.getCol() - getCol();
int dy = target.getRow() - getRow();
// y axis points opposite to mathematical orientation
int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));
int compassAngle = RIGHT - angle;
```


5. Using the parameter, the method returns the adjacent neighbor location according to the compass direction that is closest to the direction given.

```
// @file: info/gridworld/grid/Location.java
// @line: 133~139
int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
if (adjustedDirection < 0)
    adjustedDirection += FULL_CIRCLE;

adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
int dc = 0;
int dr = 0;
```


1. For a reference to a grid object as gird, we can get the count of objects in the grid by using grid.getOccupiedLocations().size() and get the count of empty objects in the grid by using grid.getNumRows() * gird.getNumCols() - grid.getOccupiedLocations().size()

```
// @file: info/gridworld/grid/Grid.java
// @line: 35, 41, 85
int getNumRows();
int getNumCols();
ArrayList<Location> getOccupiedLocations();
```

2. Check whether isValid(new Location(10,10)) return a true boolean value or not, if we get a true value, it means location(10,10) is in a grid.

```
// @file: info/gridworld/grid/Grid.java
// @line: 50
boolean isValid(Location loc);
```

For BoundedGrid,
```
// @file: info/gridworld/grid/UnboundedGrid.java
// @line: 60~64
public boolean isValid(Location loc)
{
    return 0 <= loc.getRow() && loc.getRow() < getNumRows()
            && 0 <= loc.getCol() && loc.getCol() < getNumCols();
}
```

For UnBoundGrid,
```
// @file: info/gridworld/grid/UnboundedGrid.java
// @line: 53~56
public boolean isValid(Location loc)
{
    return true;
}
```



3. Grid is an interface which should be implemented in the another class such as BoundedGrid and UnboundedGrid.

```
// @file: info/gridworld/grid/UnboundedGrid.java
// @line: 31
public class UnboundedGrid<E> extends AbstractGrid<E>
```

```
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 29
public class BoundedGrid<E> extends AbstractGrid<E>
```

```
// @file: info/gridworld/grid/AbstractGrid.java
// @line: 26
public abstract class AbstractGrid<E> implements Grid<E>
```

4. Although array is more easier to be used than ArrayList, it may cost a lot when definition. We need to give the size of array before using it but we don't need to do anything before using ArrayList.

For example:
```
// @file: info/gridworld/grid/BoundedGrid.java
// @line: 78
theLocations.add(loc);
```
We can dynamically to add element when running.

1. color direction location

```
// @file: info/gridworld/actor/Actor.java
// @line: 32~34
private Location location;
private int direction;
private Color color;
```

2. North Blue

```
// @file: info/gridworld/actor/Actor.java
// @line: 39~45
public Actor()
{
    color = Color.BLUE;
    direction = Location.NORTH;
    grid = null;
    location = null;
}
```

3. In the semantics, Actor consists of behavior and property, so we shouldn't use interface to construct Actor.

```
// @file: info/gridworld/actor/Actor.java
// @line: 29, 32~34, 51
public class Actor
private Location location;
private int direction;
private Color color;
public Color getColor()
```

4. No, it will throw a IllegalStateException if we make an actor put or remove itself into a grid twice. But we can place an actor into a grid, remove itself, and then put it self back.

```
// @file: info/gridworld/actor/Actor.java
// @line: 117~119
if (grid != null)
    throw new IllegalStateException(
            "This actor is already contained in a grid.");
```

5. setDirection(getDirection() + Location.Right)

```
// @file: info/gridworld/actor/Actor.java
// @line: 80~85
public void setDirection(int newDirection)
{
    direction = newDirection % Location.FULL_CIRCLE;
    if (direction < 0)
        direction += Location.FULL_CIRCLE;
}
```

```
// @file: info/gridworld/grid/Location.java
// @line: 40
public static final int RIGHT = 90;
```

1. As follows
```
// @file: info/gridworld/actor/Bug.java
// @line: 98~99
if (!gr.isValid(next))
    return false;
```

2. As follows

```
// @file: info/gridworld/actor/Bug.java
// @line: 101
return (neighbor == null) || (neighbor instanceof Flower);
```

3. The *isValid* and *get* method, where the first method is used to check whether the position is in the bounded of map or not and second method is used to check whether the position is occupied by another actor or not.

```
// @file: info/gridworld/actor/Bug.java
// @line: 93~101
Grid<Actor> gr = getGrid();
if (gr == null)
    return false;
Location loc = getLocation();
Location next = loc.getAdjacentLocation(getDirection());
if (!gr.isValid(next))
    return false;
Actor neighbor = gr.get(next);
return (neighbor == null) || (neighbor instanceof Flower);
```


4. The *getAdjacentLocation* method is used to get the next position of Bug using now direction.

```
// @file: info/gridworld/actor/Bug.java
// @line: 97
Location next = loc.getAdjacentLocation(getDirection());
```


5. *getLocation*, *getDirection* and *getGrid*

```
// @file: info/gridworld/actor/Bug.java
// @line: 93, 96, 97
Grid<Actor> gr = getGrid();
Location loc = getLocation();
Location next = loc.getAdjacentLocation(getDirection());
```


6. It may remove itself from the grid.

```
// @file: info/gridworld/actor/Bug.java
// @line: 78~81
if (gr.isValid(next))
    moveTo(next);
else
    removeSelfFromGrid();
```

7. Yes, *loc* is needed to record the old position for placing flower.

```
// @file: info/gridworld/actor/Bug.java
// @line: 83
flower.putSelfInGrid(gr, loc);
```


8. It can be known by click the menu in the GUI for checking the property and open the file of the code.

```
// @file: info/gridworld/actor/Bug.java
// @line: 82
Flower flower = new Flower(getColor());
```

9. In the *move* method in Bug class, it will place a flower when remove, but in the pure *removeSelfFromGrid* it only remove itself.

```
// @file: info/gridworld/actor/Bug.java
// @line: 78~83
if (gr.isValid(next))
    moveTo(next);
else
    removeSelfFromGrid();
Flower flower = new Flower(getColor());
flower.putSelfInGrid(gr, loc);
```

10. 
`Flower flower = new Flower(getColor());`

`flower.putSelfInGrid(gr, loc);`

```
// @file: info/gridworld/actor/Bug.java
// @line: 82~83
Flower flower = new Flower(getColor());
flower.putSelfInGrid(gr, loc);
```

11. 4 times

```
// @file: info/gridworld/actor/Bug.java
// @line: 62~65
public void turn()
{
    setDirection(getDirection() + Location.HALF_RIGHT);
}
```
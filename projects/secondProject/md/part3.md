1. loc1.getRow();


2. false


3. new Location(4, 4);


4. 135


5. Using the parameter, the method returns the adjacent neighbor location according to the compass direction that is closest to the direction given.

1. For a reference to a grid object as gird, we can get the count of objects in the grid by using grid.getOccupiedLocations().size() and get the count of empty objects in the grid by using grid.getNumRows() * gird.getNumCols() - grid.getOccupiedLocations().size()

2. Check whether isValid(new Location(10,10)) return a true boolean value or not, if we get a true value, it means location(10,10) is in a grid.

3. Grid is an interface which should be implemented in the another class such as BoundedGrid and UnboundedGrid.

4. Although array is more easier to be used than ArrayList, it may cost a lot when definition. We need to give the size of array before using it but we don't need to do anything before using ArrayList.


1. color direction location

2. North Blue

3. In the semantics, Actor consists of behavior and property, so we shouldn't use interface to construct Actor.

4. No, it will throw a IllegalStateException if we make an actor put or remove itself into a grid twice. But we can place an actor into a grid, remove itself, and then put it self back.

5. setDirection(getDirection() + Location.Right)




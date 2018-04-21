1. What methods are implemented in Critter?
`act`, `getActors`, `processActors`, `getMoveLocations`, `selectMoveLocation`, `makeMove`

```
// @file: info/gridworld/actor/Critter.java
// @line: 38, 56, 71, 88, 104, 125
public void act()
public ArrayList<Actor> getActors()
public void processActors(ArrayList<Actor> actors)
public ArrayList<Location> getMoveLocations()
public Location selectMoveLocation(ArrayList<Location> locs)
public void makeMove(Location loc)
```

2. What are the five basic actions common to all critters when they act?

`getActors`, `processActors`, `getMoveLocations`, `selectMoveLocation`, `makeMove`

```
// @file: info/gridworld/actor/Critter.java
// @line: 38~47
public void act()
{
    if (getGrid() == null)
        return;
    ArrayList<Actor> actors = getActors();
    processActors(actors);
    ArrayList<Location> moveLocs = getMoveLocations();
    Location loc = selectMoveLocation(moveLocs);
    makeMove(loc);
}
```

3. Should subclasses of Critter override the getActors method? Explain.

Yes, subclasses of Critter may need to choose which locations to influence and choose which actor to influence.

For example:
```
// @file: critter/CrabCritter.java
// @line: 46~54
ArrayList<Actor> actors = new ArrayList<Actor>();
int[] dirs =
    { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
for (Location loc : getLocationsInDirections(dirs))
{
    Actor a = getGrid().get(loc);
    if (a != null)
        actors.add(a);
}
```

4. Describe the way that a critter could process actors.

1. Make actors disappear
2. Change actors' color
3. Change actors' direction

For example:
```
// @file: info/gridworld/actor/Critter.java
// @line: 71~78
public void processActors(ArrayList<Actor> actors)
{
    for (Actor a : actors)
    {
        if (!(a instanceof Rock) && !(a instanceof Critter))
            a.removeSelfFromGrid();
    }
}
```

5. What three methods must be invoked to make a critter move? Explain each of these methods.
 
For a Critter class:
1. `getMoveLocations` is used to get a list of possible locations for the next move.
2. `selectMoveLocation` is used to select the location for the next move.
3. `makeMove` is used to move this critter to the given location loc, or removes this critter from its grid if loc is null.

```
// @file: info/gridworld/actor/Critter.java
// @line: 38~47
public void act()
{
    if (getGrid() == null)
        return;
    ArrayList<Actor> actors = getActors();
    processActors(actors);
    ArrayList<Location> moveLocs = getMoveLocations();
    Location loc = selectMoveLocation(moveLocs);
    makeMove(loc);
}
```


6. Why is there no Critter constructor?

Because Critter class extends Actor class, Actor class has a default constructor which means Critter class also has a default constructor.

```
// @file: info/gridworld/actor/Critter.java
// @line: 31
public class Critter extends Actor
```

```
// @file: info/gridworld/actor/Actor.java
// @line: 39
public Actor()
```


1. Why does act cause a ChameleonCritter to act differently from a Critter even though ChameleonCritter does not override act?

Method `act` calls lots of method for example `processActors` and `makeMove` and ChameleonCritter overrides them, thus it acts differently from a Critter.

```
// @file: critters/ChameleonCritter.java
// @line: 39, 61
public void processActors(ArrayList<Actor> actors)
public void makeMove(Location loc)
```

2. Why does the makeMove method of ChameleonCritter call super.makeMove?

Calling `super.makeMove` can write less code. After changing its direction, it need to move itself to next position which is as same as its super class Critter.

```
// @file: critters/ChameleonCritter.java
// @line: 61~65
public void makeMove(Location loc)
{
    setDirection(getLocation().getDirectionToward(loc));
    super.makeMove(loc);
}
```

3. How would you make the ChameleonCritter drop flowers in its old location when it moves?

Add a statement in `makeMove` method as follows:

```
// @file: critters/ChameleonCritter.java
// @line: 61~65
public void makeMove(Location loc)
{
    Location oldLoc = getLocation();
    setDirection(getLocation().getDirectionToward(loc));
    super.makeMove(loc);
    if(!oldLoc.equals(loc)){
        Flower flo = new Flower(getColor());
        flo.putSelfInGrid(getGrid(), oldLoc);
    } 
}
```

place a flower behind it if it changes its location.

4. Why doesn’t ChameleonCritter override the getActors method?

Because it wants to do so, it doesn't override the `getActors` method and get all actors near ChameleonCritter object.

```
// @file: info/gridworld/actor/Critter.java
// @line: 56~59
public ArrayList<Actor> getActors()
{
    return getGrid().getNeighbors(getLocation());
}
```

5. Which class contains the getLocation method?

Actor class and all classes which inherits Actor class.

```
// @file: info/gridworld/actor/Actor.java
// @line: 102~105
public Location getLocation()
{
    return location;
}
```


6. How can a Critter access its own grid?

Use `getGrid` method which is inherited from Actor class.

```
// @file: info/gridworld/actor/Actor.java
// @line: 92~95
public Grid<Actor> getGrid()
{
    return grid;
}
```


1. Why doesn’t CrabCritter override the processActors method?

It need to eat its neighbors, which is as same as its super class Critter.

```
// @file: info/gridworld/actor/Critter.java
// @line: 71~78
public void processActors(ArrayList<Actor> actors)
{
    for (Actor a : actors)
    {
        if (!(a instanceof Rock) && !(a instanceof Critter))
            a.removeSelfFromGrid();
    }
}
```

2. Describe the process a CrabCritter uses to find and eat other actors. Does it always eat all neighboring actors? Explain.

No, it only eat actors in the three locations immediately in front, to its front-right and to its front-left.

```
// @file: critters/CrabCritter.java
// @line: 71~78
int[] dirs = { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
for (Location loc : getLocationsInDirections(dirs))
{
    Actor a = getGrid().get(loc);
    if (a != null)
        actors.add(a);
}
```

3. Why is the getLocationsInDirections method used in CrabCritter?

It will get the valid adjacent locations according to the direction array parameter.

```
// @file: critters/CrabCritter.java
// @line: 107~112
for (int d : directions)
{
    Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
    if (gr.isValid(neighborLoc))
        locs.add(neighborLoc);
}
```

4. If a CrabCritter has location (3, 4) and faces south, what are the possible locations for actors that are returned by a call to the getActors method?

(4,3), (4,4), and (4,5)

```
// @file: critters/CrabCritter.java
// @line: 47~48
int[] dirs ={ Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
```


5. What are the similarities and differences between the movements of a CrabCritter and a Critter?

Similarity: They both choose their next position randomly.

```
// @file: info/gridworld/actor/Critter.java
// @line: 104~111
public Location selectMoveLocation(ArrayList<Location> locs)
{
    int n = locs.size();
    if (n == 0)
        return getLocation();
    int r = (int) (Math.random() * n);
    return locs.get(r);
}
```

Difference: CrabCritter only move to its right or left position but Critter can move to any empty adjacent location. In addition, CrabCritter can change its direction to left or right when moving.

```
// @file: critters/CrabCritter.java
// @line: 65~66, 87
int[] dirs = { Location.LEFT, Location.RIGHT };
setDirection(getDirection() + angle);
```

6. How does a CrabCritter determine when it turns instead of moving?

If its now location equals to next location, it will change its direction to left or right randomly as follows.

```
// @file: critters/CrabCritter.java
// @line: 79~88
if (loc.equals(getLocation()))
{
    double r = Math.random();
    int angle;
    if (r < 0.5)
        angle = Location.LEFT;
    else
        angle = Location.RIGHT;
    setDirection(getDirection() + angle);
}
```


7. Why don’t the CrabCritter objects eat each other?

Because `processActors` method is inherited from Critter. CrabCritter only eat actors which are not Rock and Critter.

```
// @file: info/gridworld/actor/Critter.java
// @line: 71~78
public void processActors(ArrayList<Actor> actors)
{
    for (Actor a : actors)
    {
        if (!(a instanceof Rock) && !(a instanceof Critter))
            a.removeSelfFromGrid();
    }
}
```
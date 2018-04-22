import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Bug;

/**
 * This class runs a world with additional grid choices.
 */
public final class SparseGridRunner
{
    private SparseGridRunner(){}

    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();

        // Add four grid class
        world.addGridClass("SparseBoundedGrid");
        world.addGridClass("SparseBoundedGrid2");
        world.addGridClass("SparseBoundedGrid3");
        world.addGridClass("UnboundedGrid1");

        // Init a new Critter
        world.add(new Location(2, 2), new Bug());
        world.add(new Location(5, 5), new Bug());

        world.show();
    }
}
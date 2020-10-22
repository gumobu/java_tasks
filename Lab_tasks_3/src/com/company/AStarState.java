package com.company;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;

    /** Initialize a map for all open waypoints**/
    private HashMap<Location, Waypoint> openHeights = new HashMap<Location, Waypoint> ();

    /** Initialize a map for all closed waypoints**/
    private HashMap<Location, Waypoint> closeHeights = new HashMap<Location, Waypoint> ();

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint() {
        //Return null if there are no open waypoints
        if (numOpenWaypoints() == 0){
            return null;
        }
        //Initialize a KeySet for all open heights
        Set<Location> openHeightsKeys = openHeights.keySet();
        //Initialize iterator for iterating through the set
        Iterator iter = openHeightsKeys.iterator();
        //Initialize variables to store the best Waypoint' cost and the best Waypoint itself
        Waypoint best = null;
        float bestCost = Float.MAX_VALUE;
        //Iterates through all open waypoints
        while (iter.hasNext()){
            Location location = (Location)iter.next();
            Waypoint waypoint = openHeights.get(location);
            float waypointTotalCost = waypoint.getTotalCost();
            //If the total cost of the waypoint is lower than of stored one
            //Replace the values
            if (waypointTotalCost < bestCost){
                best = openHeights.get(location);
                bestCost = waypointTotalCost;
            }
        }
        //Returns the best waypoint
        return best;
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        // Finds the location of the new waypoint.
        Location location = newWP.getLocation();
        // Checks to see if there is already an open waypoint at the new location.
        if (openHeights.containsKey(location))
        {
            // If true, checks to see if the waypoint's previous cost is less than
            // the current waypoint's previous cost value.
            Waypoint current_waypoint = openHeights.get(location);
            if (newWP.getPreviousCost() < current_waypoint.getPreviousCost())
            {
                // Replace if true
                openHeights.put(location, newWP);
                return true;
            }
            return false;
        }
        // If there is no open waypoint at the location
        openHeights.put(location, newWP);
        return true;
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints() {
        return openHeights.size();
    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
        Waypoint waypoint = openHeights.remove(loc);
        closeHeights.put(loc, waypoint);
    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {
        return closeHeights.containsKey(loc);
    }
}


package storyworlds.model.implementation;

import java.util.HashMap;
import java.util.Map;

import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.Player;

public class BidirectionalLink implements Link {

    private final Map<Location, Location> locationMap = new HashMap<Location, Location>();
    private final String                  text;

    public BidirectionalLink(String text, Location startLocation, Location endLocation) {
        this.text = text;
        locationMap.put(startLocation,
                        endLocation);
        locationMap.put(endLocation,
                        startLocation);
    }

    public String getText(Player player) {
        return text;
    }

    public Location getLinkedLocation(Location location) {
        return locationMap.get(location);
    }

    public boolean isPassable(Player player) {
        return true;
    }

}

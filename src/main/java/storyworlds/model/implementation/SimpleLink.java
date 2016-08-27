package storyworlds.model.implementation;

import java.util.HashMap;
import java.util.Map;

import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.Player;

public class SimpleLink implements Link {
    private Map<Location, Location> locations;
    private String text;
    private boolean isPassable;

    public SimpleLink(Location location1, Location location2, String text, boolean isPassable) {
        this.locations = new HashMap<Location, Location>();
        this.locations.put(location1, location2);
        this.locations.put(location2, location1);
        this.text = text;
        this.isPassable = isPassable;
    }
    
    public Location getLinkedLocation(Location startLocation) {
        return locations.get(startLocation);
    }

    public boolean isPassable(Player player) {
        return isPassable;
    }

    public String getText(Player player) {
        return text;
    }
}

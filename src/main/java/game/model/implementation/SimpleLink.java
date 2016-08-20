package game.model.implementation;

import game.model.*;
import java.util.Map;
import java.util.HashMap;

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

    public String getText() {
        return text;
    }

    public boolean isPassable(Player player) {
        return true;
    }
}

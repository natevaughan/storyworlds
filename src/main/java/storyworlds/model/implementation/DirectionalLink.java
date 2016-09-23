package storyworlds.model.implementation;

import java.util.HashMap;
import java.util.Map;

import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.Player;

public class DirectionalLink implements Link {

    protected final Location endLocation;
    protected final String                  passText;

    public DirectionalLink(String passText, Location endLocation) {
        this.passText = passText;
        this.endLocation = endLocation;
    }

    public String getText(Player player) {
        return passText;
    }

    public Location getLinkedLocation(Location location) {
        return endLocation;
    }

    public boolean isPassable(Player player) {
        return true;
    }

}

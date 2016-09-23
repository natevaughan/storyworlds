package storyworlds.model.implementation;

import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.Player;

public class DirectionalLink implements Link {

    protected String description;
    protected final Location endLocation;
    protected final String                  passText;

    public DirectionalLink(String description, String passText, Location endLocation) {
        this.description = description;
        this.passText = passText;
        this.endLocation = endLocation;
    }

    public String getDescription() {
        return description;
    }

    public String getPassText(Player player) {
        return passText;
    }

    public Location getLinkedLocation(Location location) {
        return endLocation;
    }

    public boolean isPassable(Player player) {
        return true;
    }

}

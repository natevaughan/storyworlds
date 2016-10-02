package storyworlds.model.implementation;

import storyworlds.model.Location;

/**
 * Created by nvaughan on 9/24/2016.
 */
public class AbstractLink {
    protected final String description;
    protected final Location endLocation;

    public AbstractLink(String description, Location endLocation) {
        this.endLocation = endLocation;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Location getLinkedLocation(Location startLocation) {
        return endLocation;
    }
}

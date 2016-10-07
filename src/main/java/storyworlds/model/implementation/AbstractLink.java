package storyworlds.model.implementation;

import storyworlds.model.Direction;
import storyworlds.model.Link;
import storyworlds.model.Location;

/**
 * Created by nvaughan on 9/24/2016.
 */
public abstract class AbstractLink implements Link {
    protected final String description;
    protected final Location toLocation;
    protected final Location fromLocation;
    protected final Direction fromDirection;

    public AbstractLink(String description, Location toLocation, Location fromLocation, Direction fromDirection) {
        this.description = description;
        this.toLocation = toLocation;
        this.fromLocation = fromLocation;
        this.fromDirection = fromDirection;
    }

    public String getDescription() {
        return description;
    }

    public Location getToLocation() {
        return toLocation;
    }

    public Location getFromLocation() {
        return fromLocation;
    }

    public Direction getFromDirection() {
        return fromDirection;
    }

    public void bind() {
        fromLocation.addOutboundLink(this);
        toLocation.addInboundLink(this);
    }
}

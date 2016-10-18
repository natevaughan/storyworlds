package storyworlds.model.implementation;

import org.springframework.data.mongodb.core.mapping.DBRef;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.enumeration.Direction;

/**
 * Created by nvaughan on 9/24/2016.
 */
public abstract class AbstractLink implements Link {

    protected boolean active;
    protected final String description;
    @DBRef(lazy = true)
    protected Location toLocation;
    @DBRef(lazy = true)
    protected final Location fromLocation;
    protected final Direction fromDirection;

    public AbstractLink(String description, Location toLocation, Location fromLocation, Direction fromDirection) {
        this.active = true;
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

    public void setToLocation(Location toLocation) {
        this.toLocation = toLocation;
    }

    public Location getFromLocation() {
        return fromLocation;
    }

    public Direction getFromDirection() {
        return fromDirection;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void bind() {
        fromLocation.addOutboundLink(this);
        toLocation.addInboundLink(this);
    }
}

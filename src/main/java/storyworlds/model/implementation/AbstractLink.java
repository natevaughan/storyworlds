package storyworlds.model.implementation;

import org.springframework.data.annotation.Id;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.enumeration.Direction;

/**
 * Created by nvaughan on 9/24/2016.
 */
public abstract class AbstractLink implements Link {

    @Id
    String id;
    protected boolean active;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

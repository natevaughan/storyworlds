package storyworlds.model.implementation;

import org.springframework.data.mongodb.core.mapping.DBRef;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.enumeration.Direction;

/**
 * Created by nvaughan on 9/24/2016.
 */
public abstract class AbstractLink implements Link {

    protected final String description;
    @DBRef(lazy = true)
    protected final Location toLocation;

    public AbstractLink(String description, Location toLocation) {
        this.description = description;
        this.toLocation = toLocation;
    }

    public String getDescription() {
        return description;
    }

    public Location getToLocation() {
        return toLocation.getForwardingLocation();
    }

}

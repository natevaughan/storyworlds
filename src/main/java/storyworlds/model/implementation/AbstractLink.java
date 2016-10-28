package storyworlds.model.implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.DBRef;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.Player;

/**
 * Created by nvaughan on 9/24/2016.
 */
public abstract class AbstractLink implements Link {

    protected final String description;
    @DBRef(lazy = true)
    @JsonIgnore
    protected final Location toLocation;
    @DBRef(lazy = true)
    @JsonIgnore
    private final Player creator;

    public AbstractLink(String description, Location toLocation, Player creator) {
        this.description = description;
        this.toLocation = toLocation;
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public Location getToLocation() {
        return toLocation.getForwardingLocation();
    }

    public Player getCreator() {
        return creator;
    }
}

package storyworlds.model.builder;

import storyworlds.model.Location;
import storyworlds.model.Player;

/**
 * Created by nvaughan on 11/5/2016.
 */
public abstract class AbstractLinkBuilder extends AbstractBuilder implements LinkBuilder {

    protected String passText = null;
    protected String description = null;
    protected Location toLocation = null;
    protected LocationBuilder toLocationBuilder = null;
    protected Player creator = null;

    // only used by front-end for linking to an existing location
    // ultimately a Location or LocationBuilder must be present for .build()
    protected String toLocationId = null;

    public String getPassText() {
        return passText;
    }

    public String getDescription() {
        return description;
    }

    public Location getToLocation() {
        return toLocation;
    }

    public LocationBuilder getToLocationBuilder() {
        return toLocationBuilder;
    }

    public Player getCreator() {
        return creator;
    }

    public String getToLocationId() {
        return toLocationId;
    }
}

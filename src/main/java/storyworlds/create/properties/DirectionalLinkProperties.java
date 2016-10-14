package storyworlds.create.properties;

import storyworlds.model.Location;

/**
 * Created by nvaughan on 10/10/2016.
 */
public class DirectionalLinkProperties extends AbstractCreatableProperties {

    private String passText;
    private Location fromLocation;
    private Location toLocation;

    public String getPassText() {
        return passText;
    }

    public DirectionalLinkProperties setPassText(String passText) {
        this.passText = passText;
        return this;
    }

    public Location getFromLocation() {
        return fromLocation;
    }

    public DirectionalLinkProperties setFromLocation(Location fromLocation) {
        this.fromLocation = fromLocation;
        return this;
    }

    public Location getToLocation() {
        return toLocation;
    }

    public DirectionalLinkProperties setToLocation(Location toLocation) {
        this.toLocation = toLocation;
        return this;
    }

    public DirectionalLinkProperties setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isValid() {
        return description != null
                && passText != null
                && fromLocation != null
                && toLocation != null;
    }
}

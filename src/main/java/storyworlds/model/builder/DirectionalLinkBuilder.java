package storyworlds.model.builder;

import storyworlds.exception.UncreateableException;
import storyworlds.model.Location;
import storyworlds.model.Player;

/**
 * Created by nvaughan on 11/5/2016.
 */
public class DirectionalLinkBuilder extends AbstractLinkBuilder {

    public DirectionalLinkBuilder setPassText(String passText) {
        this.passText = passText;
        return this;
    }

    public DirectionalLinkBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public DirectionalLinkBuilder setToLocation(Location toLocation) {
        this.toLocation = toLocation;
        return this;
    }

    public DirectionalLinkBuilder setToLocationBuilder(LocationBuilder builder) {
        this.toLocationBuilder = builder;
        return this;
    }

    public DirectionalLinkBuilder setCreator(Player creator) {
        this.creator = creator;
        return this;
    }

    public DirectionalLinkBuilder setToLocationId(String toLocationId) {
        this.toLocationId = toLocationId;
        return this;
    }

    public storyworlds.model.implementation.DirectionalLink build() throws UncreateableException {
        validate();
        return new storyworlds.model.implementation.DirectionalLink(description, toLocation, passText, creator);
    }

    private void validate() throws UncreateableException {
        validate(this.toLocationBuilder != null && this.toLocation != null, "found both a location and a location builder");
        if (this.toLocationBuilder != null) {
            toLocationBuilder.setCreator(creator);
            this.toLocation = toLocationBuilder.build();
        }
        validate(this.toLocation == null, "null to location and location builder");
        validate(this.description == null || this.description.isEmpty(), "null or empty description");
        validate(this.passText == null || this.passText.isEmpty(), "null pass text");
        validate(this.creator == null, "null creator");
    }
}
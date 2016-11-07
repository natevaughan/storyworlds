package storyworlds.model.builder;

import storyworlds.exception.UncreateableException;
import storyworlds.model.Item;
import storyworlds.model.Location;
import storyworlds.model.Player;

/**
 * Created by nvaughan on 11/5/2016.
 */
public class BlockableLinkBuilder extends AbstractLinkBuilder {

    private Item requiredItem = null;
    private String failText = null;

    public BlockableLinkBuilder setPassText(String passText) {
        this.passText = passText;
        return this;
    }

    public BlockableLinkBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public BlockableLinkBuilder setToLocationBuilder(LocationBuilder toLocationBuilder) {
        this.toLocationBuilder = toLocationBuilder;
        return this;
    }

    public LocationBuilder getToLocationBuilder() {
        return toLocationBuilder;
    }

    @Override
    public LinkBuilder setToLocationId(String id) {
        this.toLocationId = id;
        return this;
    }

    public storyworlds.model.builder.BlockableLinkBuilder setToLocation(Location toLocation) {
        this.toLocation = toLocation;
        return this;
    }

    public BlockableLinkBuilder setRequiredItem(Item requiredItem) {
        this.requiredItem = requiredItem;
        return this;
    }

    public BlockableLinkBuilder setFailText(String failText) {
        this.failText = failText;
        return this;
    }

    public BlockableLinkBuilder setCreator(Player creator) {
        this.creator = creator;
        return this;
    }

    public storyworlds.model.implementation.BlockableLink build() throws UncreateableException {
        validate();
        return new storyworlds.model.implementation.BlockableLink(description, toLocation, passText, failText, requiredItem, creator);
    }

    private void validate() throws UncreateableException {
        validate(this.toLocationBuilder != null && this.toLocation != null, "found both a location and a location builder");
        if (this.toLocationBuilder != null) {
            this.toLocation = toLocationBuilder.build();
        }
        validate(this.toLocation == null, "null to location and location builder");
        validate(this.description == null || this.description.isEmpty(), "null or empty description");
        validate(this.passText == null || this.passText.isEmpty(), "null pass text");
        validate(this.failText == null || this.failText.isEmpty(), "null fail text");
        validate(this.requiredItem == null, "null required item");
        validate(this.creator == null, "null creator");
    }
}
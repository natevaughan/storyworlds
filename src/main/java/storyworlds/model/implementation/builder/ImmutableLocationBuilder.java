package storyworlds.model.implementation.builder;

import storyworlds.exception.UncreateableException;
import storyworlds.model.Item;
import storyworlds.model.Location;
import storyworlds.model.LocationBuilder;
import storyworlds.model.Player;
import storyworlds.model.Storyworld;
import storyworlds.model.implementation.ImmutableLocation;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by nvaughan on 11/5/2016.
 */
public class ImmutableLocationBuilder implements LocationBuilder {

    private String description;
    private Storyworld storyworld;
    private Collection<Item> items = new HashSet<>();
    private Location previousLocation = null;
    private Player creator = null;

    public ImmutableLocationBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public ImmutableLocationBuilder setStoryworld(Storyworld storyworld) {
        this.storyworld = storyworld;
        return this;
    }

    public ImmutableLocationBuilder add(Item item) {
        this.items.add(item);
        return this;
    }

    public ImmutableLocationBuilder addAll(Collection<Item> items) {
        this.items.addAll(items);
        return this;
    }

    public ImmutableLocationBuilder setPreviousLocation(Location previousLocation) {
        this.previousLocation = previousLocation;
        return this;
    }

    public ImmutableLocationBuilder setCreator(Player creator) {
        this.creator = creator;
        return this;
    }

    public ImmutableLocation build() throws UncreateableException {
        validate();
        return new ImmutableLocation(description, storyworld, items, previousLocation, creator);
    }

    private void validate() throws UncreateableException {
        if (description == null) {
            throw new UncreateableException("null description");
        }
        if (storyworld == null) {
            throw new UncreateableException("null storyworld");
        }
        if (creator == null) {
            throw new UncreateableException("null creator");
        }
    }

    public String getDescription() {
        return description;
    }

    public Storyworld getStoryworld() {
        return storyworld;
    }

    public Collection<Item> getItems() {
        return items;
    }

    public Location getPreviousLocation() {
        return previousLocation;
    }

    public Player getCreator() {
        return creator;
    }
}
package storyworlds.model.implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.LinkedHashSet;
import java.util.Set;
import org.springframework.data.mongodb.core.mapping.DBRef;
import storyworlds.model.Item;
import storyworlds.model.Location;
import storyworlds.model.Progress;
import storyworlds.model.Storyworld;

/**
 * Created by nvaughan on 11/6/2016.
 */
public class StoryworldProgress implements Progress {

    @DBRef
    private final Storyworld storyworld;

    @JsonIgnore
    @DBRef
    private final Set<Location> visitedLocations = new LinkedHashSet<>();

    @JsonIgnore
    @DBRef
    private final Set<Item> items = new LinkedHashSet<>();

    @DBRef
    private Location location;

    @DBRef
    private Item activeItem;

    public StoryworldProgress(Storyworld storyworld) {
        this.storyworld = storyworld;
       // setLocation(storyworld.getEntry());
    }

    public Storyworld getStoryworld() {
        return storyworld;
    }

    public Set<Location> getVisitedLocations() {
        return visitedLocations;
    }


    public Set<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public Location getLocation() {
        if (location == null) {
            location = storyworld.getEntry();
        }
        return location;
    }

    public void setLocation(Location location) {
        visitedLocations.add(location);
        this.location = location;
    }

    public Item getActiveItem() {
        return activeItem;
    }

    public void setActiveItem(Item activeItem) {
        if (items.contains(activeItem)) {
            this.activeItem = activeItem;
        }
    }
}

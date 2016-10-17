package storyworlds.model.implementation;

import org.springframework.data.mongodb.core.mapping.DBRef;
import storyworlds.model.Item;
import storyworlds.model.Location;
import storyworlds.model.Player;
import storyworlds.model.Storyworld;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class User implements Player {

    protected final String name;
    @DBRef
    protected final Set<Item> items;
    @DBRef
    protected Location location;
    @DBRef
    protected Item activeItem;
    @DBRef
    protected final Set<Location> locationHistory;
    @DBRef
    protected Storyworld currentStoryworld;
    
    public User(String name) {
        this.name = name;
        this.items = new LinkedHashSet<Item>();
        this.locationHistory = new LinkedHashSet<>();
    }

    public String getName() {
        return name;
    }

    public Collection<Item> listItems() {
        return items;
    }

    public void addItem(Item item) {
        items.add(item);
    }
    
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
        locationHistory.add(location);
    }

    public Item getActiveItem() {
        return activeItem;
    }

    public void activate(Item item) {
        if (!this.items.contains(item)) {
            return;
        }
        this.activeItem = item;
    }

    public Collection<Location> getLocationHistory() {
        return locationHistory;
    }

    public Storyworld getCurrentStoryworld() {
        return currentStoryworld;
    }

    public void setCurrentStoryworld(Storyworld storyworld) {
        currentStoryworld = storyworld;
    }
}


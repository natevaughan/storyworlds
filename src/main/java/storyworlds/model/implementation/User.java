package storyworlds.model.implementation;

import storyworlds.model.Item;
import storyworlds.model.Location;
import storyworlds.model.Player;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

public class User implements Player {

    protected final String name;
    protected final Set<Item> items;
    protected Location location;
    protected Item activeItem;
    protected final Set<Location> locationHistory;
    
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
}


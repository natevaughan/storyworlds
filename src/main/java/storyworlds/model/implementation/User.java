package storyworlds.model.implementation;

import storyworlds.model.Item;
import storyworlds.model.Location;
import storyworlds.model.Player;

import java.util.*;

public class User implements Player {

    protected final String name;
    protected final Set<Item> items;
    protected Location location;
    protected Item activeItem;
    
    public User(String name) {
        this.name = name;
        this.items = new HashSet<Item>();
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
}


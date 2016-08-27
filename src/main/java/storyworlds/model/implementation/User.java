package storyworlds.model.implementation; 

import java.util.Collection;
import java.util.HashSet;

import storyworlds.model.Item;
import storyworlds.model.Location;
import storyworlds.model.Player;

public class User implements Player {

    protected final String name;
    protected Collection<Item> items;
    protected Location location;
    
    public User(String name) {
        this.name = name;
        this.items = new HashSet<Item>();
    }

    public String getName() {
        return name;
    }

    public Collection<Item> getItems() {
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
}	


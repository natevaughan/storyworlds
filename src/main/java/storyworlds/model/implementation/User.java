package storyworlds.model.implementation;

import storyworlds.model.Item;
import storyworlds.model.Location;
import storyworlds.model.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class User implements Player {

    protected final String name;
    protected Map<String, Item> items;
    protected Location location;
    protected Item activeItem;
    
    public User(String name) {
        this.name = name;
        this.items = new HashMap<String, Item>();
    }

    public String getName() {
        return name;
    }

    public Collection<Item> listItems() {
        return items.values();
    }

    public Item getItem(String name) {
        return items.get(name.toUpperCase());
    }

    public void addItem(Item item) {
        items.put(item.getName().toUpperCase(), item);
    }
    
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}	


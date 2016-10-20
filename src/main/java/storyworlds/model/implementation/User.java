package storyworlds.model.implementation;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import storyworlds.model.Item;
import storyworlds.model.Location;
import storyworlds.model.Player;
import storyworlds.model.Storyworld;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
@Document(collection = "player")
public class User implements Player {

    private final String name;
    @DBRef
    private final Map<Integer, Item> items;
    @DBRef
    private Location location;
    @DBRef
    private Item activeItem;
    @DBRef
    private final Map<Integer, Location> locationHistory;
    @DBRef
    private Storyworld currentStoryworld;
    
    public User(String name) {
        this.name = name;
        this.items = new ConcurrentHashMap<>();
        this.locationHistory = new ConcurrentHashMap<>();
    }

    public String getName() {
        return name;
    }

    public Collection<Item> listItems() {
        return items.values();
    }

    public void addItem(Item item) {
        items.put(item.hashCode(), item);
    }
    
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
        locationHistory.put(location.hashCode(), location);
    }

    public Item getActiveItem() {
        return activeItem;
    }

    public void activate(Item item) {
        if (this.items.get(item.hashCode()) == null) {
            return;
        }
        this.activeItem = item;
    }

    public Collection<Location> getLocationHistory() {
        return locationHistory.values();
    }

    public synchronized Storyworld getCurrentStoryworld() {
        return currentStoryworld;
    }

    public synchronized void setCurrentStoryworld(Storyworld storyworld) {
        currentStoryworld = storyworld;
    }
}


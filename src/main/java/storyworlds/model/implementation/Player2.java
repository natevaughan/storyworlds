package storyworlds.model.implementation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import storyworlds.model.Item;
import storyworlds.model.Location;
import storyworlds.model.Player;
import storyworlds.model.Storyworld;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by nvaughan on 10/19/2016.
 */
@Document(collection = "player2")
public class Player2 implements Player {
    @Id
    private String id;
    private final String name;
    private final String email;
    private final String password;
    @DBRef
    private Storyworld currentStoryworld;
    @DBRef
    private Location location;
    @DBRef
    private Set<Location> previousLocations;
    @DBRef
    private Set<Item> items;
    @DBRef
    private Item activeItem;

    public Player2(String name, String email, String password) {
        this.items = new LinkedHashSet<>();
        this.previousLocations = new LinkedHashSet<>();
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Storyworld getCurrentStoryworld() {
        return currentStoryworld;
    }

    public void setCurrentStoryworld(Storyworld storyworld) {
        this.currentStoryworld = storyworld;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Item getActiveItem() {
        return activeItem;
    }

    public void activate(Item item) {
        if (items.contains(item)) {
            this.activeItem = item;
        }
    }

    public Collection<Location> getLocationHistory() {
        return previousLocations;
    }

    public void setName(String name) {
    }

    public Location getLocation() {
        return location.getForwardingLocation();
    }

    public void setLocation(Location location) {
        previousLocations.add(location);
        this.location = location;
    }
}

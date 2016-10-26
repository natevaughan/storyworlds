package storyworlds.model.implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import storyworlds.model.Item;
import storyworlds.model.Location;
import storyworlds.model.Player;
import storyworlds.model.Storyworld;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by nvaughan on 10/19/2016.
 */
@Document(collection = "player")
public class IdentifiedPlayer implements Player {
    @Id
    @JsonIgnore
    private String id;
    private final String name;
    private final String email;
    private final String password;
    @JsonIgnore
    @DBRef
    private Storyworld currentStoryworld;
    @JsonIgnore
    @DBRef
    private Location location;
    @JsonIgnore
    @DBRef
    private Set<Location> previousLocations;
    @JsonIgnore
    @DBRef
    private Set<Item> items;
    @JsonIgnore
    @DBRef
    private Item activeItem;

    public IdentifiedPlayer(String name, String email, String password) {
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

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
import java.util.Set;

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
    private Location location;
    @DBRef
    private Set<Location> previousLocations;
    private Set<Item> items;

    public Player2(String name, String email, String password) {
        this.items = new HashSet<Item>();
        this.previousLocations = new HashSet<Location>();
        this.name = name;
        this.email = email;
        this.password = password;
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

    @Override
    public Collection<Item> listItems() {
        return items;
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public Item getActiveItem() {
        return null;
    }

    @Override
    public void activate(Item item) {

    }

    @Override
    public Collection<Location> getLocationHistory() {
        return previousLocations;
    }

    @Override
    public Storyworld getCurrentStoryworld() {
        return null;
    }

    @Override
    public void setCurrentStoryworld(Storyworld storyworld) {

    }

    public void setName(String name) {
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        previousLocations.add(location);
        this.location = location;
    }
}

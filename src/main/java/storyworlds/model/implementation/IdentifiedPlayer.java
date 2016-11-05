package storyworlds.model.implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final String username;
    private final String email;
    @JsonIgnore
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

    public IdentifiedPlayer(String username, String email, String password) {
        this.items = new LinkedHashSet<>();
        this.previousLocations = new LinkedHashSet<>();
        this.username = username;
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

    public String getUsername() {
        return username;
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

    public Location getLocation() {
        return location.getForwardingLocation();
    }

    public void setLocation(Location location) {
        previousLocations.add(location);
        this.location = location;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public String getPassword() {
        return password;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "IdentifiedPlayer: " + username + ", id: " + id;
    }
}

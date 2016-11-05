
package storyworlds.model.implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Item;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.LocationBuilder;
import storyworlds.model.Player;
import storyworlds.model.Storyworld;
import storyworlds.model.Direction;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

@Document(collection = "location")
public class ImmutableLocation implements Location {

    @Id
    private String id;
    private boolean active;
    private final String description;
    @DBRef(lazy = true)
    @JsonIgnore
    private final Storyworld storyworld;
    private final Map<Direction, Link> outboundLinks;
    @DBRef
    private final Collection<Item> items;
    @DBRef
    @JsonIgnore
    private Location forwardingLocation;
    @DBRef(lazy = true)
    @JsonIgnore
    private final Location previousLocation;
    @DBRef(lazy = true)
    @JsonIgnore
    private final Player creator;

    public ImmutableLocation(String description, Storyworld storyworld, Collection<Item> items, Location previousLocation, Player creator) {
        this.outboundLinks = new ConcurrentHashMap<>();
        this.items = new ConcurrentSkipListSet<>(items);
        this.active = true;
        this.description = description;
        this.storyworld = storyworld;
        this.previousLocation = previousLocation;
        this.creator = creator;
    }

    public synchronized String getId() {
        return id;
    }

    public synchronized void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public Collection<Item> getItems() {
        return items;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public Map<Direction, Link> getOutboundLinks() {
        return outboundLinks;
    }

    public Link getOutboundLink(Direction direction) {
        if (direction == null) {
            return null;
        }
        return outboundLinks.get(direction);
    }

    public void addOutboundLink(Direction direction, Link link) {
        outboundLinks.put(direction, link);
    }

    // recursively climbs chain to most recent location.
    // use where possible to ensure players are operating at most recent location
    public synchronized Location getForwardingLocation() {
        if (forwardingLocation == null) {
            return this;
        }
        return forwardingLocation.getForwardingLocation();
    }

    public synchronized void setForwardingLocation(Location location) {
        this.forwardingLocation = location;
    }

    public synchronized boolean isActive() {
        return active;
    }

    public synchronized void setActive(boolean active) {
        this.active = active;
    }

    public Storyworld getStoryworld() {
        return storyworld;
    }

    public Location getPreviousLocation() {
        return previousLocation;
    }

    public Item getItem(String itemName) {
        for (Item item : items) {
            if (item.getName().equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public Player getCreator() {
        return creator;
    }
}

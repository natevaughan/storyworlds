
package storyworlds.model.implementation;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import storyworlds.model.Item;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.enumeration.Direction;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Document(collection = "location")
public class ImmutableLocation implements Location, Serializable {

    @Id
    private String id;
    private boolean active;
    private final String description;
    private final Map<Direction, Link> outboundLinks;
    private final Map<Integer, Link> inboundLinks;
    @DBRef
    private final Map<String, Item> items;
    @DBRef
    private Location previousLocation;

    public ImmutableLocation(String description, Location previousLocation, Map<String, Item> items) {
        this.active = true;
        this.previousLocation = previousLocation;
        this.description = description;
        this.outboundLinks = new ConcurrentHashMap<>();
        this.inboundLinks = new ConcurrentHashMap<>();
        this.items = new ConcurrentHashMap<>(items);
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

    public Map<String, Item> getItems() {
        return items;
    }

    public Item getItem(String name) {
        return items.get(name.toUpperCase());
    }

    public Item takeItem(String name) {
        return getItem(name);
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

    public Collection<Link> getInboundLinks() {
        return inboundLinks.values().stream().filter(Link::isActive).collect(Collectors.toSet());
    }

    public void addInboundLink(Link link) {
        inboundLinks.put(link.hashCode(), link);
    }

    public void addOutboundLink(Link link) {
        outboundLinks.put(link.getFromDirection(), link);
    }

    public Location getPreviousLocation() {
        return previousLocation;
    }

    public synchronized boolean isActive() {
        return active;
    }

    public synchronized void setActive(boolean active) {
        this.active = active;
    }
}

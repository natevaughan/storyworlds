
package storyworlds.model.implementation;

import org.springframework.data.annotation.PersistenceConstructor;
import storyworlds.model.Item;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.enumeration.Direction;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ImmutableLocation implements Location { 

    private boolean active;
    private final String description;
    private final Map<Direction, Link> outboundLinks;
    private final Map<Integer, Link> inboundLinks;
    private final Map<String, Item> items;
    private Location previousLocation;

    @PersistenceConstructor
    public ImmutableLocation(String description, Location previousLocation, Collection<Item> items) {
        this.previousLocation = previousLocation;
        Map<String, Item> itemsMap = new HashMap<String, Item>();
        for (Item item : items) {
            itemsMap.put(item.getName().toUpperCase(), item);
        }
        this.description = description;
        this.outboundLinks = new ConcurrentHashMap<>();
        this.inboundLinks = new ConcurrentHashMap<>();
        this.items = Collections.unmodifiableMap(new LinkedHashMap<>(itemsMap));
    }


    public String getDescription() {
        return description;
    }

    public Collection<Item> listItems() {
        return items.values();
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
        return outboundLinks.get(direction);
    }

    public Collection<Link> getInboundLinks() {
        return inboundLinks.values();
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}


package storyworlds.model.implementation;

import storyworlds.model.Direction;
import storyworlds.model.Item;
import storyworlds.model.Link;
import storyworlds.model.Location;

import java.util.*;

public class ImmutableLocation implements Location { 

    private final String text;
    private final Map<Direction, Link> outboundLinks;
    private final Collection<Link> inboundLinks;
    private final Map<String, Item> items;
    
    public ImmutableLocation(String text) {
        this(text, new HashSet<Item>());
    }

    public ImmutableLocation(String text, Collection<Item> items) {
        this(text, items, new HashSet<Link>(), new HashSet<Link>());
    }

    public ImmutableLocation(String text, Collection<Item> items, Collection<Link> outboundLinks, Collection<Link> inboundLinks) {
        Map<String, Item> itemsMap = new HashMap<String, Item>();
        for (Item item : items) {
            itemsMap.put(item.getName().toUpperCase(), item);
        }
        this.text = text;
        this.outboundLinks = new HashMap<>();
        outboundLinks.forEach(Link::bind);
        this.inboundLinks = new HashSet<Link>(inboundLinks);
        inboundLinks.forEach(Link::bind);
        this.items = Collections.unmodifiableMap(new LinkedHashMap<>(itemsMap));
    }


    public String getText() {
        return text;
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
        return inboundLinks;
    }

    public void addInboundLink(Link link) {
        inboundLinks.add(link);
    }

    public void addOutboundLink(Link link) {
        outboundLinks.put(link.getFromDirection(), link);
    }
}

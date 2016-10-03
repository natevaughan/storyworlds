
package storyworlds.model.implementation;

import storyworlds.model.Direction;
import storyworlds.model.Item;
import storyworlds.model.Link;
import storyworlds.model.Location;

import java.util.*;

public class ImmutableLocation implements Location { 

    private final String text;
    private final Map<Direction, Link> links;
    private final Map<String, Item> items;
    
    public ImmutableLocation(String text) {
        this(text, new HashSet<Item>());
    }

    public ImmutableLocation(String text, Collection<Item> items) {
        this(text, items, new HashMap<Direction, Link>());
    }

    public ImmutableLocation(String text, Collection<Item> items, Map<Direction, Link> links) {
        Map<String, Item> itemsMap = new HashMap<String, Item>();
        for (Item item : items) {
            itemsMap.put(item.getName().toUpperCase(), item);
        }
        this.text = text;
        this.links = links;
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

    public Map<Direction, Link> getLinks() {
        return links;
    }

    public Link getLink(Direction direction) {
        return links.get(direction);
    }

    public void addLink(Link link) {
        links.put(link.getFromDirection(), link);
    }
}

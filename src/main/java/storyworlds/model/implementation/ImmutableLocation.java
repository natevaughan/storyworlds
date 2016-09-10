
package storyworlds.model.implementation; 

import java.util.*;

import storyworlds.model.Direction;
import storyworlds.model.Item;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.Player;

public class ImmutableLocation implements Location { 

    private final String text;
    private final Map<Direction, Link> links;
    private final Map<String, Item> items;
    private final Collection<Player> currentPlayers;
    
    public ImmutableLocation(String text) {
        this(text, new HashSet<Item>());
    }


    public ImmutableLocation(String text, Collection<Item> items) {
        this(text, items, new HashMap<Direction, Link>());
    }

    public ImmutableLocation(String text, Collection<Item> items, Map<Direction, Link> links) {
        this.items = new HashMap<String, Item>();
        for (Item item : items) {
            this.items.put(item.getName().toUpperCase(), item);
        }
        this.text = text;
        this.links = links;
        this.currentPlayers = new HashSet<Player>();
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
        return items.remove(name.toUpperCase());
    }

    public Collection<Player> listPlayers() {
        return currentPlayers;
    }

    public Map<Direction, Link> getLinks() {
        return links;
    }

    public Link getLink(Direction direction) {
        return links.get(direction);
    }

    public void setLink(Direction direction, Link link) {
        links.put(direction, link);
    }
}

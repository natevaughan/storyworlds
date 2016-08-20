package game.model.implementation; 

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import game.model.*;

public class ImmutableLocation implements Location { 

    private final String text;
    private final Map<Direction, Link> links;
    private final Collection<Item> items;
    private final Collection<Player> currentPlayers;
    
    public ImmutableLocation(String text) {
        this(text, new HashMap<Direction, Link>(), new HashSet<Item>());
    }

    public ImmutableLocation(String text, Map<Direction, Link> links, Collection<Item> items) {
        this.text = text;
        this.links = links;
        this.items = items;
        this.currentPlayers = new HashSet<Player>();
    }

    public String getText() {
        return text;
    }

    public Collection<Item> listItems() {
        return items;
    }

    public Collection<Player> listPlayers() {
        return currentPlayers;
    }

    public Collection<Link> listLinks() {
        return links.values();
    }

    public Link getLink(Direction direction) {
        return links.get(direction);
    }

    public void setLink(Direction direction, Link link) {
        links.put(direction, link);
    }

    public String getMap() {
        return "map not yet supported";
    }
}

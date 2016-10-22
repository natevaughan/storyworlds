package storyworlds.model;

import storyworlds.model.enumeration.Direction;

import java.util.Collection;
import java.util.Map;

public interface Location {

    public String getId();

    public void setId(String id);

    public String getDescription();

    public Collection<Item> getItems();

    public void addItem(Item item);

    public Map<Direction, Link> getOutboundLinks();

    public Link getOutboundLink(Direction direction);

    public void addOutboundLink(Direction direction, Link link);

    public boolean isActive();

    public void setActive(boolean active);

    public Storyworld getStoryworld();

    public Location getForwardingLocation();

    public void setForwardingLocation(Location location);

    public Location getPreviousLocation();

    Item getItem(String itemName);
}


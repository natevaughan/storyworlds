package storyworlds.model;

import storyworlds.model.enumeration.Direction;

import java.util.Collection;
import java.util.Map;

public interface Location {

    public String getId();

    public void setId(String id);

    public String getDescription();

    public Map<String, Item> getItems();

    public Item getItem(String name);

    public Item takeItem(String name);

    public Map<Direction, Link> getOutboundLinks();

    public Link getOutboundLink(Direction direction);

    public void addOutboundLink(Link link);

    public Collection<Link> getInboundLinks();

    public void addInboundLink(Link link);

    public Location getPreviousLocation();

    public boolean isActive();

    public void setActive(boolean active);
}


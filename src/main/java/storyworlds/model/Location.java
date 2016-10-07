package storyworlds.model;

import java.util.Collection;
import java.util.Map;

public interface Location { 

    public String getText();

    public Collection<Item> listItems();

    public Item getItem(String name);

    public Item takeItem(String name);

    public Map<Direction, Link> getOutboundLinks();

    public Link getOutboundLink(Direction direction);

    public void addOutboundLink(Link link);

    public Collection<Link> getInboundLinks();

    public void addInboundLink(Link link);
}


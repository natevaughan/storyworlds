package storyworlds.model;

import java.util.Collection;
import java.util.Map;

public interface Location extends Persistable {

    String getDescription();

    Collection<Item> getItems();

    void addItem(Item item);

    Map<Direction, Link> getOutboundLinks();

    Link getOutboundLink(Direction direction);

    void addOutboundLink(Direction direction, Link link);

    boolean isActive();

    void setActive(boolean active);

    Storyworld getStoryworld();

    Location getForwardingLocation();

    void setForwardingLocation(Location location);

    Location getPreviousLocation();

    Item getItem(String itemName);

    Player getCreator();
}


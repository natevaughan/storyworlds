package storyworlds.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.Collection;
import java.util.Map;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include= JsonTypeInfo.As.PROPERTY, property="@class")
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


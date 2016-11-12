package storyworlds.model;

import java.util.Collection;

/**
 * Created by nvaughan on 11/6/2016.
 */
public interface Progress {
    Storyworld getStoryworld();
    Collection<Location> getVisitedLocations();
    Collection<Item> getItems();
    void addItem(Item item);
    Location getLocation();
    void setLocation(Location location);
    Item getActiveItem();
    void setActiveItem(Item activeItem);
}

package storyworlds.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

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

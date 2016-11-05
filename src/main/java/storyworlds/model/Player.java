package storyworlds.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Document(collection = "player")
public interface Player extends Persistable {

    String getUsername();

    Location getLocation();

    void setLocation(Location location);

    Collection<Item> listItems();

    void addItem(Item item);

    Item getActiveItem();

    void activate(Item item);

    Collection<Location> getLocationHistory();

    Storyworld getCurrentStoryworld();

    void setCurrentStoryworld(Storyworld storyworld);
}

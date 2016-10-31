package storyworlds.model;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;

@Document(collection = "player")
public interface Player extends Persistable {

    public String getName();

    public Location getLocation();

    public void setLocation(Location location);

    public Collection<Item> listItems();

    public void addItem(Item item);

    public Item getActiveItem();

    public void activate(Item item);

    public Collection<Location> getLocationHistory();

    public Storyworld getCurrentStoryworld();

    public void setCurrentStoryworld(Storyworld storyworld);
}

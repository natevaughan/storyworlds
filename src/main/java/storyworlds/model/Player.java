package storyworlds.model;

import java.util.Collection;

public interface Player {

    public String getName();

    public Location getLocation();

    public void setLocation(Location location);

    public Collection<Item> listItems();

    public void addItem(Item item);

    public Item getActiveItem();

    public void activate(Item item);

    public Collection<Location> getLocationHistory();
}

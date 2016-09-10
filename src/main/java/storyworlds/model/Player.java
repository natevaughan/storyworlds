package storyworlds.model;

import java.util.Collection;

public interface Player {

    public String getName();

    public Collection<Item> listItems();

    public Item getActiveItem();

    public void addItem(Item item);

    public void setActiveItem(Item item);

    public Item getItem(String name);

    public Location getLocation();

    public void setLocation(Location location);
}

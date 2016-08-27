package storyworlds.model;

import java.util.Collection;

public interface Player {

    public String getName();

    public Direction getDirection();

    public Collection<Item> getItems();

    public Location getLocation();

    public void setLocation(Location location);
}

package storyworlds.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import storyworlds.model.enumeration.Direction;

public interface Link {

    public String getDescription();

    public boolean isPassable(Player player);
    
    public String getPassText(Player player);

    public Location getFromLocation();

    public Direction getFromDirection();

    public Location getToLocation();

    public void bind();

    public boolean isActive();

    public void setActive(boolean active);

    public Link clone(Location newToLocation);

    public Link clone(Location newFromLocation, Direction newFromDirection);

    public Link clone(String newDescription, String newPassText);
}	

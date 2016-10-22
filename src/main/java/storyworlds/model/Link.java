package storyworlds.model;

import storyworlds.model.enumeration.Direction;

public interface Link {

    public String getDescription();

    public boolean isPassable(Player player);
    
    public String getPassText(Player player);

    public Location getToLocation();
}	

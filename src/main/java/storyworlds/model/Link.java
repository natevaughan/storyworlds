package storyworlds.model;

public interface Link {

    public boolean isPassable(Player player);
    
    public String getText(Player player);

    public Location getLinkedLocation(Location startLocation);
}	

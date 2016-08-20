package game.model;

public interface Link {

    public boolean isPassable(Player player);
    
    public String getText();

    public Location getLinkedLocation(Location startLocation);
}	

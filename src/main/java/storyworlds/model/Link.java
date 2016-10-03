package storyworlds.model;

public interface Link {

    public String getDescription();

    public boolean isPassable(Player player);
    
    public String getPassText(Player player);

    public Location getFromLocation();

    public Direction getFromDirection();

    public Location getToLocation();

}	

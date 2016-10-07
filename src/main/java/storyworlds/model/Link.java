package storyworlds.model;

public interface Link {

    public String getDescription();

    public boolean isPassable(Player player);
    
    public String getPassText(Player player);

    public Location getFromLocation();

    public Direction getFromDirection();

    public Location getToLocation();

    public void bind();

    public Link clone(Location newToLocation);

    public Link clone(Location newFromLocation, Direction newFromDirection);

    public Link clone(String newDescription, String newPassText);
}	

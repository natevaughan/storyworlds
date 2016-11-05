package storyworlds.model;

public interface Link {

    String getDescription();

    boolean isPassable(Player player);
    
    String getPassText(Player player);

    Location getToLocation();

    Player getCreator();
}	

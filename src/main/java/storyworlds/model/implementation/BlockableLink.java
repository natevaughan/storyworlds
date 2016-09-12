package storyworlds.model.implementation;

import java.util.HashMap;
import java.util.Map;

import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.Player;

/**
 * @author nvaughan since 8/26/16
 */
public class BlockableLink extends BidirectionalLink implements Link {
    
    private Map<Location, Boolean> isPassableMap = new HashMap<Location, Boolean>();

    public BlockableLink(String text, Location location1, Location location2) {
        super(text, location1, location2);
        isPassableMap.put(location1, true);
        isPassableMap.put(location2, true);
    }

    @Override
    public Location getLinkedLocation(Location location) {
        return super.getLinkedLocation(location);
    }

    @Override
    public boolean isPassable(Player player) {
        return isPassableMap.get(player.getLocation());
    }
    
    public void setPassable(Location fromLocation, boolean passable) {
        isPassableMap.put(fromLocation, passable);
    }
}

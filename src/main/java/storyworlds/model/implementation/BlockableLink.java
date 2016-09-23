package storyworlds.model.implementation;

import java.util.HashMap;
import java.util.Map;

import storyworlds.model.Item;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.Player;

/**
 * @author nvaughan since 8/26/16
 */
public class BlockableLink extends DirectionalLink implements Link {
    
    private final Item requiredItem;
    private final String                  failText;

    public BlockableLink(String passText, String failText, Item requiredItem, Location endLocation) {
        super(passText, endLocation);
        this.requiredItem = requiredItem;
        this.failText = failText;
    }

    @Override
    public Location getLinkedLocation(Location location) {
        return super.getLinkedLocation(location);
    }

    @Override
    public boolean isPassable(Player player) {
        for (Item item : player.listItems()) {
            if (item.equals(requiredItem) && item.isActive()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String getText(Player player) {
        if (isPassable(player)) {
            return passText;
        } else {
            return failText;
        }
    }
}

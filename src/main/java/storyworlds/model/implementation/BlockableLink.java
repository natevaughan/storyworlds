package storyworlds.model.implementation;

import storyworlds.model.Direction;
import storyworlds.model.Item;
import storyworlds.model.Location;
import storyworlds.model.Player;

/**
 * @author nvaughan since 8/26/16
 */
public class BlockableLink  extends AbstractLink {
    
    private final Item                    requiredItem;
    private final String                  passText;
    private final String                  failText;

    public BlockableLink(String description, Location toLocation, Location fromLocation, Direction fromDirection, String passText, String failText, Item requiredItem) {
        super(description, toLocation, fromLocation, fromDirection);
        this.requiredItem = requiredItem;
        this.passText = passText;
        this.failText = failText;
    }

    public boolean isPassable(Player player) {
        if (player.getActiveItem() != null)  {
            if (player.getActiveItem().equals(requiredItem)) {
                return true;
            }
        }
        return false;
    }

    public String getPassText(Player player) {
        if (isPassable(player)) {
            return passText;
        } else {
            return failText;
        }
    }
}

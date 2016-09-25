package storyworlds.model.implementation;

import storyworlds.model.Item;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.Player;

/**
 * @author nvaughan since 8/26/16
 */
public class BlockableLink  extends AbstractLink implements Link {
    
    private final Item requiredItem;
    private final String                  passText;
    private final String                  failText;

    public BlockableLink(String description, String passText, String failText, Item requiredItem, Location endLocation) {
        super(description, endLocation);
        requiredItem.setActive(true);
        this.requiredItem = requiredItem;
        this.passText = passText;
        this.failText = failText;
    }

    public boolean isPassable(Player player) {
        for (Item item : player.listItems()) {
            if (item.equals(requiredItem)) {
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

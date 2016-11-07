package storyworlds.model.implementation;

import storyworlds.model.Item;
import storyworlds.model.Location;
import storyworlds.model.Player;

/**
 * @author nvaughan since 8/26/16
 */
public class BlockableLink extends AbstractLink {

    private final Item                    requiredItem;
    private final String                  passText;
    private final String                  failText;

    public BlockableLink(String description, Location toLocation, String passText, String failText, Item requiredItem, Player creator) {
        super(description, toLocation, creator);
        this.requiredItem = requiredItem;
        this.passText = passText;
        this.failText = failText;
    }

    public boolean isPassable(Player player) {
        if (player.getCurrentProgress().getActiveItem() != null)  {
            if (player.getCurrentProgress().getActiveItem().equals(requiredItem)) {
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

package storyworlds.model.implementation;

import storyworlds.model.Item;
import storyworlds.model.Location;
import storyworlds.model.Player;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nvaughan on 9/12/2016.
 */
public class UnlockableLink extends BidirectionalLink {

    private final Map<Location, Item> requiredItemsMap;

    public UnlockableLink(String text, Location location1,  Location location2) {
        super(text, location1, location2);
        requiredItemsMap = new HashMap<Location, Item>();
    }

    @Override
    public boolean isPassable(Player player) {
        if (requiredItemsMap.get(player.getLocation()) != null) {
            return player.listItems().contains(requiredItemsMap.get(player.getLocation()));
        }
        return true;
    }

    public void setRequiredItem(Location location, Item item) {
        item.setActive(true);
        requiredItemsMap.put(location, item);
    }
}

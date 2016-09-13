package storyworlds.factory;

import storyworlds.constants.GameTextConstants;
import storyworlds.model.Direction;
import storyworlds.model.Item;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.implementation.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author nvaughan since 8/26/16
 */
public class MapFactory implements GameTextConstants {
    public static Location getStartMap() {
        Location location = new ImmutableLocation(DEFAULT_LOCATION_TEXT,  createItems());
        location.setLink(Direction.UP, new BidirectionalLink(DEFAULT_LOCATION_TEXT_UP, location, getBlankLocation(Direction.DOWN, location)));
        UnlockableLink lockedLink = new UnlockableLink(DEFAULT_LOCATION_TEXT_DOWN, location, getBlankLocation(Direction.UP, location));

        lockedLink.setRequiredItem(location, new UsableItem(ITEM_NAME, ITEM_PROPERTIES));
        location.setLink(Direction.DOWN, lockedLink);

        location.setLink(Direction.NORTH, new BidirectionalLink(DEFAULT_LOCATION_TEXT_NORTH, location, getBlankLocation(Direction.SOUTH, location)));
        location.setLink(Direction.SOUTH, new BidirectionalLink(DEFAULT_LOCATION_TEXT_SOUTH, location, getBlankLocation(Direction.NORTH, location)));
        location.setLink(Direction.EAST, new ImpassableLink(DEFAULT_LOCATION_TEXT_EAST));
        location.setLink(Direction.WEST, new ImpassableLink(DEFAULT_LOCATION_TEXT_WEST));
        return location;
    }

    private static Collection<Item> createItems() {
        Collection<Item> items = new ArrayList<Item>();
        Item item = new UsableItem(ITEM_NAME,ITEM_PROPERTIES);
        items.add(item);
        return items;
    }


    public static Location getBlankLocation() {
        Location location = new ImmutableLocation(BLANK_LOCATION_TEXT);
        location.setLink(Direction.DOWN, new ImpassableLink());
        location.setLink(Direction.UP, new ImpassableLink());
        location.setLink(Direction.NORTH, new ImpassableLink());
        location.setLink(Direction.SOUTH, new ImpassableLink());
        location.setLink(Direction.EAST, new ImpassableLink());
        location.setLink(Direction.WEST, new ImpassableLink());
        return location;
    }

    public static Location getBlankLocation(Direction fromDirection, Location fromLocation) {
        Location location = getBlankLocation();
        location.setLink(fromDirection, new BlockableLink("Return to your previous position", fromLocation, location));
        return location;
    }
}


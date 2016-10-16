package storyworlds.initial.map;

import storyworlds.constants.GameTextConstants;
import storyworlds.model.Item;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.enumeration.Direction;
import storyworlds.model.implementation.BlockableLink;
import storyworlds.model.implementation.DirectionalLink;
import storyworlds.model.implementation.ImmutableLocation;
import storyworlds.model.implementation.UsableItem;

import java.util.*;

public class MapFactory implements GameTextConstants {
    public static Location getStartMap() {
        Location location = new ImmutableLocation(DEFAULT_LOCATION_TEXT, null, createItems());
        Link up = new DirectionalLink(DEFAULT_LOCATION_TEXT_UP, getBlankLocation(Direction.DOWN, location), location, Direction.UP, DEFAULT_LOCATION_PASS_TEXT_UP);
        up.bind();
        Item key = new UsableItem(KEY_NAME, KEY_USE_MESSAGE, KEY_DESCRIPTION);
        BlockableLink lockedLink = new BlockableLink(DEFAULT_LOCATION_TEXT_DOWN, getBlankLocation(Direction.UP, location), location, Direction.DOWN, DEFAULT_LOCATION_PASS_TEXT_DOWN, DEFAULT_LOCATION_FAIL_TEXT_DOWN, key);
        lockedLink.bind();

        Link north = new DirectionalLink(DEFAULT_LOCATION_TEXT_NORTH, getBlankLocation(Direction.SOUTH, location),  location, Direction.NORTH, DEFAULT_LOCATION_PASS_TEXT_NORTH);
        north.bind();
        Link south = new DirectionalLink(DEFAULT_LOCATION_TEXT_SOUTH, getBlankLocation(Direction.NORTH, location), location, Direction.SOUTH, DEFAULT_LOCATION_PASS_TEXT_SOUTH);
        south.bind();
        location.getInboundLinks().forEach(Link::bind);
        return location;
    }

    private static Map<String, Item> createItems() {
        Map<String, Item> items = new HashMap<>();
        Item item = new UsableItem(KEY_NAME, KEY_USE_MESSAGE, KEY_DESCRIPTION);
        items.put(item.getName(), item);
        return items;
    }


    public static Location getBlankLocation() {
        Location location = new ImmutableLocation(BLANK_LOCATION_TEXT, null, new HashMap<>());
        return location;
    }

    public static Location getBlankLocation(Direction fromDirection, Location fromLocation) {
        Location location = getBlankLocation();
        Link link = new DirectionalLink(RETURN_DESCRIPTION, fromLocation, location, fromDirection, RETURN_PASS_TEXT);
        link.bind();
        return location;
    }
}


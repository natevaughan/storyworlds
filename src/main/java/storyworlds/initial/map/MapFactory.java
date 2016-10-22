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
import storyworlds.model.implementation.WikiStoryworld;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class MapFactory implements GameTextConstants {
    public static Location getStartMap() {
        Location location = new ImmutableLocation(DEFAULT_LOCATION_TEXT, new WikiStoryworld(), createItems(), null);
        Link up = new DirectionalLink(DEFAULT_LOCATION_TEXT_UP, getBlankLocation(Direction.DOWN, location), DEFAULT_LOCATION_PASS_TEXT_UP);
        Item key = new UsableItem(KEY_NAME, KEY_USE_MESSAGE, KEY_DESCRIPTION);
        BlockableLink lockedLink = new BlockableLink(DEFAULT_LOCATION_TEXT_DOWN, getBlankLocation(Direction.UP, location), DEFAULT_LOCATION_PASS_TEXT_DOWN, DEFAULT_LOCATION_FAIL_TEXT_DOWN, key);

        Link north = new DirectionalLink(DEFAULT_LOCATION_TEXT_NORTH, getBlankLocation(Direction.SOUTH, location), DEFAULT_LOCATION_PASS_TEXT_NORTH);
        Link south = new DirectionalLink(DEFAULT_LOCATION_TEXT_SOUTH, getBlankLocation(Direction.NORTH, location), DEFAULT_LOCATION_PASS_TEXT_SOUTH);
        return location;
    }

    private static Collection<Item> createItems() {
        Set<Item> items = new HashSet<>();
        Item item = new UsableItem(KEY_NAME, KEY_USE_MESSAGE, KEY_DESCRIPTION);
        items.add(item);
        return items;
    }


    public static Location getBlankLocation() {
        Location location = new ImmutableLocation(BLANK_LOCATION_TEXT, new WikiStoryworld(), new HashSet<>(), null);
        return location;
    }

    public static Location getBlankLocation(Direction fromDirection, Location fromLocation) {
        Location location = getBlankLocation();
        Link link = new DirectionalLink(RETURN_DESCRIPTION, location, RETURN_PASS_TEXT);
        return location;
    }
}


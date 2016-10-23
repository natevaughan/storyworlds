package storyworlds.initial.map;

import storyworlds.constants.GameTextConstants;
import storyworlds.model.Item;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.Player;
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
    public static Location getStartMap(Player player) {
        Location location = new ImmutableLocation(DEFAULT_LOCATION_TEXT, new WikiStoryworld(), createItems(player), null, player);
        Link up = new DirectionalLink(DEFAULT_LOCATION_TEXT_UP, getBlankLocation(Direction.DOWN, location, player), DEFAULT_LOCATION_PASS_TEXT_UP, player);
        Item key = new UsableItem(KEY_NAME, KEY_USE_MESSAGE, KEY_DESCRIPTION, player);
        BlockableLink lockedLink = new BlockableLink(DEFAULT_LOCATION_TEXT_DOWN, getBlankLocation(Direction.UP, location, player), DEFAULT_LOCATION_PASS_TEXT_DOWN, DEFAULT_LOCATION_FAIL_TEXT_DOWN, key, player);

        Link north = new DirectionalLink(DEFAULT_LOCATION_TEXT_NORTH, getBlankLocation(Direction.SOUTH, location, player), DEFAULT_LOCATION_PASS_TEXT_NORTH, player);
        Link south = new DirectionalLink(DEFAULT_LOCATION_TEXT_SOUTH, getBlankLocation(Direction.NORTH, location, player), DEFAULT_LOCATION_PASS_TEXT_SOUTH, player);
        return location;
    }

    private static Collection<Item> createItems(Player player) {
        Set<Item> items = new HashSet<>();
        Item item = new UsableItem(KEY_NAME, KEY_USE_MESSAGE, KEY_DESCRIPTION, player);
        items.add(item);
        return items;
    }


    public static Location getBlankLocation(Player player) {
        Location location = new ImmutableLocation(BLANK_LOCATION_TEXT, new WikiStoryworld(), new HashSet<>(), null, player);
        return location;
    }

    public static Location getBlankLocation(Direction fromDirection, Location fromLocation, Player player) {
        Location location = getBlankLocation(player);
        Link link = new DirectionalLink(RETURN_DESCRIPTION, location, RETURN_PASS_TEXT, player);
        return location;
    }
}


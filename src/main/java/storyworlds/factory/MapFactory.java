package storyworlds.factory;

import storyworlds.constants.GameTextConstants;
import storyworlds.model.Direction;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.implementation.ImmutableLocation;
import storyworlds.model.implementation.SimpleLink;

    
public class MapFactory implements GameTextConstants {
    public static Location getStartMap() {
        Location location = new ImmutableLocation(DEFAULT_LOCATION_TEXT);
        location.setLink(Direction.UP, new SimpleLink(location, getBlankLocation(Direction.DOWN, location), DEFAULT_LOCATION_TEXT_UP, true));
        location.setLink(Direction.DOWN, new SimpleLink(location, getBlankLocation(Direction.UP, location), DEFAULT_LOCATION_TEXT_DOWN, true));
        location.setLink(Direction.NORTH, new SimpleLink(location, getBlankLocation(Direction.SOUTH, location), DEFAULT_LOCATION_TEXT_NORTH, true));
        location.setLink(Direction.SOUTH, new SimpleLink(location, getBlankLocation(Direction.NORTH, location), DEFAULT_LOCATION_TEXT_SOUTH, true));
        location.setLink(Direction.EAST, getBlankLink(location, DEFAULT_LOCATION_TEXT_EAST)); 
        location.setLink(Direction.WEST, getBlankLink(location, DEFAULT_LOCATION_TEXT_WEST));
        return location;
    }
   
    public static Location getBlankLocation() {
        Location location = new ImmutableLocation(BLANK_LOCATION_TEXT);
        location.setLink(Direction.DOWN, getBlankLink(location));
        location.setLink(Direction.UP, getBlankLink(location));
        location.setLink(Direction.NORTH, getBlankLink(location));
        location.setLink(Direction.SOUTH, getBlankLink(location));
        location.setLink(Direction.EAST, getBlankLink(location));
        location.setLink(Direction.WEST, getBlankLink(location));
        return location;
    }

    public static Location getBlankLocation(Direction fromDirection, Location fromLocation) {
        Location location = getBlankLocation();
        location.setLink(fromDirection, new SimpleLink(location, fromLocation, "Return to your previous position", true));
        return location;
    }

    public static Link getBlankLink(Location location) {
        return getBlankLink(location, BLANK_LINK_TEXT);
    }

    public static Link getBlankLink(Location location, String text) {
        return new SimpleLink(location, location, text, false);
    }
}


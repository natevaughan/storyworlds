package storyworlds.constants; 

public interface GameTextConstants {

    public final String WELCOME_MESSAGE = "Welcome to Adventureland!\nPlease enter your name to begin:";

    public final String DEFAULT_LOCATION_TEXT = "\n" +
    "    /^\\\n" +
    "____|_|____\n" +
    "You find yourself in a small hut in a tiny village";

    public final String DEFAULT_LOCATION_TEXT_UP = "You look up to see a trap door";
    public final String DEFAULT_LOCATION_TEXT_DOWN = "You look down to see a dirt floor with a trap door and a padlock.";
    public final String DEFAULT_LOCATION_TEXT_NORTH = "To the north is the front door";
    public final String DEFAULT_LOCATION_TEXT_SOUTH = "To the south is a door leading to another room in the hut";
    public final String DEFAULT_LOCATION_TEXT_EAST = "On the east wall hangs a portrait of a royal figure";
    public final String DEFAULT_LOCATION_TEXT_WEST = "The west wall is blank";

    public final String BLANK_LOCATION_TEXT = "There is nothing here. \nEDIT this location to continue the story and expand the game universe";
    public final String BLANK_LINK_TEXT = "Cannot move beyond this location. \nEDIT this location to link it to new locations and expand the game universe.";

    public final String KEY_NAME = "key";
    public final String KEY_USE_MESSAGE = "You retrieve your brass key.";
    public final String KEY_DESCRIPTION = "A large brass key that looks to be useful for opening ancient locks";
}

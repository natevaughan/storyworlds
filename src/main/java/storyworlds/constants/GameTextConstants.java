package storyworlds.constants; 

public interface GameTextConstants {

    public static final String WELCOME_MESSAGE = "Welcome to Adventureland!\nPlease enter your name to begin:";

    public static final String DEFAULT_LOCATION_TEXT = "\n" +
    "    /^\\\n" +
    "____|_|____\n" +
    "You find yourself in a small hut in a tiny village";

    public static final String DEFAULT_LOCATION_TEXT_UP = "You look up to see a trap door";
    public static final String DEFAULT_LOCATION_TEXT_DOWN = "You look down to see a dirt floor. A staircase nearby descends into the dirt";
    public static final String DEFAULT_LOCATION_TEXT_NORTH = "To the north is the front door";
    public static final String DEFAULT_LOCATION_TEXT_SOUTH = "To the south is a door leading to another room in the hut";
    public static final String DEFAULT_LOCATION_TEXT_EAST = "On the east wall hangs a portrait of a royal figure";
    public static final String DEFAULT_LOCATION_TEXT_WEST = "The west wall is blank";

    public static final String BLANK_LOCATION_TEXT = "There is nothing here. \nEDIT this location to continue the story and expand the game universe";
    public static final String BLANK_LINK_TEXT = "Cannot move beyond this location. \nEDIT this location to link it to new locations and expand the game universe."; 
}

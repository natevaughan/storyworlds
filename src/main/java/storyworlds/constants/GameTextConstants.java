package storyworlds.constants; 

public interface GameTextConstants {

    public final String WELCOME_MESSAGE = "Please enter your name to begin:";

    public final String RETURN_DESCRIPTION = "the direction you came";
    public final String RETURN_PASS_TEXT = "You head back the way you came.";

    public final String DEFAULT_LOCATION_TEXT = "You find yourself in a small hut. It is nearly empty except for a small table and a single chair.";
    public final String DEFAULT_LOCATION_TEXT_UP = "a trap door";
    public final String DEFAULT_LOCATION_PASS_TEXT_UP = "You climb through the trap door.";
    public final String DEFAULT_LOCATION_TEXT_DOWN = "a dirt floor with a trap door and a padlock.";
    public final String DEFAULT_LOCATION_FAIL_TEXT_DOWN = "You try the trap door but the padlock is solid.";
    public final String DEFAULT_LOCATION_PASS_TEXT_DOWN = "Your key turns in the padlock and opens it with ease.";
    public final String DEFAULT_LOCATION_TEXT_NORTH = "the front door";
    public final String DEFAULT_LOCATION_PASS_TEXT_NORTH = "You open the front door and step out into glorious sunshine.";
    public final String DEFAULT_LOCATION_TEXT_SOUTH = "a low curtain leading to another room in the hut";
    public final String DEFAULT_LOCATION_PASS_TEXT_SOUTH = "You wander through a dingy door.";

    public final String BLANK_LOCATION_TEXT = "There is nothing here. \nEDIT this location to continue the story and expand the game universe";

    public final String KEY_NAME = "key";
    public final String KEY_USE_MESSAGE = "You retrieve your brass key.";
    public final String KEY_DESCRIPTION = "a large brass key that looks to be useful for opening ancient locks";
}

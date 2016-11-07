package storyworlds.constants; 

public interface GameTextConstants {

    String WELCOME_MESSAGE = "Please create a username to begin:";

    String RETURN_DESCRIPTION = "the direction you came";
    String RETURN_PASS_TEXT = "You head back the way you came.";

    String DEFAULT_LOCATION_TEXT = "You find yourself in a small hut. It is nearly empty except for a small table and a single chair.";
    String DEFAULT_LOCATION_TEXT_UP = "a trap door";
    String DEFAULT_LOCATION_PASS_TEXT_UP = "You climb through the trap door.";
    String DEFAULT_LOCATION_TEXT_DOWN = "a dirt floor with a trap door and a padlock.";
    String DEFAULT_LOCATION_FAIL_TEXT_DOWN = "You try the trap door but the padlock is solid.";
    String DEFAULT_LOCATION_PASS_TEXT_DOWN = "Your key turns in the padlock and opens it with ease.";
    String DEFAULT_LOCATION_TEXT_NORTH = "the front door";
    String DEFAULT_LOCATION_PASS_TEXT_NORTH = "You open the front door and step out into glorious sunshine.";
    String DEFAULT_LOCATION_TEXT_SOUTH = "a low curtain leading to another room in the hut";
    String DEFAULT_LOCATION_PASS_TEXT_SOUTH = "You wander through a dingy door.";

    String BLANK_LOCATION_TEXT = "There is nothing here. \nEDIT this location to continue the story and expand the game universe";

    String KEY_NAME = "key";
    String KEY_USE_MESSAGE = "You retrieve your brass key.";
    String KEY_DESCRIPTION = "a large brass key that looks to be useful for opening ancient locks";
}

package storyworlds.action.parser;

import storyworlds.exception.InvalidDirectionException;
import storyworlds.model.Direction;

public class DirectionParser {

    public static Direction parse(String input) throws InvalidDirectionException {
        if (input != null) {
            input = input.trim();
            for (Direction direction : Direction.values()) {
                if (direction.toString()
                             .equalsIgnoreCase(input)) {
                    return direction;
                }
            }
        }
        throw new InvalidDirectionException("Invalid direction: " + input);
    }
}

package storyworlds.action.parser;

import storyworlds.model.Direction;

public class DirectionParser {

    public Direction parse(String input) {
        input = input.trim();
        for (Direction direction : Direction.values()) {
            if (direction.toString().equalsIgnoreCase(input)) {
                return direction;
            }
        }
        return Direction.ERROR;
    }
}

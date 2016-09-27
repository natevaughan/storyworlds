package storyworlds.action.parser;

import storyworlds.model.Direction;

import java.util.Map;

public class DirectionParser {
    
    private Map<Direction, Direction> oppositeMap;

    public DirectionParser() {
    }

    public Direction parse(String input) {
        for (Direction direction : Direction.values()) {
            if (direction.toString().equalsIgnoreCase(input)) {
                return direction;
            }
        }
        return Direction.ERROR;
    }
}

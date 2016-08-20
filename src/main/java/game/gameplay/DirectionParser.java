package game.gameplay;

import game.model.Direction;
import java.util.Map;
import java.util.HashMap;

public class DirectionParser {
    
    private Map<Direction, Direction> oppositeMap;

    public DirectionParser() {
        oppositeMap = new HashMap<Direction, Direction>();
        oppositeMap.put(Direction.NORTH, Direction.SOUTH);
        oppositeMap.put(Direction.SOUTH, Direction.NORTH);
        oppositeMap.put(Direction.WEST, Direction.EAST);
        oppositeMap.put(Direction.EAST, Direction.WEST);
        oppositeMap.put(Direction.UP, Direction.DOWN);
        oppositeMap.put(Direction.DOWN, Direction.UP);
    }

    public Direction parse(String input) {
        for (Direction direction : Direction.values()) {
            if (direction.toString().equalsIgnoreCase(input)) {
                return direction;
            }
        }
        return Direction.ERROR;
    }

    public Direction opposite(Direction direction) {
        return oppositeMap.get(direction);
    }
}

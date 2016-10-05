package storyworlds.action;

import storyworlds.model.Direction;

public abstract class DirectionedAction extends AbstractAction {

    protected Direction direction;

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    
    public Direction getDirection() {
        return direction;
    }
}

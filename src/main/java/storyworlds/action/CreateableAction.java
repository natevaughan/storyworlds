package storyworlds.action;

import storyworlds.create.Createable;
import storyworlds.model.Direction;


public interface CreateableAction extends Actionable {
    Createable getCreateable();

    void setCreateable(Createable creatable);

    void setDirection(Direction direction);

    Direction getDirection();

    boolean isCreateable();
}

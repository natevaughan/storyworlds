package storyworlds.action;

import storyworlds.create.CreateableType;
import storyworlds.model.Direction;


public interface CreateableAction extends Actionable {

    CreateableType getCreateableType();

    void setCreateable(CreateableType creatable);

    void setDirection(Direction direction);

    Direction getDirection();

    boolean isCreateable();
}

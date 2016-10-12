package storyworlds.action;

import storyworlds.create.Createable;
import storyworlds.model.Direction;


public interface CreateableAction extends Actionable {
    public Createable getCreateable();

    public void setCreateable(Createable creatable);

    public void setDirection(Direction direction);

    public Direction getDirection();

    public boolean isCreateable();
}

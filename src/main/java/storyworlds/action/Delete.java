package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;
import storyworlds.create.Createable;
import storyworlds.model.Direction;

/**
 * Created by nvaughan on 10/13/2016.
 */
public class Delete extends AbstractCreateableAction  {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public boolean isCreateable() {
        return creatable != null
                && !Createable.ERROR.equals(creatable)
                && direction != null
                && !Direction.ERROR.equals(direction);
    }
}

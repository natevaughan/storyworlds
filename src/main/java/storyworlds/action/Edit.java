package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;
import storyworlds.create.Createable;
import storyworlds.model.Direction;

public class Edit extends AbstractCreateableAction {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

    public boolean isCreateable() {
        if (direction == null) {
            return Createable.LOCATION.equals(creatable);
        } else {
            return creatable != null
                    && !Createable.LOCATION.equals(creatable)
                    && !Createable.ERROR.equals(creatable)
                    && !Direction.ERROR.equals(direction);
        }
    }
}

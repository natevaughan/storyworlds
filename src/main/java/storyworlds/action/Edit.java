package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;
import storyworlds.create.Createables;
import storyworlds.model.Direction;

public class Edit extends AbstractCreateableAction {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

    public boolean isCreateable() {
        if (direction == null) {
            return Createables.LOCATION.equals(creatable);
        } else {
            return creatable != null && !Createables.LOCATION.equals(creatable) && !Createables.ERROR.equals(creatable) && !Direction.ERROR.equals(direction);
        }
    }
}

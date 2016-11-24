package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;
import storyworlds.create.Createable;

public class Edit extends AbstractCreateableAction {

    public void accept(ActionVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    public boolean isCreateable() {
        if (getDirection() == null) {
            return Createable.LOCATION.equals(creatable);
        } else {
            return creatable != null
                    && !Createable.LOCATION.equals(creatable)
                    && getDirection() != null;
        }
    }
}

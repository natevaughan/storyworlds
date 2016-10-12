package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;
import storyworlds.create.Createable;
import storyworlds.model.Direction;

public class Create extends AbstractCreateableAction {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

    public boolean isCreateable() {
        return creatable != null
                && !Createable.ERROR.equals(creatable)
                && !Direction.ERROR.equals(direction)
                && message.getPlayer().getLocation().getOutboundLink(direction) == null
                && !(Createable.LOCATION.equals(creatable) && direction == null);
    }


}

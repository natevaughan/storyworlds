package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;
import storyworlds.create.Createables;
import storyworlds.model.Direction;

public class Create extends AbstractCreateableAction {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

    public boolean isCreateable() {
        return creatable != null && direction != null && !Createables.ERROR.equals(creatable) && !Direction.ERROR.equals(direction) && message.getPlayer().getLocation().getOutboundLink(direction) == null;
    }
}

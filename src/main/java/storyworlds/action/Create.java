package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;
import storyworlds.create.Createable;

public class Create extends AbstractCreateableAction {

    public void accept(ActionVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    public boolean isCreateable() {
        return creatable != null
                && (Createable.ITEM.equals(creatable) || getDirection() != null)
                && message.getPlayer().getCurrentProgress().getLocation().getOutboundLink(getDirection()) == null
                && !(Createable.LOCATION.equals(creatable) && getDirection() == null);
    }
}

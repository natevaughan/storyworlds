package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;
import storyworlds.create.CreateableType;

public class Create extends AbstractCreateableAction {

    public void accept(ActionVisitor visitor) throws Exception {
        visitor.visit(this);
    }

    public boolean isCreateable() {
        return creatableType != null
                && (CreateableType.ITEM.equals(creatableType) || getDirection() != null)
                && message.getPlayer().getCurrentProgress().getLocation().getOutboundLink(getDirection()) == null
                && !(CreateableType.LOCATION.equals(creatableType) && getDirection() == null);
    }
}

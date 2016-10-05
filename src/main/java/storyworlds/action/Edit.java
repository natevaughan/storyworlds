package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;

public class Edit extends AbstractCreateableAction {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

}

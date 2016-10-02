package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;

public class Status extends AbstractAction {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

}

package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;

public class Create extends AbstractAction {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

}

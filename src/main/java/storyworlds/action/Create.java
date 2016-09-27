package storyworlds.action;

import storyworlds.visitor.ActionVisitor;

public class Create extends AbstractAction {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

}

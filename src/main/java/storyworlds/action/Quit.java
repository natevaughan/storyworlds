package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;

public class Quit extends AbstractAction {

    public void accept(ActionVisitor visitor) throws Exception {
        visitor.visit(this);
    }

}

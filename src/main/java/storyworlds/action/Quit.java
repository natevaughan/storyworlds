package storyworlds.action;

import storyworlds.action.AbstractAction;
import storyworlds.action.visitor.ActionVisitor;

public class Quit extends AbstractAction {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

}

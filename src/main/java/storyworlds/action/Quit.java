package storyworlds.action;

import storyworlds.action.AbstractAction;

public class Quit extends AbstractAction {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

}

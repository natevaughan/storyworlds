package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;

public class Map extends AbstractAction {

    public void accept(ActionVisitor visitor) throws Exception {
        visitor.visit(this);
    }

}

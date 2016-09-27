package storyworlds.action;

import storyworlds.visitor.ActionVisitor;

public class Map extends AbstractAction {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

}

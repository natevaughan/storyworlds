package storyworlds.action;

import storyworlds.visitor.ActionVisitor;

public class Move extends DirectionedAction {
    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

}

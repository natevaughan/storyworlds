package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;

public class Edit extends DirectionedAction {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

}

package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;
import storyworlds.exception.BadLinkException;

public class Move extends DirectionedAction {
    public void accept(ActionVisitor visitor) throws BadLinkException {
        visitor.visit(this);
    }

}

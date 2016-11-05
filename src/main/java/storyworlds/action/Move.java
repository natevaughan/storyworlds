package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;
import storyworlds.exception.InvalidDirectionException;
import storyworlds.exception.InvalidLinkException;

public class Move extends DirectionedAction {
    public void accept(ActionVisitor visitor) throws InvalidLinkException, InvalidDirectionException {
        visitor.visit(this);
    }

}

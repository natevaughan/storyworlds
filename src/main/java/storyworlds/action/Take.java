package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;
import storyworlds.exception.UnrecognizedInputException;

public class Take extends ItemAction {

    public void accept(ActionVisitor visitor) throws UnrecognizedInputException {
        visitor.visit(this);
    }

}

package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;
import storyworlds.exception.NotFoundException;

public class Take extends ItemAction {

    public void accept(ActionVisitor visitor) throws NotFoundException {
        visitor.visit(this);
    }

}

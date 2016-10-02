package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;

public class Take extends ItemAction {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

}

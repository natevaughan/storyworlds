package storyworlds.action;

import storyworlds.visitor.ActionVisitor;

public class Edit extends AbstractCreatableAction {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

}

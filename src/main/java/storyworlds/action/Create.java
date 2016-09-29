package storyworlds.action;

import storyworlds.create.Createables;
import storyworlds.visitor.ActionVisitor;

public class Create extends AbstractCreatableAction {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }
}

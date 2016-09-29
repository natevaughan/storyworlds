package storyworlds.action;

import storyworlds.action.AbstractAction;
import storyworlds.create.Creatable;
import storyworlds.visitor.ActionVisitor;

public class Error extends AbstractAction implements Creatable{

    public Error(String message) {
        this.message = message;
    }

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

}

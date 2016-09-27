package storyworlds.model;

import storyworlds.action.AbstractAction;
import storyworlds.visitor.ActionVisitor;
import storyworlds.create.Creatable;

public class Error extends AbstractAction implements Creatable{

    public Error(String message) {
        this.message = message;
    }

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

}

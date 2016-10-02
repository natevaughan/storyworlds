package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;
import storyworlds.create.Creatable;

public class Error extends AbstractAction implements Creatable{

    public Error(String message) {
        this.message.addLine(message);
    }

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

}

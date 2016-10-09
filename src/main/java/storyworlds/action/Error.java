package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;

public class Error extends AbstractAction {

    public Error(String message) {
        this.message.addLine(message);
    }

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

}

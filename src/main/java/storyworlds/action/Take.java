package storyworlds.action;

public class Take extends AbstractAction {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

}

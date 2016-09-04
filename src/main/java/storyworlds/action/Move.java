package storyworlds.action;

public class Move extends AbstractAction {

    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }
    
    
}

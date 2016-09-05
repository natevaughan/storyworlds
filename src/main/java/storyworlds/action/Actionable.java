package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;

public interface Actionable {
    
    public void accept(ActionVisitor visitor);
    
}

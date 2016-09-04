package storyworlds.action;

public interface Actionable {
    
    public void accept(ActionVisitor visitor);
    
}

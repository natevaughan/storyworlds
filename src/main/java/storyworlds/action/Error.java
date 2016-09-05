package storyworlds.action;

public class Error extends AbstractAction {

    private String message;
    
    public Error(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void accept(ActionVisitor visitor) {
        visitor.visit(this);
    }

}

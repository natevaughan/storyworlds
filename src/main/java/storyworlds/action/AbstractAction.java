package storyworlds.action;

public abstract class AbstractAction implements Actionable {

    protected String message;
    protected boolean successful = true;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

}

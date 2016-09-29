package storyworlds.action;

import storyworlds.service.message.Message;

public abstract class AbstractAction implements Actionable {

    protected Message message = new Message();
    protected boolean successful = false;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

}

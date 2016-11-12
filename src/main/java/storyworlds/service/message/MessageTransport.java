package storyworlds.service.message;

import storyworlds.action.Actionable;

/**
 * Created by nvaughan on 9/28/2016.
 */
public class MessageTransport {

    private Message message;
    private Actionable actionable;
    private String primary;
    private String secondary;

    public MessageTransport(Message message) {
        this.message = message;
        primary = null;
        secondary = null;
    }

    public void accept(MessageProcessor processor) throws Exception {
        processor.process(this);
    }

    public Message getMessage() {
        return message;
    }

    public Actionable getActionable() {
        return actionable;
    }

    public void setActionable(Actionable actionable) {
        this.actionable = actionable;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getSecondary() {
        return secondary;
    }

    public void setSecondary(String secondary) {
        this.secondary = secondary;
    }

    public Actionable getResponse() {
        return actionable;
    };
}

package storyworlds.action;

import storyworlds.service.message.Message;
import storyworlds.visitor.ActionVisitor;

public interface Actionable {

    public void accept(ActionVisitor visitor);

    public void setMessage(Message message);

    public Message getMessage();

    public void setSuccessful(boolean successful);

    public boolean isSuccessful();
}

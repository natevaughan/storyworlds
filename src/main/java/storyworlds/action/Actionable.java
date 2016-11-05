package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;
import storyworlds.exception.BadLinkException;
import storyworlds.model.Persistable;
import storyworlds.model.Storyworld;
import storyworlds.service.message.Message;

public interface Actionable extends Persistable {

    Storyworld getStoryworld();

    void setStoryworld(Storyworld storyworld);

    void accept(ActionVisitor visitor) throws BadLinkException;

    void setMessage(Message message);

    Message getMessage();

    void setSuccessful(boolean successful);

    boolean isSuccessful();
}

package storyworlds.action;

import storyworlds.action.visitor.ActionVisitor;
import storyworlds.exception.InvalidDirectionException;
import storyworlds.exception.InvalidLinkException;
import storyworlds.model.Persistable;
import storyworlds.model.Storyworld;
import storyworlds.service.message.Message;

public interface Actionable extends Persistable {

    Storyworld getStoryworld();

    void setStoryworld(Storyworld storyworld);

    void accept(ActionVisitor visitor) throws InvalidLinkException, InvalidDirectionException;

    void setMessage(Message message);

    Message getMessage();

    void setSuccessful(boolean successful);

    boolean isSuccessful();
}

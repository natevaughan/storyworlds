package storyworlds.service.message;

import storyworlds.action.visitor.ActionDoVisitor;
import storyworlds.exception.BadLinkException;

public class MessageExecutor implements MessageProcessor {

    ActionDoVisitor actionDoVisitor = new ActionDoVisitor();

    public void process(MessageTransport transport) throws BadLinkException {
        transport.getActionable().accept(actionDoVisitor);
    }
}

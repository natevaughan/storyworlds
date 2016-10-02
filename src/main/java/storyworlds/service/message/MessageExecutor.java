package storyworlds.service.message;

import storyworlds.action.visitor.ActionDoVisitor;

public class MessageExecutor implements MessageProcessor {

    ActionDoVisitor actionDoVisitor = new ActionDoVisitor();

    public void process(MessageTransport transport) {
        transport.getActionable().accept(actionDoVisitor);
    }
}

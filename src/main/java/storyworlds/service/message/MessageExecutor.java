package storyworlds.service.message;

import storyworlds.visitor.ActionDoVisitor;

public class MessageExecutor implements MessageProcessor {

    ActionDoVisitor actionDoVisitor = new ActionDoVisitor();

    public void process(MessageTransport transport) {
        transport.getActionable().accept(actionDoVisitor);
    }
}

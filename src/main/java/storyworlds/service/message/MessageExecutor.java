package storyworlds.service.message;

import storyworlds.visitor.ActionDoVisitor;

/**
 * Created by nvaughan on 9/28/2016.
 */
public class MessageExecutor implements MessageProcessor {

    ActionDoVisitor actionDoVisitor = new ActionDoVisitor();

    public void process(MessageTransport transport) {
        transport.getActionable().accept(actionDoVisitor);
    }
}

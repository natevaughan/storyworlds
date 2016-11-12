package storyworlds.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storyworlds.service.ActionDoVisitorService;

@Service
public class MessageExecutor implements MessageProcessor {

    @Autowired
    ActionDoVisitorService actionDoVisitor;

    public void process(MessageTransport transport) throws Exception {
        transport.getActionable().accept(actionDoVisitor);
    }
}

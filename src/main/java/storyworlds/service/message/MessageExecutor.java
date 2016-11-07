package storyworlds.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storyworlds.exception.InvalidDirectionException;
import storyworlds.exception.UnrecognizedInputException;
import storyworlds.service.ActionDoVisitorService;
import storyworlds.exception.InvalidLinkException;

@Service
public class MessageExecutor implements MessageProcessor {

    @Autowired
    ActionDoVisitorService actionDoVisitor;

    public void process(MessageTransport transport) throws InvalidLinkException, InvalidDirectionException, UnrecognizedInputException {
        transport.getActionable().accept(actionDoVisitor);
    }
}

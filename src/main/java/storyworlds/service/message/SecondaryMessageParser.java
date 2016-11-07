package storyworlds.service.message;

import storyworlds.action.visitor.SecondaryParserVisitor;
import storyworlds.exception.InvalidDirectionException;
import storyworlds.exception.InvalidLinkException;
import storyworlds.exception.UnrecognizedInputException;

/**
 * Created by nvaughan on 9/28/2016.
 */
public class SecondaryMessageParser implements MessageProcessor {

    private SecondaryParserVisitor secondaryParserVisitor = new SecondaryParserVisitor();

    public void process(MessageTransport transport) throws InvalidLinkException, InvalidDirectionException, UnrecognizedInputException {
        secondaryParserVisitor.setSecondary(transport.getSecondary());
        transport.getActionable().accept(secondaryParserVisitor);
    }
}

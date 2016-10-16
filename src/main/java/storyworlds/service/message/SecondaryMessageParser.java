package storyworlds.service.message;

import storyworlds.action.visitor.SecondaryParserVisitor;
import storyworlds.exception.BadLinkException;

/**
 * Created by nvaughan on 9/28/2016.
 */
public class SecondaryMessageParser implements MessageProcessor {

    private SecondaryParserVisitor secondaryParserVisitor = new SecondaryParserVisitor();

    public void process(MessageTransport transport) throws BadLinkException {
        secondaryParserVisitor.setSecondary(transport.getSecondary());
        transport.getActionable().accept(secondaryParserVisitor);
    }
}

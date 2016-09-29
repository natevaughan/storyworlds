package storyworlds.service.message;

import storyworlds.visitor.SecondaryParserVisitor;

/**
 * Created by nvaughan on 9/28/2016.
 */
public class SecondaryMessageParser implements MessageProcessor {

    private SecondaryParserVisitor secondaryParserVisitor = new SecondaryParserVisitor();

    public void process(MessageTransport transport) {
        secondaryParserVisitor.setSecondary(transport.getSecondary());
        transport.getActionable().accept(secondaryParserVisitor);
    }
}

package storyworlds.service.message;

import storyworlds.action.Actionable;
import storyworlds.exception.BadLinkException;

public class MessageService {

    public Actionable process(Message message) throws BadLinkException {
        System.out.println("Message " + message.getText() + " received from " + message.getPlayer().getName() + " at " + message.getTime().toString());

        MessageTransport transport = new MessageTransport(message);

        transport.accept(new PrimaryMessageParser());
        transport.accept(new SecondaryMessageParser());
        transport.accept(new MessageExecutor());

        return transport.getResponse();
    }
}

package storyworlds.service.message;

import storyworlds.action.Actionable;
import storyworlds.exception.InvalidActionException;

public class MessageService {

    public Actionable process(Message message) {
        System.out.println("Message " + message.getText() + " received from " + message.getPlayer().getName() + " at " + message.getTime().toString());

        MessageTransport transport = new MessageTransport(message);

        try {
            transport.accept(new PrimaryMessageParser());
            transport.accept(new SecondaryMessageParser());
            transport.accept(new MessageExecutor());
        } catch (InvalidActionException e) {
            // this is not the way to handle this
            storyworlds.action.Error error = new storyworlds.action.Error(e.getMessage());
        }

        return transport.getResponse();
    }
}

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
            storyworlds.action.Error error = new storyworlds.action.Error();
            error.setMessage(new Message(message.getPlayer(), e.getMessage()));
        }

        return transport.getResponse();
    }
}

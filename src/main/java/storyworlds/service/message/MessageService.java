package storyworlds.service.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storyworlds.action.Actionable;
import storyworlds.exception.InvalidDirectionException;
import storyworlds.exception.InvalidLinkException;
import storyworlds.exception.UnrecognizedInputException;

@Service
public class MessageService {

    @Autowired
    MessageExecutor executor;

    public Actionable process(Message message) throws InvalidLinkException, UnrecognizedInputException, InvalidDirectionException {
        System.out.println("Message " + message.getText() + " received from " + message.getPlayer().getUsername() + " at " + message.getTime().toString());

        MessageTransport transport = new MessageTransport(message);

        transport.accept(new PrimaryMessageParser());
        transport.accept(new SecondaryMessageParser());
        transport.accept(executor);

        return transport.getResponse();
    }
}

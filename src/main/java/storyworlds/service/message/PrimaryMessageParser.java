package storyworlds.service.message;

import storyworlds.action.ActionFactory;
import storyworlds.action.Actionable;
import storyworlds.exception.UnrecognizedInputException;

/**
 * Created by nvaughan on 9/28/2016.
 */
public class PrimaryMessageParser implements MessageProcessor {

    public void process(MessageTransport transport) throws UnrecognizedInputException {
        String input = transport.getMessage().getCommand().trim();

        if (!input.isEmpty()) {
            if (input.contains(" ")) {
                transport.setPrimary(input.substring(0, input.indexOf(" ")));
                transport.setSecondary(input.substring(input.indexOf(" ") + 1));
            } else {
                transport.setPrimary(input);
            }
        } else {
            throw new UnrecognizedInputException("Empty input");
        }

        Actionable actionable = ActionFactory.get(transport.getPrimary());

        actionable.setMessage(transport.getMessage());
        transport.setActionable(actionable);
    }
}

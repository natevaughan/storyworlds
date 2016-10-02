package storyworlds.service.message;

import storyworlds.action.ActionFactory;
import storyworlds.action.Actionable;

/**
 * Created by nvaughan on 9/28/2016.
 */
public class PrimaryMessageParser implements MessageProcessor {

    public void process(MessageTransport transport) {
        String input = transport.getMessage().getCommand().trim();

        if (!input.isEmpty()) {
            if (input.contains(" ")) {
                transport.setPrimary(input.substring(0, input.indexOf(" ")));
                transport.setSecondary(input.substring(input.indexOf(" ") + 1));
            } else {
                transport.setPrimary(input);
            }
        }

        Actionable actionable = ActionFactory.get(transport.getPrimary());

        // XXX: todo
        String possibleErrorMessage = actionable.getMessage().getText();

        actionable.setMessage(transport.getMessage());
        actionable.getMessage().addLine(possibleErrorMessage);
        transport.setActionable(actionable);
    }
}

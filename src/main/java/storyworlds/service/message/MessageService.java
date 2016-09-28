package storyworlds.service.message;

import storyworlds.action.ActionFactory;
import storyworlds.action.Actionable;
import storyworlds.visitor.ActionDoVisitor;
import storyworlds.visitor.SecondaryParserVisitor;

public class MessageService {

    public Actionable process(Message message) {
        System.out.println("Message " + message.getText() + " received from " + message.getPlayer().getName() + " at " + message.getTime().toString());

        ActionDoVisitor actionDoVisitor = new ActionDoVisitor(message.getPlayer());
        SecondaryParserVisitor secondaryParser = new SecondaryParserVisitor(message.getPlayer());

        String input = message.getText().trim();

        String primary = null;
        String secondary = null;

        if (!input.isEmpty()) {
            if (input.contains(" ")) {
                primary = input.substring(0, input.indexOf(" "));
                secondary = input.substring(input.indexOf(" ") + 1);
            } else {
                primary = input;
            }
        }

        Actionable actionable = ActionFactory.get(primary);

        secondaryParser.setSecondary(secondary);

        actionable.accept(secondaryParser);

        actionable.accept(actionDoVisitor);

        return actionable;
    }
}

package storyworlds.action.parser;

import storyworlds.action.Action;
import storyworlds.action.ActionFactory;
import storyworlds.action.Actionable;

public class ActionParser {

    public Actionable parse(String input) {

        Actionable actionable = ActionFactory.get(Action.ERROR);
        
        for (Action action : Action.values()) {
            if (action.toString().equalsIgnoreCase(input)) {
                actionable = ActionFactory.get(action);
            }
        }
        
        return actionable;
    }
}

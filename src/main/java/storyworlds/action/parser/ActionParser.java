package storyworlds.action.parser;

import storyworlds.action.Action;
import storyworlds.action.ActionFactory;
import storyworlds.action.Actionable;

public class ActionParser {

    public Action parse(String input) {

        Action actionable = Action.ERROR;
        
        for (Action action : Action.values()) {
            if (action.toString().equalsIgnoreCase(input)) {
                actionable = action;
            }
        }
        
        return actionable;
    }
}

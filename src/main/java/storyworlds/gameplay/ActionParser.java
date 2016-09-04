package storyworlds.gameplay;

import storyworlds.action.Action;
import storyworlds.action.Actionable;

public class ActionParser {

    public Actionable parse(String input) {

        Actionable actionable = Action.ERROR.getActionable();
        

        for (Action action : Action.values()) {
            if (action.toString().equalsIgnoreCase(input)) {
                actionable = action.getActionable();
            }
        }
        
        return actionable;
    }
}

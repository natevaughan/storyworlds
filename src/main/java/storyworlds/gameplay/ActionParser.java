package storyworlds.gameplay;

import storyworlds.model.Action;

public class ActionParser {
    
    public Action parse(String input) {
        for (Action action : Action.values()) {
            if (action.toString().equalsIgnoreCase(input)) {
                return action;
            }
        }
        return Action.ERROR;
    }
}

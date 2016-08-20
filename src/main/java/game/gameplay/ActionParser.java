package game.gameplay;

import game.model.Action;

public class ActionParser {
    
    public static Action parse(String input) {
        for (Action action : Action.values()) {
            if (action.toString().equalsIgnoreCase(input)) {
                return action;
            }
        }
        return Action.ERROR;
    }
}

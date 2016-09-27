package storyworlds.action;

import storyworlds.model.Error;

public class ActionFactory {
    public static Actionable get(String input) {
        Action action = parse(input);
        switch (action) {
            case CREATE:
                return new Create();
            case EDIT:
                return new Edit();
            case HELP:
                return new Help();
            case MAP:
                return new Map();
            case MOVE:
                return new Move();
            case QUIT:
                return new Quit();
            case STATUS:
                return new Status();
            case TAKE:
                return new Take();
            case USE:
                return new Use();
            default:
                return new Error("Unhandled action: " + input);
        }
    }

    public static Action parse(String input) {

        Action actionable = Action.ERROR;

        for (Action action : Action.values()) {
            if (action.toString().equalsIgnoreCase(input)) {
                actionable = action;
            }
        }

        return actionable;
    }
}



package storyworlds.action;

import storyworlds.exception.UnrecognizedInputException;

public class ActionFactory {
    public static Actionable get(String input) throws UnrecognizedInputException {
        Action action = parse(input);
        switch (action) {
            case CREATE:
                return new Create();
            case EDIT:
                return new Edit();
            case DELETE:
                return new Delete();
            case HELP:
                return new Help();
            case MAP:
                return new Map();
            case MOVE:
                return new Move();
            case STATUS:
                return new Status();
            case QUIT:
                return new Quit();
            case TAKE:
                return new Take();
            case USE:
                return new Use();
            default:
                throw new UnrecognizedInputException("Unrecognized input: " + input);
        }
    }

    public static Action parse(String input) throws UnrecognizedInputException {

        input = input.trim();
        Action actionable = null;

        for (Action action : Action.values()) {
            if (action.toString().equalsIgnoreCase(input)) {
                actionable = action;
            }
        }

        if (actionable == null) {
            throw new UnrecognizedInputException("Unrecognized input: " + input);
        }

        return actionable;
    }
}



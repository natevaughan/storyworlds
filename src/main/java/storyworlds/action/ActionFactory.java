package storyworlds.action;

public class ActionFactory {
    public static Actionable get(String input) {
        Action action = parse(input);
        switch (action) {
            case STATUS:
                return new Status();
            case MAP:
                return new Map();
            case MOVE:
                return new Move();
            case QUIT:
                return new Quit();
            case TAKE:
                return new Take();
            case USE:
                return new Use();
            default:
                return new storyworlds.action.Error("Unhandled action: " + input);
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



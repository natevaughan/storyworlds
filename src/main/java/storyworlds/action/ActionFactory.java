package storyworlds.action;

public class ActionFactory {
    public static Actionable get(Action action) {
        switch (action) {
            case STATUS:
                return new Status();
            case LOOK:
                return new Look();
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
                return new storyworlds.action.Error("Unhandled action: " + action);
        }
    }
}

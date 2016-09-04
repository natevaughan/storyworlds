package storyworlds.action;

public enum Action {
    QUIT(new Quit()),
    ERROR(new storyworlds.action.Error()), 
    MAP(new Map()), 
    LOOK(new Look()), 
    MOVE(new Move()), 
    TAKE(new Take()), 
    USE(new Use()), 
    ITEMS(new Items());

    private final Actionable actionable;

    private Action(Actionable actionable) {
        this.actionable = actionable;
    }
    
    public Actionable getActionable() {
        return actionable;
    }
}

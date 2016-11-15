package storyworlds.action;

public enum Action {
    CREATE("<location | link> <direction>"),
    EDIT("<location | link> <direction>"),
    HELP(""),
    MAP(""),
    MOVE("<direction>"),
    QUIT(""),
    STATUS(""),
    TAKE("<item name>"),
    USE("<item name>");

    private String explanation;

    Action(String explanation) {
        this.explanation = explanation;
    }

    public String getExplanation() {
        return this.name() + " " + this.explanation;
    }
}

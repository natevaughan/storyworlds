package storyworlds.model.enumeration;

public enum Direction {
    NORTH("To the "),
    SOUTH("To the "),
    EAST("To the "),
    WEST("To the "),
    UP(""),
    DOWN(""),
    ERROR("");

    Direction(String prefix) {
        this.prefix = prefix;
    }
    private String prefix;
    public String formatted() {
        return this.prefix + this.toString();
    }

}

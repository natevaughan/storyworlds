package storyworlds.model;

public enum Direction {
    NORTH("To the "),
    SOUTH("To the "),
    EAST("To the "),
    WEST("To the "),
    UP(""),
    DOWN("");

    Direction(String prefix) {
        this.prefix = prefix;
    }
    private String prefix;
    public String formatted() {
        return this.prefix + this.toString();
    }

}

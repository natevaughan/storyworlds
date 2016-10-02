package storyworlds.model.enumeration;

public enum Links {
    BLOCKABLE("A link that requires a user to have and activate an item to pass through it."),
    DIRECTIONAL("A one-directional link that allows all users to pass from a start location to a finish location");

    private final String description;

    Links(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

package storyworlds.model.implementation;

/**
 * Created by nvaughan on 9/28/2016.
 */
public enum Links {
    BLOCKABLE("A link that requires a user to have and activate an item to pass through it."),
    DIRECTIONAL("A one-directional link that allows all users to pass from a start location to a finish location"),
    BIDIRECTIONAL("A two-directional link that allows all users to pass and provides the same text in both directions");

    private final String description;

    Links(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

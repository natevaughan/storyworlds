package storyworlds.model.enumeration;

public enum Locations {
    IMMUTABLE("A location that cannot be edited once created.");

    private final String description;

    Locations(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

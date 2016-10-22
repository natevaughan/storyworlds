package storyworlds.model.implementation;

import storyworlds.exception.UncreateableException;
import storyworlds.model.LinkBuilder;
import storyworlds.model.Location;
import storyworlds.model.Player;
import storyworlds.model.enumeration.Direction;

public class DirectionalLink extends AbstractLink {

    protected final String                  passText;

    public DirectionalLink(String description, Location toLocation, String passText) {
        super(description, toLocation);
        this.passText = passText;
    }

    public String getPassText(Player player) {
        return passText;
    }

    public boolean isPassable(Player player) {
        return true;
    }

    public DirectionalLink clone(Location newToLocation) {
        return new DirectionalLink(description, newToLocation, passText);
    }
    public DirectionalLink clone(String newDescription, String newPassText) {
        return new DirectionalLink(newDescription, toLocation, newPassText);
    }

    public static class Builder implements LinkBuilder {

        private String passText;
        private String description;
        private Location toLocation;

        public static Builder newInstance() {
            return new Builder();
        }

        public Builder setPassText(String passText) {
            this.passText = passText;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setToLocation(Location toLocation) {
            this.toLocation = toLocation;
            return this;
        }

        public DirectionalLink build() throws UncreateableException {
            validate();
            return new DirectionalLink(description, toLocation, passText);
        }

        private void validate() throws UncreateableException {
            validate(this.description == null || this.description.isEmpty(), "null or empty description");
            validate(this.toLocation == null, "null to location");
            validate(this.passText == null || this.passText.isEmpty(), "null pass text");
        }

        private void validate(Boolean condition, String message) throws UncreateableException {
            if (condition) {
                throw new UncreateableException(message);
            }
        }
    }
}

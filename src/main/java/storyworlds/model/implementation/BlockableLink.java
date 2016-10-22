package storyworlds.model.implementation;

import storyworlds.exception.UncreateableException;
import storyworlds.model.Item;
import storyworlds.model.LinkBuilder;
import storyworlds.model.Location;
import storyworlds.model.Player;
import storyworlds.model.enumeration.Direction;

/**
 * @author nvaughan since 8/26/16
 */
public class BlockableLink extends AbstractLink {

    private final Item                    requiredItem;
    private final String                  passText;
    private final String                  failText;

    public BlockableLink(String description, Location toLocation, String passText, String failText, Item requiredItem) {
        super(description, toLocation);
        this.requiredItem = requiredItem;
        this.passText = passText;
        this.failText = failText;
    }

    public boolean isPassable(Player player) {
        if (player.getActiveItem() != null)  {
            if (player.getActiveItem().equals(requiredItem)) {
                return true;
            }
        }
        return false;
    }


    public String getPassText(Player player) {
        if (isPassable(player)) {
            return passText;
        } else {
            return failText;
        }
    }

    public BlockableLink clone(Location newToLocation) {
        return new BlockableLink(description, newToLocation, passText, failText, requiredItem);
    }

    public BlockableLink clone(String newDescription, String newPassText) {
        return new BlockableLink(newDescription, toLocation, newPassText, failText, requiredItem);
    }

    public class Builder implements LinkBuilder {

        private String description;
        private Location toLocation;
        private String passText;
        private Item requiredItem;
        private String failText;

        public BlockableLink.Builder newInstance() {
            return new BlockableLink.Builder();
        }

        public BlockableLink.Builder setPassText(String passText) {
            this.passText = passText;
            return this;
        }

        public BlockableLink.Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public BlockableLink.Builder setToLocation(Location toLocation) {
            this.toLocation = toLocation;
            return this;
        }

        public Builder setRequiredItem(Item requiredItem) {
            this.requiredItem = requiredItem;
            return this;
        }

        public Builder setFailText(String failText) {
            this.failText = failText;
            return this;
        }

        public BlockableLink build() throws UncreateableException {
            validate();
            return new BlockableLink(description, toLocation, passText, failText, requiredItem);
        }

        private void validate() throws UncreateableException {
            validate(this.description == null || this.description.isEmpty(), "null or empty description");
            validate(this.toLocation == null, "null to location");
            validate(this.passText == null || this.passText.isEmpty(), "null pass text");
            validate(this.failText == null || this.failText.isEmpty(), "null fail text");
            validate(this.requiredItem == null, "null required item");
        }

        protected void validate(Boolean condition, String message) throws UncreateableException {
            if (condition) {
                throw new UncreateableException(message);
            }
        }
    }

}

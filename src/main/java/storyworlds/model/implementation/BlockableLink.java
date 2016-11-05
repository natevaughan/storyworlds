package storyworlds.model.implementation;

import org.springframework.data.mongodb.core.mapping.DBRef;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Item;
import storyworlds.model.LinkBuilder;
import storyworlds.model.Location;
import storyworlds.model.LocationBuilder;
import storyworlds.model.Player;

/**
 * @author nvaughan since 8/26/16
 */
public class BlockableLink extends AbstractLink {

    private final Item                    requiredItem;
    private final String                  passText;
    private final String                  failText;

    public BlockableLink(String description, Location toLocation, String passText, String failText, Item requiredItem, Player creator) {
        super(description, toLocation, creator);
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

    public static class Builder implements LinkBuilder {

        private String description = null;
        private Location toLocation = null;
        private LocationBuilder toLocationBuilder = null;
        private String passText = null;
        private Item requiredItem = null;
        private String failText = null;
        private Player creator = null;

        public static BlockableLink.Builder newInstance() {
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

        public Builder setToLocationBuilder(LocationBuilder toLocationBuilder) {
            this.toLocationBuilder = toLocationBuilder;
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

        public Builder setCreator(Player creator) {
            this.creator = creator;
            return this;
        }

        public BlockableLink build() throws UncreateableException {
            validate();
            return new BlockableLink(description, toLocation, passText, failText, requiredItem, creator);
        }

        private void validate() throws UncreateableException {
            validate(this.toLocationBuilder != null && this.toLocation != null, "found both a location and a location builder");
            if (this.toLocationBuilder != null) {
                this.toLocation = toLocationBuilder.build();
            }
            validate(this.toLocation == null, "null to location and location builder");
            validate(this.description == null || this.description.isEmpty(), "null or empty description");
            validate(this.passText == null || this.passText.isEmpty(), "null pass text");
            validate(this.failText == null || this.failText.isEmpty(), "null fail text");
            validate(this.requiredItem == null, "null required item");
            validate(this.creator == null, "null creator");
        }

        protected void validate(Boolean condition, String message) throws UncreateableException {
            if (condition) {
                throw new UncreateableException(message);
            }
        }
    }

}

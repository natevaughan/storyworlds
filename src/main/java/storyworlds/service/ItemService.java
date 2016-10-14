package storyworlds.service;

import storyworlds.action.Create;
import storyworlds.create.properties.ItemProperties;
import storyworlds.create.properties.Validateable;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Item;
import storyworlds.model.Location;
import storyworlds.model.implementation.ImmutableLocation;
import storyworlds.model.implementation.UsableItem;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by nvaughan on 10/7/2016.
 */
public class ItemService {

    LocationService locationService = new LocationService();

    public Item create(Create create) throws UncreateableException {
        validateAll(create);
        Validateable properties = create.getProperties();

        if (properties instanceof ItemProperties) {
            String[] nameArgs = ((ItemProperties) properties).getName().split(" ");
            if (nameArgs.length > 1) {
                throw new UncreateableException("Item name must be one word.");
            }

            Item item = new UsableItem(((ItemProperties) properties).getName(),
                    ((ItemProperties) properties).getDescription(),
                    ((ItemProperties) properties).getUseText());
            Location previousLocation = create.getMessage().getPlayer().getLocation();
            Collection<Item> previousItems = new HashSet<>(previousLocation.listItems());
            previousItems.add(item);
            Location location = new ImmutableLocation(previousLocation.getDescription(), previousLocation, previousItems);
            locationService.cloneLinks(location, previousLocation);
            create.getMessage().getPlayer().setLocation(location);
            return item;
        }
        throw new UncreateableException("Uncreatable Item.");
    }

    private void validateAll(Create create) throws UncreateableException {
        validate(create != null, "Create was null");
        validate(create.getMessage() != null, "Message was null");
        Validateable properties = create.getProperties();
        validate(properties != null, "Null properties");
        validate(properties.isValid(), "Invalid properties");
        validate(properties instanceof ItemProperties, "Wrong properties type");
        validate(create.getMessage().getPlayer() != null, "Null player");
        validate(create.getMessage().getPlayer().getLocation() != null, "Null player location.");

    }

    private void validate(boolean condition, String message) throws UncreateableException {
        if (!condition) {
            throw new UncreateableException("Uncreateable item: " + message);
        }
    }
}

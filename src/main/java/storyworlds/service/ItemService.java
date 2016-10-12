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
        Validateable properties = create.getProperties();
        if (properties.isValid() && properties instanceof ItemProperties) {
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
}

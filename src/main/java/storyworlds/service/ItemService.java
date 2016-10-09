package storyworlds.service;

import storyworlds.action.Create;
import storyworlds.constants.PropertyKeys;
import storyworlds.exception.UncreateableItemException;
import storyworlds.model.Item;
import storyworlds.model.Location;
import storyworlds.model.implementation.ImmutableLocation;
import storyworlds.model.implementation.UsableItem;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by nvaughan on 10/7/2016.
 */
public class ItemService implements PropertyKeys {

    LocationService locationService = new LocationService();

    public Item create(Create create) throws UncreateableItemException {
        if (!create.getMessage().getFields().containsKey(KEY_ITEM_NAME)) {
            throw new UncreateableItemException("Uncreatable Item. Missing: " + KEY_ITEM_NAME);

        }
        if (!create.getMessage().getFields().containsKey(KEY_ITEM_DESCRIPTION)) {
            throw new UncreateableItemException("Uncreatable Item. Missing: " + KEY_ITEM_DESCRIPTION);

        }
        if (!create.getMessage().getFields().containsKey(KEY_ITEM_USE_TEXT)) {
            throw new UncreateableItemException("Uncreatable Item. Missing: " + KEY_ITEM_USE_TEXT);
        }
        String[] nameArgs = create.getMessage().getFields().get(KEY_ITEM_NAME).split(" ");
        if (nameArgs.length > 1) {
            throw new UncreateableItemException("Item name must be one word.");
        }

            Item item = new UsableItem(create.getMessage().getFields().get(KEY_ITEM_NAME),
                     create.getMessage().getFields().get(KEY_ITEM_DESCRIPTION),
                     create.getMessage().getFields().get(KEY_ITEM_USE_TEXT));
            Location previousLocation = create.getMessage().getPlayer().getLocation();
            Collection<Item> previousItems = new HashSet<>(previousLocation.listItems());
            previousItems.add(item);
            Location location = new ImmutableLocation(previousLocation.getText(), previousLocation, previousItems);
            locationService.cloneLinks(location, previousLocation);
            create.getMessage().getPlayer().setLocation(location);
            return item;
    }
}

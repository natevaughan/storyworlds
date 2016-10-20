package storyworlds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storyworlds.action.Create;
import storyworlds.create.properties.ItemProperties;
import storyworlds.create.properties.Validateable;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Item;
import storyworlds.model.implementation.UsableItem;
import storyworlds.model.implementation.persistence.LocationRepository;

/**
 * Created by nvaughan on 10/7/2016.
 */
@Service
public class ItemService {

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

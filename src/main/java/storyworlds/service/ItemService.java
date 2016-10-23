package storyworlds.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Item;
import storyworlds.model.ItemBuilder;
import storyworlds.model.implementation.persistence.ItemRepository;

/**
 * Created by nvaughan on 10/7/2016.
 */
@Service
public class ItemService {

    private Logger logr = LoggerFactory.getLogger(getClass());

    @Autowired
    ItemRepository itemRepository;

    public Item create(ItemBuilder itemBuilder) throws UncreateableException {

        Item item = itemRepository.save(itemBuilder.build());

        logr.debug("Item created: " + item.getName() + " by " + item.getCreator().getName() + " in " + item.getCreator().getCurrentStoryworld().getTitle());

        return item;

    }

}

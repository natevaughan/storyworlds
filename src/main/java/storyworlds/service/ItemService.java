package storyworlds.service;

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

    @Autowired
    ItemRepository itemRepository;

    public Item create(ItemBuilder itemBuilder) throws UncreateableException {

            return itemRepository.save(itemBuilder.build());

    }

}

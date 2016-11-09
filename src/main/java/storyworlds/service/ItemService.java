package storyworlds.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import storyworlds.model.Item;
import storyworlds.model.implementation.persistence.ItemRepository;

import javax.annotation.PostConstruct;

/**
 * Created by nvaughan on 10/7/2016.
 */
@Service
public class ItemService extends AbstractCachingService<Item> {

    private Logger logr = LoggerFactory.getLogger(getClass());

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    Environment env;

    @PostConstruct
    private void setup() {
        lruCache = new LRUCache<String, Item>(Integer.parseInt(env.getProperty(KEY_ITEM_CACHE_SIZE)));
        repo = itemRepository;
    }
}

package storyworlds.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.repository.MongoRepository;
import storyworlds.config.PropertyKeys;
import storyworlds.model.Persistable;

/**
 * Created by nvaughan on 10/30/2016.
 */
public class AbstractCachingService<T extends Persistable> implements PropertyKeys {

    protected Logger logr = LoggerFactory.getLogger(getClass());


    protected LRUCache<String, T> cache;
    protected MongoRepository<T, String> repo;

    public T get(String id) {
        T object = cache.get(id);

        if (object == null) {
            object = repo.findOne(id);
            if (object == null) {
                return null;
            }
            object = cache.putIfAbsent(object.getId(), object);
        }

        return object;
    }
}

package storyworlds.service;

import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.repository.MongoRepository;
import storyworlds.config.PropertyKeys;
import storyworlds.exception.NotFoundException;
import storyworlds.model.Persistable;

/**
 * Created by nvaughan on 10/30/2016.
 */
public class AbstractCachingService<T extends Persistable> implements PropertyKeys {

    protected Logger logr = LoggerFactory.getLogger(getClass());

    protected LRUCache<String, T> lruCache;
    protected MongoRepository<T, String> repo;

    public T get(String id) throws NotFoundException {
        T entity = lruCache.get(id);

        if (entity == null) {
            entity = repo.findOne(id);
            if (entity == null) {
                throw new NotFoundException(entity.getClass().getSimpleName() + " not found: " + entity.getId());
            }
            entity = lruCache.putIfAbsent(entity.getId(), entity);
        }

        return entity;
    }

    public T update(T entity) throws NotFoundException {
        if  (entity == null) {
            throw new NotFoundException(entity.getClass().getSimpleName() + " not found: " + entity.getId());
        }
        if (entity.getId() == null) {
            return create(entity);
        }
        lruCache.put(entity.getId(), entity);
        return repo.save(entity);
    }


    public T create(T entity) {
        entity = repo.save(entity);
        return lruCache.put(entity.getId(), entity);
    }

    public T cache(T entity) {
        return lruCache.put(entity.getId(), entity);
    }

    public Collection<T> list() {
        return repo.findAll();
    }

    public void deleteAll() {
        repo.deleteAll();
    }
}

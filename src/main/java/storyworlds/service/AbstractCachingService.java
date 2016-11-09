package storyworlds.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.repository.MongoRepository;
import storyworlds.config.PropertyKeys;
import storyworlds.model.Persistable;

import java.util.Collection;

/**
 * Created by nvaughan on 10/30/2016.
 */
public class AbstractCachingService<T extends Persistable> implements PropertyKeys {

    protected Logger logr = LoggerFactory.getLogger(getClass());

    protected LRUCache<String, T> lruCache;
    protected MongoRepository<T, String> repo;

    public T get(String id) {
        T entity = lruCache.get(id);

        if (entity == null) {
            entity = repo.findOne(id);
            if (entity == null) {
                return null;
            }
            entity = lruCache.putIfAbsent(entity.getId(), entity);
        }

        return entity;
    }

    public T update(T entity) {
        if  (entity == null) {
            return null;
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

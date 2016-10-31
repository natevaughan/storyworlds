package storyworlds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import storyworlds.model.Storyworld;
import storyworlds.model.implementation.persistence.StoryworldRepository;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * Created by nvaughan on 10/29/2016.
 */
@Service
public class StoryworldService extends AbstractCachingService<Storyworld> {

    @Autowired
    private StoryworldRepository storyworldRepository;

    @Autowired
    private Environment env;

    @PostConstruct
    private void init() {
        cache = new LRUCache<>(Integer.parseInt(env.getProperty(KEY_STORYWORLD_CACHE_SIZE)));
        repo = storyworldRepository;
    }

    public Collection<Storyworld> list() {
        return storyworldRepository.findAll();
    }
}

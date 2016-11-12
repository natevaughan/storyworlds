package storyworlds.service;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import storyworlds.config.PropertyKeys;
import storyworlds.model.implementation.AnonymousPlayer;

/**
 * Created by nvaughan on 11/6/2016.
 */
@Service
public class AnonymousPlayerService implements PropertyKeys {

    @Autowired
    Environment env;

    protected Logger logr = LoggerFactory.getLogger(getClass());

    protected SelfExpiringHashMap<String, AnonymousPlayer> expiringCache;

    @PostConstruct
    private void setup() {
        expiringCache = new SelfExpiringHashMap<String, AnonymousPlayer>(Long.parseLong(env.getProperty(KEY_ANONYMOUS_PLAYER_CACHE_TIMEOUT)));
    }

    public AnonymousPlayer get(String id) {
        return expiringCache.get(id);
    }

    public AnonymousPlayer cache(AnonymousPlayer entity) {
        if  (entity == null) {
            return null;
        }
        return expiringCache.put(entity.getId(), entity);
    }
}

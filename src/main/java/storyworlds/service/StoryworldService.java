package storyworlds.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import storyworlds.model.Player;
import storyworlds.model.Storyworld;
import storyworlds.model.implementation.IdentifiedPlayer;
import storyworlds.model.implementation.UserDetailsImpl;
import storyworlds.model.implementation.persistence.StoryworldRepository;

import javax.annotation.PostConstruct;

/**
 * Created by nvaughan on 10/29/2016.
 */
@Service
public class StoryworldService extends AbstractCachingService<Storyworld> {

    @Autowired
    private StoryworldRepository storyworldRepository;

    @Autowired
    PlayerService playerService;

    @Autowired
    private Environment env;

    @PostConstruct
    private void init() {
        lruCache = new LRUCache<>(Integer.parseInt(env.getProperty(KEY_STORYWORLD_CACHE_SIZE)));
        repo = storyworldRepository;
    }

    public Storyworld create(Storyworld storyworld) {
        return create(storyworld, true);
    }

    public Storyworld create(Storyworld storyworld, boolean withSessionPlayer) {
        if (withSessionPlayer) {
            IdentifiedPlayer sessionPlayer = (IdentifiedPlayer) playerService.get(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPlayer());
            storyworld.setCreator(sessionPlayer);
        }
        return super.create(storyworld);
    }
}

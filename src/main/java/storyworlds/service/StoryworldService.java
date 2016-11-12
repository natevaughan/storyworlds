package storyworlds.service;

import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import storyworlds.exception.NotFoundException;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Location;
import storyworlds.model.Storyworld;
import storyworlds.model.builder.LocationBuilder;
import storyworlds.model.builder.StoryworldBuilder;
import storyworlds.model.implementation.IdentifiedPlayer;
import storyworlds.model.implementation.UserDetailsImpl;
import storyworlds.model.implementation.persistence.StoryworldRepository;

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
    LocationService locationService;

    @Autowired
    private Environment env;

    @PostConstruct
    private void init() {
        lruCache = new LRUCache<>(Integer.parseInt(env.getProperty(KEY_STORYWORLD_CACHE_SIZE)));
        repo = storyworldRepository;
    }

    public Storyworld create(StoryworldBuilder storyworld) throws UncreateableException {
        return create(storyworld, true);
    }

    // builds a storyworld and entry location in same operation
    // note that storyworld must have a LocationBuilder entryBuilder set
    public Storyworld create(StoryworldBuilder builder, boolean withSessionPlayer) throws UncreateableException {
        if (builder.getEntryBuilder() == null) {
            throw new UncreateableException("Cannot create Storyworld builder without LocationBuilder entryBuilder.");
        }
        LocationBuilder locationBuilder = builder.getEntryBuilder();
        if (withSessionPlayer) {
            IdentifiedPlayer sessionPlayer = null;
            try {
                sessionPlayer = (IdentifiedPlayer) playerService.get(((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPlayer());
            } catch (NotFoundException e) {
                throw new UncreateableException(e);
            }
            builder.setCreator(sessionPlayer);
            locationBuilder.setCreator(sessionPlayer);
        }
        // builds storyworld first, as LocationBuilder throws exception without storyworld
        Storyworld storyworld = builder.build();
        super.create(storyworld);
        locationBuilder.setStoryworld(storyworld);
        Location location = locationService.create(locationBuilder.build());
        storyworld.setEntry(location);
        try {
            return super.update(storyworld);
        } catch (NotFoundException e) {
            // should never get here, we just created the storyworld
            throw new UncreateableException(e);
        }
    }
}

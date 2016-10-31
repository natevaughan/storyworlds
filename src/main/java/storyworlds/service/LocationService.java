package storyworlds.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import storyworlds.action.Delete;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Location;
import storyworlds.model.LocationBuilder;
import storyworlds.model.implementation.persistence.LocationRepository;
import storyworlds.model.implementation.persistence.PlayerRepository;
import storyworlds.model.implementation.persistence.StoryworldRepository;

import javax.annotation.PostConstruct;

@Service
public class LocationService extends AbstractCachingService<Location> {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    StoryworldRepository storyworldRepository;

    @Autowired
    Environment env;

    @PostConstruct
    private void init() {
        this.cache = new LRUCache<String, Location>(Integer.parseInt(env.getProperty(KEY_LOCATION_CACHE_SIZE)));
        this.repo = locationRepository;
    }

    public Location create(LocationBuilder builder) throws UncreateableException {

        Location location = locationRepository.save(builder.build());

        cache.putIfAbsent(location.getId(), location);

        logr.debug("Location created: '" + StringUtils.abbreviate(location.getDescription(), 20) +"' by " + location.getCreator() + " in " + location.getStoryworld().getTitle());

        return location;
    }

    public void delete(Delete delete) {
        // XXX: todo
    }

    public void update(Location previousLocation) {
        locationRepository.save(previousLocation);
    }
}


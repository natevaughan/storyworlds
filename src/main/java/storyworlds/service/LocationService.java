package storyworlds.service;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Direction;
import storyworlds.model.Link;
import storyworlds.model.LinkBuilder;
import storyworlds.model.Location;
import storyworlds.model.LocationBuilder;
import storyworlds.model.implementation.persistence.LocationRepository;
import storyworlds.model.implementation.persistence.PlayerRepository;
import storyworlds.model.implementation.persistence.StoryworldRepository;
import sun.reflect.annotation.TypeAnnotation;

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
        return create(builder.build());
    }

    public Location create(LinkBuilder builder, Direction direction) throws UncreateableException {
        Link link = builder.build();
        if (link.getToLocation().getId() == null) {
            create(link.getToLocation());
        }
        Location currentLocation = link.getCreator().getLocation();
        currentLocation.addOutboundLink(direction, link);
        return link.getToLocation();
    }

    private Location create(Location location) {

        locationRepository.save(location);

        cache.putIfAbsent(location.getId(), location);

        logr.debug("Location created: '" + StringUtils.abbreviate(location.getDescription(), 20) +"' by " + location.getCreator() + " in " + location.getStoryworld().getTitle());

        return location;
    }

    public void update(Location previousLocation) {
        locationRepository.save(previousLocation);
    }

    public Location get(Location toLocation) {
        return get(toLocation.getId());
    }
}


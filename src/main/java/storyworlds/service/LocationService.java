package storyworlds.service;


import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import storyworlds.exception.NotFoundException;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Direction;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.builder.LinkBuilder;
import storyworlds.model.builder.LocationBuilder;
import storyworlds.model.implementation.persistence.LocationRepository;
import storyworlds.model.implementation.persistence.PlayerRepository;
import storyworlds.model.implementation.persistence.StoryworldRepository;

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
        this.lruCache = new LRUCache<String, Location>(Integer.parseInt(env.getProperty(KEY_LOCATION_CACHE_SIZE)));
        this.repo = locationRepository;
    }

    public Location create(LocationBuilder builder) throws UncreateableException {
        return create(builder.build());
    }

    public Location create(LinkBuilder builder, Direction direction) throws UncreateableException {

        // if coming from front end, LinkBuilder will just have an id for toLocation
        // .build() method requires a Location or LocationBuilder obj
        if (builder.getToLocationId() != null) {
            try {
                builder.setToLocation(get(builder.getToLocationId()));
            } catch (NotFoundException e) {
                throw new UncreateableException(e);
            }
        }
        Link link = builder.build();

        // if this was a new link and new location, the location must be persisted
        if (link.getToLocation().getId() == null) {
            create(link.getToLocation());
        }
        Location currentLocation = link.getCreator().getCurrentProgress().getLocation();
        currentLocation.addOutboundLink(direction, link);

        // createOrUpdate the current location to persist link
        try {
            createOrUpdate(currentLocation);
        } catch (NotFoundException e) {
            throw new UncreateableException(e);
        }
        return link.getToLocation();
    }

    public Location get(Location toLocation) throws NotFoundException {
        return get(toLocation.getId());
    }
}


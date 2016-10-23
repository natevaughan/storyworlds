package storyworlds.service;


import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storyworlds.action.Delete;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Location;
import storyworlds.model.LocationBuilder;
import storyworlds.model.implementation.persistence.LocationRepository;
import storyworlds.model.implementation.persistence.Player2Repo;
import storyworlds.model.implementation.persistence.StoryworldRepository;

@Service
public class LocationService {

    private Logger logr = LoggerFactory.getLogger(getClass());

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    Player2Repo playerRepository;

    @Autowired
    StoryworldRepository storyworldRepository;

    public Location create(LocationBuilder builder) throws UncreateableException {

        Location location = locationRepository.save(builder.build());

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


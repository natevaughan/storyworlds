package storyworlds.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storyworlds.action.Delete;
import storyworlds.action.Edit;
import storyworlds.create.properties.Validateable;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.LocationBuilder;
import storyworlds.model.implementation.ImmutableLocation;
import storyworlds.model.implementation.persistence.LocationRepository;
import storyworlds.model.implementation.persistence.Player2Repo;
import storyworlds.model.implementation.persistence.StoryworldRepository;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    @Autowired
    Player2Repo playerRepository;

    @Autowired
    StoryworldRepository storyworldRepository;

    public Location create(LocationBuilder builder) throws UncreateableException {

        return locationRepository.save(builder.build());

    }

    public void delete(Delete delete) {
        // XXX: todo
    }

    public void update(Location previousLocation) {
        locationRepository.save(previousLocation);
    }
}


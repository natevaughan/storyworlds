package storyworlds.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import storyworlds.action.Create;
import storyworlds.action.Delete;
import storyworlds.action.Edit;
import storyworlds.create.properties.LocationProperties;
import storyworlds.create.properties.Validateable;
import storyworlds.exception.UncreateableException;
import storyworlds.model.Link;
import storyworlds.model.Location;
import storyworlds.model.implementation.ImmutableLocation;
import storyworlds.model.implementation.persistence.LocationRepository;

import java.util.HashMap;

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public Location build(Create create) throws UncreateableException {

        Validateable properties = create.getProperties();

        if (properties.isValid() && properties instanceof LocationProperties) {
            Location location = new ImmutableLocation(((LocationProperties) properties).getDescription(), null, new HashMap<>());
            return location;
        }
        throw new UncreateableException("Failed to create location.");
    }

    public Location edit(Edit edit) throws UncreateableException {
        Validateable properties = edit.getProperties();

        if (properties.isValid() && properties instanceof LocationProperties) {
            Location formerLocation = edit.getMessage().getPlayer().getLocation();
            formerLocation.setActive(false);
            Location location = new ImmutableLocation(((LocationProperties) properties).getDescription(), formerLocation, formerLocation.getItems());
            cloneLinks(location, formerLocation);
            edit.getMessage().getPlayer().setLocation(location);
            if (edit.getMessage().getPlayer().getCurrentStoryworld().getEntry().equals(formerLocation)) {
                edit.getMessage().getPlayer().getCurrentStoryworld().setEntry(location);
            }
            locationRepository.save(location);
            return location;
        }
        throw new UncreateableException("Failed to create location.");
    }

    public void cloneLinks(Location location, Location formerLocation) {
        for (Link l : formerLocation.getOutboundLinks().values()) {
            l.setActive(false);
            Link outbound = l.clone(location, l.getFromDirection());
            outbound.bind();
        }
        for (Link l : formerLocation.getInboundLinks()) {
            l.setActive(false);
            Link inbound = l.clone(location);
            inbound.bind();
        }
    }

    public void delete(Delete delete) {
        // XXX: todo
    }
}


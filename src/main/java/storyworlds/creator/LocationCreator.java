package storyworlds.creator;


import storyworlds.model.Location;
import storyworlds.model.implementation.ImmutableLocation;
import storyworlds.model.implementation.User;

public class LocationCreator {
    public Location createLocation(User user) {
        return new ImmutableLocation("");
    }
}


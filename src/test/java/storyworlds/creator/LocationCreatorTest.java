package storyworlds.creator;

import org.junit.Before;
import org.junit.Test;
import storyworlds.factory.MapFactory;
import storyworlds.model.Location;
import storyworlds.model.Player;
import storyworlds.model.implementation.User;

public class LocationCreatorTest {

    private Player user;
    private Location location;

    @Before
    public void setup() {
        user = new User("foo");
        location = MapFactory.getBlankLocation();
        user.setLocation(location);
    }

    @Test
    public void editExistingLocation() {
        // todo
    }
}


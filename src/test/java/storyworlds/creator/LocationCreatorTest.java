package storyworlds.creator;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import storyworlds.factory.MapFactory;
import storyworlds.model.*;
import storyworlds.model.implementation.*;

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
        
        assertNotNull(user.getLocation());
        assertTrue(true);
    }
}


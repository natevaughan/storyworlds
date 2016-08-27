package storyworlds.factory;

import storyworlds.factory.*;
import storyworlds.model.*;
import storyworlds.model.implementation.*;

import static org.junit.Assert.*;
import org.junit.Test;

public class MapFactoryTest {

    @Test
    public void testMapFactory() {
        Location loc = MapFactory.getStartMap();
        assertNotNull("Location cannot be null", loc);
        assertNotNull("Location had missing link direction", loc.getLink(Direction.UP));
        assertNotNull("Location had missing link direction", loc.getLink(Direction.DOWN));
        assertNotNull("Location had missing link direction", loc.getLink(Direction.NORTH));
        assertNotNull("Location had missing link direction", loc.getLink(Direction.EAST));
        assertNotNull("Location had missing link direction", loc.getLink(Direction.WEST));
        assertNotNull("Location had missing link direction", loc.getLink(Direction.SOUTH));
    }
}

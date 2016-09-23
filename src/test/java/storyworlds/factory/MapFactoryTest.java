package storyworlds.factory;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import storyworlds.model.Direction;
import storyworlds.model.Location;

public class MapFactoryTest {

    @Test
    public void testMapFactory() {
        Location loc = MapFactory.getStartMap();
        assertNotNull("Location cannot be null", loc);
        assertNotNull("Location had missing link direction", loc.getLink(Direction.UP));
        assertNotNull("Location had missing link direction", loc.getLink(Direction.DOWN));
        assertNotNull("Location had missing link direction", loc.getLink(Direction.NORTH));
        assertNotNull("Location had missing link direction", loc.getLink(Direction.SOUTH));
    }
}

package storyworlds.factory;

import org.junit.Test;
import storyworlds.model.Direction;
import storyworlds.model.Location;

import static org.junit.Assert.assertNotNull;

public class MapFactoryTest {

    @Test
    public void testMapFactory() {
        Location loc = MapFactory.getStartMap();
        assertNotNull("Location cannot be null", loc);
        assertNotNull("Location had missing link direction", loc.getOutboundLink(Direction.UP));
        assertNotNull("Location had missing link direction", loc.getOutboundLink(Direction.DOWN));
        assertNotNull("Location had missing link direction", loc.getOutboundLink(Direction.NORTH));
        assertNotNull("Location had missing link direction", loc.getOutboundLink(Direction.SOUTH));
    }
}

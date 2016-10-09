package storyworlds.gameplay;

import org.junit.Before;
import storyworlds.initial.map.MapFactory;
import storyworlds.model.Location;

/**
 * Created by nvaughan on 9/27/2016.
 */
public class AbstractMapGameplayTest extends AbstractGameplayTest {

    protected Location start;

    @Before
    public void setup() {
        super.setup();
        user.setLocation(MapFactory.getStartMap());
        start = user.getLocation();
    }
}

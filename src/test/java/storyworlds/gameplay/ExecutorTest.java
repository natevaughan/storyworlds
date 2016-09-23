package storyworlds.gameplay;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import storyworlds.action.Actionable;
import storyworlds.factory.MapFactory;
import storyworlds.model.Location;
import storyworlds.model.Player;
import storyworlds.model.implementation.User;

public class ExecutorTest extends AbstractGameplayTest {

    private Location start;

    @Before
    public void setup() {
        super.setup();
        user.setLocation(MapFactory.getStartMap());
        start = user.getLocation();
    }
    
    @Test
    public void testMoveUser() {
        executor.execute("mOve nOrtH");
        assertFalse("Player should no longer be in start location", user.getLocation().equals(start));
        executor.execute("move South");
        assertTrue("Executor should return user to start location", user.getLocation().equals(start));
    }
    
    @Test
    public void ensureUserCannotMoveBeyondBoundary() {
        Location boundedLocation = MapFactory.getBlankLocation();
        user.setLocation(boundedLocation);
        executor.execute("mOve nOrtH");
        assertTrue("User should not move past a boundary", user.getLocation().equals(boundedLocation));
    }    
    
    @Test
    public void badDirectionError() {
        executor.execute("mOve");
        assertTrue("User should remain in current locaion", user.getLocation().equals(start));
    }
    
    
}

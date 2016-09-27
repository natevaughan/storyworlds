package storyworlds.action;


import org.junit.Test;
import storyworlds.factory.MapFactory;
import storyworlds.gameplay.AbstractMapGameplayTest;
import storyworlds.model.Location;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MoveTest extends AbstractMapGameplayTest {

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
    public void invalidDirectionTest() {
        super.setup();
        Actionable a = executor.execute("move foo");
        assertTrue("Actionable should be Move.class", Move.class.equals(a.getClass()));
        assertFalse("Move actionable should not be successful", a.isSuccessful());
        assertTrue("User should remain in current location", user.getLocation().equals(start));
    }
}

package storyworlds.action;


import org.junit.Test;
import storyworlds.gameplay.AbstractMapGameplayTest;
import storyworlds.initial.map.MapFactory;
import storyworlds.model.Location;
import storyworlds.service.message.Message;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MoveTest extends AbstractMapGameplayTest {

    @Test
    public void testMoveUser() throws Exception {
        messageService.process(new Message(user, "mOve nOrtH"));
        assertFalse("Player should no longer be in start location", user.getLocation().equals(start));
        messageService.process(new Message(user, "mOve SOuTh"));
        assertTrue("Executor should return user to start location", user.getLocation().equals(start));
    }

    @Test
    public void ensureUserCannotMoveBeyondBoundary() throws Exception {
        Location boundedLocation = MapFactory.getBlankLocation();
        user.setLocation(boundedLocation);
        messageService.process(new Message(user, "mOve nOrtH"));
        assertTrue("User should not move past a boundary", user.getLocation().equals(boundedLocation));
    }

    @Test
    public void invalidDirectionTest() throws Exception {
        super.setup();
        Actionable a = messageService.process(new Message(user, "mOve foo"));
        assertTrue("Actionable should be Move.class", Move.class.equals(a.getClass()));
        assertFalse("Move actionable should not be successful", a.isSuccessful());
        assertTrue("User should remain in current location", user.getLocation().equals(start));
    }
}

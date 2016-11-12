package storyworlds.action;


import org.junit.Ignore;
import org.junit.Test;
import storyworlds.exception.InvalidDirectionException;
import storyworlds.exception.InvalidLinkException;
import storyworlds.gameplay.AbstractMapGameplayTest;
import storyworlds.initial.map.MapFactory;
import storyworlds.model.Location;
import storyworlds.service.message.Message;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MoveTest extends AbstractMapGameplayTest {

    @Test
    @Ignore // set up mockito mocking to avoid calling repo
    public void testMoveUser() throws Exception {
        messageService.process(new Message(user, "mOve nOrtH"));
        assertFalse("Player should no longer be in start location", user.getCurrentProgress().getLocation().equals(start));
        messageService.process(new Message(user, "mOve SOuTh"));
        assertTrue("Executor should return user to start location", user.getCurrentProgress().getLocation().equals(start));
    }

    @Test(expected = InvalidLinkException.class)
    public void ensureUserCannotMoveBeyondBoundary() throws Exception {
        Location boundedLocation = MapFactory.getBlankLocation(user);
        user.getCurrentProgress().setLocation(boundedLocation);
        messageService.process(new Message(user, "mOve nOrtH"));
    }

    @Test(expected = InvalidDirectionException.class)
    public void invalidDirectionTest() throws Exception {
        super.setup();
        Actionable a = messageService.process(new Message(user, "mOve foo"));
    }
}

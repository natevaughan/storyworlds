package game.gameplay;

import storyworlds.factory.MapFactory;
import storyworlds.gameplay.Executor;
import storyworlds.model.*;
import storyworlds.model.implementation.User;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ExecutorTest {
    
    Executor executor;
    Location start;
    Action action;

    @Before
    public void setup() {
        Player user = new User("foo");
        user.setLocation(MapFactory.getStartMap());
        executor = new Executor(user);
        start = executor.getPlayer().getLocation();
    }
    
    @Test
    public void testMoveUser() {
        action = executor.execute("mOve nOrtH");
        assertTrue("Executor should return correct action", Action.MOVE.equals(action));
        assertFalse("Player should no longer be in start location", executor.getPlayer().getLocation().equals(start));
        action = executor.execute("move South");
        assertTrue("Executor should return user to start location", executor.getPlayer().getLocation().equals(start));
    }
    
    @Test
    public void ensureUserCannotMoveBeyondBoundary() {
        Location boundedLocation = MapFactory.getBlankLocation();
        executor.getPlayer().setLocation(boundedLocation);
        action = executor.execute("mOve nOrtH");
        assertTrue("User should not move past a boundary", executor.getPlayer().getLocation().equals(boundedLocation));
    }
}

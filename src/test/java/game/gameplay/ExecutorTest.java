package game.gameplay;

import game.model.*;
import game.factory.MapFactory;
import game.model.implementation.User;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class ExecutorTest {
    
    Executor executor;

    @Before
    public void setup() {
        Player user = new User("foo");
        user.setLocation(MapFactory.getStartMap());
        executor = new Executor(user);
    }
    
    @Test
    public void testMoveUser() {
        Location start = executor.getPlayer().getLocation();
        Action action = executor.execute("mOve nOrtH");
        assertTrue("Executor should return correct action", Action.MOVE.equals(action));
        assertFalse("Player should no longer be in start location", executor.getPlayer().getLocation().equals(start));
        action = executor.execute("move South");
        assertTrue("Executor should return user to start location", executor.getPlayer().getLocation().equals(start));
    }
}
